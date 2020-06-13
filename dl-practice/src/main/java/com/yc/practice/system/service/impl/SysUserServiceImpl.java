package com.yc.practice.system.service.impl;

import cn.hutool.core.util.IdcardUtil;
import cn.hutool.core.util.ObjectUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yc.common.constant.CommonConstant;
import com.yc.common.constant.CommonEnum;
import com.yc.common.global.error.Error;
import com.yc.common.global.error.ErrorException;
import com.yc.core.system.entity.SysUser;
import com.yc.core.system.entity.SysUserRole;
import com.yc.core.system.mapper.SysUserMapper;
import com.yc.core.system.mapper.SysUserRoleMapper;
import com.yc.core.system.model.form.SysUserForm;
import com.yc.core.system.model.query.UserQuery;
import com.yc.core.system.model.vo.SysUserVO;
import com.yc.practice.common.UserUtil;
import com.yc.practice.system.service.SysLogService;
import com.yc.practice.system.service.SysUserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * 功能描述：
 *
 * <p>版权所有：</p>
 * 未经本人许可，不得以任何方式复制或使用本程序任何部分
 *
 * @Company: 紫色年华
 * @Author xieyc
 * @Date 2019-09-19
 * @Version: 1.0.0
 */
@Service
@Transactional(rollbackFor = Exception.class)
@Slf4j
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {

    private final SysUserRoleMapper sysUserRoleMapper;
    private final SysLogService sysLogService;
    private final PasswordEncoder passwordEncoder;
    private final RedisTemplate<String,String> redisTemplate;

    @Autowired
    public SysUserServiceImpl(SysUserRoleMapper sysUserRoleMapper,
                              SysLogService sysLogService,RedisTemplate<String,String> redisTemplate,
                              PasswordEncoder passwordEncoder) {
        this.sysUserRoleMapper = sysUserRoleMapper;
        this.passwordEncoder = passwordEncoder;
        this.redisTemplate = redisTemplate;
        this.sysLogService = sysLogService;
    }

    @Override
    public void logout(HttpServletRequest request, HttpServletResponse response) {
        String token = StringUtils.remove(request.getHeader(CommonConstant.HEADER_STRING), CommonConstant.TOKEN_PREFIX).trim();
        redisTemplate.delete(CommonConstant.SYS_USERS_CACHE+UserUtil.getUserId());
        sysLogService.addLog(request,"用户名: "+UserUtil.getUser().getLoginName()+",退出成功！",CommonConstant.LOG_TYPE_1,
                UserUtil.getUserId(),"/sysUser/logout",token);
    }

    @Override
    public Page<SysUserVO> userList(Page<SysUser> page, UserQuery userQuery) {
        return this.baseMapper.userList(page,userQuery);
    }

    @Override
    public void add(JSONObject jsonObject) {
        SysUser user = JSON.parseObject(jsonObject.toJSONString(), SysUser.class);
        user.setPassWord(passwordEncoder.encode(CommonConstant.DEFAULT_PASSWORD));
        user.setCreateUserId(UserUtil.getUserId());
        user.setAge(IdcardUtil.getAgeByIdCard(user.getIdCard()));
        user.setSex(IdcardUtil.getGenderByIdCard(user.getIdCard()));
        user.setBirthday(LocalDate.parse(IdcardUtil.getBirthByIdCard(user.getIdCard())));
        this.save(user);
        String roles = jsonObject.getString("selectedroles");
        if(StringUtils.isNotEmpty(roles)) {
            String[] arr = roles.split(",");
            for (String roleId : arr) {
                SysUserRole userRole = new SysUserRole(user.getSysUserId(), roleId);
                sysUserRoleMapper.insert(userRole);
            }
        }
    }

    @Override
    public void edit(SysUserForm user){
        String roles = user.getSelectedroles();
        SysUser sysUser = new SysUser();
        BeanUtils.copyProperties(user,sysUser);
        this.updateById(sysUser);
        // 角色先删后加
        sysUserRoleMapper.delete(new LambdaQueryWrapper<SysUserRole>()
                .eq(SysUserRole::getUserId, user.getSysUserId()));
        if(StringUtils.isNotEmpty(roles)) {
            String[] arr = roles.split(",");
            for (String roleId : arr) {
                SysUserRole userRole = new SysUserRole(user.getSysUserId(), roleId);
                sysUserRoleMapper.insert(userRole);
            }
        }
    }

    @Override
    public void checkIsOnly(String loginName) {
        List<SysUser> list = this.baseMapper.selectList(new LambdaQueryWrapper<SysUser>()
            .eq(SysUser::getLoginName,loginName)
        );
        if(list!= null && list.size()>0){
            throw new ErrorException(Error.UserExisted);
        }
    }

    @Override
    public void deleteUser(String id) {
        // 删除用户角色关联关系
        sysUserRoleMapper.delete(new LambdaQueryWrapper<SysUserRole>()
            .eq(SysUserRole::getUserId,id)
        );
        //删除用户
        SysUser user = new SysUser();
        user.setSysUserId(id);
        user.setDelFlag(CommonEnum.DelFlag.DEL.getCode());
        this.baseMapper.updateById(user);
    }

    @Override
    public void deleteBatch(String ids) {
        String[] arr = ids.split(",");
        for (String id : arr) {
            if (StringUtils.isNotEmpty(id)) {
                this.deleteUser(id);
            }
        }
    }

    @Override
    public List<String> queryUserRole(String userId) {
        List<String> list = new ArrayList<String>();
        List<SysUserRole> userRole = sysUserRoleMapper.selectList(new LambdaQueryWrapper<SysUserRole>()
                .eq(SysUserRole::getUserId, userId));
        if (ObjectUtil.isNull(userRole)) {
            throw new ErrorException(Error.UserNotFound);
        } else {
            for (SysUserRole sysUserRole : userRole) {
                list.add(sysUserRole.getRoleId());
            }
        }
        return list;
    }

    @Override
    public void resetPassword(String sysUserId) {
        SysUser user = this.baseMapper.selectById(sysUserId);
        user.setPassWord(passwordEncoder.encode(CommonConstant.DEFAULT_PASSWORD));
        this.baseMapper.updateById(user);
    }

    @Override
    public String updatePassword(SysUser sysUser) {
        SysUser dbSysUser = this.baseMapper.selectById(sysUser.getSysUserId());
        if(passwordEncoder.matches(sysUser.getOldPassword(),dbSysUser.getPassWord())){
            sysUser.setPassWord(passwordEncoder.encode(sysUser.getPassWord()));
            int result = this.baseMapper.updateById(sysUser);
            return result > 0 ? "success" : "error";
        } else {
            throw new ErrorException(Error.OldPasswordError);
        }
    }

    @Override
    public Page<SysUserVO> chatPage(Page<SysUser> page) {
        Page<SysUserVO> pRecord = this.baseMapper.chatPage(page);
        pRecord.getRecords().forEach(i->{
            if(redisTemplate.hasKey(CommonConstant.SYS_USERS_CACHE+i.getSysUserId())){
                i.setOnline("1");
            }else {
                i.setOnline("0");
            }
        });
        return pRecord;
    }

}

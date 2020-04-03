package com.yc.practice.system.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yc.common.constant.CacheConstant;
import com.yc.common.constant.CommonConstant;
import com.yc.common.global.error.Error;
import com.yc.common.global.error.ErrorException;
import com.yc.common.utils.IdcardUtil;
import com.yc.core.system.entity.SysUser;
import com.yc.core.system.entity.SysUserRole;
import com.yc.core.system.mapper.SysUserMapper;
import com.yc.core.system.mapper.SysUserRoleMapper;
import com.yc.core.system.model.form.SysUserForm;
import com.yc.core.system.model.query.UserQuery;
import com.yc.core.system.model.vo.CurrUserVO;
import com.yc.core.system.model.vo.SysUserVO;
import com.yc.practice.common.dao.DaoApi;
import com.yc.practice.system.service.SysLogService;
import com.yc.practice.system.service.SysUserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
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

    private final DaoApi daoApi;
    private final SysLogService sysLogService;
    private final SysUserRoleMapper sysUserRoleMapper;
    private RedisTemplate redisTemplate;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public SysUserServiceImpl(SysUserRoleMapper sysUserRoleMapper,
                              SysLogService sysLogService,RedisTemplate redisTemplate,
                              DaoApi daoApi,PasswordEncoder passwordEncoder) {
        this.sysUserRoleMapper = sysUserRoleMapper;
        this.passwordEncoder = passwordEncoder;
        this.redisTemplate = redisTemplate;
        this.daoApi = daoApi;
        this.sysLogService = sysLogService;
    }

    @Override
    public void logout(HttpServletRequest request, HttpServletResponse response) {
        CurrUserVO sysUser = daoApi.getCurrUser();
        sysLogService.addLog("用户名: "+sysUser.getLoginName()+",退出成功！", CommonConstant.LOG_TYPE_1, "sysUser/logout","");
        String token = request.getHeader(CommonConstant.X_ACCESS_TOKEN);
        //清空用户Token缓存
        // redisTemplate.delete(CacheConstant.LOGIN_USER_TOKEN_ + token);
        //清空用户权限缓存：权限Perms和角色集合
        // redisTemplate.delete(CacheConstant.LOGIN_USER_ROLES_+ sysUser.getLoginName());
        // redisTemplate.delete(CacheConstant.LOGIN_USER_PERMISSION_+ sysUser.getLoginName());
    }

    @Override
    public Page<SysUserVO> userList(Page<SysUserVO> page, UserQuery userQuery) {
        return this.baseMapper.userList(page,userQuery);
    }

    @Override
    public void add(JSONObject jsonObject) {
        SysUser user = JSON.parseObject(jsonObject.toJSONString(), SysUser.class);
        user.setPassword(passwordEncoder.encode("123456"));
        user.setCreateUserId(daoApi.getCurrUserId());
        user.setAge(IdcardUtil.getAgeByIdCard(user.getIdCard()));
        user.setSex(IdcardUtil.getSexByIdCard(user.getIdCard()));
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
    @CacheEvict(value={CacheConstant.SYS_USERS_CACHE}, allEntries=true)
    public void deleteUser(String id) {
        // 删除用户角色关联关系
        sysUserRoleMapper.delete(new LambdaQueryWrapper<SysUserRole>()
            .eq(SysUserRole::getUserId,id)
        );
        //删除用户
        SysUser user = new SysUser();
        user.setSysUserId(id);
        user.setDelFlag(CommonConstant.DEL_FLAG_1);
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
        user.setPassword(passwordEncoder.encode("123456"));
        this.baseMapper.updateById(user);
    }

}

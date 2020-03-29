package com.yc.practice.system.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yc.common.constant.CommonConstant;
import com.yc.common.global.error.Error;
import com.yc.common.global.error.ErrorException;
import com.yc.core.system.entity.SysRole;
import com.yc.core.system.entity.SysRolePermission;
import com.yc.core.system.mapper.SysRoleMapper;
import com.yc.core.system.model.query.RoleQuery;
import com.yc.practice.system.service.SysRolePermissionService;
import com.yc.practice.system.service.SysRoleService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

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
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements SysRoleService {

    private final SysRolePermissionService sysRolePermissionService;

    @Autowired
    public SysRoleServiceImpl(SysRolePermissionService sysRolePermissionService) {
        this.sysRolePermissionService = sysRolePermissionService;
    }

    @Override
    public Page<SysRole> rolePage(Page<SysRole> page, RoleQuery roleQuery) {
        return this.baseMapper.roleList(page,roleQuery);
    }

    @Override
    public List<SysRole> roleList() {
        return this.baseMapper.selectList(new LambdaQueryWrapper<SysRole>()
            .eq(SysRole::getDelFlag, CommonConstant.DEL_FLAG_0)
            .orderByAsc(SysRole::getSort)
        );
    }

    @Override
    public Set<String> getUserRoles(String loginName) {
        Set<String> roleSet = new HashSet<>();
        List<SysRole> rolesList = this.baseMapper.getUserRoles(loginName);
        for (SysRole po : rolesList) {
            if (StringUtils.isNotEmpty(po.getRoleCode())) {
                roleSet.add(po.getRoleCode());
            }
        }
        return new HashSet<>(roleSet);
    }

    @Override
    public void duplicate(String roleCode) {
        List<SysRole> list = this.baseMapper.selectList(new LambdaQueryWrapper<SysRole>()
            .eq(SysRole::getRoleCode,roleCode)
                .eq(SysRole::getDelFlag,CommonConstant.DEL_FLAG_0)
        );
        if(list != null && list.size() >0){
            throw new ErrorException(Error.RoleExisted);
        }
    }

    @Override
    public List<String> queryRolePermission(String roleId) {
        List<SysRolePermission> list = sysRolePermissionService.list(new LambdaQueryWrapper<SysRolePermission>()
                .eq(SysRolePermission::getRoleId, roleId));
        return list.stream().map(sysRolePermission ->
                String.valueOf(sysRolePermission.getPermissionId())).collect(Collectors.toList());
    }

    @Override
    public void saveRolePermission(JSONObject json) {
        // 角色id
        String roleId = json.getString("sysRoleId");
        // permissionIds 新权限点
        String permissionIds = json.getString("permissionIds");
        // lastPermissionIds 旧权限点
        String lastPermissionIds = json.getString("lastPermissionIds");
        List<String> add = getDiff(lastPermissionIds,permissionIds);
        if(CollectionUtils.isNotEmpty(add)) {
            List<SysRolePermission> list = new ArrayList<>();
            for (String p : add) {
                if(StringUtils.isNotEmpty(p)) {
                    SysRolePermission rolepms = new SysRolePermission(roleId, p);
                    list.add(rolepms);
                }
            }
            this.sysRolePermissionService.saveBatch(list);
        }
        List<String> delete = getDiff(permissionIds,lastPermissionIds);
        if(delete!=null && delete.size()>0) {
            for (String permissionId : delete) {
                this.sysRolePermissionService.remove(new LambdaQueryWrapper<SysRolePermission>()
                        .eq(SysRolePermission::getRoleId, roleId)
                        .eq(SysRolePermission::getPermissionId, permissionId));
            }
        }
    }

    /**
     * 从diff中找出main中没有的元素
     * @param main 上一次权限点
     * @param diff 本次权限点
     * @return list
     */
    private List<String> getDiff(String main,String diff){
        if(StringUtils.isEmpty(diff)){
            return null;
        }
        if(StringUtils.isEmpty(main)) {
            return Arrays.asList(diff.split(","));
        }
        String[] mainArr = main.split(",");
        String[] diffArr = diff.split(",");
        Map<String, Integer> map = new HashMap<>();
        for (String string : mainArr) {
            map.put(string, 1);
        }
        List<String> res = new ArrayList<>();
        for (String key : diffArr) {
            if(StringUtils.isNotEmpty(key) && !map.containsKey(key)) {
                res.add(key);
            }
        }
        return res;
    }

}

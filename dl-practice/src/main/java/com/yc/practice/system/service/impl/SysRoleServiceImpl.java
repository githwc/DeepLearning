package com.yc.practice.system.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yc.common.config.exception.RunException.RunningException;
import com.yc.common.constant.CommonConstant;
import com.yc.practice.common.dao.DaoApi;
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

import java.util.HashSet;
import java.util.List;
import java.util.Set;
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

    @Autowired
    private DaoApi daoApi;

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
        Set<String> roleSet = new HashSet<String>();
        List<SysRole> rolesList = this.baseMapper.getUserRoles(loginName);
        for (SysRole po : rolesList) {
            if (StringUtils.isNotEmpty(po.getRoleCode())) {
                roleSet.add(po.getRoleCode());
            }
        }
        return new HashSet<String>(roleSet);
    }

    @Override
    public void duplicate(String roleCode) {
        List<SysRole> list = this.baseMapper.selectList(new LambdaQueryWrapper<SysRole>()
            .eq(SysRole::getRoleCode,roleCode)
                .eq(SysRole::getDelFlag,CommonConstant.DEL_FLAG_0)
        );
        if(list != null && list.size() >0){
            throw new RunningException("该角色码已存在！");
        }
    }

    @Override
    public List<String> queryRolePermission(String roleId) {
        List<SysRolePermission> list = sysRolePermissionService.list(new LambdaQueryWrapper<SysRolePermission>()
                .eq(SysRolePermission::getRoleId, roleId));
        return list.stream().map(SysRolePermission ->
                String.valueOf(SysRolePermission.getPermissionId())).collect(Collectors.toList());
    }

    @Override
    public void saveRolePermission(JSONObject json) {
        String roleId = json.getString("sysRoleId");
        String permissionIds = json.getString("permissionIds");
        String lastPermissionIds = json.getString("lastPermissionIds");
        this.sysRolePermissionService.saveRolePermission(roleId, permissionIds, lastPermissionIds);
    }

}

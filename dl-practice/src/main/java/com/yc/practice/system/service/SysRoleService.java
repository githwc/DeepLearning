package com.yc.practice.system.service;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yc.core.system.entity.SysRole;
import com.yc.core.system.model.query.RoleQuery;

import java.util.List;
import java.util.Set;

/**
 * 功能描述:
 *
 * @Author: xieyc && 紫色年华
 * @Date 2019-09-19
 * @Version: 1.0.0
 */
public interface SysRoleService extends IService<SysRole> {

    /**
     * 获取用户权限
     *
     * @param loginName 登录名称
     * @return
     */
    Set<String> getUserRoles(String loginName);

    /**
     * 加载有效角色
     *
     * @return
     */
    List<SysRole> roleList();

    /**
     * 分页查询所有角色
     *
     * @param page
     * @param roleQuery
     * @return
     */
    Page<SysRole> rolePage(Page<SysRole> page, RoleQuery roleQuery);

    /**
     * 角色代码唯一性校验
     *
     * @param roleCode
     */
    void duplicate(String roleCode);

    /**
     * 查询角色拥有的权限
     *
     * @param roleId
     * @return
     */
    List<String> queryRolePermission(String roleId);

    /**
     * 保存角色授权
     *
     * @return
     */
    void saveRolePermission(JSONObject json);

}

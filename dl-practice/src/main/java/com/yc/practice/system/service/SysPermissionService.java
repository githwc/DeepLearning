package com.yc.practice.system.service;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yc.core.system.entity.SysPermission;
import com.yc.core.system.model.vo.SysPermissionTree;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 功能描述：
 *
 *  <p>版权所有：</p>
 *  未经本人许可，不得以任何方式复制或使用本程序任何部分
 *
 * @Company: 紫色年华
 * @Author xieyc
 * @Date 2019-09-20
 * @Version: 1.0.0
 *
 */
public interface SysPermissionService extends IService<SysPermission> {

    /**
     * 获取用户权限码 例如：admin,guest,xxx
     * @param loginName
     * @return
     */
    Set<String> getUserPermCodes(String loginName);

    /**
     * 获取用户权限码 例如：admin,guest,xxx
     * @param loginName
     * @return
     */
    List<SysPermission> getUserPerm(String loginName);

    /**
     * 根据Token获取用户拥有的权限
     * @param token
     * @param response
     * @return
     */
    JSONObject getUserPermissionByToken(String token, HttpServletResponse response);

    /**
     * 加载全部权限
     * @return
     */
    List<SysPermissionTree> permissionlist();

    /**
     * 获取全部的权限树
     *
     * @return
     */
    Map<String,Object> queryTreeList();

    /**
     * 添加
     * @param sysPermission
     * @return
     */
    void addPermission(SysPermission sysPermission);

    /**
     * 编辑
     * @param sysPermission
     */
    void editPermission(SysPermission sysPermission);

    /**
     * 删除
     * @param id
     */
    void deletePermission(String id);

    /**
     * 批量删除
     * @param ids
     */
    void deleteBatch(String ids);

    /**
     * 菜单权限树
     * @return
     */
    Map<String,Object> permissionMapTree();

}

package com.yc.practice.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yc.core.system.entity.SysRolePermission;

/**
 * 功能描述：
 *
 *  <p>版权所有：</p>
 *  未经本人许可，不得以任何方式复制或使用本程序任何部分
 *
 * @Company: LionHerding
 * @Author xieyc && 紫色年华
 * @Date 2019-09-20
 * @Version: 1.0.0
 *
 */
public interface SysRolePermissionService extends IService<SysRolePermission> {

    /**
     * 保存授权 将上次的权限和这次作比较 差异处理提高效率
     *
     * @param roleId 关联权限
     * @param permissionIds 最新权限集
     * @param lastPermissionIds 上次权限集
     */
    void saveRolePermission(String roleId, String permissionIds, String lastPermissionIds);
}

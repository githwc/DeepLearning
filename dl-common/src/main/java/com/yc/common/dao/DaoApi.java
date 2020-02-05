package com.yc.common.dao;


import com.yc.core.system.entity.SysUser;

/**
 * 功能描述：Interface - DAO data operation layer
 *
 * <p>
 * <p>版权所有：</p>
 * 未经本人许可，不得以任何方式复制或使用本程序任何部分
 *
 * @Company: 紫色年华
 * @Author: xieyc
 * @Datetime: 2019-05-31 11:39
 * @Version: 1.0.0
 */
public interface DaoApi {

    /**
     * 获取当前用户
     * @return
     */
    SysUser getCurrentUser();

    /**
     * 获取当前用户的ID
     */
    String getCurrentUserId();

}

package com.yc.core.system.model.vo;

import com.yc.core.system.entity.SysUser;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 功能描述：用户信息展示层
 * <p>版权所有：</p>
 * 未经本人许可，不得以任何方式复制或使用本程序任何部分
 *
 * @Company: 紫色年华
 * @Author: xieyc
 * @Datetime: 2020-01-07
 * @Version: 1.0.0
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class SysUserVO extends SysUser {

    /**
     * 部门名称
     */
    private String departName;

    /**
     * 在线状态[0:离线 1:在线 2:隐身]
     */
    private String online;
}

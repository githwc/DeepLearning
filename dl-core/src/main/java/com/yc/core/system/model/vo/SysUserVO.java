package com.yc.core.system.model.vo;

import com.yc.core.system.entity.SysUser;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 功能描述:用户信息展示层

 *

 * @Author: xieyc && 紫色年华
 * @Date: 2020-01-07
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

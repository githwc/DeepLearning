package com.yc.core.system.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 功能描述:
 *
 * @Author:  xieyc && 紫色年华
 * @Date 2019-09-19
 * @Version: 1.0.0
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class SysUserRole implements Serializable {

    private static final long serialVersionUID = 1L;
    @TableId(value = "sys_user_role_id", type = IdType.ASSIGN_UUID)
    private String sysUserRoleId;
    /**
     * 角色ID
     */
    private String roleId;
    /**
     * 用户ID
     */
    private String userId;

    /////////////////////////////// 预定义方法 ///////////////////////////////
    public SysUserRole(String userId, String roleId) {
        this.userId = userId;
        this.roleId = roleId;
    }

    /////////////////////////////// 非表字段 ///////////////////////////////

}

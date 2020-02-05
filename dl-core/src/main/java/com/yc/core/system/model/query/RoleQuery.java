package com.yc.core.system.model.query;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 功能描述：角色查询入参
 * <p>版权所有：</p>
 * 未经本人许可，不得以任何方式复制或使用本程序任何部分
 *
 * @Company: 紫色年华
 * @Author: xieyc
 * @Datetime: 2020-01-05
 * @Version: 1.0.0
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class RoleQuery implements Serializable {

    /**
     * 角色名称
     */
    private String roleName;
}

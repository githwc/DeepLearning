package com.yc.core.system.model.query;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 功能描述:

 *

 * @Author: xieyc && 紫色年华
 * @Date: 2020-04-10
 * @Version: 1.0.0
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class PermissionQuery {

    /**
     * 菜单类型(0:一级菜单; 1:子菜单:2:按钮)
     */
    private Integer menuType;
}

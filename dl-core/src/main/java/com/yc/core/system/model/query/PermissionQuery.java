package com.yc.core.system.model.query;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 功能描述：
 * <p>版权所有：</p>
 * 未经本人许可，不得以任何方式复制或使用本程序任何部分
 *
 * @Company: 紫色年华
 * @Author: xieyc
 * @Datetime: 2020-04-10
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

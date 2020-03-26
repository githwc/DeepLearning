package com.yc.core.system.model.vo;

import com.yc.core.system.entity.SysUser;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 功能描述：当前用户信息
 * <p>版权所有：</p>
 * 未经本人许可，不得以任何方式复制或使用本程序任何部分
 *
 * @Company: 紫色年华
 * @Author: xieyc
 * @Datetime: 2020-03-22
 * @Version: 1.0.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class CurrUserVO extends SysUser {

    /**
     * 权限ID
     */
    private String roleId;
}

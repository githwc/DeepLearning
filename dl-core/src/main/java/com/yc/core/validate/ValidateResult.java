package com.yc.core.validate;

import lombok.Data;

/**
 * 功能描述：
 * <p>版权所有：</p>
 * 未经本人许可，不得以任何方式复制或使用本程序任何部分
 *
 * @Company: 紫色年华
 * @Author: xieyc
 * @Datetime: 2020-06-12
 * @Version: 1.0.0
 */
@Data
public class ValidateResult {

    /**
     * 验证结果
     */
    private boolean result;

    /**
     * 参数名称
     */
    private String paramName;

    /**
     * 校验信息
     */
    private String msg;
}

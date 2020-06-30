package com.yc.core.validate;

import lombok.Data;

/**
 * 功能描述:

 *

 * @Author: xieyc && 紫色年华
 * @Date: 2020-06-12
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

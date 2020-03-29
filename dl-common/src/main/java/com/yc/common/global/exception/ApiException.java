package com.yc.common.global.exception;

import com.yc.common.global.response.RestResult;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 功能描述：
 * <p>版权所有：</p>
 * 未经本人许可，不得以任何方式复制或使用本程序任何部分
 *
 * @Company: 紫色年华
 * @Author: xieyc
 * @Datetime: 2019-09-25
 * @Version: 1.0.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class ApiException extends RuntimeException {

    /**
     * 错误码
     */
    private int code;
    /**
     * 错误信息
     */
    private String msg;

    public ApiException(int code, String msg, Object... params) {
        super(RestResult.formatMsg(msg, params));
        this.code = code;
        this.msg = RestResult.formatMsg(msg, params);
    }

}

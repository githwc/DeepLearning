package com.yc.common.global.error;

import com.yc.common.global.response.RestResult;

/**
 * 功能描述：接口服务通用异常
 * <p>版权所有：</p>
 * 未经本人许可，不得以任何方式复制或使用本程序任何部分
 *
 * @Company: 紫色年华
 * @Author: xieyc
 * @Datetime: 2020-03-22
 * @Version: 1.0.0
 */
public class ErrorException extends RuntimeException {

    private Integer code;
    private Integer httpStatusCode;
    private String msg;

    public ErrorException(Integer code,Integer httpStatusCode,String msg) {
        super(msg);
        this.code = code;
        this.httpStatusCode = httpStatusCode;
        this.msg = msg;
    }

    public ErrorException(IError error) {
        super(error.getMsg());
        this.code = error.getCode();
        this.httpStatusCode = error.getHttpStatusCode();
        this.msg = error.getMsg();
    }

    public ErrorException(IError error, String msg) {
        super(msg);
        this.code = error.getCode();
        this.httpStatusCode = error.getHttpStatusCode();
        this.msg = msg;
    }

    public ErrorException(IError error, String msg, Object... msgParams) {
        super(RestResult.formatMsg(msg, msgParams));
        this.code = error.getCode();
        this.httpStatusCode = error.getHttpStatusCode();
        this.msg = RestResult.formatMsg(msg, msgParams);
    }

    public Integer getCode() {
        return code;
    }

    public Integer getHttpStatusCode() {
        return httpStatusCode;
    }

    public String getMsg() {
        return msg;
    }
}

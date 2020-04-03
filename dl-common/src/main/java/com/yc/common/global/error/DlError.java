package com.yc.common.global.error;

/**
 * 功能描述：自定义错误码
 *  扩展Error，应对各自项目扩展业务自定义错误码
 *      [throw new ErrorException(DlError.RoleNoDelete);]
 *
 * <p>版权所有：</p>
 * 未经本人许可，不得以任何方式复制或使用本程序任何部分
 *
 * @Company: 紫色年华
 * @Author: xieyc
 * @Datetime: 2020-04-03
 * @Version: 1.0.0
 */
public enum DlError implements IError {
    /**
     * 字典异常
     */
    DictTypeNotFound(404, 40409, "字典类型不存在"),

    /**
     * 续租开始时间要晚于原入驻结束时间
     */
    RERENTDATEERROR(200,200006,"续租开始时间要晚于原入驻结束时间！"),
    ;
    /**
     * HTTP状态码
     */
    private int httpStatusCode;
    /**
     * 自定义错误码
     */
    private int code;
    /**
     * 错误描述
     */
    private String msg;

    DlError(int httpStatusCode, int code, String msg) {
        this.httpStatusCode = httpStatusCode;
        this.code = code;
        this.msg = msg;
    }

    @Override
    public int getHttpStatusCode() {
        return this.httpStatusCode;
    }

    @Override
    public int getCode() {
        return this.code;
    }

    @Override
    public String getMsg() {
        return this.msg;
    }
}

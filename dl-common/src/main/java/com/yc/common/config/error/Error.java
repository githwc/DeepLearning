package com.yc.common.config.error;

/**
 * 功能描述：
 * <p>版权所有：</p>
 * 未经本人许可，不得以任何方式复制或使用本程序任何部分
 *
 * @Company: 紫色年华
 * @Author: xieyc
 * @Datetime: 2020-03-22
 * @Version: 1.0.0
 */
public enum Error implements IError {

    /**
     * 安全认证
     */
    Success(200, 0, null),
    AuthError(401, 40100, "认证错误"),
    SignError(401, 40101, "签名错误"),
    TokenTimeout(401, 40102, "token超时"),
    TokenError(401, 40103, "token错误"),
    NoAccess(403, 40300, "无权限"),
    /**
     * 资源已存在
     */
    Existed(400, 40200, "资源已存在"),
    ResultExisted(400, 40201, "结果已存在"),
    ParameterExisted(400, 40202, "参数已存在"),
    UserExisted(400, 40203, "用户已存在"),
    OrderExisted(400, 40204, "订单已存在"),
    FileExisted(400, 40205, "文件已存在"),
    LoginNameExisted(400, 40206, "用户名已存在"),
    /**
     * 资源不存在
     */
    NotFound(404, 40400, "资源不存在"),
    ResultNotFound(404, 40401, "结果不存在"),
    ParameterNotFound(404, 40402, "参数不存在"),
    UserNotFound(404, 40403, "用户不存在"),
    OrderNotFound(404, 40404, "订单不存在"),
    FileNotFound(404, 40405, "文件不存在"),
    LoginNameNotFound(404, 40406, "登录名不存在"),
    /**
     * 用户异常
     */
    UserError(400, 42000, "用户错误"),
    NotLogin(400, 42001, "未登录"),
    UserDisabled(400, 42004, "用户已禁用"),
    UserIdcardError(400, 42006, "用户证件错误"),
    LoginNameOrPwdError(400, 42010, "用户名或密码错误"),
    LoginPwdError(400, 42011, "用户密码错误"),
    /**
     * 订单异常
     */
    OrderError(400, 43000, "订单错误"),
    OrderPayError(400, 43001, "订单支付错误"),
    OrderPayRepeatCallback(400, 43002, "订单支付重复回调"),
    /**
     * 格式错误
     */
    FormatError(400, 44000, "格式错误"),
    /**
     * 数据错误
     */
    DataError(400, 40000, "数据错误"),
    CheckCodeError(400, 40001, "验证码错误"),
    Disabled(400, 40002, "已禁用"),
    Deleted(400, 40003, "已删除"),
    /**
     * 消息异常
     */
    MsgError(400, 45000, "消息错误"),
    SMSBizLimit(400, 45001, "短信发送频繁"),
    SMSTodayPhoneLimit(400, 45002, "该手机号今日已发送超量"),
    SMSTodayIPLimit(400, 45003, "该IP今日已发送超量"),
    /**
     * 服务器异常
     */
    ServiceError(500, 50000, "内部错误"),
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

    Error(int httpStatusCode, int code, String msg) {
        this.httpStatusCode = httpStatusCode;
        this.code = code;
        this.msg = msg;
    }

    @Override
    public int getHttpStatusCode() {
        return httpStatusCode;
    }

    @Override
    public int getCode() {
        return code;
    }

    @Override
    public String getMsg() {
        return msg;
    }
}

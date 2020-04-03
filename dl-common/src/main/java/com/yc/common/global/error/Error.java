package com.yc.common.global.error;

/**
 * 功能描述：异常代码枚举
 *  [throw new ErrorException(DlError.RoleNoDelete);]
 *
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
     * 数据请求错误
     */
    DataError(400, 40000, "请求参数错误"),
    CheckCodeError(400, 40001, "验证码错误"),
    Disabled(400, 40002, "已禁用"),
    Deleted(400, 40003, "已删除"),
    PathIsNull(400,40004,"字典路径不能为空，禁止读取根字典信息!"),
    PathIsError(400,40005,"字典路径格式有误"),

    /**
     * 安全认证
     */
    AuthError(401, 40100, "认证错误"),
    SignError(401, 40101, "签名错误"),
    TokenError(401, 40103, "登录信息已失效，请重新登录！"),

    /**
     * 资源已存在
     */
    Existed(400, 40200, "资源已存在"),
    RoleExisted(400, 40201, "角色已存在"),
    UserExisted(400, 40203, "用户已存在"),
    DictExisted(400, 40204, "存在重复字典项,请重新填写"),
    FileExisted(400, 40205, "文件已存在"),
    LoginNameExisted(400, 40206, "用户名已存在"),

    /**
     * 资源不存在
     */
    NotFound(404, 40400, "资源不存在"),
    ResultNotFound(404, 40401, "结果不存在"),
    ParameterNotFound(404, 40402, "参数不存在"),
    UserNotFound(404, 40403, "用户不存在"),
    DeptNotFound(404, 40404, "部门不存在"),
    PermissionNotFound(404, 40405, "权限点不存在"),
    DictNotFound(404, 40406, "字典不存在"),
    /**
     * 用户异常
     */
    UserError(400, 42000, "用户错误"),
    AccountLock(400, 42001, "该账号密码输入错误五次，请10分钟以后重试"),
    UserDisabled(400, 42004, "用户已禁用"),
    LoginNameOrPwdError(400, 42010, "用户名或密码错误"),
    LoginPwdError(400, 42011, "用户密码错误"),

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

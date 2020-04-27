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
     * 安全认证
     */
    AuthError(401, 40100, "您的账号已在异地登录,请注意个人信息安全!"),
    TokenError(401, 40103, "登录信息已失效，请重新登录！"),
    ParameterNotFound(404, 40402, "请求参数错误"),
    CheckCodeError(400, 40001, "验证码错误"),

    /**
     * 用户异常
     */
    LoginNameOrPwdError(400, 42010, "用户名或密码错误"),
    LoginPwdError(400, 42011, "用户密码错误"),
    UserDeleted(400, 42000, "用户已注销"),
    UserDisabled(400, 42004, "用户已禁用"),
    UserExisted(400, 40203, "用户已存在"),
    UserNotFound(404, 40403, "用户不存在"),
    LoginNameIsNull(400, 42002, "用户名不能为空"),
    AccountLock(400, 42001, "该账号密码输入错误五次，请10分钟以后重试"),

    /**
     * 字典管理
     */
    PathIsNull(400,40004,"字典路径不能为空，禁止读取根字典信息!"),
    PathIsError(400,40005,"字典路径格式有误"),
    DictExisted(400, 40204, "存在重复字典项,请重新填写"),
    DictNotFound(404, 40406, "字典不存在"),

    /**
     * 角色/权限管理
     */
    RoleExisted(400, 40202, "角色已存在"),
    PermissionNotFound(404, 40405, "权限点不存在"),

    /**
     * 部门管理
     */
    DeptNotFound(404, 40404, "部门不存在"),

    /**
     * 服务器异常
     */
    ServiceError(500, 50000, "内部错误"),

    // ===================== 业务层错误 ==================
    /**
     * 缺少商品
     */
    GoodNotFound(200, 40409, "请添加选购商品"),
    /**
     * 库存不足
     */
    StockLow(200,200001,"库存不足,请重新选购"),
    /**
     * 存在重复路径
     */
    URLNotUnique(200,200002,"存在重复路径,请修改!"),
    /**
     * 原密码输入错误
     */
    OldPasswordError(200,20003,"原密码输入错误!"),
    /**
     * 请选择要上传的图片
     */
    UploadImgError(200,20004,"请选择要上传的图片!"),
    /**
     * 请选择要上传的图片
     */
    ImgFormatError(200,20004," 请选择jpg,jpeg,gif,png格式的图片!"),
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

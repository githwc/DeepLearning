package com.yc.common.global.error;

/**
 * 功能描述：异常代码枚举
 *  一、[throw new ErrorException(DlError.RoleNoDelete);]
 *  二、
 *      httpStatusCode: http响应码(非200前端进入error代码逻辑块)
 *      code:           平台响应码
 *      msg:            响应信息
 *  三、
 *      401:缺乏有效身份认证凭证,多指未登录
 *      403:服务器拒绝响应,权限不足
 *
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
    TokenError(401, 99999, "登录信息已失效，请重新登录！"),
    CheckCodeError(403, 40101, "验证码错误"),
    ParameterNotFound(403, 40102, "请求参数错误"),
    GetCodeAgain(403, 40104, "验证码不存在!"),
    IllegalRequest(403, 40105, "非法请求"),

    /**
     * 用户异常
     */
    LoginNameOrPwdError(403, 42010, "用户名或密码错误"),
    LoginPwdError(403, 42011, "用户密码错误"),
    UserDeleted(403, 42000, "用户已注销"),
    UserDisabled(403, 42004, "用户已禁用"),
    UserExisted(403, 40203, "用户已存在"),
    UserNotFound(403, 40403, "用户不存在"),
    LoginNameIsNull(403, 42002, "用户名不能为空"),
    AccountLock(403, 42001, "该账号密码输入错误五次，请10分钟以后重试"),

    /**
     * 字典管理
     */
    PathIsNull(200,40004,"字典路径不能为空，禁止读取根字典信息!"),
    PathIsError(200,40005,"字典路径格式有误"),
    DictExisted(200, 20004, "存在重复字典项,请重新填写"),
    DictNotFound(200, 40406, "字典不存在"),

    /**
     * 角色\权限管理
     */
    RoleExisted(200, 40202, "角色已存在"),
    PermissionNotFound(200, 40405, "权限点不存在"),

    /**
     * 部门管理
     */
    DeptNotFound(403, 40404, "部门不存在"),

    /**
     * 服务器异常
     */
    ServiceError(200, 50000, "系统错误"),

    // ===================== 业务层错误 ==================
    /**
     * 缺少商品
     */
    GoodNotFound(200, 40409, "请添加选购商品"),
    /**
     * 商品不存在
     */
    GoodError(200, 20000, "商品不存在"),
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
     * 请选择要上传的文件
     */
    UploadFileError(200,20004,"请选择要上传的文件!"),
    /**
     * 请选择要上传的图片
     */
    ImgFormatError(200,20005,"请选择jpg,jpeg,gif,png格式的图片!"),
    /**
     * 保存文件异常
     */
    SaveImgError(200,20006,"保存文件异常!"),
    /**
     * 商品未上架
     */
    GoodNotUp(200, 20007, "商品未上架"),
    /**
     * 商品库存不足
     */
    GoodStock(200, 20008, "商品库存不足"),
    /**
     * 收货地址不存在
     */
    ShippingNotFound(200, 20009, "收货地址不存在"),
    /**
     * 金额计算有误
     */
    AmountError(200, 20008, "金额计算有误"),

    /**
     * 重复秒杀
     */
    DuplicateSeckill(200,20009,"请勿重复秒杀!"),

    /**
     * 秒杀结束
     */
    SeckillOver(200,20010,"秒杀结束!"),
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

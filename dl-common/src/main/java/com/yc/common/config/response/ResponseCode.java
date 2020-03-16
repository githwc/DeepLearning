package com.yc.common.config.response;

/**
 * 功能描述：
 *
 * <p>版权所有：</p>
 * 未经本人许可，不得以任何方式复制或使用本程序任何部分
 *
 * @Company: 紫色年华
 * @Author: xieyc
 * @Datetime: 2019-08-22 08:54
 * @Version: 1.0.0
 */
public class ResponseCode {

    /**
     * 成功
     */
    public static final int SUCCESS = 200;

    /**
     * 未授权
     */
    public static final int NO_ACCESS_EXCEPTION = 401;

    /**
     * 拒绝请求
     */
    public static final int REFUSE_REQUEST = 403;

    /**
     * 未找到
     */
    public static final int NOT_FOUND_EXCEPTION = 404;


    /**
     * 系统错误
     */
    public static final int SYSTEM_EXCEPTION = 10500;

    //=========================自定义 =====================

    /**
     * 登录名或密码错误
     */
    public static final int USER_LOGINNAME_OR_PWD_EXCEPTION = 10000 ;

    /**
     * 用户名错误
     */
    public static final int USER_LOGINNAME_EXCEPTION = 10001 ;

    /**
     * 密码错误
     */
    public static final int USER_PASSWORD_EXCEPTION = 10002 ;

    /**
     * 用户已存在
     */
    public static final int USER_LOGINNAME_EXISTS_EXCEPTION = 10003 ;

    /**
     * 用户已冻结
     */
    public static final int USER_DISABLE_EXCEPTION = 10004 ;

    /**
     * 用户不存在
     */
    public static final int USER_NO_EXIST_EXCEPTION = 10005 ;

    /**
     * Token 为空
     */
    public static final int TOKEN_IS_NULL_EXCEPTION = 10006 ;

    /**
     * 结果集错误
     */
    public static final int RESULT_EXCEPTION = 10300;
    /**
     * 参数错误
     */
    public static final int PARAMETER_EXCEPTION = 10400;


}

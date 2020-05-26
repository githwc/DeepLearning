package com.yc.common.constant;

/**
 * 功能描述：全局常量
 * <p>版权所有：</p>
 * 未经本人许可，不得以任何方式复制或使用本程序任何部分
 *
 * @Company: 紫色年华
 * @Author: xieyc
 * @Datetime: 2019-09-21
 * @Version: 1.0.0
 */
public class CommonConstant {

    // =============== 基础常量 ====================
    /**
     * 成功码
     */
    public static final int SUCCESS_CODE = 200;


    // =============== 系统日志 ====================
    /**
     * 日志类型： 普通操作
     */
    public static final int LOG_TYPE_0 = 0;

    /**
     * 日志类型： 登录 & 登出
     */
    public static final int LOG_TYPE_1 = 1;

    /**
     * 日志类型： 定时任务
     */
    public static final int LOG_TYPE_2 = 2;

    /**
     * 日志操作结果 成功
     */
    public final static int OPSTATE_SUCCESS = 0;
    /**
     * 日志操作结果 失败
     */
    public final static int OPSTATE_FAILURE = 1;

    /**
     * 日志操作类型 增加
     */
    public final static int OPTYPE_CREATE = 0;
    /**
     * 日志操作类型 删除
     */
    public final static int OPTYPE_DELETE = 1;
    /**
     * 日志操作类型 修改
     */
    public final static int OPTYPE_UPDATE = 2;
    /**
     * 日志操作类型 读取
     */
    public final static int OPTYPE_READ = 3;

    // ================== 编码格式 ===========================

    /**
     * 文本编码
     */
    public static String TEXT_CONTENTTYPE = "text/plain;charset=UTF-8";

    /**
     * JSON编码
     */
    public static String JSON_CONTENTTYPE = "application/json;charset=UTF-8";

    /**
     * XML编码
     */
    public static String XML_CONTENTTYPE = "text/xml;charset=UTF-8";

    /**
     * 编码格式
     */
    public static final String CHARSET_UTF_8 = "UTF-8";

    /**
     * 编码格式
     */
    public static final String CHARSET_GBK = "GBK";

    //================= 加解密 ====================
    /**
     * MD5
     */
    public static final String ENCODE_MD5 = "MD5";

    /**
     * AES
     */
    public static final String ENCODE_AES = "AES";

    /**
     * RSA
     */
    public static final String ENCODE_RSA = "RSA";

    /**
     * SHA1PRNG 算法
     */
    public static final String ALGORITHM_SHA1PRNG = "SHA1PRNG";
    // =============== 凭证相关 ==================

    public static String X_ACCESS_TOKEN = "X-Access-Token";

    /**
     * 存放Token的Header Key
     */
    public static final String HEADER_STRING = "authorization";

    /**
     * Token前缀
     */
    public static final String TOKEN_PREFIX = "Bearer";

    // ===================== 缓存key /key前缀 ======================

    /**
     * 用户信息
     */
    public final static String SYS_USERS_CACHE = "SYS_USERS_CACHE_";

    /**
     * 今日订单号
     */
    public static final String ORDER_NO_TODAY_CACHE = "ORDER_NO_NOW_CACHE";

    /**
     * 聊天对象集合
     */
    public static final String CHAT_OBJECT = "CHAT_OBJECT";

    /**
     * 购物车缓存
     */
    public static final String CART = "CART_";

    // ====================== 图片格式 ======================
    /**
     * 图片格式
     */
    public final static String IMG_FORMAT = "jpg,jpeg,gif,png";

    // ====================== 默认数据 ======================
    /**
     * 默认密码
     */
    public final static String DEFAULT_PASSWORD = "123456";


}

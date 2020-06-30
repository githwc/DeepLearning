package com.yc.common.constant;

/**
 * 功能描述:全局常量
 *
 * @Author: xieyc && 紫色年华
 * @Date: 2019-09-21
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

    /**
     * RSA公钥(对数据加密)
     */
    public static final String RSA_PUBLIC_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAitE3SxGmSvx2tv226rN9" +
            "/mUPe8G5jsIcBJz98/IPg4m4ML7J0VbXTryxMZKTXETuAOVTQJnA3yoHa17dtJ5xiEwTG9WR0GtwnTONRwjkLZ+NydfXphh6ykxDfXlnyytVdKExpisNTEm5zy0F7A1lcYhG134G3pPjlqueYq67yyDfUfxYmMZdP89l2GiFq0Q2pkSOYf2Uw4MyWfb9Lt+dJoOs9RimR+/4ApbtKFchBWbdNUYKOcWHSO5f6BfI1XRCdOvqhB5U9v+FOrHsUXKzM89H9la8iYN6B9/lxYQZ5090Wx1tlBV/9PtUYEYvbAlduVgMa+3VDbyFbb0IRAMn0wIDAQAB";

    /**
     * RSA私钥(对数据解密)
     */
    public static final String RSA_PRIVATE_KEY = "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQCK0TdLEaZK/Ha2" +
            "/bbqs33+ZQ97wbmOwhwEnP3z8g+DibgwvsnRVtdOvLExkpNcRO4A5VNAmcDfKgdrXt20nnGITBMb1ZHQa3CdM41HCOQtn43J19emGHrKTEN9eWfLK1V0oTGmKw1MSbnPLQXsDWVxiEbXfgbek+OWq55irrvLIN9R/FiYxl0/z2XYaIWrRDamRI5h/ZTDgzJZ9v0u350mg6z1GKZH7/gClu0oVyEFZt01Rgo5xYdI7l/oF8jVdEJ06+qEHlT2/4U6sexRcrMzz0f2VryJg3oH3+XFhBnnT3RbHW2UFX/0+1RgRi9sCV25WAxr7dUNvIVtvQhEAyfTAgMBAAECggEAaF4d/VG6vhwMQU6FzPXAX5ipyHkBQsTL/efuww8V/OZ5ViTLZZmt7SPO8R9rrW6hpojETAiNSvu1clNzys1pPHk90KqEo7dzhq1wpZyYuXiBSOT/3IABKcGm6Fpz3/unlzVuN6mcqOOxUINgNXZo86/q2y+EiKmV7fz/6t/Gj1P/CEH3fBfhwNg+VQFArIpx7YdzqAysEx7At/91CVTKOM8FkfH+clRy6j2lNm+6mo6ERcxVC+Tz9FUlat28HGI/uVFx2wAdtZZYVo5NH5kH1BUo+dYb3llfY7Mxq2s4N8Hf84WUf3pzimWiYZYLkYD2Own/KEwTrHAcJinAIBWLWQKBgQDDwJfpNclN7/l1HtPRyalY90+UOe/mM06TIYOOmt2afcVyyNRjkqve6f63QuSbf4e2FGeE9svTgle8EZh74KPtAjevST8SXtsAhUxUznbmHk2JqYSuDjSG5Qkf5AgCqc8V8I4VCZu6WSfRcOgLtYXAUAE7BMGelqd1q0jOLPSUFwKBgQC1iq9Uh9VZ3+CAf/mPajH11m+XTa7c0f9jplNJdQRwZB/QKx2RPK2Q+CFBl00uVcVOT8F/wiQ6gqFVbo9EQiMfy8KMpE4vvcC+WoirKrs2jY4absM4OblLIfPjCkqFoUwFIPeuwrpjhyxLR3tfv/JMA018WwuLF7rGiuYJ+u4TpQKBgB5qY3A6CkBQ5Drdgl4bbnPDYZsPcYYcPL5Bu9ZBm+MwXaibKd0a4het1gUVoL3EkAqrmvYe/sarfwRa242L9wyRhqN2xxUMpgyrqZOXzQOue6/DJjmBPYokNkm9Pb1kMygei4UbTBYlWjmByJxhJRoflEFeWNNSZ0yyQ4q9k3cbAoGBALBJc+iJ3svfcopVu2lgnhMzoDk03lDgOzfxE3+Jej4JfDsRIy0d1w2nZePjoLcV4vsgx63gQH3wy+nB6q1F7AW8P7eC9cm2Yax3Da/pVtKqswtH85pts1kaJ1KZc7Q/5So10U6LGaP8Zy7SwyM/qXHW4y+W2aa/7EdQKgIXAYlhAoGBALqYWSBjMkpUrtkjkDoaPJl1OsrxZ+CiEw/OKdcT6zS5qf/NQ2yTzjcwge2N4Oaj9pBBHR3MYQ+6DyUgnNxbKpsr09X4aE5Siod3e+Lm+11fd9SWCjSOUJJJEm2Z3OpKkUDolXfXVRpRP3I1ibIPwEIz+N8WllvSRP2C9iRysGqJ";

    /**
     * 盐
     */
    public static final String SLAT = "u8*9wje^&jsapdu@!~%#u03&4f')kd(*";
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
    public static final String TODAY_ORDER_NO = "ORDER_NO_NOW_";

    /**
     * 聊天对象集合
     */
    public static final String CHAT_OBJECT = "CHAT_OBJECT";

    /**
     * 购物车
     */
    public static final String CART = "CART_";

    /**
     * 秒杀项目
     */
    public static final String MALL_SECKILL_ = "MALL_SECKILL_";

    /**
     * 用户ID
     */
    public final static String USER_BY_ID_ = "USER_BY_ID_";

    /**
     * 总成绩排名
     */
    public final static String SCORE_BANK = "SCORE_BANK";

    /**
     * 设备号
     */
    public final static String DEVICE_REPAIR = "DEVICE_REPAIR";

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

    // ======================= 日期格式 =======================
    /**
     * 2020-05-05 12:12:23
     */
    public static final String yyyyMMddHHmmss = "yyyy-MM-dd HH:mm:ss";


}

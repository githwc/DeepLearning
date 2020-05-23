package com.yc.common.utils;

import com.yc.common.constant.CommonConstant;
import lombok.extern.slf4j.Slf4j;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;

/**
 * 功能描述：关于加解密、加解码的工具类
 * <p>
 * <p>版权所有：</p>
 * 未经本人许可，不得以任何方式复制或使用本程序任何部分
 *
 * @Company: 紫色年华
 * @Author: xieyc
 * @Datetime: 2019-05-10 13:48
 */
@Slf4j
public class EncoderUtil {

    // ================= MD5 加密 START ====================
    /**
     * 对原始数据进行MD5加密，编码使用UTF-8，MD5加密不可逆。
     * @param cPlainText 原报文+md5密钥
     * @return MD5加密后的字符串
     */
    public static String md5(String cPlainText) {
        StringBuffer tBuf = new StringBuffer();
        try {
            MessageDigest tMd = MessageDigest.getInstance(CommonConstant.ENCODE_MD5);
            tMd.update(cPlainText.getBytes(CommonConstant.DEFAULT_CHARSET));
            byte[] tByte = tMd.digest();

            for (int j = 0; j < tByte.length; ++j) {
                int i = tByte[j];
                if (i < 0) {
                    i += 256;
                }
                if (i < 16) {
                    tBuf.append("0");
                }
                tBuf.append(Integer.toHexString(i));
            }
        } catch (Exception e) {
            log.info(e.getMessage());
        }
        return tBuf.toString();
    }

    // ================= MD5 加密 END ====================

    /**
     * @Description:URL解码
     * @Date: 16:55 2019/5/9
     * @Param:   encoderVal:需要解码的参数
     * @Return:  正确解码的中文参数值
     */
    public static String getURLDecoderString(String encoderVal) {
        String result = "";
        if (null == encoderVal) {
            return "";
        }
        try {
            result = java.net.URLDecoder.decode(encoderVal, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * @Description:URL转码
     * @Date: 17:13 2019/5/9
     * @Param: decoderVal:需要转码的参数
     * @Return: 正确转码的参数值
     */
    public static String getURLEncoderString(String decoderVal) {
        String result = "";
        if (null == decoderVal) {
            return "";
        }
        try {
            result = java.net.URLEncoder.encode(decoderVal, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * @Description:编码转换,从UTF-8到GBK
     * @Date: 13:41 2019/5/10
     * @Param strVal:UTF-8字符
     * @Return: GBK字符
     */
    public static String UTF82GBK(String strVal) {
        try {
            if (strVal == null) {
                return "";
            } else {
                strVal = strVal.trim();
                strVal = new String(strVal.getBytes("UTF-8"), "GBK");
                return strVal;
            }
        } catch (Exception exp) {
            return "";
        }
    }

    /**
     * @Description:编码转换：从GBK到UTF8
     * @Date: 13:46 2019/5/10
     * @Param:
     * @Return:
     */
    public static String GBK2UTF8(String strVal) {
        try {
            if (strVal == null) {
                return "";
            } else {
                strVal = new String(strVal.getBytes("GBK"), "UTF-8");
                return strVal;
            }
        } catch (Exception exp) {
            return "";
        }
    }

    /**
     * @Description:编码转换,从“ISO8859_1”到“GBK”得到的字符串
     * @Date: 13:44 2019/5/10
     * @Param strVal 要转换的字符串
     * @Return:
     */
    public static String ISO2GBK(String strVal) {
        try {
            if (strVal == null) {
                return "";
            } else {
                strVal = strVal.trim();
                strVal = new String(strVal.getBytes("ISO8859_1"), "GBK");
                return strVal;
            }
        } catch (Exception exp) {
            return "";
        }
    }

    /**
     * @Description:编码转换：从“GBK”到“ISO8859_1”得到的字符串
     * @Date: 13:45 2019/5/10
     * @Param:
     * @Return:
     */
    public static String GBK2ISO(String strVal) {
        try {
            if (strVal == null) {
                return "";
            } else {
                strVal = new String(strVal.getBytes("GBK"), "ISO8859_1");
                return strVal;
            }
        } catch (Exception exp) {
            return "";
        }
    }

    /**
     * @Description:编码转换 从ISO到UTF8
     * @Date: 13:50 2019/5/10
     * @Param:
     * @Return:
     */
    public static String ISO2UTF8(String strVal) {
        try {
            if (strVal == null) {
                return "";
            } else {
                strVal = new String(strVal.getBytes("ISO-8859-1"), "UTF-8");
                return strVal;
            }
        } catch (Exception exp) {
            return "";
        }
    }

    /**
     * @Description:编码转换,从UTF8到ISO-8895-1
     * @Date: 13:51 2019/5/10
     * @Param:
     * @Return:
     */
    public static String UTF82ISO(String strVal) {
        try {
            if (strVal == null) {
                return "";
            } else {
                strVal = new String(strVal.getBytes("UTF-8"), "ISO-8859-1");
                return strVal;
            }
        } catch (Exception exp) {
            return "";
        }
    }

     // ======================本类使用START=======================


    public static String byteArrayToHexString(byte b[]) {
        StringBuffer resultSb = new StringBuffer();
        for (int i = 0; i < b.length; i++){
            resultSb.append(byteToHexString(b[i]));
        }
        return resultSb.toString();
    }

    private static String byteToHexString(byte b) {
        int n = b;
        if (n < 0) {
            n += 256;
        }
        int d1 = n / 16;
        int d2 = n % 16;
        return hexDigits[d1] + hexDigits[d2];
    }

    private static final String hexDigits[] = { "0", "1", "2", "3", "4", "5",
            "6", "7", "8", "9", "a", "b", "c", "d", "e", "f" };

    // ======================本类使用END=======================
}

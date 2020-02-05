package com.yc.common.utils;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;
import java.io.UnsupportedEncodingException;
import java.security.Key;
import java.security.MessageDigest;

/**
 * 功能描述：关于加解码,加解密 的工具类
 * <p>
 * <p>版权所有：</p>
 * 未经本人许可，不得以任何方式复制或使用本程序任何部分
 *
 * @Company: 紫色年华
 * @Author: xieyc
 * @Datetime: 2019-05-10 13:48
 */
public class EncoderUtil {

    /**
     * 定义使用的算法为:PBEWITHMD5andDES算法
     */
    public static final String ALGORITHM = "PBEWithMD5AndDES";//加密算法

    /**
     * 定义迭代次数为1000次
     */
    private static final int ITERATIONCOUNT = 1000;

    /**
     * 加密明文字符串
     *
     * @param plaintext 待加密的明文字符串
     * @param password  生成密钥时所使用的密码
     * @param salt      盐值
     * @return 加密后的密文字符串
     * @throws Exception
     */
    public static String encrypt(String plaintext, String password, String salt) {
        Key key = getPBEKey(password);
        byte[] encipheredData = null;
        PBEParameterSpec parameterSpec = new PBEParameterSpec(salt.getBytes(), ITERATIONCOUNT);
        try {
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE, key, parameterSpec);
            encipheredData = cipher.doFinal(plaintext.getBytes());
        } catch (Exception e) {
        }
        return bytesToHexString(encipheredData);
    }

    /**
     * 解密密文字符串
     *
     * @param ciphertext 待解密的密文字符串
     * @param password   生成密钥时所使用的密码(如需解密,该参数需要与加密时使用的一致)
     * @param salt       盐值(如需解密,该参数需要与加密时使用的一致)
     * @return 解密后的明文字符串
     * @throws Exception
     */
    public static String decrypt(String ciphertext, String password, String salt) {
        Key key = getPBEKey(password);
        byte[] passDec = null;
        PBEParameterSpec parameterSpec = new PBEParameterSpec(salt.getBytes(), ITERATIONCOUNT);
        try {
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.DECRYPT_MODE, key, parameterSpec);
            passDec = cipher.doFinal(hexStringToBytes(ciphertext));
        } catch (Exception e) {
        }
        return new String(passDec);
    }

    public static String MD5Encode(String origin, String charsetname) {
        String resultString = null;
        try {
            resultString = new String(origin);
            MessageDigest md = MessageDigest.getInstance("MD5");
            if (charsetname == null || "".equals(charsetname)) {
                resultString = byteArrayToHexString(md.digest(resultString.getBytes()));
            } else {
                resultString = byteArrayToHexString(md.digest(resultString.getBytes(charsetname)));
            }
        } catch (Exception exception) {
        }
        return resultString;
    }

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
    /**
     * 根据PBE密码生成一把密钥
     *
     * @param password 生成密钥时所使用的密码
     * @return Key PBE算法密钥
     */
    private static Key getPBEKey(String password) {
        // 实例化使用的算法
        SecretKeyFactory keyFactory;
        SecretKey secretKey = null;
        try {
            keyFactory = SecretKeyFactory.getInstance(ALGORITHM);
            // 设置PBE密钥参数
            PBEKeySpec keySpec = new PBEKeySpec(password.toCharArray());
            // 生成密钥
            secretKey = keyFactory.generateSecret(keySpec);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return secretKey;
    }

    /**
     * 将字节数组转换为十六进制字符串
     *
     * @param src 字节数组
     * @return
     */
    public static String bytesToHexString(byte[] src) {
        StringBuilder stringBuilder = new StringBuilder("");
        if (src == null || src.length <= 0) {
            return null;
        }
        for (int i = 0; i < src.length; i++) {
            int v = src[i] & 0xFF;
            String hv = Integer.toHexString(v);
            if (hv.length() < 2) {
                stringBuilder.append(0);
            }
            stringBuilder.append(hv);
        }
        return stringBuilder.toString();
    }

    /**
     * 将十六进制字符串转换为字节数组
     *
     * @param hexString 十六进制字符串
     * @return
     */
    public static byte[] hexStringToBytes(String hexString) {
        if (hexString == null || hexString.equals("")) {
            return null;
        }
        hexString = hexString.toUpperCase();
        int length = hexString.length() / 2;
        char[] hexChars = hexString.toCharArray();
        byte[] d = new byte[length];
        for (int i = 0; i < length; i++) {
            int pos = i * 2;
            d[i] = (byte) (charToByte(hexChars[pos]) << 4 | charToByte(hexChars[pos + 1]));
        }
        return d;
    }

    private static byte charToByte(char c) {
        return (byte) "0123456789ABCDEF".indexOf(c);
    }



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

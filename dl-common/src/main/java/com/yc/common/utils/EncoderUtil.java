package com.yc.common.utils;

import com.yc.common.constant.CommonConstant;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

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
     * MD5加密,编码UTF-8,签名统一转成小写字符
     * @param cPlainText 原报文+md5密钥
     * @return MD5加密后的字符串
     */
    public static String md5(String cPlainText) {
        StringBuffer tBuf = new StringBuffer();
        try {
            MessageDigest tMd = MessageDigest.getInstance(CommonConstant.ENCODE_MD5);
            tMd.update(cPlainText.getBytes(CommonConstant.CHARSET_UTF_8));
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

    // ================= AES 加密 START ==================
    /**
     *  AES加密
     *
     * @param orignalStr 明文
     * @param encryKey  aes密钥
     * @return 结果描述
     */
    public static String aesEncrypt(String orignalStr,String encryKey) {
        String encryptMsg = "";
        try {
            encryptMsg = base64Encode(aesEncryptToBytes(orignalStr, encryKey));
        } catch (Exception e) {
            log.error("AES加密失败" , e);
        }
        return encryptMsg;
    }

    /**
     * base 64 encode
     * @param bytes 待编码的byte[]
     * @return 编码后的base 64 code
     */
    private static String base64Encode(byte[] bytes){
        return new BASE64Encoder().encode(bytes);
    }

    /**
     * AES加密
     * @param content 待加密的内容
     * @param encryptKey 加密密钥
     * @return 加密后的byte[]
     * @throws Exception
     */
    private static byte[] aesEncryptToBytes(String content, String encryptKey) throws Exception {
        KeyGenerator kgen = KeyGenerator.getInstance(CommonConstant.ENCODE_AES);
        SecureRandom random = SecureRandom.getInstance(CommonConstant.ALGORITHM_SHA1PRNG);
        random.setSeed(encryptKey.getBytes());
        kgen.init(128, random);
        Cipher cipher = Cipher.getInstance(CommonConstant.ENCODE_AES);
        cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(kgen.generateKey().getEncoded(), CommonConstant.ENCODE_AES));
        return cipher.doFinal(content.getBytes(CommonConstant.CHARSET_UTF_8));
    }

    /**
     * Aes解密
     * @param encryptStr 密文
     * @param decryptKey aeskey
     * @return
     */
    public static String aesDecrypt(String encryptStr, String decryptKey){
        if(StringUtils.isNotBlank(encryptStr)) {
            try {
                return aesDecryptByBytes(base64Decode(encryptStr), decryptKey);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     * base64解密
     * @param base64Code base64code
     * @return byty[]
     * @throws Exception
     */
    private static byte[] base64Decode(String base64Code) throws Exception {
        if(StringUtils.isNotEmpty(base64Code)) {
            return (new BASE64Decoder()).decodeBuffer(base64Code);
        }
        return null;
    }

    private static String aesDecryptByBytes(byte[] encryptBytes, String decryptKey) throws Exception {
        KeyGenerator kgen = KeyGenerator.getInstance(CommonConstant.ENCODE_AES);
        SecureRandom random = SecureRandom.getInstance(CommonConstant.ALGORITHM_SHA1PRNG);
        random.setSeed(decryptKey.getBytes());
        kgen.init(128, random);
        Cipher cipher = Cipher.getInstance(CommonConstant.ENCODE_AES);
        cipher.init(2, new SecretKeySpec(kgen.generateKey().getEncoded(), CommonConstant.ENCODE_AES));
        byte[] decryptBytes = cipher.doFinal(encryptBytes);
        return new String(decryptBytes);
    }
    // ================= AES 加密 END ====================

    // ================= RSA 加密 END ====================
    /**
     * RSA公钥加密
     *
     * @param str       加密字符串
     * @param publicKey 公钥
     * @return 密文
     * @throws Exception 加密过程中的异常信息
     */
    public static String rsaEncrypt(String str, String publicKey) {
        String resultStr = null;
        try {
            byte[] decoded = Base64.decodeBase64(publicKey);
            RSAPublicKey pubKey = (RSAPublicKey) KeyFactory.getInstance(CommonConstant.ENCODE_RSA).generatePublic(new X509EncodedKeySpec(decoded));
            Cipher cipher = Cipher.getInstance(CommonConstant.ENCODE_RSA);
            cipher.init(Cipher.ENCRYPT_MODE, pubKey);
            resultStr = Base64.encodeBase64String(cipher.doFinal(str.getBytes(CommonConstant.CHARSET_UTF_8)));
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return resultStr;
    }

    /**
     * RSA私钥解密
     *
     * @param str        加密字符串
     * @param privateKey 私钥
     * @return 铭文
     * @throws Exception 解密过程中的异常信息
     */
    public static String rsaDecrypt(String str, String privateKey) throws Exception {
        //64位解码加密后的字符串
        byte[] inputByte = Base64.decodeBase64(str.getBytes(CommonConstant.CHARSET_UTF_8));
        //base64编码的私钥
        byte[] decoded = Base64.decodeBase64(privateKey);
        RSAPrivateKey priKey = (RSAPrivateKey) KeyFactory.getInstance(CommonConstant.ENCODE_RSA).generatePrivate(new PKCS8EncodedKeySpec(decoded));
        //RSA解密
        Cipher cipher = Cipher.getInstance(CommonConstant.ENCODE_RSA);
        cipher.init(Cipher.DECRYPT_MODE, priKey);
        return new String(cipher.doFinal(inputByte));
    }

    // ================= RSA 加密 END ====================
}

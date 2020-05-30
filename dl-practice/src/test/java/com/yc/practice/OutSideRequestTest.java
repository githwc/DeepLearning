package com.yc.practice;

import cn.hutool.crypto.digest.DigestUtil;
import com.alibaba.fastjson.JSONObject;
import com.yc.common.constant.CommonConstant;
import com.yc.common.propertie.EncodeProperties;
import com.yc.common.propertie.OutSideUrlProperties;
import com.yc.common.utils.EncoderUtil;
import com.yc.common.utils.HttpClientUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.Map;

/**
 * 功能描述：对外请求测试
 * <p>版权所有：</p>
 * 未经本人许可，不得以任何方式复制或使用本程序任何部分
 *
 * @Company: 紫色年华
 * @Author: xieyc
 * @Datetime: 2020-05-23
 * @Version: 1.0.0
 */
@Slf4j
public class OutSideRequestTest extends PracticeTest {

    @Autowired
    private OutSideUrlProperties outSideUrlProperties;

    @Autowired
    private EncodeProperties encodeProperties;

    @Test
    public void getNoParam() {
        log.info("==========开始调用==========");
        String result = HttpClientUtil.doGet(outSideUrlProperties.getTest1(),null);
        log.info(result);
        log.info("==========调用結束==========");
    }

    @Test
    public void getWithParamByMap() {
        log.info("==========开始调用==========");
        Map<String,String> params = new HashMap<>();
        params.put("PayInfoId", "你好");
        String result = HttpClientUtil.doGet(outSideUrlProperties.getTest1(),params);
        log.info(result);
        log.info("==========调用結束==========");
    }

    @Test
    public void postNoParam() {
        log.info("==========开始调用==========");
        Map<String,String> params = new HashMap<>();
        params.put("PayInfoId", "你好");
        String result = HttpClientUtil.doPostMap(outSideUrlProperties.getTest2(),null);
        log.info(result);
        log.info("==========调用結束==========");
    }

    @Test
    public void postWithParamByMap() {
        log.info("==========开始调用==========");
        Map<String,String> params = new HashMap<>();
        params.put("PayInfoId", "你好");
        String result = HttpClientUtil.doPostMap(outSideUrlProperties.getTest2(),params);
        log.info(result);
        log.info("==========调用結束==========");
    }

    @Test
    public void postWithParamByJson() {
        log.info("==========开始调用==========");
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("PayInfoId","werwe");
        String result = HttpClientUtil.doPostJson(outSideUrlProperties.getTest2(),jsonObject.toJSONString());
        log.info(result);
        log.info("==========调用結束==========");
    }

    /**
     * 对称加密 [签名+密文]
     * JSON加密的密文字符串
     * MD5对(待签名字符串+密钥)进行加密生成签名,签名须统一转成小写字符,编码格式UTF-8
     * AES加密对(JSON报文+密钥)进行加密生成密文,编码格式UTF-8
     */
    @Test
    public void test(){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name","乔治");
        jsonObject.put("age","23");
        jsonObject.put("sysUserId","wejriowerweojos89723984asjdi");
        // 签名
        String sign = DigestUtil.md5Hex(jsonObject.toJSONString()+encodeProperties.getSecretKey());
        //密文
        String requestData = EncoderUtil.aesEncrypt(jsonObject.toJSONString(),encodeProperties.getAesKey());
        JSONObject jsonObject1 = new JSONObject();
        jsonObject1.put("signData", sign);
        jsonObject1.put("requestData", requestData);
        String result = HttpClientUtil.doPostJson(outSideUrlProperties.getTest3(),jsonObject1.toJSONString());
        log.info(result);
    }


    /**
     * 非对称加密 [签名+密文]
     * MD5对（待签名字符串+密钥）进行加密生成签名，签名须统一转成小写字符，编码格式UTF-8
     * RSA加密对（JSON报文+密钥）进行加密生成密文,编码格式UTF-8
     */
    @Test
    public void test2(){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name","乔治");
        jsonObject.put("age","23");
        jsonObject.put("sysUserId","wejriowerweojos89723984asjdi");
        // 签名
        String sign = DigestUtil.md5Hex(jsonObject.toJSONString()+encodeProperties.getSecretKey());
        //密文
        String requestData = EncoderUtil.rsaEncrypt(jsonObject.toJSONString(), CommonConstant.RSA_PUBLIC_KEY);
        JSONObject jsonObject1 = new JSONObject();
        jsonObject1.put("signData", sign);
        jsonObject1.put("requestData", requestData);
        String result = HttpClientUtil.doPostJson(outSideUrlProperties.getTest4(),jsonObject1.toJSONString());
        log.info(result);
    }

}

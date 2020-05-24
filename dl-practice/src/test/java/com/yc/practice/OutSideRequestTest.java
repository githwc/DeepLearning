package com.yc.practice;

import com.alibaba.fastjson.JSONObject;
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
     * JSON加密的密文字符串
     * 签名规则：将参与签名的参数（包含key=密钥），按照参数名根据ASCII码从小到大排序（字典序）；将参与签名的参数，调整为参数名=参数值，用“|”分隔连接成待签名字符串；如果参数的值为空不参与签名；参数名区分大小写；
     * MD5对（待签名字符串+密钥）进行加密生成签名；
     * AES加密对（JSON报文+密钥）进行加密生成密文
     */
    @Test
    public void test(){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name","乔治");
        jsonObject.put("age","23");
        jsonObject.put("sysUserId","wejriowerweojos89723984asjdi");
        // 签名
        String sign = EncoderUtil.md5(jsonObject.toJSONString()+encodeProperties.getSecretKey());
        JSONObject jsonObject1 = new JSONObject();
        Map<String,String> m = new HashMap<>();
        jsonObject1.put("singData", sign);
        jsonObject1.put("notifyData", jsonObject);
        String result = HttpClientUtil.doPostJson(outSideUrlProperties.getTest3(),jsonObject1.toJSONString());
        log.info(result);

    }


}

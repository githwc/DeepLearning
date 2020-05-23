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
        String result = HttpClientUtil.doPostJson(outSideUrlProperties.getTest3(),jsonObject1.toJSONString());
        log.info(result);

    }


}

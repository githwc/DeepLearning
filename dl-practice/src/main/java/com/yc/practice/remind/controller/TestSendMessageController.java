package com.yc.practice.remind.controller;

import com.alibaba.fastjson.JSONObject;
import com.yc.common.webSocket.WebSocket;
import com.yc.practice.common.UserUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 功能描述：
 * <p>版权所有：</p>
 * 未经本公司许可，不得以任何方式复制或使用本程序任何部分
 *
 * @Company: 紫色年华
 * @Author: xieyc
 * @Datetime: 2020-05-02
 * @Version: 1.0.0
 */
@RestController
@RequestMapping("/testMessage")
@Slf4j
public class TestSendMessageController {

    @Autowired
    private WebSocket webSocket;

    @Scheduled(cron="0 0/1 * * * ?")
    public void sendMessage(){
        log.info("开始推送");
        JSONObject obj = new JSONObject();
        //消息内容
            obj.put("content", "定向推送");
        boolean flag = webSocket.sendOneMessage("3946d9f631e3cd4619afcb9512842435", obj.toJSONString());
        log.info(flag ? "推送成功" : "推送失败");
    }

}

package com.yc.practice.timer.controller;

import com.alibaba.fastjson.JSONObject;
import com.yc.practice.timer.service.TimerRecordService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * 功能描述：定时器记录 控制层
 *
 *  <p>版权所有：</p>
 *  未经本人许可，不得以任何方式复制或使用本程序任何部分
 *
 * @Company: 紫色年华
 * @Author xieyc
 * @Date 2020-04-16
 * @Version: 1.0.0
 *
 */
@RestController
@RequestMapping("/timerRecord")
@Slf4j
public class TimerRecordController {

    @Autowired
    public TimerRecordService iTimerRecordService;

    // @Scheduled(cron="0 0/1 * * * ?")
    public void sendMessage(){
        log.info("开始推送");
        JSONObject obj = new JSONObject();
        //消息内容
        obj.put("content", "定向推送");
    }
}

package com.yc.practice.timer.controller;

import com.yc.practice.timer.service.TimerRecordService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * 功能描述:定时器记录 控制层
 *
 * @Author:  xieyc && 紫色年华
 * @Date 2020-04-16
 * @Version: 1.0.0
 */
@RestController
@RequestMapping("/timerRecord")
@Slf4j
public class TimerRecordController {

    private final TimerRecordService service;

    @Autowired
    public TimerRecordController (TimerRecordService service){
        this.service = service;
    }

    /**
     * 超时订单取消
     */
    @Scheduled(cron="0 0/5 * * * ?")
    public void orderCheck(){
        service.orderCheck();
    }
}

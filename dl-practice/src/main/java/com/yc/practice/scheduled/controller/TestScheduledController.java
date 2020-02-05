package com.yc.practice.scheduled.controller;

import com.yc.practice.scheduled.service.ScheduledService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * 功能描述：测试定时任务
 * <p>版权所有：</p>
 * 未经本人许可，不得以任何方式复制或使用本程序任何部分
 *
 * @Company: 紫色年华
 * @Author: xieyc
 * @Datetime: 2019-10-09
 * @Version: 1.0.0
 */
@Component
@EnableAsync
@Slf4j
public class TestScheduledController {

    private final ScheduledService scheduledService;

    @Autowired
    public TestScheduledController(ScheduledService scheduledService) {
        this.scheduledService = scheduledService;
    }

    // @Scheduled(cron="0 */1 * * * ?")
    public void first() throws InterruptedException {
        log.info("第一个定时任务开始 : " + LocalDateTime.now().toLocalTime() + "\r\n线程 : " + Thread.currentThread().getName());
        // Thread.sleep(1000 * 10);
        // 定向推送消息
        scheduledService.pushMessage();
    }

    // @Async
    // @Scheduled(fixedDelay = 2000)
    // public void second() {
    //     log.info("第二个定时任务开始 : " + LocalDateTime.now().toLocalTime() + "\r\n线程 : " + Thread.currentThread().getName());
    //     ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(5);
    //     for (int i = 0; i < 10; i++) {
    //         scheduledThreadPool.schedule(new Runnable() {
    //             public void run() {
    //                 log.info("delay 3 seconds=========================="  +  Thread.currentThread().getId() + Thread.currentThread().getName());
    //             }
    //         }, 3, TimeUnit.SECONDS);
    //     }
    // }
}

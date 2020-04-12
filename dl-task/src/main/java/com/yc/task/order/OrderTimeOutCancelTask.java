package com.yc.task.order;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * 功能描述：
 * <p>版权所有：</p>
 * 未经本人许可，不得以任何方式复制或使用本程序任何部分
 *
 * @Company: 紫色年华
 * @Author: xieyc
 * @Datetime: 2020-04-04
 * @Version: 1.0.0
 */
@Slf4j
@Component
public class OrderTimeOutCancelTask {

    @Scheduled(cron="0 0/1 * * * ?")
    private void cancelTimeOutOrder() {
        System.out.println(LocalDateTime.now());
        System.out.println(System.currentTimeMillis());
        System.out.println(System.currentTimeMillis());
        log.info("取消订单，并根据sku编号释放锁定库存");
    }
}

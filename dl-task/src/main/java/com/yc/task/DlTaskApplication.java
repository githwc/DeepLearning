package com.yc.task;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;


/**
 * 功能描述：定时任务启动类
 *
 * <p>版权所有：</p>
 * 未经本人许可，不得以任何方式复制或使用本程序任何部分
 *
 * @Company: 紫色年华
 * @Author:  xieyc
 * @Datetime: 2020-04-04
 */
@SpringBootApplication
// @Configuration
@EnableScheduling
public class DlTaskApplication {

    public static void main(String[] args) {
        SpringApplication.run(DlTaskApplication.class, args);
    }

}

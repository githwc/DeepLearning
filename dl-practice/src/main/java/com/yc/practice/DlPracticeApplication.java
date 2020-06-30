package com.yc.practice;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * 功能描述:
 * 主程序类、主入口类
 *
 * @Author:  xieyc && 紫色年华
 * @Date: 2019-05-30
 */
@SpringBootApplication
@EnableScheduling
@ComponentScan({"com.yc"})
@MapperScan({"com.yc.core.*.mapper"})
public class DlPracticeApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(DlPracticeApplication.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder){
        return builder.sources(DlPracticeApplication.class);
    }

    // TODO: 2020/5/24 秒杀
    // TODO: 2020/5/24 优惠券
}

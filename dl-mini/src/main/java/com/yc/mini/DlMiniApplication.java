package com.yc.mini;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * 主程序启动类
 *
 * @author xieyc
 * @date 2020/6/27
 */
@SpringBootApplication
@EnableScheduling
@ComponentScan({"com.yc"})
@MapperScan({"com.yc.core.*.mapper"})
public class DlMiniApplication  extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(DlMiniApplication.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder){
        return builder.sources(DlMiniApplication.class);
    }
}

package com.yc.practice;

import com.yc.common.utils.LocalHostUtil;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.env.Environment;

/**
 * 功能描述：
 * 主程序类、主入口类
 *      @SpringBootApplication：标注一个主程序类，说明这是一个Spring Boot 应用
 *          @Target({ElementType.TYPE})
 *          @Retention(RetentionPolicy.RUNTIME)
 *          @Documented
 *          @Inherited
 *          @SpringBootConfiguration:Spring Boot的配置类
 *          @EnableAutoConfiguration：
 *              开启自动配置功能
 *              将主配置类（@SpringBootApplication标注的类）的所在包及下面所有子包里面的所有组件扫描到Spring容器；
 *          @ComponentScan
 *
 * <p>版权所有：</p>
 * 未经本人许可，不得以任何方式复制或使用本程序任何部分
 *
 * @Company: 紫色年华
 * @Author:  xieyc
 * @Datetime: 2019-05-30
 */
@SpringBootApplication
@ComponentScan({"com.yc"})
@MapperScan({"com.yc.core.*.mapper"})
@Slf4j
public class DlPracticeApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext application = SpringApplication.run(DlPracticeApplication.class, args);
        Environment env = application.getEnvironment();
        String ip = LocalHostUtil.getIpAddress();
        String port = env.getProperty("server.port");
        String path = env.getProperty("server.servlet.context-path");
        log.info("\n----------------------------------------------------------\n\t" +
                "Application LionHerding is running! Access URLs:\n\t" +
                "Local: \t\thttp://localhost:" + port + path + "/\n\t" +
                "External: \thttp://" + ip + ":" + port + path + "/\n\t" +
                "swagger-ui: http://" + ip + ":" + port + path + "/swagger-ui.html\n\t" +
                "----------------------------------------------------------");
    }

}

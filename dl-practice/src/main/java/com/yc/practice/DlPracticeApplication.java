package com.yc.practice;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

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
 *      @ComponentScan: 扫描bean
 *      @MapperScan: 扫描mapper接口文件
 *      @EnableConfigurationProperties: 使使用 @ConfigurationProperties 注解的类生效。
 *      @EnableTransactionManagement(proxyTargetClass = true)
 *
 * <p>版权所有：</p>
 * 未经本人许可，不得以任何方式复制或使用本程序任何部分
 *
 * @Company: 紫色年华
 * @Author:  xieyc
 * @Datetime: 2019-05-30
 */
@EnableScheduling
@SpringBootApplication
@ComponentScan({"com.yc"})
@MapperScan({"com.yc.core.*.mapper"})
public class DlPracticeApplication  extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(DlPracticeApplication.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder){
        return builder.sources(DlPracticeApplication.class);
    }
}

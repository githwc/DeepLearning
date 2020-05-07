package com.yc.practice.config.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

/**
 * 功能描述：application 级别,进行全局配置
 * <p>版权所有：</p>
 * 未经本公司许可，不得以任何方式复制或使用本程序任何部分
 *
 * @Company: 紫色年华
 * @Author: xieyc
 * @Datetime: 2020-05-07
 * @Version: 1.0.0
 */
// @Component
@Slf4j
public class ApplicationSelfListener implements ApplicationListener<ContextRefreshedEvent> {

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        log.info("================= 初始化信息 ====================");
        log.info("================= 初始化信息 ====================");
        // 获取到 application 上下文
        // ApplicationContext applicationContext = contextRefreshedEvent.getApplicationContext();
        // 获取对应的 service
        // SysUserService userService = applicationContext.getBean(SysUserService.class);
        // SysUser user = userService.getById("1eb30c2c2b10cdfa667c1f4f03b34908");
        // 获取 application 域对象，将查到的信息放到 application 域中
        // ServletContext application = applicationContext.getBean(ServletContext.class);
        // application.setAttribute("user", user);
    }
}

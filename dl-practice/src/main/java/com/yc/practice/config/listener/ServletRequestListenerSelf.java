package com.yc.practice.config.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.http.HttpServletRequest;

/**
 * 功能描述：request 级别监听器，针对每一个客户请求，
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
public class ServletRequestListenerSelf implements ServletRequestListener {

    @Override
    public void requestInitialized(ServletRequestEvent sre) {
        HttpServletRequest request = (HttpServletRequest) sre.getServletRequest();
        log.info("request url为：{}", request.getRequestURL());
    }


    @Override
    public void requestDestroyed(ServletRequestEvent sre) {
        log.info(" request监听器：销毁");
    }

}

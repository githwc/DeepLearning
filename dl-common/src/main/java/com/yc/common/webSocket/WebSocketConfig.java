package com.yc.common.webSocket;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

/**
 * 功能描述:websocket 配置类
 *
 * @Author: xieyc && 紫色年华
 * @Date: 2019-10-08
 * @Version: 1.0.0
 */
// @Configuration
public class WebSocketConfig {

    /**
     *     注入ServerEndpointExporter，
     *     这个bean会自动注册使用了@ServerEndpoint注解声明的Websocket endpoint
     */
    @Bean
    public ServerEndpointExporter serverEndpointExporter() {
        return new ServerEndpointExporter();
    }
}

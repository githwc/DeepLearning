package com.yc.practice.redis.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;

/**
 * 功能描述:监听通道信息
 *
 * @Author: xieyc && 紫色年华
 * @Date: 2020-01-29
 * @Version: 1.0.0
 */
@Configuration
public class ChannelMessageConfig {

    @Bean
    MessageListenerAdapter listenerAdapter() {
        return new MessageListenerAdapter(new MyRedisChannelListener());
    }

    @Bean
    RedisMessageListenerContainer container(RedisConnectionFactory connectionFactory, MessageListenerAdapter listenerAdapter) {
        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        //可以添加多个 messageListener
        container.addMessageListener(listenerAdapter, new PatternTopic("goodLucky*"));
        container.addMessageListener(listenerAdapter, new PatternTopic("index"));
        return container;
    }

}


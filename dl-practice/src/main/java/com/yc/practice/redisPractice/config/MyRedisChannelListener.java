package com.yc.practice.redisPractice.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;

import java.io.UnsupportedEncodingException;

/**
 * 功能描述：订阅频道的消息
 *
 * <p>版权所有：</p>
 * 未经本人许可，不得以任何方式复制或使用本程序任何部分
 *
 * @Company: 紫色年华
 * @Author: xieyc
 * @Datetime: 2020-01-29
 * @Version: 1.0.0
 */
@Slf4j
public class MyRedisChannelListener implements MessageListener {

    @Override
    public void onMessage(Message message, byte[] bytes) {
        log.info("============>>>>> onMessage");
        byte[] channel=message.getChannel();
        byte[] body=message.getBody();

        try {
            String title=new String(channel,"UTF-8");
            String content=new String(body,"UTF-8");

            log.info("消息频道名称："+title);
            log.info("消息内容是:"+content);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

    }

}

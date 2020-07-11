package com.yc.common.propertie;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 微信小程序配置
 *
 * @author xieyc
 * @date 2019-07-10
 */
@Component
@ConfigurationProperties(prefix = "wx.miniapp")
@Data
public class WxMaProperties {
    /**
     * 设置微信小程序的appid
     */
    private String appid;
    /**
     * 小程序的名称
     */
    private String appname;
    /**
     * 设置微信小程序的Secret
     */
    private String secret;
    /**
     * 设置微信小程序消息服务器配置的token
     */
    private String token;
    /**
     * 设置微信小程序消息服务器配置的EncodingAESKey
     */
    private String aesKey;
    /**
     * 消息格式，XML或者JSON
     */
    private String msgDataFormat;
}

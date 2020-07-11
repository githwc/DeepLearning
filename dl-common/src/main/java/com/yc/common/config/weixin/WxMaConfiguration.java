package com.yc.common.config.weixin;

import cn.binarywang.wx.miniapp.api.WxMaMsgService;
import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.api.impl.WxMaMsgServiceImpl;
import cn.binarywang.wx.miniapp.api.impl.WxMaServiceImpl;
import cn.binarywang.wx.miniapp.config.impl.WxMaDefaultConfigImpl;
import com.yc.common.propertie.WxMaProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 功能描述: 微信小程序配置初始化
 *
 * @Author: xieyc && 紫色年华
 * @Date: 2020-07-10
 * @Version: 1.0.0
 */
@Configuration
public class WxMaConfiguration {

    private final WxMaProperties wxMaProperties;

    @Autowired
    public WxMaConfiguration(WxMaProperties wxMaProperties) {
        this.wxMaProperties = wxMaProperties;
    }

    @Bean
    public WxMaService getWxMaService() {
        WxMaDefaultConfigImpl config = new WxMaDefaultConfigImpl();
        config.setAppid(wxMaProperties.getAppid());
        config.setSecret(wxMaProperties.getSecret());
        config.setToken(wxMaProperties.getToken());
        config.setAesKey(wxMaProperties.getAesKey());
        config.setMsgDataFormat(wxMaProperties.getMsgDataFormat());

        WxMaService service = new WxMaServiceImpl();
        service.setWxMaConfig(config);

        return service;
    }


    @Bean
    public WxMaMsgService getWxMaMsgService() {
        return new WxMaMsgServiceImpl(this.getWxMaService());
    }
}

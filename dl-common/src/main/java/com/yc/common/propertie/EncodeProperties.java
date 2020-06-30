package com.yc.common.propertie;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 功能描述: 密钥配置
 *
 * @Author: xieyc && 紫色年华
 * @Date: 2020-05-23
 * @Version: 1.0.0
 */
@Data
@Component
@ConfigurationProperties(prefix = "dl.encode")
public class EncodeProperties {

    /**
     * 密钥
     */
    private String secretKey;

    /**
     * AesKey
     */
    private String aesKey;
}

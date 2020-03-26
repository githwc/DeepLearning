package com.yc.common.security;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 功能描述：安全配置
 * <p>版权所有：</p>
 * 未经本人许可，不得以任何方式复制或使用本程序任何部分
 *
 * @Company: 紫色年华
 * @Author: xieyc
 * @Datetime: 2020-03-23
 * @Version: 1.0.0
 */
@Component
@ConfigurationProperties(prefix = "dl.security")
@Data
public class SecurityProperties {

    /**
     * jwt加密密钥
     */
    private String jwtSecret;

    /**
     * jwt token 有效时间（毫秒）
     * 默认30分钟
     */
    private Integer jwtActiveTime = 1800000;

    /**
     * jwt token 续签时间（毫秒）
     * 默认20分钟后开始续签
     */
    private Integer jwtRenewTime = 1200000;

    /**
     * 安全校验排除资源路径
     */
    private String[] excludes;

    /**
     * 默认登陆账号
     * 该属性在环境为开发环境时生效，将前端接口改为此账号登陆
     */
    private String defaultLoginName;
}

package com.yc.common.propertie;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 功能描述：
 * <p>版权所有：</p>
 * 未经本人许可，不得以任何方式复制或使用本程序任何部分
 *
 * @Company: 紫色年华
 * @Author: xieyc
 * @Datetime: 2020-05-23
 * @Version: 1.0.0
 */
@Data
@Component
@ConfigurationProperties(prefix = "dl.url")
public class OutSideUrlProperties {

    private String test1;

    private String test2;

    private String test3;

    private String test4;
}

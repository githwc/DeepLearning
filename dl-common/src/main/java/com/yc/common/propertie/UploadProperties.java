package com.yc.common.propertie;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 功能描述:上传文件配置
 *
 * @Author: xieyc && 紫色年华
 * @Date: 2020-04-26
 * @Version: 1.0.0
 */
@Data
@Component
@ConfigurationProperties(prefix = "dl.upload")
public class UploadProperties {

    /**
     * 图片保存路径
     */
    private String imgSavePath;

    /**
     * 图片访问路径
     */
    private String imgAccessPath;

    /**
     * 文件保存路径
     */
    private String fileSavePath;

    /**
     * 文件访问路径
     */
    private String fileAccessPath;


}

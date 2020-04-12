package com.yc.common.propertie;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 功能描述：上传属性
 * <p>版权所有：</p>
 * 未经本人许可，不得以任何方式复制或使用本程序任何部分
 *
 * @Company: 紫色年华
 * @Author: xieyc
 * @Datetime: 2020-04-06
 * @Version: 1.0.0
 */
@Component
@ConfigurationProperties(prefix = "dl.upload")
public class UploadProperty {

    /**
     * 上传服务请求地址
     */
    private String serverUrl;
    /**
     * 上传至服务器的根路径
     */
    private String rootPath;
    /**
     * 临时目录项目名称
     */
    private String tempProjectName = "dlupload";


    public String getRootPath() {
        return rootPath;
    }

    public void setRootPath(String rootPath) {
        this.rootPath = rootPath;
    }

    public String getTempProjectName() {
        return tempProjectName;
    }

    public void setTempProjectName(String tempProjectName) {
        this.tempProjectName = tempProjectName;
    }

    public String getServerUrl() {
        return serverUrl;
    }

    public void setServerUrl(String serverUrl) {
        this.serverUrl = serverUrl;
    }
}

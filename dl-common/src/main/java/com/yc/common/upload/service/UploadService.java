package com.yc.common.upload.service;

import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

/**
 * 功能描述：
 * <p>版权所有：</p>
 * 未经本公司许可，不得以任何方式复制或使用本程序任何部分
 *
 * @Company: 紫色年华
 * @Author: xieyc
 * @Datetime: 2020-04-27
 * @Version: 1.0.0
 */
public interface UploadService {

    /**
     * 上传单张图片
     * @param request
     * @param file
     * @return
     */
    String uploadImg(HttpServletRequest request, MultipartFile file);
}

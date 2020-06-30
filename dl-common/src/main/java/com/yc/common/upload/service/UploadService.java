package com.yc.common.upload.service;

import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

/**
 * 功能描述:
 *
 * @Author: xieyc && 紫色年华
 * @Date: 2020-04-27
 * @Version: 1.0.0
 */
public interface UploadService {

    /**
     * 上传单张图片
     * @param request 请求信息
     * @param file 图片
     * @return 路径
     */
    String uploadImg(HttpServletRequest request, MultipartFile file);

    /**
     * 上传单文件
     * @param request 请求信息
     * @param file 文件
     * @return 路径
     */
    String uploadFile(HttpServletRequest request, MultipartFile file);

}

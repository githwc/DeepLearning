package com.yc.common.upload.controller;

import com.yc.common.propertie.UploadProperties;
import com.yc.common.upload.service.UploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

/**
 * 功能描述：上传文件 控制层
 * <p>版权所有：</p>
 * 未经本公司许可，不得以任何方式复制或使用本程序任何部分
 *
 * @Company: 紫色年华
 * @Author: xieyc
 * @Datetime: 2020-04-26
 * @Version: 1.0.0
 */
@RestController
@RequestMapping("/upload")
public class UploadController {


    private final UploadService uploadService;

    @Autowired
    public UploadController(UploadService uploadService){
        this.uploadService = uploadService;
    }

    /**
     * 图片上传(单张)
     * @param request 请求信息
     * @param file 文件
     * @return 文件名称
     */
    @PostMapping("/img")
    public String uploadImg(HttpServletRequest request,
                         @RequestParam(name = "file") MultipartFile file) {

        return uploadService.uploadImg(request,file);
    }


}

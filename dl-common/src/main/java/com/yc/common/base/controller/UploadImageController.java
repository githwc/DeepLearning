package com.yc.common.base.controller;

import com.yc.common.base.upload.Upload;
import com.yc.common.global.error.Error;
import com.yc.common.global.error.ErrorException;
import com.yc.common.base.upload.UploadResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * 功能描述：图片上传控制层
 * <p>版权所有：</p>
 * 未经本人许可，不得以任何方式复制或使用本程序任何部分
 *
 * @Company: 紫色年华
 * @Author: xieyc
 * @Datetime: 2020-04-06
 * @Version: 1.0.0
 */
@RestController
@RequestMapping("/upload/image")
public class UploadImageController {

    /**
     * 单图片上传
     * @param file 上传图片
     * @return
     */
    @PostMapping("/single")
    public String single(MultipartFile file) {
        if (file == null) {
            throw new ErrorException(Error.FileNotFound);
        }
        UploadResponse response = Upload.file(file).start();
        return response.url();
    }


}

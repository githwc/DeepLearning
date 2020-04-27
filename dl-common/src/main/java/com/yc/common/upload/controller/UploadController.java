package com.yc.common.upload.controller;

import com.yc.common.global.error.Error;
import com.yc.common.global.error.ErrorException;
import com.yc.common.propertie.UploadProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.UUID;

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

    private final UploadProperties uploadProperties;

    @Autowired
    public UploadController(UploadProperties uploadProperties){
        this.uploadProperties = uploadProperties;
    }
    @PostMapping("/img")
    public String upload(@RequestParam(name = "file") MultipartFile file,
                             HttpServletRequest request) {
        if (file == null) {
            throw new ErrorException(Error.UploadImgError);
        }
        if (file.getSize() > 1024 * 1024 * 10) {
            throw new ErrorException(200,50001,"文件大小不能大于10M");
        }
        //获取文件后缀
        String suffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".") + 1, file.getOriginalFilename().length());
        if (!"jpg,jpeg,gif,png".toUpperCase().contains(suffix.toUpperCase())) {
            throw new ErrorException(Error.ImgFormatError);
        }
        String savePath = uploadProperties.getImgFilePath();
        File savePathFile = new File(savePath);
        if (!savePathFile.exists()) {
            //若不存在该目录，则创建目录
            savePathFile.mkdir();
        }
        //通过UUID生成唯一文件名
        String filename = UUID.randomUUID().toString().replaceAll("-","") + "." + suffix;
        try {
            //将文件保存指定目录
            file.transferTo(new File(savePath + filename));
        } catch (Exception e) {
            e.printStackTrace();
            // 保存文件异常
            throw new ErrorException(Error.UserExisted);
        }
        //返回文件名称
        return filename;
    }

}

package com.yc.common.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * 功能描述：Spring文件上传工具类
 * <p>版权所有：</p>
 * 未经本人许可，不得以任何方式复制或使用本程序任何部分
 *
 * @Company: 紫色年华
 * @Author: xieyc
 * @Datetime: 2020-04-07
 * @Version: 1.0.0
 */
public class MultipartFileUtil {

    private static Logger log = LoggerFactory.getLogger(MultipartFileUtil.class);

    /**
     * spring file to java file
     *
     * @param file
     * @return
     */
    public static File toFile(MultipartFile file) {
        String fileName = file.getOriginalFilename();
        String fileExt = DlFileUtil.getFileExtension(fileName);
        try {
            File tempFile = File.createTempFile("temp" + RandomUtil.getUUID(),
                    DlFileUtil.FILE_EXTENSION_SEPARATOR + fileExt);
            file.transferTo(tempFile);
            return tempFile;
        } catch (IOException e) {
            log.error("MultipartFile文件对象转File时出错", e);
        }
        return null;
    }
}

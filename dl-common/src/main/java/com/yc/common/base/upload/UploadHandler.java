package com.yc.common.base.upload;

import cn.hutool.core.io.FileTypeUtil;
import cn.hutool.core.io.FileUtil;
import com.google.common.collect.Multimap;
import net.coobird.thumbnailator.Thumbnails;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

/**
 * 功能描述：上传文件后对文件的处理类
 * <p>版权所有：</p>
 * 未经本人许可，不得以任何方式复制或使用本程序任何部分
 *
 * @Company: 紫色年华
 * @Author: xieyc
 * @Datetime: 2020-04-07
 * @Version: 1.0.0
 */
public class UploadHandler {

    /**
     * 图片上传后对图片进行处理
     *
     * @param multimap
     */
    static void handle(Multimap<String, File> multimap) {
        List<File> handleFiles = new LinkedList<>();
        Collection<File> imageFiles = multimap.get("image");
        for (File imageFile : imageFiles) {
            try {
                File tempFile = FileUtil.createTempFile("temp", "." + FileTypeUtil.getType(imageFile),
                        FileUtils.getTempDirectory(), true);
                // 对图片进行压缩
                Thumbnails.of(imageFile).scale(1).toFile(tempFile);
                handleFiles.add(tempFile);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        multimap.removeAll("image");
        multimap.putAll("image", handleFiles);
    }

}

package com.yc.common.base.upload;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.IdUtil;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import com.yc.common.global.error.Error;
import com.yc.common.global.error.ErrorException;
import com.yc.common.propertie.UploadProperty;
import com.yc.common.utils.Base64FileUtil;
import com.yc.common.utils.DlFileUtil;
import com.yc.common.utils.MultipartFileUtil;
import okhttp3.*;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * 功能描述：
 * <p>版权所有：</p>
 * 未经本人许可，不得以任何方式复制或使用本程序任何部分
 *
 * @Company: 紫色年华
 * @Author: xieyc
 * @Datetime: 2020-04-06
 * @Version: 1.0.0
 */
public class Upload {

    private static UploadProperty uploadProperty;
    private static Logger log = LoggerFactory.getLogger(Upload.class);

    private OkHttpClient okHttpClient = new OkHttpClient();
    private Request request;
    private RequestBody requestBody;

    @Autowired
    private void setHaitaoUploadProperty(UploadProperty uploadProperty) {
        Upload.uploadProperty = uploadProperty;
    }
    /**
     * Upload类兼容文件上传及上传文件删除的工作，标示当前操作状态
     * 1:文件上传
     * 0:上传文件删除
     */
    private int handleMethod = 1;
    /**
     * 上传文件根目录
     */
    private String rootPath;

    private Multimap<String, File> multimap = ArrayListMultimap.create();

    /**
     * 添加上传文件对象
     *
     * @param files
     * @return
     */
    public static Upload file(File... files) {
        Upload upload = new Upload();
        for (File file : files) {
            upload.multimap.put("file", file);
        }
        return upload;
    }

    /**
     * 添加上传Spring文件对象
     *
     * @param files
     * @return
     */
    public static Upload file(MultipartFile... files) {
        Upload upload = new Upload();
        for (MultipartFile file : files) {
            File tempFile = MultipartFileUtil.toFile(file);
            upload.multimap.put("file", tempFile);
        }
        return upload;
    }

    /**
     * 添加上传图片文件对象
     *
     * @param files
     * @return
     */
    public static Upload image(File... files) {
        Upload upload = new Upload();
        for (File file : files) {
            upload.multimap.put("image", file);
        }
        return upload;
    }

    /**
     * 添加Spring文件上传对象
     *
     * @param files
     * @return
     */
    public static Upload image(MultipartFile... files) {
        Upload upload = new Upload();
        for (MultipartFile file : files) {
            File tempFile = MultipartFileUtil.toFile(file);
            upload.multimap.put("image", tempFile);
        }
        return upload;
    }

    /**
     * 添加base64图片上传对象，文件名根据base64获得
     *
     * @param base64
     * @return
     */
    public static Upload base64Image(String base64) {
        if (base64.isEmpty()) {
            return new Upload();
        }
        String format;
        if (base64.contains("data:image/") && base64.contains(";base64,")) {
            format = base64.substring(base64.indexOf("data:image/") + "data:image/".length(), base64.indexOf(";base64,"));
        } else {
            format = ".png";
        }
        return base64Image(base64, IdUtil.simpleUUID() + "." + format);
    }

    /**
     * 添加base64图片上传对象，需指定上传的图片名称含后缀
     *
     * @param base64
     * @param fileName
     * @return
     */
    public static Upload base64Image(String base64, String fileName) {
        if (base64.isEmpty()) {
            return new Upload();
        }
        String[] base64s = new String[]{base64};
        String[] fileNames = new String[]{fileName};
        return base64Image(base64s, fileNames);
    }

    /**
     * 添加base64多图片上传对象，base64和文件名需要一一对应
     *
     * @param base64s
     * @param fileNames
     * @return
     */
    public static Upload base64Image(String[] base64s, String[] fileNames) {
        Upload upload = new Upload();
        if (base64s.length != fileNames.length) {
            throw new ErrorException(Error.ParameterNotFound, "Base64上传的图片与上传图片文件名不对应");
        }
        for (int i = 0; i < base64s.length; i++) {
            if (base64s[i].isEmpty()) {
                continue;
            }
            String fileName = fileNames[i];
            String prefix = fileName.substring(0, fileName.lastIndexOf("."));
            String suffix = fileName.substring(fileName.lastIndexOf(".") + 1);

            String filePath =
                    FileUtil.createTempFile(prefix, "." + suffix, DlFileUtil.getTempDirectory(), true).getPath();
            Base64FileUtil.base64ToFile(base64s[i], filePath);
            upload.multimap.put("image", new File(filePath));
        }
        return upload;
    }

    /**
     * 添加base64文件上传对象，和图片的区别在于文件不进行压缩
     *
     * @param base64
     * @param fileName
     * @return
     */
    public static Upload base64File(String base64, String fileName) {
        if (base64.isEmpty()) {
            return new Upload();
        }
        String[] base64s = new String[]{base64};
        String[] fileNames = new String[]{fileName};
        return base64File(base64s, fileNames);
    }

    /**
     * 添加base64多文件上传对象，base64和文件名需要一一对应
     *
     * @param base64s
     * @param fileNames
     * @return
     */
    public static Upload base64File(String[] base64s, String[] fileNames) {
        Upload upload = new Upload();
        if (base64s.length != fileNames.length) {
            throw new ErrorException(Error.ParameterNotFound, "Base64上传的文件与上传文件文件名不对应");
        }
        for (int i = 0; i < base64s.length; i++) {
            if (base64s[i].isEmpty()) {
                continue;
            }
            String fileName = fileNames[i];
            String prefix = fileName.substring(0, fileName.lastIndexOf("."));
            String suffix = fileName.substring(fileName.lastIndexOf(".") + 1);
            String filePath =
                    FileUtil.createTempFile(prefix, "." + suffix, DlFileUtil.getTempDirectory(), true).getPath();
            Base64FileUtil.base64ToFile(base64s[i], filePath);
            upload.multimap.put("file", new File(filePath));
        }
        return upload;
    }

    /**
     * 自定义上传服务器的根路径，从/开始
     *
     * @param rootPath
     * @return
     */
    public Upload rootPath(String rootPath) {
        this.rootPath = rootPath;
        return this;
    }

    /**
     * 可自定义设置上传内容，但是注意服务器只能接收文件类型
     *
     * @param requestBody
     */
    public void requestBody(RequestBody requestBody) {
        if (requestBody != null) {
            this.requestBody = requestBody;
        }
    }

    /**
     * 删除服务器文件，可传递多个路径批量删除
     *
     * @param paths 从rootpath之后的文件路径
     *              如：/yjtong/image/2018/10/11/1.jpg
     * @return
     */
    public static Upload delete(String... paths) {
        Upload upload = new Upload();
        // 设置upload处理方式为删除服务器文件
        upload.handleMethod = 0;

        if (StringUtils.isBlank(upload.rootPath)) {
            upload.rootPath = uploadProperty.getRootPath();
        }
        JSONObject body = new JSONObject();
        body.put("rootPath", upload.rootPath);
        body.put("paths", paths);

        upload.requestBody = RequestBody.create(MediaType.parse("application/json"), body.toJSONString());
        upload.request = new Request.Builder().url(uploadProperty.getServerUrl() + "/delete/files")
                .post(upload.requestBody).build();
        return upload;
    }

    /**
     * 开始上传
     *
     * @return
     */
    public UploadResponse start() {
        if (StringUtils.isBlank(rootPath)) {
            rootPath = uploadProperty.getRootPath();
        }

        // 文件上传
        if (this.handleMethod == 1) {
            UploadHandler.handle(multimap);
            MultipartBody.Builder multipartBodyBuilder = new MultipartBody.Builder()
                    .setType(MultipartBody.FORM)
                    .addFormDataPart("rootPath", rootPath);

            if (!multimap.isEmpty()) {
                for (File value : multimap.values()) {
                    String fileName = value.getName();
                    multipartBodyBuilder.addFormDataPart("files", fileName,
                            RequestBody.create(MediaType.parse("application/octet-stream"), value));
                }
                // 设置文件上传body
                this.requestBody(multipartBodyBuilder.build());
                // 生成request对象，准备执行
                request = new Request.Builder()
                        .url(uploadProperty.getServerUrl() + "/upload/files")
                        .post(requestBody).build();
            } else {
                throw new ErrorException(Error.ParameterNotFound, "上传文件不存在");
            }
        } else if (this.handleMethod != 0) {
            // !=0 标示除了删除方法的其他上传方法
            request = new Request.Builder()
                    .url(uploadProperty.getServerUrl() + "/upload/files")
                    .post(requestBody).build();
        }

        try {
            if (requestBody == null) {
                throw new NullPointerException("上传对象的RequestBody是空");
            }
            if (request == null) {
                throw new NullPointerException("Okhttp对象是空的");
            }
            Response response = okHttpClient.newCall(request).execute();
            return new UploadResponse(response);
        } catch (IOException e) {
            log.error("upload remove server error", e);
        }
        return null;
    }

}

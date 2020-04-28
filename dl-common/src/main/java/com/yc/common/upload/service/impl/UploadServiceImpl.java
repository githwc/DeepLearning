package com.yc.common.upload.service.impl;

import com.yc.common.constant.CommonConstant;
import com.yc.common.global.error.Error;
import com.yc.common.global.error.ErrorException;
import com.yc.common.propertie.UploadProperties;
import com.yc.common.upload.service.UploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

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
@Service
@Transactional(rollbackFor = Exception.class)
public class UploadServiceImpl implements UploadService {

    private final RedisTemplate redisTemplate;
    private final UploadProperties uploadProperties;

    @Autowired
    public UploadServiceImpl(UploadProperties uploadProperties,RedisTemplate redisTemplate){
        this.uploadProperties = uploadProperties;
        this.redisTemplate = redisTemplate;
    }


    @Override
    public String uploadImg(HttpServletRequest request, MultipartFile file) {
        if (file == null) {
            throw new ErrorException(Error.UploadImgError);
        }
        if (file.getSize() > 1024 * 1024 * 10) {
            throw new ErrorException(200,50001,"文件大小不能大于10M");
        }
        //获取文件后缀
        String suffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".") + 1);
        if (!CommonConstant.IMG_FORMAT.toUpperCase().contains(suffix.toUpperCase())) {
            throw new ErrorException(Error.ImgFormatError);
        }
        String savePath = uploadProperties.getImgFilePath();
        File savePathFile = new File(savePath);
        if (!savePathFile.exists()) {
            //若不存在该目录，则创建目录
            savePathFile.mkdir();
        }
        //通过日期加自增序号生成唯一文件名
        String filename = generateOrderNo() + "." + suffix;
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


    /**
     * 生成唯一文件名称
     *  [20200427171843000001]
     * @return 20位数字[当前时间(毫秒) + 自增id]
     */
    private String generateOrderNo(){
        StringBuilder sb = new StringBuilder();
        Long nowLong = Long.parseLong(new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()));
        sb.append(nowLong.toString());
        String date = new SimpleDateFormat("yyyyMMddHHmm").format(new Date());
        String key = CommonConstant.ORDER_NO_TODAY_CACHE + date;
        Long increment = redisTemplate.opsForValue().increment(key,1);
        String incrementStr = increment.toString();
        if (incrementStr.length() <= 6) {
            sb.append(String.format("%06d", increment));
        } else {
            sb.append(incrementStr);
        }
        return sb.toString();
    }


}

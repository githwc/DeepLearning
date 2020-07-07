package com.yc.practice.config.security.service.impl;

import cn.hutool.core.util.IdUtil;
import com.alibaba.fastjson.JSONObject;
import com.yc.common.utils.Base64FileUtil;
import com.yc.common.utils.ImageValidateUtil;
import com.yc.practice.config.security.service.LoginService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * 功能描述:
 *
 * @Author: xieyc && 紫色年华
 * @Date: 2020-04-24
 * @Version: 1.0.0
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class LoginServiceImpl implements LoginService {

    private final RedisTemplate<String, String> redisTemplate;

    @Autowired
    public LoginServiceImpl(RedisTemplate<String, String> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Override
    public JSONObject getImageVerify() {
        String verifyKey = IdUtil.simpleUUID();
        String verifyCode = ImageValidateUtil.generateVerifyCode(4);
        int w = 200, h = 80;
        String fileName = IdUtil.simpleUUID();
        String tempPath = System.getProperty("java.io.tmpdir");
        String filePath = StringUtils.join(tempPath, File.separator, fileName, ".jpg");
        File file = new File(filePath);
        try {
            ImageValidateUtil.outputImage(w, h, file, verifyCode);
            redisTemplate.opsForValue().set(verifyKey, verifyCode, 5, TimeUnit.MINUTES);
            String verifyFile = Base64FileUtil.fileToBase64ByLocal(filePath);
            return new JSONObject()
                    .fluentPut("verifyKey", verifyKey)
                    .fluentPut("verifyFile", verifyFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}

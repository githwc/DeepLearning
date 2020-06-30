package com.yc.practice.config.security.service;

import com.alibaba.fastjson.JSONObject;

/**
 * 功能描述:
 *
 * @Author: xieyc && 紫色年华
 * @Date: 2020-04-24
 * @Version: 1.0.0
 */
public interface LoginService {

    /**
     * 获取图片验证码
     * @return 验证码
     */
    JSONObject getImageVerify();
}

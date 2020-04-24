package com.yc.practice.config.security.service;

import com.alibaba.fastjson.JSONObject;

/**
 * 功能描述：
 * <p>版权所有：</p>
 * 未经本公司许可，不得以任何方式复制或使用本程序任何部分
 *
 * @Company: 紫色年华
 * @Author: xieyc
 * @Datetime: 2020-04-24
 * @Version: 1.0.0
 */
public interface LoginService {

    /**
     * 获取图片验证码
     * @return 验证码
     */
    JSONObject getImageVerify();
}

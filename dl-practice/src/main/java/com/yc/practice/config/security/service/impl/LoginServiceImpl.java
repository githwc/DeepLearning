package com.yc.practice.config.security.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.yc.practice.config.security.service.LoginService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 功能描述：
 * <p>版权所有：</p>
 * 未经本人许可，不得以任何方式复制或使用本程序任何部分
 *
 * @Company: 紫色年华
 * @Author: xieyc
 * @Datetime: 2020-04-24
 * @Version: 1.0.0
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class LoginServiceImpl implements LoginService {

    @Override
    public JSONObject getImageVerify() {
        return null;
    }
}

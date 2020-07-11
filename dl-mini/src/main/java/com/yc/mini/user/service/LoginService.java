package com.yc.mini.user.service;

import com.alibaba.fastjson.JSONObject;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;

/**
 * 功能描述:
 *
 * @Author: xieyc && 紫色年华
 * @Date: 2020-07-09
 * @Version: 1.0.0
 */
public interface LoginService {

    /**
     * 微信小程序登录
     * @param param 微信code
     * @return
     */
    void wxLogin(JSONObject param, HttpServletResponse response);

}

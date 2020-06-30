package com.yc.practice.config.security.controller;

import com.alibaba.fastjson.JSONObject;
import com.yc.practice.config.security.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 功能描述:登录控制器

 *

 * @Author: xieyc && 紫色年华
 * @Date: 2020-03-21
 * @Version: 1.0.0
 */
@RestController
@RequestMapping("/auth")
public class LoginController {

    private final LoginService loginService;

    @Autowired
    public LoginController(LoginService loginService){
        this.loginService = loginService;
    }


    /**
     * 获取图片验证码
     *
     * @return 返回图片验证码
     */
    @GetMapping("/imageVerifyCode")
    public JSONObject getImageVerify() {
        return loginService.getImageVerify();
    }


}

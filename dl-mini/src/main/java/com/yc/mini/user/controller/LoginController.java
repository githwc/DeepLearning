package com.yc.mini.user.controller;

import com.alibaba.fastjson.JSONObject;
import com.yc.mini.user.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

/**
 * 功能描述:
 *
 * @Author: xieyc && 紫色年华
 * @Date: 2020-07-09
 * @Version: 1.0.0
 */
@RestController
@RequestMapping("/login")
public class LoginController {

    private LoginService loginService;

    @Autowired
    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    /**
     * 微信小程序登录
     *
     * @param param 入参
     * @return json
     */
    @PostMapping("/wxLogin")
    public void wxLogin(@RequestBody JSONObject param, HttpServletResponse response) {
        loginService.wxLogin(param, response);
    }
}

package com.yc.mini.security.service;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import javax.servlet.http.HttpServletResponse;

/**
 * 功能描述:
 *
 * @Author: xieyc && 紫色年华
 * @Date: 2020-03-22
 * @Version: 1.0.0
 */
public interface TokenService {

    /**
     * 创建Token
     *
     * @param loginName 登陆账号
     * @return token
     */
    String create(String loginName);

    /**
     * 校验Token
     *
     * @param response 响应
     * @param token    token
     * @return 检验结果
     */
    UsernamePasswordAuthenticationToken verify(HttpServletResponse response, String token);
}

package com.yc.practice.config.security.service;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import javax.servlet.http.HttpServletResponse;

/**
 * 功能描述：
 * <p>版权所有：</p>
 * 未经本人许可，不得以任何方式复制或使用本程序任何部分
 *
 * @Company: 紫色年华
 * @Author: xieyc
 * @Datetime: 2020-03-22
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

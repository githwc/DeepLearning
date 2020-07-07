package com.yc.practice.config.security.filter;

import cn.hutool.extra.servlet.ServletUtil;
import cn.hutool.http.ContentType;
import com.yc.common.constant.CommonConstant;
import com.yc.common.global.error.ErrorException;
import com.yc.common.global.response.RestResult;
import com.yc.practice.config.security.service.TokenService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.Charset;

/**
 * 功能描述:JWT登录授权过滤器
 * [
 * 1、在用户名和密码校验前添加的过滤器
 * 如果请求中有jwt的token且有效，会取出token中的用户名
 * 然后调用SpringSecurity的API进行登录操作
 * ]
 * [
 * 2、OncePerRequestFilter:让重复执行的filter实现一次执行过程(一次请求执行一次)
 * 解决在使用SpringSecurity过程中，SpringSecurity中的Filter被加载了两次或多次的情况
 * ]
 *
 * @Author: xieyc && 紫色年华
 * @Date: 2020-03-20
 * @Version: 1.0.0
 */
@Component
@Slf4j
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

    private final TokenService tokenService;

    @Autowired
    public JwtAuthenticationTokenFilter(TokenService tokenService) {
        this.tokenService = tokenService;
    }

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request,
                                    @NonNull HttpServletResponse response,
                                    @NonNull FilterChain filterChain) throws ServletException, IOException {
        String token = request.getHeader(CommonConstant.HEADER_STRING);
        log.debug("TOKEN校验 - 请求地址：{}", request.getRequestURI());
        // 不合规的Token格式不予处理
        if (StringUtils.isBlank(token) || !token.startsWith(CommonConstant.TOKEN_PREFIX)) {
            filterChain.doFilter(request, response);
            return;
        }
        //设置返回客户端响应头以及json类型
        token = StringUtils.remove(token, CommonConstant.TOKEN_PREFIX).trim();
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken;
        try {
            usernamePasswordAuthenticationToken = tokenService.verify(response, token);
        } catch (ErrorException e) {
            String errorMsg = RestResult.error(e.getCode(), e.getMsg()).toJSONString();
            log.error(errorMsg);
            ServletUtil.write(response, errorMsg, ContentType.build(CommonConstant.JSON_CONTENTTYPE,
                    Charset.forName(CommonConstant.CHARSET_UTF_8)));
            return;
        } catch (Exception e) {
            String errorMsg = RestResult.error(40103, e.getMessage()).toJSONString();
            log.error(errorMsg);
            ServletUtil.write(response, errorMsg, ContentType.build(CommonConstant.JSON_CONTENTTYPE,
                    Charset.forName(CommonConstant.CHARSET_UTF_8)));
            return;
        }
        SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
        filterChain.doFilter(request, response);
    }

}

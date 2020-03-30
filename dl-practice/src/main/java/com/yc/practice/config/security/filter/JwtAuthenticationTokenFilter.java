package com.yc.practice.config.security.filter;

import cn.hutool.extra.servlet.ServletUtil;
import cn.hutool.http.ContentType;
import com.yc.common.constant.CommonConstant;
import com.yc.common.global.error.ErrorException;
import com.yc.common.global.response.RestResult;
import com.yc.common.constant.BaseConstant;
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
 * 功能描述：JWT登录授权过滤器
 *  [ 在用户名和密码校验前添加的过滤器
 *  如果请求中有jwt的token且有效，会取出token中的用户名
 *  然后调用SpringSecurity的API进行登录操作]
 *
 * <p>版权所有：</p>
 * 未经本人许可，不得以任何方式复制或使用本程序任何部分
 *
 * @Company: 紫色年华
 * @Author: xieyc
 * @Datetime: 2020-03-20
 * @Version: 1.0.0
 */
@Component
@Slf4j
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter{

    private final TokenService tokenService;

    @Autowired
    public JwtAuthenticationTokenFilter(TokenService tokenService) {
        this.tokenService = tokenService;
    }

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request,
                                    @NonNull HttpServletResponse response,
                                    @NonNull FilterChain filterChain) throws ServletException, IOException {
        log.debug("TOKEN校验 - 请求地址：{}", request.getRequestURI());
        String token = request.getHeader(BaseConstant.HEADER_STRING);
        // 不合规的Token格式不予处理
        if (StringUtils.isBlank(token) || !token.startsWith(BaseConstant.TOKEN_PREFIX)) {
            filterChain.doFilter(request, response);
            return;
        }
        //设置返回客户端响应头以及json类型
        token = StringUtils.remove(token, BaseConstant.TOKEN_PREFIX).trim();
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken;
        try {
            usernamePasswordAuthenticationToken = tokenService.verify(response, token);
        } catch (ErrorException e) {
            String errorMsg = RestResult.error(e.getCode(), e.getMsg()).toJSONString();
            log.error(errorMsg);
            ServletUtil.write(response, errorMsg, ContentType.build(CommonConstant.JSON_CONTENTTYPE,
                    Charset.forName(CommonConstant.DEFAULT_CHARSET)));
            return;
        } catch (Exception e) {
            String errorMsg = RestResult.error(10503, e.getMessage()).toJSONString();
            log.error(errorMsg);
            ServletUtil.write(response, errorMsg, ContentType.build(CommonConstant.JSON_CONTENTTYPE,
                    Charset.forName(CommonConstant.DEFAULT_CHARSET)));
            return;
        }
        SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
        filterChain.doFilter(request, response);
    }
}

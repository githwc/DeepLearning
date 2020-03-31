package com.yc.practice.config.security.filter;

import cn.hutool.extra.servlet.ServletUtil;
import cn.hutool.http.ContentType;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.yc.common.constant.BaseConstant;
import com.yc.common.constant.CommonConstant;
import com.yc.common.global.error.Error;
import com.yc.common.global.error.ErrorException;
import com.yc.common.global.response.RestResult;
import com.yc.core.system.model.vo.CurrUserVO;
import com.yc.practice.config.security.service.LoginService;
import com.yc.practice.config.security.service.TokenService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.concurrent.TimeUnit;

/**
 * 功能描述：JWT登陆校验是否合法，基于SpringSecurity
 * <p>版权所有：</p>
 * 未经本人许可，不得以任何方式复制或使用本程序任何部分
 *
 * @Company: 紫色年华
 * @Author: xieyc
 * @Datetime: 2020-03-23
 * @Version: 1.0.0
 */
@Slf4j
public class SysUserLoginFilter extends UsernamePasswordAuthenticationFilter {

    private final LoginService loginService;
    private final TokenService TokenService;
    private final RedisTemplate<String,String> redisTemplate;

    public SysUserLoginFilter(AuthenticationManager authenticationManager,
                              LoginService loginService, TokenService TokenService,
                              RedisTemplate<String,String> redisTemplate) {
        this.loginService = loginService;
        this.TokenService = TokenService;
        this.redisTemplate = redisTemplate;
        setAuthenticationManager(authenticationManager);
    }

    private String username;

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        JSONObject loginData;
        try {
            loginData = JSON.parseObject(request.getInputStream(), JSONObject.class);
            if (loginData == null) {
                String errorMsg = RestResult.error(Error.ParameterNotFound.getCode(),
                        Error.ParameterNotFound.getMsg()).toJSONString();
                ServletUtil.write(response, errorMsg, ContentType.build(CommonConstant.JSON_CONTENTTYPE,
                        Charset.forName(CommonConstant.DEFAULT_CHARSET)));
                return null;
            }
        } catch (IOException e) {
            log.error(e.getMessage(), e);
            String errorMsg = RestResult.error(Error.ParameterNotFound.getCode(), Error.ParameterNotFound.getMsg()).toJSONString();
            ServletUtil.write(response, errorMsg, ContentType.build(CommonConstant.JSON_CONTENTTYPE,
                    Charset.forName(CommonConstant.DEFAULT_CHARSET)));
            return null;
        }

        username = loginData.getString("loginName");
        String password = loginData.getString("password");

        // 失败次数拦截
        String cacheErrNum = redisTemplate.opsForValue().get(username+ "_errorNumber");
        if(StringUtils.isNotEmpty(cacheErrNum)){
            int errorNum = Integer.parseInt(cacheErrNum);
            if(errorNum >= 5 ){
                String errorMsg =
                        RestResult.error(Error.AccountLock.getCode(), Error.AccountLock.getMsg()).toJSONString();
                ServletUtil.write(response, errorMsg, ContentType.build(CommonConstant.JSON_CONTENTTYPE,
                        Charset.forName(CommonConstant.DEFAULT_CHARSET)));
                return null;
            }
        }
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username, password);
        return getAuthenticationManager().authenticate(token);
    }

    /**
     * 认证成功返回
     *
     * @param request req
     * @param response res
     * @param chain chain
     * @param authResult auth
     */
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response,
                                            FilterChain chain, Authentication authResult) {
        JSONObject jsonObject = new JSONObject();
        CurrUserVO currUserVo = loginService.loginSuccess(authResult.getName());
        jsonObject.put("userInfo", currUserVo);
        String jwtToken = TokenService.create(authResult.getName());
        jwtToken = BaseConstant.TOKEN_PREFIX + " " + jwtToken;
        response.addHeader(BaseConstant.HEADER_STRING, jwtToken);
        response.setHeader("Access-Control-Allow-Headers", "Authorization");
        response.setHeader("Access-Control-Expose-Headers", "Authorization");
        jsonObject.put("token", jwtToken);
        String successMsg = RestResult.success().data(jsonObject).toJSONString();
        ServletUtil.write(response, successMsg, ContentType.build(CommonConstant.JSON_CONTENTTYPE,
                Charset.forName(CommonConstant.DEFAULT_CHARSET)));
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
                                              AuthenticationException failed) {
        String errorMsg;
        if (failed.getCause() instanceof ErrorException) {
            ErrorException ee = (ErrorException) failed.getCause();
            errorMsg = RestResult.error(ee.getCode(), ee.getMessage()).toJSONString();
        } else {
            String key = username + "_errorNumber";
            String cacheErrNum = redisTemplate.opsForValue().get(key);
            int errorNum = StringUtils.isBlank(cacheErrNum) ? 1 : Integer.parseInt(cacheErrNum) + 1;
            redisTemplate.opsForValue().set(key, String.valueOf(errorNum), 10, TimeUnit.MINUTES);
            String msg = 5 - errorNum == 0 ? "当前账号密码您输入错误五次，请10分钟之后再登录!" : "账号密码输入错误,您还有" + (5 - errorNum) + "次机会!";
            errorMsg = RestResult.error(400, msg).toJSONString();
        }
        ServletUtil.write(response, errorMsg, ContentType.build(CommonConstant.JSON_CONTENTTYPE,
                Charset.forName(CommonConstant.DEFAULT_CHARSET)));
    }

}

package com.yc.mini.security.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DateUtil;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.yc.common.constant.CommonConstant;
import com.yc.common.global.error.Error;
import com.yc.common.global.error.ErrorException;
import com.yc.common.propertie.SecurityProperties;
import com.yc.core.mini.entity.MiniUser;
import com.yc.core.mini.mapper.MiniUserMapper;
import com.yc.mini.security.auth.UserDetailsSelf;
import com.yc.mini.security.service.TokenService;
import com.yc.mini.security.utils.JwtTokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletResponse;
import java.util.Date;

/**
 * 功能描述:JWT
 *
 * @Author: xieyc && 紫色年华
 * @Date: 2020-03-23
 * @Version: 1.0.0
 */
@Service
@Transactional(rollbackFor = Exception.class)
@Slf4j
public class TokenServiceImpl implements TokenService {

    private final MiniUserMapper miniUserMapper;
    private final JwtTokenUtil jwtTokenUtil;
    private final SecurityProperties securityProperties;

    @Autowired
    public TokenServiceImpl(MiniUserMapper miniUserMapper, JwtTokenUtil jwtTokenUtil,
                            SecurityProperties securityProperties) {
        this.miniUserMapper = miniUserMapper;
        this.jwtTokenUtil = jwtTokenUtil;
        this.securityProperties = securityProperties;
    }

    @Override
    public String create(String loginName) {
        return jwtTokenUtil.createJWT(loginName);
    }

    @Override
    public UsernamePasswordAuthenticationToken verify(HttpServletResponse response, String token) {
        String id = jwtTokenUtil.getId(token);
        MiniUser miniUser = miniUserMapper.selectById(id);
        UserDetailsSelf userDetailsSelf = new UserDetailsSelf();
        BeanUtil.copyProperties(miniUser, userDetailsSelf);
        try {
            jwtTokenUtil.validateToken(token);
            DecodedJWT jwt = jwtTokenUtil.getDecodedJWT(token);
            Date now = new Date();
            Date iatTime = jwt.getIssuedAt();
            Date expTime = jwt.getExpiresAt();
            Date renewTime = new Date(iatTime.getTime() + securityProperties.getJwtRenewTime());
            log.debug("签发时间：{}", DateUtil.formatDateTime(iatTime));
            log.debug("到期时间：{}", DateUtil.formatDateTime(expTime));
            log.debug("续签时间：{}", DateUtil.formatDateTime(renewTime));
            // 满足续签条件
            if (now.compareTo(renewTime) >= 0) {
                String newToken = this.create(jwtTokenUtil.getId(token));
                response.addHeader(CommonConstant.HEADER_STRING, CommonConstant.TOKEN_PREFIX + " " + newToken);
                response.setHeader("Access-Control-Allow-Headers", "authorization");
                response.setHeader("Access-Control-Expose-Headers", "authorization");
            }
        } catch (TokenExpiredException e) {
            throw new ErrorException(Error.TokenError);
        }
        return new UsernamePasswordAuthenticationToken(userDetailsSelf, null);
    }
}

package com.yc.practice.config.security.service.impl;

import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.yc.common.global.error.Error;
import com.yc.common.global.error.ErrorException;
import com.yc.common.constant.BaseConstant;
import com.yc.common.security.JwtTokenUtil;
import com.yc.common.security.SecurityProperties;
import com.yc.common.utils.DateTimeUtil;
import com.yc.core.system.mapper.SysUserMapper;
import com.yc.core.system.model.vo.CurrUserVO;
import com.yc.practice.config.security.service.TokenService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletResponse;
import java.util.Date;

/**
 * 功能描述：JWT
 * <p>版权所有：</p>
 * 未经本人许可，不得以任何方式复制或使用本程序任何部分
 *
 * @Company: 紫色年华
 * @Author: xieyc
 * @Datetime: 2020-03-23
 * @Version: 1.0.0
 */
@Service
@Transactional(rollbackFor = Exception.class)
@Slf4j
public class TokenServiceImpl implements TokenService {

    private final SysUserMapper sysUserMapper;
    private final JwtTokenUtil jwtTokenUtil;
    private final SecurityProperties securityProperties;

    @Autowired
    public TokenServiceImpl(SysUserMapper sysUserMapper, JwtTokenUtil jwtTokenUtil,
                            SecurityProperties securityProperties) {
        this.sysUserMapper = sysUserMapper;
        this.jwtTokenUtil = jwtTokenUtil;
        this.securityProperties = securityProperties;
    }

    @Override
    public String create(String loginName) {
        return jwtTokenUtil.createJWT(loginName);
    }

    @Override
    public UsernamePasswordAuthenticationToken verify(HttpServletResponse response, String token) {
        String loginName = jwtTokenUtil.getName(token);
        CurrUserVO currUserVO = sysUserMapper.loginByName(loginName);
        try {
            jwtTokenUtil.validateToken(token);
            DecodedJWT jwt = jwtTokenUtil.getDecodedJWT(token);
            Date now = new Date();
            Date iatTime = jwt.getIssuedAt();
            Date expTime = jwt.getExpiresAt();
            Date renewTime = new Date(iatTime.getTime() + securityProperties.getJwtRenewTime());
            log.debug("签发时间：{}", DateTimeUtil.dateToString(iatTime), "到期时间：{}", DateTimeUtil.dateToString(expTime),
                    "续签时间：{}", DateTimeUtil.dateToString(renewTime));
            log.debug("Bearer " + token);
            // 满足续签条件
            if (now.compareTo(renewTime) >= 0) {
                String newToken = this.create(jwtTokenUtil.getName(token));
                response.addHeader(BaseConstant.HEADER_STRING, BaseConstant.TOKEN_PREFIX + " " + newToken);
                response.setHeader("Access-Control-Allow-Headers", "Authorization");
                response.setHeader("Access-Control-Expose-Headers", "Authorization");
            }
        } catch (TokenExpiredException e) {
            throw new ErrorException(Error.TokenError);
        }
        return new UsernamePasswordAuthenticationToken(currUserVO, currUserVO.getPassword(), null);
    }
}

package com.yc.mini.security.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.yc.common.propertie.SecurityProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * 功能描述:生成jwtToken
 *
 * @Author: xieyc && 紫色年华
 * @Date: 2020-07-11
 * @Version: 1.0.0
 */
@Component
@Slf4j
public class JwtTokenUtil {

    private final SecurityProperties securityProperties;

    @Autowired
    public JwtTokenUtil(SecurityProperties securityProperties) {
        this.securityProperties = securityProperties;
    }

    private static final String CLAIM_KEY_USERNAME = "miniUserId";

    /**
     * 根据用户信息生成token
     *
     * @param miniUserId 用户ID
     * @return token
     */
    public String createJWT(String miniUserId) {
        Date iatTime = new Date();
        Date expDate = new Date(iatTime.getTime() + securityProperties.getJwtActiveTime());
        Algorithm algorithm = Algorithm.HMAC256(securityProperties.getJwtSecret());
        return JWT.create()
                .withIssuedAt(iatTime)
                .withClaim(CLAIM_KEY_USERNAME, miniUserId)
                .withExpiresAt(expDate)
                .sign(algorithm);
    }


    /**
     * 验证token是否还有效
     *
     * @param token 客户端传入的token
     */
    public void validateToken(String token) {
        Algorithm algorithm = Algorithm.HMAC256(securityProperties.getJwtSecret());
        JWTVerifier verifier = JWT.require(algorithm).build();
        verifier.verify(token);
    }


    /**
     * 获取token中的id
     *
     * @param token token
     * @return string
     */
    public String getId(String token) {
        DecodedJWT jwt = JWT.decode(token);
        return jwt.getClaim(CLAIM_KEY_USERNAME).toString();
    }

    /**
     * 解密token内容
     *
     * @param token token
     * @return decoded
     */
    public DecodedJWT getDecodedJWT(String token) {
        return JWT.decode(token);
    }

}

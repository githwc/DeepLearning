package com.yc.common.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * 功能描述：
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
public class JwtTokenUtil {

    private final SecurityProperties securityProperties;

    @Autowired
    public JwtTokenUtil(SecurityProperties securityProperties) {
        this.securityProperties = securityProperties;
    }

    private static final String CLAIM_KEY_USERNAME = "name";

    /**
     * 根据用户信息生成token
     * @param name   用户信息
     * @return token
     */
    public String createJWT(String name) {
        Date iatTime = new Date();
        Date expDate = new Date(iatTime.getTime() + securityProperties.getJwtActiveTime());
        Algorithm algorithm = Algorithm.HMAC256(securityProperties.getJwtSecret());
        return JWT.create()
                .withIssuedAt(iatTime)
                .withClaim(CLAIM_KEY_USERNAME, name)
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
        return jwt.getClaim("id").toString();
    }

    /**
     * 获取token中的name
     *
     * @param token token
     * @return string
     */
    public String getName(String token) {
        DecodedJWT jwt = JWT.decode(token);
        return jwt.getClaim("name").asString();
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

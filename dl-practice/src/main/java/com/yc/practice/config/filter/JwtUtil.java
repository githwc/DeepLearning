package com.yc.practice.config.filter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.yc.common.config.exception.ApiException;
import com.yc.common.config.exception.RunException.RunningException;
import org.apache.commons.lang3.StringUtils;
import org.thymeleaf.spring5.context.SpringContextUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;

/**
 *
 * 功能描述：JWT 工具类
 *
 *  <p>版权所有：</p>
 *  未经本人许可，不得以任何方式复制或使用本程序任何部分
 *
 * @Company: 紫色年华
 * @Author xieyc
 * @Date 2019-09-19
 * @Version: 1.0.0
 *
 */
public class JwtUtil {

    /**
     * 设置过期时间30分钟
     */
	public static final long EXPIRE_TIME = 30 * 60 * 1000;

	/**
	 * 校验token是否正确
	 *
	 * @param token  密钥
	 * @param secret 用户的密码
	 * @return 是否正确
	 */
	public static boolean verifyToken(String token, String username, String secret) {
		try {
			// 根据密码生成JWT效验器
			Algorithm algorithm = Algorithm.HMAC256(secret);
			JWTVerifier verifier = JWT.require(algorithm).withClaim("loginName", username).build();
			// 校验TOKEN
			DecodedJWT jwt = verifier.verify(token);
			return true;
		} catch (Exception exception) {
			return false;
		}
	}

	/**
     * @DESC: 获取当前用户名,无需secret解密
	 *
	 * @return token中包含的用户名
	 */
	public static String getUsername(String token) {
		try {
			DecodedJWT jwt = JWT.decode(token);
			return jwt.getClaim("loginName").asString();
		} catch (JWTDecodeException e) {
			return null;
		}
	}

	/**
	 * 生成签名,30min后过期
	 *
	 * @param loginName 用户名
	 * @param secret   用户的密码
	 * @return 加密的token
	 */
	public static String sign(String loginName, String secret) {
		Date date = new Date(System.currentTimeMillis() + EXPIRE_TIME);
		Algorithm algorithm = Algorithm.HMAC256(secret);
		// 附带username信息
		return JWT.create().withClaim("loginName", loginName).withExpiresAt(date).sign(algorithm);

	}

	/**
	 * 根据request中的token获取用户账号
	 *
	 * @param request
	 * @return
	 * @throws ApiException
	 */
	public static String getUserNameByToken(HttpServletRequest request) throws ApiException {
		String accessToken = request.getHeader("X-Access-Token");
		String username = getUsername(accessToken);
		if (StringUtils.isEmpty(username)) {
			throw new RunningException();
		}
		return username;
	}

}

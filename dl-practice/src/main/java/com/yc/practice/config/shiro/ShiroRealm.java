package com.yc.practice.config.shiro;

import com.yc.common.config.exception.RunException.RunningException;
import com.yc.common.constant.CacheConstant;
import com.yc.common.constant.CommonConstant;
import com.yc.core.system.entity.SysUser;
import com.yc.practice.config.filter.JwtToken;
import com.yc.practice.config.filter.JwtUtil;
import com.yc.practice.system.service.SysPermissionService;
import com.yc.practice.system.service.SysRoleService;
import com.yc.practice.system.service.SysUserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * 功能描述：自定义Realm
 *
 * <p>版权所有：</p>
 * 未经本人许可，不得以任何方式复制或使用本程序任何部分
 *
 * @Company: 紫色年华
 * @Author:  xieyc
 * @Datetime: 2019-09-17 16:34
 * @Version: 1.0.0
 */
@Slf4j
public class ShiroRealm extends AuthorizingRealm {

    @Autowired
    @Lazy
    private SysUserService sysUserService;

    @Autowired
    @Lazy
    private SysRoleService sysRoleService;

    @Autowired
    @Lazy
    private SysPermissionService sysPermissionService;

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 必须重写此方法，不然Shiro会报错
     */
    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JwtToken;
    }

    /**
     * 功能： 执行身份认证
     *      比对 UsernamePasswordToken(前台用户信息)和SimpleAuthenticationInfo(数据库中查询到的信息)
     *      完成身份认证,错误抛出异常
     *
     * @param ——authenticationToken 用户身份信息 token
     * @return 返回封装了用户信息的 AuthenticationInfo 实例
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken auth) throws AuthenticationException {
        String token = (String)auth.getCredentials();
        if (token == null) {
            throw new RunningException("Token异常");
        }
        SysUser sysUser = this.checkUserTokenIsEffect(token);
        return new SimpleAuthenticationInfo(sysUser,token,getName());
    }

    /**
     * 功能： 执行身份授权
     * 获取用户权限信息，包括角色以及权限。
     * 只有当触发检测用户权限时才会调用此方法，例如checkRole,checkPermission
     *
     * @return AuthorizationInfo 权限信息
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        SysUser sysUser = null;
        if (principals != null) {
            sysUser = (SysUser) principals.getPrimaryPrincipal();
        }
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        // 设置用户拥有的角色集合 比如 admin,test
        Set<String> roleSet = sysRoleService.getUserRoles(sysUser.getLoginName());
        info.setRoles(roleSet);
        //设置用户拥有的权限集合 比如sys:role:add
        Set<String> permissionSet = sysPermissionService.getUserPermCodes(sysUser.getLoginName());
        info.addStringPermissions(permissionSet);
        return info;
    }


    /**
     * 校验token的有效性
     *
     * @param token
     */
    private SysUser checkUserTokenIsEffect(String token) throws AuthenticationException {
        String loginName = JwtUtil.getUsername(token);
        if (loginName == null) {
            throw new RunningException("Token非法无效!");
        }

        // 查询用户信息
        SysUser sysUser = sysUserService.getUserByName(loginName);
        if (sysUser == null) {
            throw new RunningException("用户不存在!");
        }

        // 是否冻结
        if(CommonConstant.DEL_FLAG_1.equals(sysUser.getDelFlag())){
            throw new RunningException("账号已被锁定,请联系管理员！");
        }

        // 校验token是否超时失效 & 或者账号密码是否错误
        if (!jwtTokenRefresh(token, loginName, sysUser.getPassword())) {
            throw new RunningException("Token失效，请重新登录!");
        }

        return sysUser;
    }

    /**
     * JWTToken刷新生命周期 （解决用户一直在线操作，提供Token失效问题）
     * 1、登录成功后将用户的JWT生成的Token作为k、v存储到cache缓存里面(这时候k、v值一样)
     * 2、当该用户再次请求时，通过JWTFilter层层校验之后会进入到doGetAuthenticationInfo进行身份验证
     * 3、当该用户这次请求JWTToken值还在生命周期内，则会通过重新PUT的方式k、v都为Token值，缓存中的token值生命周期时间重新计算(这时候k、v值一样)
     * 4、当该用户这次请求jwt生成的token值已经超时，但该token对应cache中的k还是存在，则表示该用户一直在操作只是JWT的token失效了，程序会给token对应的k映射的v值重新生成JWTToken并覆盖v值，该缓存生命周期重新计算
     * 5、当该用户这次请求jwt在生成的token值已经超时，并在cache中不存在对应的k，则表示该用户账户空闲超时，返回用户信息已失效，请重新登录。
     * 6、每次当返回为true情况下，都会给Response的Header中设置Authorization，该Authorization映射的v为cache对应的v值。
     * 7、注：当前端接收到Response的Header中的Authorization值会存储起来，作为以后请求token使用
     * 参考方案：https://blog.csdn.net/qq394829044/article/details/82763936
     *
     * @param loginName
     * @param passWord
     * @return
     */
    public boolean jwtTokenRefresh(String token, String loginName, String passWord) {
        ValueOperations operations = redisTemplate.opsForValue();
        String cacheToken = String.valueOf(operations.get(CacheConstant.LOGIN_USER_TOKEN_+ token));
        if (StringUtils.isNotEmpty(cacheToken)) {
            // 校验token有效性
            if (!JwtUtil.verifyToken(token, loginName, passWord)) {
                String newAuthorization = JwtUtil.sign(loginName, passWord);
                // 放入缓存并设置超时时间
                operations.set(CacheConstant.LOGIN_USER_TOKEN_ + token, newAuthorization,30, TimeUnit.MINUTES);
            } else {
                operations.set(CacheConstant.LOGIN_USER_TOKEN_ + token, cacheToken,30, TimeUnit.MINUTES);
            }
            return true;
        }
        return false;
    }
}

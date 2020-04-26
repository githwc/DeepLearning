package com.yc.practice.config.security.service.impl;

import com.yc.common.constant.CommonConstant;
import com.yc.common.global.error.Error;
import com.yc.common.global.error.ErrorException;
import com.yc.core.system.entity.SysUser;
import com.yc.core.system.mapper.SysUserMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;

/**
 * 功能描述：SpringSecurity定义的核心接口，用于根据用户名获取用户信息
 *      [登录时校验用户信息,认证成功后将认证信息存入SecurityContextHolder上下文,后续操作会跳过过滤器，无需鉴权]
 * <p>版权所有：</p>
 * 未经本人许可，不得以任何方式复制或使用本程序任何部分
 *
 * @Company: 紫色年华
 * @Author: xieyc
 * @Datetime: 2020-03-20
 * @Version: 1.0.0
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class UserDetailsServiceImpl implements UserDetailsService {

    private final SysUserMapper sysUserMapper;

    @Autowired
    public UserDetailsServiceImpl(SysUserMapper sysUserMapper) {
        this.sysUserMapper = sysUserMapper;
    }

    @Override
    public UserDetails loadUserByUsername(String loginName) {
        if(StringUtils.isEmpty(loginName)){
            throw new ErrorException(Error.LoginNameIsNull);
        }
        SysUser sysUser = sysUserMapper.loginByName(loginName);
        if (sysUser == null) {
            throw new ErrorException(Error.UserNotFound);
        } else if (sysUser.getState() == CommonConstant.PublicState.DISABLE) {
            throw new ErrorException(Error.UserDisabled);
        } else if (sysUser.getState() == CommonConstant.PublicState.ENABLE) {

            Collection<GrantedAuthority> authorities = new ArrayList<>();
            authorities.add(new SimpleGrantedAuthority(sysUser.getSysUserId()));
            return new User(sysUser.getLoginName(), sysUser.getPassWord(), authorities);
        } else {
            throw new ErrorException(Error.UserError);
        }
    }
}

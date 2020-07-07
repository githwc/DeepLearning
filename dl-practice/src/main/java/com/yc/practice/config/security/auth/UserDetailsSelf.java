package com.yc.practice.config.security.auth;

import com.yc.core.system.entity.SysUser;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

/**
 * 功能描述:
 *
 * @Author: xieyc && 紫色年华
 * @Date: 2020-04-25
 * @Version: 1.0.0
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class UserDetailsSelf extends SysUser implements UserDetails {

    /**
     * 密码
     */
    private String password;

    /**
     * 用户名
     */
    private String username;

    /**
     * 是否没过期
     */
    private boolean accountNonExpired;

    /**
     * 是否没被锁定
     */
    private boolean accountNonLocked;

    /**
     * 是否没过期
     */
    private boolean redentialsNonExpired;

    /**
     * 账号是否可用
     */
    private boolean enable;

    Collection<? extends GrantedAuthority> authorities;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enable;
    }

}

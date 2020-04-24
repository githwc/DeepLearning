package com.yc.practice.config.security.service.impl;

import com.yc.common.constant.CommonConstant;
import com.yc.common.global.error.Error;
import com.yc.common.global.error.ErrorException;
import com.yc.core.system.mapper.SysUserMapper;
import com.yc.core.system.model.vo.CurrUserVO;
import com.yc.practice.system.service.SysPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 功能描述：SpringSecurity定义的核心接口，用于根据用户名获取用户信息
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
    private final SysPermissionService sysPermissionService;


    @Autowired
    public UserDetailsServiceImpl(SysUserMapper sysUserMapper, SysPermissionService sysPermissionService) {
        this.sysUserMapper = sysUserMapper;
        this.sysPermissionService = sysPermissionService;
    }

    @Override
    public UserDetails loadUserByUsername(String loginName) {
        CurrUserVO userVO = sysUserMapper.loginByName(loginName);
        if (userVO == null) {
            throw new ErrorException(Error.UserNotFound);
        } /*else if (StringUtils.isBlank(userVO.getRoleId())) {
            throw new ErrorException(Error.NoAccess);
        } */else if (userVO.getState() == CommonConstant.PublicState.DISABLE) {
            throw new ErrorException(Error.UserDisabled);
        } else if (userVO.getState() == CommonConstant.PublicState.ENABLE) {
            List<String> permissions = sysPermissionService.getUserPerm(userVO.getLoginName());
            String[] perarray = new String[permissions.size()];
            permissions.toArray(perarray);
            UserDetails userDetail =
                    User.withUsername(userVO.getLoginName()).password(userVO.getPassword()).authorities("p1").build();
            return userDetail;
        } else {
            throw new ErrorException(Error.UserError);
        }
    }
}

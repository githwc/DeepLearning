package com.yc.practice.config.security.service.impl;

import com.yc.core.system.mapper.SysUserMapper;
import com.yc.core.system.model.vo.CurrUserVO;
import com.yc.practice.config.security.service.LoginService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 功能描述：
 * <p>版权所有：</p>
 * 未经本人许可，不得以任何方式复制或使用本程序任何部分
 *
 * @Company: 紫色年华
 * @Author: xieyc
 * @Datetime: 2020-03-21
 * @Version: 1.0.0
 */
@Slf4j
@Service
public class LoginServiceImpl implements LoginService {

    private final SysUserMapper sysUserMapper;

    @Autowired
    public LoginServiceImpl (SysUserMapper sysUserMapper){
        this.sysUserMapper = sysUserMapper;
    }


    @Override
    public CurrUserVO loginSuccess(String loginName) {
        return sysUserMapper.loginByName(loginName);
    }
}

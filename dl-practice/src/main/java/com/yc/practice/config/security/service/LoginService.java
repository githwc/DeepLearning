package com.yc.practice.config.security.service;

import com.yc.core.system.model.vo.CurrUserVO;

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
public interface LoginService {

    /**
     * 登陆成功
     *
     * @param loginName 账号
     * @return 账号持有人信息
     */
    CurrUserVO loginSuccess(String loginName);

}

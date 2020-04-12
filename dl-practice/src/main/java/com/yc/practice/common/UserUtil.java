package com.yc.practice.common;

import com.yc.common.global.error.Error;
import com.yc.common.global.error.ErrorException;
import com.yc.core.system.model.vo.CurrUserVO;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * 功能描述：
 * <p>版权所有：</p>
 * 未经本人许可，不得以任何方式复制或使用本程序任何部分
 *
 * @Company: 紫色年华
 * @Author: xieyc
 * @Datetime: 2020-04-09
 * @Version: 1.0.0
 */
public class UserUtil {

    /**
     * 获取当前用户信息
     * @return 用户信息
     */
    public static CurrUserVO getUser() {
        try {
            return (CurrUserVO) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        }catch (Exception e) {
            throw new ErrorException(Error.TokenError);
        }
    }

    /**
     * 获取当前用户ID
     * @return 用户ID
     */
    public static String getUserId() {
        try {
            return ((CurrUserVO) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getSysUserId();
        }catch (Exception e) {
            throw new ErrorException(Error.TokenError);
        }
    }
}

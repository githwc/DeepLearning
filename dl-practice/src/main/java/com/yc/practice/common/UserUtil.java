package com.yc.practice.common;

import com.yc.common.global.error.Error;
import com.yc.common.global.error.ErrorException;
import com.yc.practice.config.security.auth.UserDetailsSelf;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * 功能描述:Security 会话管理
 *
 * @Author: xieyc && 紫色年华
 * @Date: 2020-04-09
 * @Version: 1.0.0
 */
public class UserUtil {

    /**
     * 获取当前用户信息
     *
     * @return 用户信息
     */
    public static UserDetailsSelf getUser() {
        try {
            return (UserDetailsSelf) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        } catch (Exception e) {
            throw new ErrorException(Error.TokenError);
        }
    }

    /**
     * 获取当前用户ID
     *
     * @return 用户ID
     */
    public static String getUserId() {
        try {
            return ((UserDetailsSelf) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getSysUserId();
        } catch (Exception e) {
            throw new ErrorException(Error.TokenError);
        }
    }
}

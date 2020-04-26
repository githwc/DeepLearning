package com.yc.practice.config.security.filter;

import com.yc.common.global.error.Error;
import com.yc.common.global.error.ErrorException;
import org.springframework.security.web.session.SessionInformationExpiredEvent;
import org.springframework.security.web.session.SessionInformationExpiredStrategy;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import java.io.IOException;

/**
 * 功能描述：同账号异地登录拦截
 * <p>版权所有：</p>
 * 未经本公司许可，不得以任何方式复制或使用本程序任何部分
 *
 * @Company: 紫色年华
 * @Author: xieyc
 * @Datetime: 2020-04-25
 * @Version: 1.0.0
 */
@Component
public class SessionInformationExpiredStrategySelf implements SessionInformationExpiredStrategy {

    @Override
    public void onExpiredSessionDetected(SessionInformationExpiredEvent event) throws IOException, ServletException {
        System.out.println("=============进入了==================");
        System.out.println("=============进入了==================");
        System.out.println("=============进入了==================");
        System.out.println("=============进入了==================");
        System.out.println("=============进入了==================");
        System.out.println("=============进入了==================");
        throw new ErrorException(Error.AuthError);
    }
}

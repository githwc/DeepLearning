package com.yc.common.config.exception.RunException;

import com.yc.common.config.exception.ApiException;
import com.yc.common.config.response.ResponseCode;
import com.yc.common.config.response.RestResult;

/**
 * 功能描述：运行异常
 * <p>版权所有：</p>
 * 未经本人许可，不得以任何方式复制或使用本程序任何部分
 *
 * @Company: 紫色年华
 * @Author: xieyc
 * @Datetime: 2019-09-25
 * @Version: 1.0.0
 */
public class RunningException extends ApiException {
    public RunningException() {
        super(ResponseCode.SYSTEM_EXCEPTION, "运行时异常");
    }

    public RunningException(String msg, Object... params) {
        super(ResponseCode.SYSTEM_EXCEPTION, RestResult.formatMsg(msg, params));
    }
}

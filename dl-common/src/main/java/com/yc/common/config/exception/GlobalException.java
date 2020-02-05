package com.yc.common.config.exception;

import com.yc.common.config.response.ResponseBean;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 功能描述：自定义异常
 *      ExceptionHandler: 指定拦截的异常
 * <p>版权所有：</p>
 * 未经本人许可，不得以任何方式复制或使用本程序任何部分
 *
 * @Company: 紫色年华
 * @Author: xieyc
 * @Datetime: 2019-09-25
 * @Version: 1.0.0
 */
@RestControllerAdvice
@Slf4j
public class GlobalException {

    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(ApiException.class)
    public Object myErrorHandlerException(ApiException e) {
        ResponseBean response = ResponseBean.error(e.getCode(), e.getMsg());
        log.error("error : {}", response.toJSONString(), e);
        return response;
    }

}

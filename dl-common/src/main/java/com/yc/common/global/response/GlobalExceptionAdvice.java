package com.yc.common.global.response;

import com.yc.common.global.error.ErrorException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 功能描述：APP异常返回处理
 * <p>版权所有：</p>
 * 未经本人许可，不得以任何方式复制或使用本程序任何部分
 *
 * @Company: 紫色年华
 * @Author: xieyc
 * @Datetime: 2020-03-30
 * @Version: 1.0.0
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionAdvice {

    /**
     * 处理自定义异常
     *
     * @param e
     * @param request
     * @param response
     * @return
     */
    @ExceptionHandler(ErrorException.class)
    public Object handleAppException(ErrorException e, HttpServletRequest request, HttpServletResponse response) {
        response.setStatus(e.getHttpStatusCode());
        RestResult restResult = RestResult.error(e.getCode(), e.getMsg());
        log.error("error : {}", restResult.toJSONString(), e);

        if (isAjax(request)) {
            return restResult;
        } else {
            ModelAndView modelAndView = new ModelAndView();
            modelAndView.addObject("status", response.getStatus());
            modelAndView.addObject("message", String.format("[%s] %s", e.getCode(), e.getMsg()));
            modelAndView.setViewName("/error");
            return modelAndView;
        }
    }

    /**
     * 判断是否是ajax请求
     *
     * @param request
     * @return
     */
    private static boolean isAjax(HttpServletRequest request) {
        // 使用HttpServletRequest中的header检测请求是否为ajax, 如果是ajax则返回json, 如果为非ajax则返回view(即ModelAndView)
        String contentTypeHeader = request.getHeader("Content-Type");
        String acceptHeader = request.getHeader("Accept");
        String xRequestedWith = request.getHeader("X-Requested-With");
        return (contentTypeHeader != null && contentTypeHeader.contains("application/json"))
                || (acceptHeader != null && acceptHeader.contains("application/json"))
                || "XMLHttpRequest".equalsIgnoreCase(xRequestedWith);
    }

}

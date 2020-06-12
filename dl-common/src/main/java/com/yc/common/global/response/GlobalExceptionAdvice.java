package com.yc.common.global.response;

import com.yc.common.global.error.ErrorException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolationException;

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
     * @param e 自定义异常
     * @param request 请求信息
     * @param response 响应信息
     * @return r
     */
    @ExceptionHandler(ErrorException.class)
    public Object handleAppException(ErrorException e, HttpServletRequest request, HttpServletResponse response) {
        return this.globalResponse(request,response,e.getMsg());
    }

    /**
     * 全局单一参数校验 ConstraintViolationException
     *  [ 注: @Validated 注解在类上
     *      public void test(@NotBlank(message = "用户名不能为空") String userName){}
     *  ]
     *
     * @param e 异常
     * @param request   请求
     * @param response  响应
     * @return r
     */
    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseBody
    public Object handleConstraintViolationException(ConstraintViolationException e, HttpServletRequest request, HttpServletResponse response) {
        String errorMsg = String.format("%s >> %s", e.getMessage().split(":")[0],
                e.getMessage().split(":")[1]);
        return this.globalResponse(request,response,errorMsg);
    }

    /**
     * 全局JavaBean FORM 请求参数校验 BindException
     *
     * @param e 异常
     * @param request   请求
     * @param response  响应
     * @return r
     */
    @ExceptionHandler(BindException.class)
    @ResponseBody
    public Object handleBindExceptionException(BindException e, HttpServletRequest request, HttpServletResponse response) {
        FieldError fieldError = e.getBindingResult().getFieldError();
        String errorMsg = String.format("%s >> %s", fieldError.getField(),
                fieldError.getDefaultMessage());
        return this.globalResponse(request,response,errorMsg);
    }

    /**
     * 全局JSON 参数校验 MethodArgumentNotValidException
     *
     * @param e 异常
     * @param request   请求
     * @param response  响应
     * @return r
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public Object handleMethodArgumentNotValidException(MethodArgumentNotValidException e, HttpServletRequest request, HttpServletResponse response) {
        FieldError fieldError = e.getBindingResult().getFieldError();
        String errorMsg = String.format("%s >> %s", fieldError.getField(),
                fieldError.getDefaultMessage());
        return this.globalResponse(request,response,errorMsg);
    }

    /**
     * Exception 异常拦截
     * @param exception 异常
     * @param response 响应
     */
    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public Object defaultExceptionHandler(HttpServletRequest request,Exception exception,HttpServletResponse response) {
        try {
            throw exception;
        } catch (Exception e) {
            log.error("【全局异常捕获】>>  未知异常 stack = {}", ExceptionUtils.getStackTrace(e));
            String errorMsg = "[未知异常]>>" + ExceptionUtils.getStackTrace(e);
            return this.globalResponse(request,response,errorMsg);
        }
    }

    /**
     * 子方法 统一返回异常信息
     * @param request 请求信息
     * @param response 响应信息
     * @param errorMsg 错误信息
     * @return r
     */
    private Object globalResponse(HttpServletRequest request,HttpServletResponse response,String errorMsg){
        response.setStatus(response.getStatus());
        RestResult restResult = RestResult.error(40300, errorMsg);

        if (isAjax(request)) {
            return restResult;
        } else {
            ModelAndView modelAndView = new ModelAndView();
            modelAndView.addObject("status", response.getStatus());
            modelAndView.addObject("message", String.format("[%s] %s", "40300", errorMsg));
            modelAndView.setViewName("/error");
            return modelAndView;
        }
    }


    /**
     * 判断是否是ajax请求
     *  [使用HttpServletRequest中的header检测请求是否为ajax,
     *      如果是ajax则返回json, 如果为非ajax则返回view(即ModelAndView)]
     *
     * @param request 请求信息
     * @return r
     */
    private static boolean isAjax(HttpServletRequest request) {
        String contentTypeHeader = request.getHeader("Content-Type");
        String acceptHeader = request.getHeader("Accept");
        String xRequestedWith = request.getHeader("X-Requested-With");
        return (contentTypeHeader != null && contentTypeHeader.contains("application/json"))
                || (acceptHeader != null && acceptHeader.contains("application/json"))
                || "XMLHttpRequest".equalsIgnoreCase(xRequestedWith);
    }

}

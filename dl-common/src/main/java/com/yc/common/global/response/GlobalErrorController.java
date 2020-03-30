package com.yc.common.global.response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ErrorProperties;
import org.springframework.boot.autoconfigure.web.servlet.error.BasicErrorController;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * 功能描述：基础错误控制器 涵盖404 500错误
 * <p>版权所有：</p>
 * 未经本人许可，不得以任何方式复制或使用本程序任何部分
 *
 * @Company: 紫色年华
 * @Author: xieyc
 * @Datetime: 2020-03-30
 * @Version: 1.0.0
 */
@Controller
public class GlobalErrorController extends BasicErrorController {

    @Autowired
    public GlobalErrorController() {
        super(new DefaultErrorAttributes(), new ErrorProperties());
    }

    @Override
    @RequestMapping(produces = "text/html")
    public ModelAndView errorHtml(HttpServletRequest request, HttpServletResponse response) {
        HttpStatus status = getStatus(request);
        response.setStatus(status.value());
        Map<String, Object> errorMap = getErrorAttributes(request, false);
        String errorMsg = errorMap.containsKey("message") ? errorMap.get("message").toString() : "";

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("status", response.getStatus());
        modelAndView.addObject("message", String.format("[%s] %s", status.value(), errorMsg));
        modelAndView.setViewName("/error");
        return modelAndView;
    }

    @Override
    @RequestMapping
    @ResponseBody
    public ResponseEntity<Map<String, Object>> error(HttpServletRequest request) {
        HttpStatus status = getStatus(request);
        Map<String, Object> errorMap = getErrorAttributes(request, false);
        String errorMsg = errorMap.containsKey("message") ? errorMap.get("message").toString() : "";
        Map<String, Object> body = RestResult.error(status.value(), errorMsg);
        return new ResponseEntity<>(body, status);
    }

}

package com.yc.common.config.response;

import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.lang.reflect.InvocationTargetException;

/**
 * 功能描述：封装返回数据
 *
 * <p>版权所有：</p>
 * 未经本人许可，不得以任何方式复制或使用本程序任何部分
 *
 * @Company: 紫色年华
 * @Author: xieyc
 * @Datetime: 2019-08-21 17:58
 * @Version: 1.0.0
 */
@ControllerAdvice
public class GlobalResponseBodyAdviceHandle implements ResponseBodyAdvice<Object> {

    /**
     * 是否对指定接口生效
     *  标注了RestController的接口有效
     * @param returnType  返回类型
     * @param converterType 转化类型
     * @return boolean
     */
    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        RestController restControllerClass = returnType.getDeclaringClass().getAnnotation(RestController.class);
        return restControllerClass != null;
    }

    /**
     * 怎样封装结果集
     * @param obj
     * @param returnType
     * @param selectedContentType
     * @param selectedConverterType
     * @param request
     * @param response
     * @return
     */
    @Override
    public Object beforeBodyWrite(Object obj, MethodParameter returnType,
                                  MediaType selectedContentType,
                                  Class<? extends HttpMessageConverter<?>> selectedConverterType,
                                  ServerHttpRequest request, ServerHttpResponse response) {
        response.getHeaders().setContentType(MediaType.parseMediaType(MediaType.APPLICATION_JSON_UTF8_VALUE));

        Logger log = LoggerFactory.getLogger(returnType.getDeclaringClass());

        String objclassname = obj == null ? null : obj.getClass().getName();

        ResponseBean responseBean = ResponseBean.success();
        if (obj instanceof ResponseBean) {
            responseBean = (ResponseBean) obj;
        }else if (obj != null && objclassname.startsWith("com.baomidou.mybatisplus")) {
            // 判断是否是MyBatisPlus分页对象，是则使用反射获取Page对象属性值封装返回
            Class<?> objectClass = obj.getClass();
            try {
                // 设置数据
                responseBean.data(objectClass.getMethod("getRecords").invoke(obj));

                JSONObject pageJson = new JSONObject();
                // 当前页,兼容之前版本使用pageNum的习惯
                pageJson.put("pageNum", objectClass.getMethod("getCurrent").invoke(obj));
                pageJson.put("pageNo", objectClass.getMethod("getCurrent").invoke(obj));
                // 每页的数量
                pageJson.put("pageSize", objectClass.getMethod("getSize").invoke(obj));
                // 总条数
                pageJson.put("total", objectClass.getMethod("getTotal").invoke(obj));
                // 总页数
                pageJson.put("pages", objectClass.getMethod("getPages").invoke(obj));
                // 是否有上一页
                pageJson.put("hasPrevious", objectClass.getMethod("hasPrevious").invoke(obj));
                // 是否有下一页
                pageJson.put("hasNext", objectClass.getMethod("hasNext").invoke(obj));

                // 设置分页对象数据至page节点
                responseBean.page(pageJson);
            } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                throw new RuntimeException("封装Page对象返回值出错", e);
            }
        } else {
            if (obj != null) {
                responseBean.data(obj);
            }
        }
        log.debug("response : {}", responseBean.toJSONString());
        return responseBean;
    }
}

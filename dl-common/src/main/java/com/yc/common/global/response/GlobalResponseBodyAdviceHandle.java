package com.yc.common.global.response;

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
     *  [在supports指定的接口返回前调用]
     *
     * @param body 返回值
     * @param returnType    控制器方法的返回类型
     * @param selectedContentType 内容类型
     * @param selectedConverterType 转换器类型
     * @param request   当前请求
     * @param response  当前响应
     * @return  传入的主体或修改的(可能是新的)实例
     */
    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType,
                                  MediaType selectedContentType,
                                  Class<? extends HttpMessageConverter<?>> selectedConverterType,
                                  ServerHttpRequest request, ServerHttpResponse response) {
        response.getHeaders().setContentType(MediaType.parseMediaType(MediaType.APPLICATION_JSON_UTF8_VALUE));

        Logger log = LoggerFactory.getLogger(returnType.getDeclaringClass());

        // 通过反射获取返回值全类名
        String bodyclassname = body == null ? null : body.getClass().getName();

        RestResult restResult = RestResult.success();
        // 一个对象是否为一个类的实例
        if (body instanceof RestResult) {
            restResult = (RestResult) body;
        }else if (body != null && bodyclassname.startsWith("com.baomidou.mybatisplus")) {
            // 判断是否是MyBatisPlus分页对象，是则使用反射获取Page对象属性值封装返回
            Class<?> bodyectClass = body.getClass();
            try {
                // 设置数据
                restResult.data(bodyectClass.getMethod("getRecords").invoke(body));

                JSONObject pageJson = new JSONObject();
                // 当前页,兼容之前版本使用pageNum的习惯
                pageJson.put("pageNum", bodyectClass.getMethod("getCurrent").invoke(body));
                pageJson.put("pageNo", bodyectClass.getMethod("getCurrent").invoke(body));
                // 每页的数量
                pageJson.put("pageSize", bodyectClass.getMethod("getSize").invoke(body));
                // 总条数
                pageJson.put("total", bodyectClass.getMethod("getTotal").invoke(body));
                // 总页数
                pageJson.put("pages", bodyectClass.getMethod("getPages").invoke(body));
                // 是否有上一页
                pageJson.put("hasPrevious", bodyectClass.getMethod("hasPrevious").invoke(body));
                // 是否有下一页
                pageJson.put("hasNext", bodyectClass.getMethod("hasNext").invoke(body));

                // 设置分页对象数据至page节点
                restResult.page(pageJson);
            } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                throw new RuntimeException("封装Page对象返回值出错", e);
            }
        } else {
            if (body != null) {
                restResult.data(body);
            }
        }
        log.debug("response : {}", restResult.toJSONString());
        return restResult;
    }
}

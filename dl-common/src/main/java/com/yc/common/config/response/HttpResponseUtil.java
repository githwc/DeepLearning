package com.yc.common.config.response;

import com.alibaba.fastjson.JSON;
import com.yc.common.constant.CommonConstant;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 功能描述：HttpServletResponse 工具类
 * <p>版权所有：</p>
 * 未经本人许可，不得以任何方式复制或使用本程序任何部分
 *
 * @Company: 紫色年华
 * @Author: xieyc
 * @Datetime: 2019-09-28
 * @Version: 1.0.0
 */
@Slf4j
public class HttpResponseUtil {


    /**
     * 发送文本格式到客户端
     *  使用UTF-8编码
     *
     * @param response HttpServletResponse
     * @param text 发送的字符串
     */
    public static void sendText(HttpServletResponse response, String text) {
        render(response, CommonConstant.TEXT_CODE, text);
    }

    /**
     * 发送json格式到客户端
     *  使用UTF-8编码
     *
     * @param response
     * @param text
     */
    public static void sendJson(HttpServletResponse response, String text) {
        render(response, CommonConstant.JSON_CODE, text);
    }


    /**
     * 发送json格式到客户端
     *  使用UTF-8编码
     *
     * @param response
     * @param text 待转换成JSON 的对象
     */
    public static void sendJson(HttpServletResponse response, Object text) {
        render(response, CommonConstant.JSON_CODE, JSON.toJSONString(text));
    }


    /**
     * 发送XML到客户端
     *      使用UTF-8 编码
     *
     * @param response
     * @param text 发送的字符串
     */
    public static void sendXml(HttpServletResponse response, String text) {
        render(response, CommonConstant.XML_CODE, text);
    }

    /**
     * 发送内容。使用UTF-8编码。
     *
     * @param response
     * @param contentType   指定格式
     * @param text  指定格式的内容
     */
    public static void render(HttpServletResponse response, String contentType,
                              String text) {
        response.setContentType(contentType);
        response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        try {
            response.getWriter().write(text);
            response.getWriter().flush();
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
    }
}

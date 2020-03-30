package com.yc.common.global.response;

import com.alibaba.fastjson.JSON;
import com.yc.common.global.error.Error;

import java.util.HashMap;

/**
 * 功能描述：封装返回对象
 *
 * <p>版权所有：</p>
 * 未经本人许可，不得以任何方式复制或使用本程序任何部分
 *
 * @Company: 紫色年华
 * @Author: xieyc
 * @Datetime: 2019-08-22 08:50
 * @Version: 1.0.0
 */
public class RestResult extends HashMap<String,Object> {

    /**
     * 成功
     */
    public static final int SUCCESS = 200;

    @Override
    public RestResult put(String key, Object value) {
        super.put(key, value);
        return this;
    }

    @Override
    public String toString() {
        return this.toJSONString();
    }

    public RestResult(){
        put("code",SUCCESS);
    }

    public static RestResult success(){
        return new RestResult();
    }

    public static RestResult success(String msg, Object... params) {
        RestResult error = new RestResult();
        error.put("msg", RestResult.formatMsg(msg, params));
        return error;
    }

    public static RestResult error(int code, String msg, Object... params) {
        RestResult e = new RestResult();
        e.put("code", code);
        e.put("msg", RestResult.formatMsg(msg, params));
        return e;
    }

    /**
     * 统一设置返回数据至page节点
     * @param data 返回数据
     * @return result
     */
    public RestResult data(Object data) {
        super.put("data", data);
        return this;
    }

    /**
     * 设置分页对象数据至page节点
     * @param page 分页对象
     * @return result
     */
    public RestResult page(Object page) {
        super.put("page", page);
        return this;
    }

    public String toJSONString() {
        return JSON.toJSONString(this);
    }

    /**
     * 格式化消息参数
     *
     * @param msg 消息体
     * @param params 参数
     * @return r
     */
    public static String formatMsg(String msg, Object... params) {
        int i = 0, j;
        StringBuilder content = new StringBuilder(msg.length() + 50);
        for (Object param : params) {
            j = msg.indexOf("{}", i);
            // 找到{}等待替换
            if (j != -1) {
                content.append(msg, i, j).append(param);
                i = j + 2;
            } else {
                break;
            }
        }
        content.append(msg, i, msg.length());
        return content.toString();
    }
}

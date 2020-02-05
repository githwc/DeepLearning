package com.yc.common.config.response;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

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
public class ResponseBean extends HashMap<String,Object> {

    public ResponseBean(){
        put("code", ResponseCode.SUCCESS);
    }

    public static ResponseBean success(){
        return new ResponseBean();
    }


    @Override
    public ResponseBean put(String key, Object value) {
        super.put(key, value);
        return this;
    }

    public static ResponseBean success(String msg, Object... params) {
        ResponseBean error = new ResponseBean();
        error.put("msg", ResultUtils.formatMsg(msg, params));
        return error;
    }

    public static ResponseBean error(int code, String msg, Object... params) {
        ResponseBean e = new ResponseBean();
        e.put("code", code);
        e.put("msg", ResultUtils.formatMsg(msg, params));
        return e;
    }

    public ResponseBean data(Object data) {
        super.put("data", data);
        return this;
    }

    public ResponseBean page(Object page) {
        super.put("page", page);
        return this;
    }

    public ResponseBean userData(Object userData) {
        super.put("userData", userData);
        return this;
    }

    @Override
    public String toString() {
        return this.toJSONString();
    }

    public String toJSONString() {
        return JSON.toJSONString(this);
    }

    public JSONObject toJSONObject() {
        return JSON.parseObject(this.toJSONString());
    }

}

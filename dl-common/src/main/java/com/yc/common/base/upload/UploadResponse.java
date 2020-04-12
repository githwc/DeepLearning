package com.yc.common.base.upload;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import okhttp3.Response;

import java.io.IOException;

/**
 * 功能描述：文件上传结果
 *
 * <p>版权所有：</p>
 * 未经本人许可，不得以任何方式复制或使用本程序任何部分
 *
 * @Company: 紫色年华
 * @Author: xieyc
 * @Datetime: 2020-04-06
 * @Version: 1.0.0
 */
public class UploadResponse {

    private boolean success = false;
    private JSONObject responseBodyJson;

    public UploadResponse(Response response) throws IOException {
        success = response.isSuccessful();
        String responseBodyStr = response.body() == null ? "{}" : response.body().string();
        responseBodyJson = JSON.parseObject(responseBodyStr);
    }

    /**
     * 校验图片上传是否成功
     *
     * @return
     */
    public boolean success() {
        if (!success) {
            return false;
        }
        return responseBodyJson.containsKey("code") &&
                responseBodyJson.getIntValue("code") == 0 && success;
    }

    /**
     * 获取单图片路径，该方法如何调用需要根据是否是多图片上传自行判断
     *
     * @return 图片相对路径
     */
    public String url() {
        JSONArray urlArray = urlJSONArray();
        if (urlArray != null && !urlArray.isEmpty()) {
            return urlArray.getString(0);
        }
        return null;
    }

    /**
     * 返回图片上传后的访问地址相对路径，多张返回集合
     *
     * @return {@link JSONArray}
     */
    public JSONArray urlJSONArray() {
        if (responseBodyJson != null && responseBodyJson.containsKey("data")) {
            return responseBodyJson.getJSONArray("data");
        }
        return null;
    }

    /**
     * 返回图片上传后的访问地址相对路径
     *
     * @return 数组集合
     */
    public String[] urlArray() {
        JSONArray jsonArray = urlJSONArray();
        String[] urlArray = new String[jsonArray.size()];
        return jsonArray.toArray(urlArray);
    }


}

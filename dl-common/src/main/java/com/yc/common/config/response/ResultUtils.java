package com.yc.common.config.response;

/**
 * 功能描述：返回结果工具类
 * <p>版权所有：</p>
 * 未经本人许可，不得以任何方式复制或使用本程序任何部分
 *
 * @Company: 紫色年华
 * @Author: xieyc
 * @Datetime: 2019-09-25
 * @Version: 1.0.0
 */
public class ResultUtils{

    /**
     * 格式化消息
     * @param msg
     * @param params
     * @return
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

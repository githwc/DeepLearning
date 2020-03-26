package com.yc.common.config.error;

/**
 * 功能描述：
 * <p>版权所有：</p>
 * 未经本人许可，不得以任何方式复制或使用本程序任何部分
 *
 * @Company: 紫色年华
 * @Author: xieyc
 * @Datetime: 2020-03-22
 * @Version: 1.0.0
 */
public interface IError {

    /**
     * 返回HTTP状态码
     *
     * @return
     */
    int getHttpStatusCode();

    /**
     * 返回自定义错误码
     *
     * @return
     */
    int getCode();

    /**
     * 获取错误消息
     *
     * @return
     */
    String getMsg();
}

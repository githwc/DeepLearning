package com.yc.common.global.error;

/**
 * 功能描述:
 *
 * @Author: xieyc && 紫色年华
 * @Date: 2020-03-22
 * @Version: 1.0.0
 */
public interface IError {

    /**
     * 返回HTTP状态码
     *
     * @return int
     */
    int getHttpStatusCode();

    /**
     * 返回自定义错误码
     *
     * @return int
     */
    int getCode();

    /**
     * 获取错误消息
     *
     * @return string
     */
    String getMsg();
}

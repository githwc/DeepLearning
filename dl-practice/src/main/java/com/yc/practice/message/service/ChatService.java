package com.yc.practice.message.service;

import com.alibaba.fastjson.JSONObject;

import java.util.List;

/**
 * 功能描述:
 *
 * @Author:  xieyc && 紫色年华
 * @Date 2019-10-08
 * @Version: 1.0.0
 */
public interface ChatService  {

    /**
     * 发送消息
     * @param jsonObject 入参
     */
    List<Object> message(JSONObject jsonObject);

    /**
     * 查看聊天记录
     * @param receiveUserId 接收人
     */
    List<Object> message(String receiveUserId);

}

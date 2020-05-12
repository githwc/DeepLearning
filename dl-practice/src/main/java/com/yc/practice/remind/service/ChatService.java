package com.yc.practice.remind.service;

import com.alibaba.fastjson.JSONObject;

import java.util.List;

/**
 * 功能描述：
 *
 *  <p>版权所有：</p>
 *  未经本人许可，不得以任何方式复制或使用本程序任何部分
 *
 * @Company: LionHerding
 * @Author xieyc
 * @Date 2019-10-08
 * @Version: 1.0.0
 *
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

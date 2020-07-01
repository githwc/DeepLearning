package com.yc.practice.message.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yc.core.message.entity.Message;

/**
 * 功能描述:
 *
 * @Author:  xieyc && 紫色年华
 * @Date 2019-10-08
 * @Version: 1.0.0
 */
public interface MessageService extends IService<Message> {

    /**
     * 发送消息给指定人
     *
     *  userId: 接收人
     *  content:消息内容
     *  level: 消息级别(0:INFO 1.WARNING 2.ERROR)
     *  type: 消息类型(0:通知公告 1: 系统消息)
     *  rid: 关联ID
     */
    void sendUser(String userId, String content, int level, int type, String rid);

    /**
     * 群发消息
     * @param message 消息内容
     */
    void sendAllUser(Message message);



}

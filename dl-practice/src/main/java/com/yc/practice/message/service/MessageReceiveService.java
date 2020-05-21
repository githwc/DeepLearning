package com.yc.practice.message.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yc.core.message.entity.MessageReceive;

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
public interface MessageReceiveService extends IService<MessageReceive> {

    /**
     * 记录我的接受消息
     * @param userId    用户ID
     * @param messageId 消息ID
     * @param receiveFlag
     */
    void insertRecord(String userId, String messageId, boolean receiveFlag);

    /**
     * 标识已读
     */
    void readMessage(String userId, String messageId);

}

package com.yc.practice.remind.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yc.core.remind.entity.RemindMessageReceive;

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
public interface RemindMessageReceiveService extends IService<RemindMessageReceive> {

    /**
     * 记录我的接受消息
     * @param userId
     * @param messageId
     */
    void insertRecord(String userId, String messageId, boolean receiveFlag);

    /**
     * 标识已读
     */
    void readMessage(String userId, String messageId);

}

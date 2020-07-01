package com.yc.practice.message.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yc.core.message.entity.MessageReceive;
import com.yc.core.message.model.query.MessageReceiveQuery;
import com.yc.core.message.model.vo.NoticeVO;

/**
 * 功能描述:
 *
 * @Author:  xieyc && 紫色年华
 * @Date 2019-10-08
 * @Version: 1.0.0
 */
public interface MessageReceiveService extends IService<MessageReceive> {

    /**
     * 我的消息分页信息
     * @param page 分页信息
     * @param query 入参
     * @return page
     */
    Page<NoticeVO> pageNotice(Page<NoticeVO> page, MessageReceiveQuery query);

    /**
     * 标注已读
     * @param messageReceive 消息内容
     */
    void readMessage(MessageReceive messageReceive);

    /**
     * 记录我的接受消息
     * @param userId    用户ID
     * @param messageId 消息ID
     * @param receiveFlag
     */
    void insertRecord(String userId, String messageId, boolean receiveFlag);

}

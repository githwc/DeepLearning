package com.yc.practice.message.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yc.core.message.entity.MessageReceive;
import com.yc.core.message.mapper.MessageReceiveMapper;
import com.yc.core.message.model.query.MessageReceiveQuery;
import com.yc.core.message.model.vo.NoticeVO;
import com.yc.practice.common.UserUtil;
import com.yc.practice.message.service.MessageReceiveService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 功能描述:
 *
 * @Author: xieyc && 紫色年华
 * @Date 2019-10-08
 * @Version: 1.0.0
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class MessageReceiveServiceImpl extends ServiceImpl<MessageReceiveMapper, MessageReceive> implements MessageReceiveService {

    @Override
    public Page<NoticeVO> pageNotice(Page<NoticeVO> page, MessageReceiveQuery query) {
        query.setUserId(UserUtil.getUserId());
        return this.baseMapper.pageNotice(page, query);
    }

    @Override
    public void readMessage(MessageReceive messageReceive) {
        messageReceive.setReadState(1);
        this.baseMapper.updateById(messageReceive);
    }

    @Override
    public void insertRecord(String userId, String messageId, boolean receiveState) {
        MessageReceive remindMessageReceive = new MessageReceive();
        remindMessageReceive.setMessageId(messageId);
        remindMessageReceive.setUserId(userId);
        remindMessageReceive.setReceiveState(receiveState);
        this.baseMapper.insert(remindMessageReceive);
    }

}

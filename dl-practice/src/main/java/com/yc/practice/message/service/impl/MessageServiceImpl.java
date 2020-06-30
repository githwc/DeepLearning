package com.yc.practice.message.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yc.common.constant.CommonEnum;
import com.yc.common.webSocket.WebSocket;
import com.yc.core.message.entity.Message;
import com.yc.core.message.entity.MessageReceive;
import com.yc.core.message.mapper.MessageMapper;
import com.yc.core.system.entity.SysUser;
import com.yc.core.system.mapper.SysUserMapper;
import com.yc.practice.common.UserUtil;
import com.yc.practice.message.service.MessageReceiveService;
import com.yc.practice.message.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

/**
* 功能描述:
*
* @Author:  xieyc && 紫色年华
* @Date 2019-10-08
* @Version: 1.0.0
*/
@Service
@Transactional(rollbackFor = Exception.class)
public class MessageServiceImpl extends ServiceImpl<MessageMapper, Message> implements MessageService {

    private WebSocket webSocket;
    private MessageReceiveService messageReceiveService;
    private SysUserMapper sysUserMapper;

    @Autowired
    public MessageServiceImpl(WebSocket webSocket, MessageReceiveService messageReceiveService,SysUserMapper sysUserMapper){
        this.webSocket = webSocket;
        this.sysUserMapper = sysUserMapper;
        this.messageReceiveService = messageReceiveService;
    }


    @Override
    public void sendUser(String userId, String content,int level,int type,String rid) {
        // 记录推送消息
        Message remindMessage = new Message();
        remindMessage.setCreateTime(LocalDateTime.now());
        remindMessage.setLevel(level);
        remindMessage.setTitle("单点定向推送");
            remindMessage.setContent(content);
        remindMessage.setReceiveType(CommonEnum.ReceiveType.SINGLE_USER.getCode());
        remindMessage.setType(type);
        remindMessage.setRid(rid);
        remindMessage.setSendState(CommonEnum.SendState.SEND.getCode());
        remindMessage.setSendTime(LocalDateTime.now());
        remindMessage.setCreateUser(UserUtil.getUser().getUserName());
        remindMessage.setCreateUserId(UserUtil.getUserId());
        int result = this.baseMapper.insert(remindMessage);
        JSONObject obj = new JSONObject();
        //消息内容
        obj.put("content", content);
        boolean flag = webSocket.sendOneMessage(userId, obj.toJSONString());
        if(result > 0){
            messageReceiveService.insertRecord(userId,remindMessage.getMessageId(),flag);
        }
    }

    @Override
    public void sendAllUser(Message message) {
        // 记录推送消息
        message.setReceiveType(1);
        message.setSendState(1);
        message.setSendTime(LocalDateTime.now());
        message.setCreateUserId(UserUtil.getUserId());
        message.setCreateUser(UserUtil.getUser().getUsername());
        this.baseMapper.insert(message);
        List<SysUser> userList = this.sysUserMapper.selectList(new LambdaQueryWrapper<SysUser>()
            .eq(SysUser::getDelFlag,0)
                .eq(SysUser::getState,0)
        ) ;
        List<MessageReceive> messageReceives = new LinkedList<>();
        for (SysUser user : userList) {
            MessageReceive messageReceive = new MessageReceive();
            messageReceive.setUserId(user.getSysUserId());
            messageReceive.setMessageId(message.getMessageId());
            messageReceives.add(messageReceive);
        }
        this.messageReceiveService.saveBatch(messageReceives);
        // 实时推送
        JSONObject obj = new JSONObject();
        obj.put("content", "您有一条新的消息!");
        webSocket.sendAllMessage(obj.toJSONString());
    }


}

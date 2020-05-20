package com.yc.practice.message.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yc.common.constant.CommonEnum;
import com.yc.common.webSocket.WebSocket;
import com.yc.core.message.entity.Message;
import com.yc.core.message.mapper.MessageMapper;
import com.yc.practice.common.UserUtil;
import com.yc.practice.message.service.MessageReceiveService;
import com.yc.practice.message.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

/**
* 功能描述：
*
*  <p>版权所有：</p>
*  未经本人许可，不得以任何方式复制或使用本程序任何部分
*
* @Company: 紫色年华
* @Author xieyc
* @Date 2019-10-08
* @Version: 1.0.0
*
*/
@Service
@Transactional(rollbackFor = Exception.class)
public class MessageServiceImpl extends ServiceImpl<MessageMapper, Message> implements MessageService {

    private WebSocket webSocket;
    private MessageReceiveService remindMessageReceiveService;

    @Autowired
    public MessageServiceImpl(WebSocket webSocket, MessageReceiveService remindMessageReceiveService){
        this.webSocket = webSocket;
        this.remindMessageReceiveService = remindMessageReceiveService;
    }


    @Override
    public void sendUser(String userId, String content,int level,int type,String rid) {
        JSONObject obj = new JSONObject();
        //消息内容
        obj.put("content", content);
        boolean flag = webSocket.sendOneMessage(userId, obj.toJSONString());
        /*记录推送消息*/
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
        if(result > 0){
            remindMessageReceiveService.insertRecord(userId,remindMessage.getMessageId(),flag);
        }
    }

    @Override
    public void sendAllUser(String content,int level,int type,String rid) {
        JSONObject obj = new JSONObject();
        obj.put("content", content);
        webSocket.sendAllMessage(obj.toJSONString());
        /*记录推送消息*/
        Message remindMessage = new Message();
        remindMessage.setCreateTime(LocalDateTime.now());
        remindMessage.setLevel(level);
        remindMessage.setTitle("群发推送");
        remindMessage.setContent(content);
        remindMessage.setReceiveType(CommonEnum.ReceiveType.All_USER.getCode());
        remindMessage.setType(type);
        remindMessage.setRid(rid);
        remindMessage.setSendState(CommonEnum.SendState.SEND.getCode());
        remindMessage.setSendTime(LocalDateTime.now());
        remindMessage.setCreateUser(UserUtil.getUser().getUserName());
        remindMessage.setCreateUserId(UserUtil.getUserId());
        this.baseMapper.insert(remindMessage);
        // TODO: 2020/5/20 循环存入接收人
    }


}

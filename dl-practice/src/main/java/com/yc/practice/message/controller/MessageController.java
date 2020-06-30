package com.yc.practice.message.controller;

import com.alibaba.fastjson.JSONObject;
import com.yc.common.constant.CommonConstant;
import com.yc.core.message.entity.Message;
import com.yc.practice.common.log.WriteLog;
import com.yc.practice.message.service.MessageService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * 功能描述:消息提醒
 *
 * @Author:  xieyc && 紫色年华
 * @Date 2019-10-08
 * @Version: 1.0.0
 */
@RestController
@RequestMapping("/message")
@Slf4j
@Api(tags = "消息提醒")
public class MessageController {

    private final MessageService iRemindMessageService;

    @Autowired
    public MessageController(MessageService iRemindMessageService) {
        this.iRemindMessageService = iRemindMessageService;
    }

    @PostMapping("/sendUser")
    @ApiOperation(value = "发送消息(点对点)", notes = "发送消息给具体的某个人")
    @WriteLog(opPosition = "发送消息(单点)" ,optype = CommonConstant.OPTYPE_CREATE)
    public void sendUser(@RequestBody JSONObject jsonObject){
        iRemindMessageService.sendUser(jsonObject.getString("userId"),
                jsonObject.getString("content"),
                jsonObject.getInteger("level"),
                jsonObject.getInteger("type"),
                jsonObject.getString("rid")
        );
    }

    @PostMapping("/sendAll")
    @ApiOperation(value = "群发消息", notes = "发送消息给多个人")
    @WriteLog(opPosition = "群发消息" ,optype = CommonConstant.OPTYPE_CREATE)
    public void sendAll(@RequestBody Message message){
        iRemindMessageService.sendAllUser(message);
    }


}

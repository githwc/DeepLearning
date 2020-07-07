package com.yc.practice.message.controller;

import com.alibaba.fastjson.JSONObject;
import com.yc.practice.message.service.ChatService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 功能描述:聊天控制层
 *
 * @Author: xieyc && 紫色年华
 * @Date: 2020-05-08
 * @Version: 1.0.0
 */
@RestController
@RequestMapping("/chat")
@Slf4j
public class ChatController {

    private final ChatService chatService;

    @Autowired
    public ChatController(ChatService chatService) {
        this.chatService = chatService;
    }

    /**
     * 发送消息
     *
     * @param jsonObject 入参
     *                   [content,receiveUserId]
     */
    @PostMapping("/sendMessage")
    public List<Object> message(@RequestBody JSONObject jsonObject) {
        return chatService.message(jsonObject);
    }

    /**
     * 查看聊天记录
     *
     * @param receiveUserId 接收人
     */
    @GetMapping("/init")
    public List<Object> message(String receiveUserId) {
        return chatService.message(receiveUserId);
    }
}

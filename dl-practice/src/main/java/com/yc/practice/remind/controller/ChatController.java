package com.yc.practice.remind.controller;

import com.alibaba.fastjson.JSONObject;
import com.yc.practice.remind.service.ChatService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 功能描述：聊天控制层
 * <p>版权所有：</p>
 * 未经本人许可，不得以任何方式复制或使用本程序任何部分
 *
 * @Company: 紫色年华
 * @Author: xieyc
 * @Datetime: 2020-05-08
 * @Version: 1.0.0
 */
@RestController
@RequestMapping("/chat")
@Slf4j
public class ChatController {

    private final ChatService chatService;

    @Autowired
    public ChatController (ChatService chatService){
        this.chatService = chatService;
    }

    /**
     * 发送消息
     * @param jsonObject 入参
     */
    @PostMapping("/sendMessage")
    public List<Object> message(@RequestBody JSONObject jsonObject) {
       return chatService.message(jsonObject);
    }

    /**
     * 查看聊天记录
     * @param receiveUserId 接收人
     */
    @GetMapping("/init")
    public List<Object> message(@RequestParam("receiveUserId") String receiveUserId) {
       return chatService.message(receiveUserId);
    }
}

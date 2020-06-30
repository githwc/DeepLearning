package com.yc.practice.message.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yc.core.message.entity.MessageReceive;
import com.yc.core.message.model.query.MessageReceiveQuery;
import com.yc.core.message.model.vo.NoticeVO;
import com.yc.practice.message.service.MessageReceiveService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 *
 * 功能描述:消息提醒接受表
 *
 * @Author:  xieyc && 紫色年华
 * @Date 2019-10-08
 * @Version: 1.0.0
 */
@RestController
@RequestMapping("/messageReceive")
@Slf4j
@Api(tags = "消息提醒接收")
public class MessageReceiveController {

    private final MessageReceiveService service;

    @Autowired
    public MessageReceiveController(MessageReceiveService service) {
        this.service = service;
    }

    /**
     * 我的消息分页信息
     * @param page 分页信息
     * @param query 入参
     * @return page
     */
    @GetMapping
    public Page<NoticeVO> pageNotice(Page<NoticeVO> page, MessageReceiveQuery query){
        return service.pageNotice(page,query);
    }

    /**
     * 标注已读
     * @param messageReceive 消息内容
     */
    @PutMapping
    public void readMessage(@RequestBody MessageReceive messageReceive){
        service.readMessage(messageReceive);
    }

}

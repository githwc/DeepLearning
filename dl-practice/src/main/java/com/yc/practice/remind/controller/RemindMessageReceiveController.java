package com.yc.practice.remind.controller;

import com.yc.practice.remind.service.RemindMessageReceiveService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * 功能描述：消息提醒接受表
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
@RestController
@RequestMapping("/remindMessageReceive")
@Slf4j
@Api(tags = "消息提醒接收")
public class RemindMessageReceiveController {

    private final RemindMessageReceiveService service;

    @Autowired
    public RemindMessageReceiveController(RemindMessageReceiveService service) {
        this.service = service;
    }
}

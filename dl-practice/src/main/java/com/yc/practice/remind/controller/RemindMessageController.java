package com.yc.practice.remind.controller;

import com.alibaba.fastjson.JSONObject;
import com.yc.common.constant.CommonConstant;
import com.yc.practice.common.log.WriteLog;
import com.yc.practice.remind.service.RemindMessageService;
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
 * 功能描述：消息提醒
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
@RequestMapping("/remindMessage")
@Slf4j
@Api(tags = "消息提醒")
public class RemindMessageController {

    private final RemindMessageService iRemindMessageService;

    @Autowired
    public RemindMessageController(RemindMessageService iRemindMessageService) {
        this.iRemindMessageService = iRemindMessageService;
    }

    @PostMapping("/sendUser")
    @ApiOperation(value = "发送消息(点对点)", notes = "发送消息给具体的某个人")
    @WriteLog(opPosition = "发送消息(单点)" ,optype = CommonConstant.OPTYPE_CREATE)
    public void sendUser(@RequestBody JSONObject jsonObject){
        iRemindMessageService.sendUser(jsonObject.getString("userId"),
                jsonObject.getString("content"),
                jsonObject.getString("level"),
                jsonObject.getString("type"),
                jsonObject.getString("modelType"),
                jsonObject.getString("rid")
        );
    }


    @PostMapping("/sendAll")
    @ApiOperation(value = "群发消息", notes = "发送消息给多个人")
    @WriteLog(opPosition = "群发消息" ,optype = CommonConstant.OPTYPE_CREATE)
    public void sendAll(@RequestBody JSONObject jsonObject){
        iRemindMessageService.sendAllUser(jsonObject.getString("content"),
                jsonObject.getString("level"),
                jsonObject.getString("type"),
                jsonObject.getString("modelType"),
                jsonObject.getString("rid")
        );
    }
}

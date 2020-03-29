package com.yc.practice.scheduled.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yc.common.webSocket.WebSocket;
import com.yc.core.TestTemp.entity.Test;
import com.yc.core.TestTemp.mapper.TestMapper;
import com.yc.practice.scheduled.service.ScheduledService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
* 功能描述：
*
*  <p>版权所有：</p>
*  未经本人许可，不得以任何方式复制或使用本程序任何部分
*
* @Company: 紫色年华
* @Author xieyc
* @Date 2019-09-29
* @Version: 1.0.0
*
*/
@Service
@Transactional(rollbackFor = Exception.class)
public class ScheduledServiceImpl extends ServiceImpl<TestMapper, Test> implements ScheduledService {

    private final WebSocket webSocket;

    @Autowired
    public ScheduledServiceImpl(WebSocket webSocket) {
        this.webSocket = webSocket;
    }

    @Override
    public void pushMessage() {
        JSONObject obj = new JSONObject();
        //消息内容
        obj.put("content", "欢迎来到新的世界！");
        webSocket.sendOneMessage("99999", obj.toJSONString());
    }
}

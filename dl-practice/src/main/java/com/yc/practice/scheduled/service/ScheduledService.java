package com.yc.practice.scheduled.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yc.core.TestTemp.entity.Test;

/**
 * 功能描述：
 *
 *  <p>版权所有：</p>
 *  未经本人许可，不得以任何方式复制或使用本程序任何部分
 *
 * @Company: LionHerding
 * @Author xieyc
 * @Date 2019-09-29
 * @Version: 1.0.0
 *
 */
public interface ScheduledService extends IService<Test> {

    /**
     * 定时推送消息
     */
    void pushMessage();
}

package com.yc.practice.timer.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yc.core.timer.entity.TimerRecord;

/**
 * 功能描述:
 *
 * @Author:  xieyc && 紫色年华
 * @Date 2020-04-16
 * @Version: 1.0.0
 */
public interface TimerRecordService extends IService<TimerRecord> {

    /**
     * 订单超时检查
     */
    void orderCheck();
}

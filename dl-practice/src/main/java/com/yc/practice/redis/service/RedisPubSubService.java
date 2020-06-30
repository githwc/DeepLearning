package com.yc.practice.redis.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yc.core.redispractice.entity.RedisPubSub;

/**
 * 功能描述:
 *
 * @Author:  xieyc && 紫色年华
 * @Date 2020-01-29
 * @Version: 1.0.0
 */
public interface RedisPubSubService extends IService<RedisPubSub> {

    /**
     * 消息发布
     */
    void sendMessage();

    /**
     * 发布订阅信息分页查询
     * @param page 分页信息
     * @return page
     */
    Page<RedisPubSub> pubSubPage(Page<RedisPubSub> page);


}

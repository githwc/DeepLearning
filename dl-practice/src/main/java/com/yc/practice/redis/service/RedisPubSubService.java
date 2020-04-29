package com.yc.practice.redis.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yc.core.redisPractice.entity.RedisPubSub;

/**
 * 功能描述：
 *
 *  <p>版权所有：</p>
 *  未经本人许可，不得以任何方式复制或使用本程序任何部分
 *
 * @Company: 紫色年华
 * @Author xieyc
 * @Date 2020-01-29
 * @Version: 1.0.0
 *
 */
public interface RedisPubSubService extends IService<RedisPubSub> {

    void sendMessage();

    Page<RedisPubSub> pubSubPage(Page<RedisPubSub> page);


}

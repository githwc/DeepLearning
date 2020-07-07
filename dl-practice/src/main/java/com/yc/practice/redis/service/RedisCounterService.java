package com.yc.practice.redis.service;


/**
 * 功能描述:
 *
 * @Author: xieyc && 紫色年华
 * @Date 2020-02-01
 * @Version: 1.0.0
 */
public interface RedisCounterService {

    /**
     * 导入设备，实现序列号自增
     *
     * @return 序列号
     */
    String importDevice();

}

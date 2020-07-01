package com.yc.practice.redis.controller;

import com.yc.practice.redis.service.RedisCounterService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 功能描述: 计数器控制层
 *
 * @Author: xieyc && 紫色年华
 * @Date: 2020-02-02
 * @Version: 1.0.0
 */
@RestController
@RequestMapping("/redisPractice/redisCounter")
@Slf4j
public class RedisCounterController {

    private final RedisCounterService redisCounterService;

    @Autowired
    public RedisCounterController(RedisCounterService redisCounterService) {
        this.redisCounterService = redisCounterService;
    }

    /**
     * 实现自增序列号
     * 每次+1，格式:YC + 日期 + 五位数字
     * 过期时间一分钟
     * 限流 一分钟内不能超过5次
     */
    @PostMapping("/importDevice")
    public String importDevice(){
        return redisCounterService.importDevice();
    }
}

package com.yc.practice.redis.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yc.core.redispractice.entity.RedisPubSub;
import com.yc.core.redispractice.mapper.RedisPubSubMapper;
import com.yc.practice.common.UserUtil;
import com.yc.practice.redis.service.RedisPubSubService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 功能描述:
 *
 * @Author: xieyc && 紫色年华
 * @Date 2020-01-29
 * @Version: 1.0.0
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class RedisPubSubServiceImpl extends ServiceImpl<RedisPubSubMapper, RedisPubSub> implements RedisPubSubService {

    private final RedisTemplate redisTemplate;

    @Autowired
    public RedisPubSubServiceImpl(RedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Override
    public void sendMessage() {
        String channel = "goodLucky";
        String content = "欢迎来到新世界!";
        RedisPubSub redisPubSub = new RedisPubSub();
        redisPubSub.setChannel(channel);
        redisPubSub.setContent(content);
        redisPubSub.setCreateUserId(UserUtil.getUserId());
        int result = this.baseMapper.insert(redisPubSub);
        if (result > 0) {
            redisTemplate.convertAndSend(channel, content);
        }
    }

    @Override
    public Page<RedisPubSub> pubSubPage(Page<RedisPubSub> page) {
        return baseMapper.selectPage(page, Wrappers.<RedisPubSub>lambdaQuery()
                .orderByDesc(RedisPubSub::getCreateTime)
        );
    }


}

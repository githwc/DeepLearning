package com.yc.practice.redisPractice.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yc.common.dao.DaoApi;
import com.yc.core.redisPractice.entity.RedisPubSub;
import com.yc.core.redisPractice.mapper.RedisPubSubMapper;
import com.yc.practice.redisPractice.service.RedisPubSubService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
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
* @Date 2020-01-29
* @Version: 1.0.0
*
*/
@Service
@Transactional(rollbackFor = Exception.class)
public class RedisPubSubServiceImpl extends ServiceImpl<RedisPubSubMapper, RedisPubSub> implements RedisPubSubService {

    private final RedisTemplate redisTemplate;
    private final DaoApi daoApi;

    @Autowired
    public RedisPubSubServiceImpl (RedisTemplate redisTemplate, DaoApi daoApi){
        this.redisTemplate = redisTemplate;
        this.daoApi = daoApi;
    }

    @Override
    public void sendMessage() {
        String channel = "goodLucky";
        String content = "欢迎来到新世界!";
        RedisPubSub redisPubSub = new RedisPubSub();
        redisPubSub.setChannel(channel);
        redisPubSub.setContent(content);
        redisPubSub.setCreateUserId(daoApi.getCurrentUserId());
        this.baseMapper.insert(redisPubSub);
        redisTemplate.convertAndSend(channel,content);
    }

    @Override
    public Page<RedisPubSub> pubSubPage(Page<RedisPubSub> page) {
        return this.baseMapper.pubSubPage(page);
    }


}

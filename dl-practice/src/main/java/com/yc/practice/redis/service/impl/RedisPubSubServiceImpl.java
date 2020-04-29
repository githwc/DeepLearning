package com.yc.practice.redis.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yc.core.redisPractice.entity.RedisPubSub;
import com.yc.core.redisPractice.mapper.RedisPubSubMapper;
import com.yc.practice.common.UserUtil;
import com.yc.practice.redis.service.RedisPubSubService;
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

    @Autowired
    public RedisPubSubServiceImpl (RedisTemplate redisTemplate){
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
        if(result > 0 ){
            redisTemplate.convertAndSend(channel,content);
        }
    }

    @Override
    public Page<RedisPubSub> pubSubPage(Page<RedisPubSub> page) {
        return this.baseMapper.pubSubPage(page);
    }


}

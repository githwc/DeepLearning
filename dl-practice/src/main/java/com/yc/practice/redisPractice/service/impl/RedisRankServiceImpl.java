package com.yc.practice.redisPractice.service.impl;

import com.yc.practice.redisPractice.constant.RedisConstant;
import com.yc.practice.redisPractice.service.RedisRankService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.DefaultTypedTuple;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
* 功能描述：
*
*  <p>版权所有：</p>
*  未经本人许可，不得以任何方式复制或使用本程序任何部分
*
* @Company: 紫色年华
* @Author xieyc
* @Date 2020-02-01
* @Version: 1.0.0
*
*/
@Service
@Transactional(rollbackFor = Exception.class)
@Slf4j
public class RedisRankServiceImpl implements RedisRankService {

    private final RedisTemplate redisTemplate;

    @Autowired
    public RedisRankServiceImpl (RedisTemplate redisTemplate){
        this.redisTemplate = redisTemplate;
    }

    @Override
    public Set getData() {
        String key = RedisConstant.SCORE_BANK ;
        if(redisTemplate.hasKey(key)){
            Long aLong = redisTemplate.opsForZSet().zCard(key);
            Set<ZSetOperations.TypedTuple<String>> range =
                    redisTemplate.opsForZSet().reverseRangeWithScores(key,0,aLong -1);
            return range;
        }else{
            return null;
        }
    }

    @Override
    public void clearData() {
        redisTemplate.delete(RedisConstant.SCORE_BANK);
    }

    @Override
    public void initRankData() {
        String score_key = RedisConstant.SCORE_BANK;
        redisTemplate.delete(score_key);

        Set<ZSetOperations.TypedTuple<String>> scoreTuples = new HashSet<>();
        for(int i = 0; i< 200;i++){
            // (int)((Math.random()*9+1)*10) 生成两位随机数字
            DefaultTypedTuple<String> scoreTuple = new DefaultTypedTuple<>("乔治_"+ i,
                    (double)((int)((Math.random()*9+1)*10)));
            scoreTuples.add(scoreTuple);
        }
        redisTemplate.opsForZSet().add(score_key, scoreTuples);
        log.info("redis排行榜初始化完成");
    }


    @Override
    public Set top10(String type) {
        String key = RedisConstant.SCORE_BANK ;
        if(redisTemplate.hasKey(key)){
            Set<ZSetOperations.TypedTuple<String>> range =
                    redisTemplate.opsForZSet().reverseRangeWithScores(key,0,9);
            return range;
        }else{
            return null;
        }
    }

    @Override
    public void add() {
        String key = RedisConstant.SCORE_BANK ;
        Long aLong = redisTemplate.opsForZSet().zCard(key);
        redisTemplate.opsForZSet().add(key, "保罗_"+aLong, 289);
    }

    @Override
    public Map userInfo() {
        String key = RedisConstant.SCORE_BANK ;
        if(redisTemplate.hasKey(key)){
            Map<String,Object> map = new HashMap<>(16);
            Long rankNum = redisTemplate.opsForZSet().reverseRank(key, "乔治_1");
            map.put("rankNum",rankNum + 1);
            Double score = redisTemplate.opsForZSet().score(key, "乔治_1");
            map.put("score",score);
            return map;
        }else{
            return null;
        }
    }

    @Override
    public Long scopeCount() {
        String key = RedisConstant.SCORE_BANK ;
        if(redisTemplate.hasKey(key)) {
            return redisTemplate.opsForZSet().count(key, 50, 80);
        }else{
            return null;
        }
    }

    @Override
    public void addScore() {
        String key = RedisConstant.SCORE_BANK ;
        if(redisTemplate.hasKey(key)) {
            Double score = redisTemplate.opsForZSet().incrementScore(key, "乔治_1", 10);
            log.info("乔治1添加500后的分数为:"+score);
        }
    }


}

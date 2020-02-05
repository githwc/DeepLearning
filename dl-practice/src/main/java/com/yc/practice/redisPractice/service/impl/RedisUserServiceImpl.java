package com.yc.practice.redisPractice.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yc.core.redisPractice.entity.RedisUser;
import com.yc.core.redisPractice.mapper.RedisUserMapper;
import com.yc.core.redisPractice.model.RedisUserQuery;
import com.yc.practice.redisPractice.constant.RedisConstant;
import com.yc.practice.redisPractice.service.RedisUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.TimeUnit;

/**
* 功能描述：
*
*  <p>版权所有：</p>
*  未经本人许可，不得以任何方式复制或使用本程序任何部分
*
* @Company: 紫色年华
* @Author xieyc
* @Date 2020-01-19
* @Version: 1.0.0
*
*/
@Service
@Transactional(rollbackFor = Exception.class)
public class RedisUserServiceImpl extends ServiceImpl<RedisUserMapper, RedisUser> implements RedisUserService {

    private final RedisTemplate redisTemplate;

    @Autowired
    public RedisUserServiceImpl (RedisTemplate redisTemplate){
        this.redisTemplate = redisTemplate;
    }

    @Override
    public Page<RedisUser> userPage(Page<RedisUser> page, RedisUserQuery query) {
        return this.baseMapper.userPage(page,query);
    }

    /**
     * 获取信息策略：先从缓存中获取用户，没有则取数据表中取数据，再将数据写入缓存
     * @param id 用户ID
     * @return
     */
    @Override
    public RedisUser findUserById(String id) {
        String key = RedisConstant.USER_BY_ID_ + id;
        ValueOperations<String,RedisUser> operations = redisTemplate.opsForValue();
        Long a = 10L;
        // 判断redis中是否有键为key的缓存
        if(redisTemplate.hasKey(key)){
            //从缓存中获得数据
            return operations.get(key);
        }else{
            RedisUser redisUser = this.baseMapper.selectById(id);
            // 写入缓存
            operations.set(RedisConstant.USER_BY_ID_+redisUser.getRedisUserId(),redisUser);
            return redisUser;
        }
    }


    /**
     * 增加信息策略:先增加数据,成功后,存入缓存
     * @param redisUser 用户信息
     */
    @Override
    public void add(RedisUser redisUser) {
        redisUser.setDelFlag(0);
        int result = this.baseMapper.insert(redisUser);
        if(result > 0){
            ValueOperations operations = redisTemplate.opsForValue();
            operations.set(RedisConstant.USER_BY_ID_ + redisUser.getRedisUserId(),this.baseMapper.selectById(redisUser.getRedisUserId()));
        }
    }

    /**
     * 更新信息策略:先更新数据表，成功之后，删除原来的缓存，再更新缓存
     * @param redisUser 用户信息
     */
    @Override
    public void updateUser(RedisUser redisUser) {
        int result = this.baseMapper.updateById(redisUser);
        if(result > 0){
            ValueOperations operations = redisTemplate.opsForValue();
            String key = RedisConstant.USER_BY_ID_ + redisUser.getRedisUserId();
            if(redisTemplate.hasKey(key)){
                redisTemplate.delete(key);
            }
            RedisUser redisUser_cache = this.baseMapper.selectById(redisUser.getRedisUserId());
            if(ObjectUtil.isNotNull(redisUser_cache)){
                operations.set(key,redisUser_cache);
            }
        }
    }

    @Override
    public void deleteUserById(String id) {
        // 删除用户策略：删除数据表中数据，成功后,然后删除缓存
        RedisUser redisUser = new RedisUser();
        redisUser.setDelFlag(1);
        int result = this.baseMapper.update(redisUser,new LambdaQueryWrapper<RedisUser>()
            .eq(RedisUser::getRedisUserId,id)
        );
        if(result > 0){
            String key = RedisConstant.USER_BY_ID_ + id;
            if(redisTemplate.hasKey(key)){
                redisTemplate.delete(key);
            }
        }
    }

    @Override
    public void setExpireTime(RedisUser redisUser) {
        int result = this.baseMapper.updateById(redisUser);
        if(result > 0){
            ValueOperations operations = redisTemplate.opsForValue();
            String key = RedisConstant.USER_BY_ID_ + redisUser.getRedisUserId();
            if(redisTemplate.hasKey(key)){
                redisTemplate.delete(key);
            }
            RedisUser redisUser_cache = this.baseMapper.selectById(redisUser.getRedisUserId());
            if(ObjectUtil.isNotNull(redisUser_cache)){
                // 放入缓存并设置超时时间
                operations.set(key, redisUser_cache,1, TimeUnit.MINUTES);
            }
        }
    }

    @Override
    public boolean expireState(String redisUserId) {
        String key = RedisConstant.USER_BY_ID_ + redisUserId;
        if(redisTemplate.hasKey(key)){
            return true;
        }
        return false;
    }

}

package com.yc.practice.redis.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yc.core.redispractice.entity.RedisUser;
import com.yc.core.redispractice.model.RedisUserQuery;
import com.yc.practice.redis.service.RedisUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 *
 * 功能描述:redis Practice API 暴露接口
 *
 * @Author:  xieyc && 紫色年华
 * @Date 2020-01-19
 * @Version: 1.0.0
 */
@RestController
@RequestMapping("/redisPractice/redisUser")
@Slf4j
public class RedisUserController {

    private final RedisUserService iRedisUserService;

    @Autowired
    public RedisUserController(RedisUserService iRedisUserService) {
        this.iRedisUserService = iRedisUserService;
    }

    /**
     * ========= Redis Cache 1.0 START ===========
     */
    @GetMapping("/userPage")
    public Page<RedisUser> userPage(Page<RedisUser> page, RedisUserQuery query){
        return iRedisUserService.userPage(page,query);
    }

    @GetMapping("/findUserById")
    public RedisUser findUserById(String redisUserId){
        return iRedisUserService.findUserById(redisUserId);
    }

    @PostMapping("/add")
    public void add(@RequestBody RedisUser redisUser){
        iRedisUserService.add(redisUser);
    }

    @PutMapping("/updateUser")
    public void updateUser(@RequestBody RedisUser redisUser){
        iRedisUserService.updateUser(redisUser);
    }

    @DeleteMapping("/deleteUserById")
    public void deleteUserById(String redisUserId){
        iRedisUserService.deleteUserById(redisUserId);
    }

    /**
     * ========= Redis Cache 2.0 (缓存过期时间) START ===========
     */

    /**
     * 设置缓存过期时间
     * @param redisUser
     */
    @PostMapping("/setExpireTime")
    public void setExpireTime(@RequestBody RedisUser redisUser){
        iRedisUserService.setExpireTime(redisUser);
    }

    /**
     * 查看该数据缓存是否过期
     * @param redisUserId 主键
     * @return boolean true: 过期 false: 有效
     */
    @GetMapping("/expireState")
    public boolean expireState(String redisUserId){
       return iRedisUserService.expireState(redisUserId);
    }

}

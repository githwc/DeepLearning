package com.yc.practice.redis.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yc.core.redispractice.entity.RedisUser;
import com.yc.core.redispractice.model.RedisUserQuery;

/**
 * 功能描述:
 *
 * @Author:  xieyc && 紫色年华
 * @Date 2020-01-19
 * @Version: 1.0.0
 */
public interface RedisUserService extends IService<RedisUser> {

    /**
     * 获取所有用户信息
     * @return
     */
    Page<RedisUser> userPage(Page<RedisUser> page, RedisUserQuery query);

    /**
     * 根据用户ID 查询
     * @param id 用户ID
     * @return
     */
    RedisUser findUserById(String id);

    /**
     * 新增用户
     * @param redisUser 用户信息
     * @return
     */
    void add(RedisUser redisUser);

    /**
     * 修改用户
     * @param redisUser 用户信息
    * @return
     */
    void updateUser(RedisUser redisUser);

    /**
     * 删除用户
     * @param id 用户ID
     * @return
     */
    void deleteUserById(String id);

    /**
     * 设置过期时间
     * @param redisUser 用户信息
     * @return
     */
    void setExpireTime(RedisUser redisUser);

    /**
     * 查看该数据缓存是否过期
     * @param redisUserId 主键
     * @return boolean true: 过期 false: 有效
     */
    boolean expireState(String redisUserId);

}

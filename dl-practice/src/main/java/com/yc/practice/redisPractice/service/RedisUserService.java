package com.yc.practice.redisPractice.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yc.core.redisPractice.entity.RedisUser;
import com.yc.core.redisPractice.model.RedisUserQuery;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 功能描述：
 *
 *  <p>版权所有：</p>
 *  未经本人许可，不得以任何方式复制或使用本程序任何部分
 *
 * @Company: LionHerding
 * @Author xieyc
 * @Date 2020-01-19
 * @Version: 1.0.0
 *
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
    boolean expireState(@RequestParam("redisUserId") String redisUserId);

}

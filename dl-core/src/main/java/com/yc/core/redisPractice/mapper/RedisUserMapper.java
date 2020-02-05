package com.yc.core.redisPractice.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yc.core.redisPractice.entity.RedisUser;
import com.yc.core.redisPractice.model.RedisUserQuery;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 *
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
@Repository
public interface RedisUserMapper extends BaseMapper<RedisUser> {

    /**
     * 用户查询
     * @param page 分页信息
     * @param query 查询参数
     * @return
     */
    Page<RedisUser> userPage(@Param("page") Page<RedisUser> page, @Param("query") RedisUserQuery query);
}

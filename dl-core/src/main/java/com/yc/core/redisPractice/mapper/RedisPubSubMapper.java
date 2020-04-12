package com.yc.core.redisPractice.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yc.core.redisPractice.entity.RedisPubSub;
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
 * @Date 2020-01-29
 * @Version: 1.0.0
 *
 */
@Repository
public interface RedisPubSubMapper extends BaseMapper<RedisPubSub> {

    Page<RedisPubSub> pubSubPage(@Param("page")Page<RedisPubSub> page);
}

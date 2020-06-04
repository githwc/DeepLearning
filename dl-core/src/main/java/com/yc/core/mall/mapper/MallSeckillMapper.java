package com.yc.core.mall.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yc.core.mall.entity.MallSeckill;
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
 * @Date 2020-06-01
 * @Version: 1.0.0
 *
 */
@Repository
public interface MallSeckillMapper extends BaseMapper<MallSeckill> {

    /**
     * 秒杀商品分页查询
     * @param page 分页信息
     * @return page
     */
    Page<MallSeckill> mallSeckillPage(@Param("page") Page<MallSeckill> page);
}

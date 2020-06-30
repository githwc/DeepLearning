package com.yc.core.mall.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yc.core.mall.entity.MallSeckill;
import com.yc.core.mall.model.form.SeckillForm;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Map;

/**
 *
 * 功能描述:
 *
 * @Author:  xieyc && 紫色年华
 * @Date 2020-06-01
 * @Version: 1.0.0
 */
@Repository
public interface MallSeckillMapper extends BaseMapper<MallSeckill> {

    /**
     * 减库存
     * @param form 秒杀信息
     * @return int
     */
    Integer reduceNumber(@Param("param") SeckillForm form);

    /**
     * 使用存储过程执行秒杀
     *
     * @param param 入参
     */
    void killByProcedure(Map<String, Object> param);

}

package com.yc.practice.mall.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yc.core.mall.entity.MallSeckill;

/**
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
public interface MallSeckillService extends IService<MallSeckill> {

    /**
     * 减库存
     * @param mallSeckillId 秒杀商品ID
     */
    void cutSeckill(String mallSeckillId);


    /**
     * 秒杀商品详情
     * @param mallSeckillId 主键
     * @return detail
     */
    MallSeckill mallSeckill(String mallSeckillId);

}

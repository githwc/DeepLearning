package com.yc.practice.mall.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yc.core.mall.entity.MallSeckill;
import com.yc.core.mall.model.form.SeckillForm;
import com.yc.core.mall.model.vo.SeckillVO;

/**
 * 功能描述:
 *
 * @Author:  xieyc && 紫色年华
 * @Date 2020-06-01
 * @Version: 1.0.0
 */
public interface MallSeckillService extends IService<MallSeckill> {

    /**
     * 秒杀商品分页查询
     * @param page 分页信息
     * @return page
     */
    Page<MallSeckill> pageMallSeckill(Page<MallSeckill> page);

    /**
     * 增加/更新秒杀商品
     * @param mallSeckill 商品信息
     */
    void saveMallSeckill(MallSeckill mallSeckill);

    /**
     * 删除指定秒杀商品
     * @param mallSeckillId 秒杀商品ID
     */
    void deleteAlone(String mallSeckillId);

    /**
     * 执行秒杀
     * @param seckillForm 秒杀商品
     */
    void execSeckill(SeckillForm seckillForm);

    /**
     * 秒杀商品详情
     * @param mallSeckillId 主键
     * @return (系统时间,加密串,秒杀ID)
     */
    SeckillVO mallSeckill(String mallSeckillId);

    /**
     * 执行秒杀
     * @param seckillForm 秒杀商品
     */
    void execSeckillByProcedure(SeckillForm seckillForm);

}

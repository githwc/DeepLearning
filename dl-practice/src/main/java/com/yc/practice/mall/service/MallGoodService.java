package com.yc.practice.mall.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yc.core.mall.entity.MallGood;
import com.yc.core.mall.model.query.GoodQuery;

/**
 * 功能描述：
 *
 *  <p>版权所有：</p>
 *  未经本人许可，不得以任何方式复制或使用本程序任何部分
 *
 * @Company: 紫色年华
 * @Author xieyc
 * @Date 2020-04-08
 * @Version: 1.0.0
 *
 */
public interface MallGoodService extends IService<MallGood> {

    /**
     * 商品分页查询
     * @param page 分页信息
     * @param query 入参
     * @return page
     */
    Page<MallGood> mallPage(Page<MallGood> page, GoodQuery query);

    /**
     * 增加商品信息
     * @param mallGood 商品信息
     */
    void add(MallGood mallGood);

    /**
     * 修改商品信息
     * @param mallGood 商品信息
     */
    void updateGood(MallGood mallGood);

    /**
     * 删除指定商品
     * @param mallGoodId 商品ID
     */
    void deleteAlone(String mallGoodId);

}

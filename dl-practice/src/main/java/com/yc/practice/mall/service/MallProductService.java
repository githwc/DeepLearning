package com.yc.practice.mall.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yc.core.mall.entity.MallProduct;
import com.yc.core.mall.model.query.GoodQuery;

/**
 * 功能描述:
 *
 * @Author:  xieyc && 紫色年华
 * @Date 2020-04-08
 * @Version: 1.0.0
 */
public interface MallProductService extends IService<MallProduct> {

    /**
     * 商品分页查询
     * @param page 分页信息
     * @param query 入参
     * @return page
     */
    Page<MallProduct> pageMallProduct(Page<MallProduct> page, GoodQuery query);

    /**
     * 增加商品信息
     * @param mallProduct 商品信息
     */
    void saveProduct(MallProduct mallProduct);

    /**
     * 删除指定商品
     * @param mallProductId 商品ID
     */
    void deleteAlone(String mallProductId);

}

package com.yc.practice.mall.service;

import com.yc.core.mall.model.form.CartForm;

/**
 * 功能描述:
 *
 * @Author: xieyc && 紫色年华
 * @Date: 2020-05-17
 * @Version: 1.0.0
 */
public interface MallCartService {


    /**
     * 我的购物车
     *
     * @return 我的购物车
     */
    CartForm list();

    /**
     * 添加购物车
     *
     * @param cartForm 商品信息
     * @return 购物车信息
     */
    CartForm add(CartForm cartForm);

    /**
     * 删除购物车中的商品
     *
     * @param cartForm 商品信息
     * @return 我的购物车
     */
    CartForm update(CartForm cartForm);

    /**
     * 删除购物车中的商品
     *
     * @param mallProductId 商品信息
     * @return 我的购物车
     */
    CartForm delete(String mallProductId);

}

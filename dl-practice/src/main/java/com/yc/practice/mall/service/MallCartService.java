package com.yc.practice.mall.service;

import com.yc.core.mall.model.form.CartForm;

/**
 * 功能描述：
 * <p>版权所有：</p>
 * 未经本人许可，不得以任何方式复制或使用本程序任何部分
 *
 * @Company: 紫色年华
 * @Author: xieyc
 * @Datetime: 2020-05-17
 * @Version: 1.0.0
 */
public interface MallCartService {


    /**
     * 我的购物车
     * @return 我的购物车
     */
    CartForm list() ;
    /**
     * 添加购物车
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
     * @param mallGoodId 商品信息
     * @return 我的购物车
     */
    CartForm delete(String mallGoodId);
}

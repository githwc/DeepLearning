package com.yc.practice.mall.controller;

import com.yc.core.mall.model.form.CartForm;
import com.yc.practice.mall.service.MallCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 功能描述：购物车控制层
 * <p>版权所有：</p>
 * 未经本人许可，不得以任何方式复制或使用本程序任何部分
 *
 * @Company: 紫色年华
 * @Author: xieyc
 * @Datetime: 2020-05-17
 * @Version: 1.0.0
 */
@RestController
@RequestMapping("/cart")
public class MallCartController {

    private final MallCartService mallCartService;

    @Autowired
    public MallCartController(MallCartService mallCartService) {
        this.mallCartService = mallCartService;
    }

    /**
     * 我的购物车
     * @return 我的购物车
     */
    @GetMapping
    public CartForm list() {
        return mallCartService.list();
    }

    /**
     * 添加商品到购物车
     *
     * @param cartForm 商品信息
     * @return 我的购物车
     */
    @PostMapping
    public CartForm add(@RequestBody CartForm cartForm) {
        return mallCartService.add(cartForm);
    }

    /**
     * 删除购物车中的商品
     *
     * @param cartForm 商品信息
     * @return 我的购物车
     */
    @PutMapping
    public CartForm update(@RequestBody CartForm cartForm) {
        return mallCartService.update(cartForm);
    }

    /**
     * 删除购物车中的商品
     *
     * @param mallGoodId 商品信息
     * @return 我的购物车
     */
    @DeleteMapping
    public CartForm delete(String mallGoodId) {
        return mallCartService.delete(mallGoodId);
    }

}

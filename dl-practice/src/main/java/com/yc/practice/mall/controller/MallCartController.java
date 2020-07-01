package com.yc.practice.mall.controller;

import com.yc.core.mall.model.form.CartForm;
import com.yc.practice.mall.service.MallCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 功能描述:购物车控制层
 *
 * @Author: xieyc && 紫色年华
 * @Date: 2020-05-17
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
    public CartForm save(@RequestBody CartForm cartForm) {
        return mallCartService.add(cartForm);
    }

    /**
     * 更新购物车中的商品
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
     * @param mallProductId 商品信息
     * @return 我的购物车
     */
    @DeleteMapping
    public CartForm delete(String mallProductId) {
        return mallCartService.delete(mallProductId);
    }

}

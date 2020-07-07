package com.yc.mini.mall.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yc.core.mall.entity.MallProduct;
import com.yc.core.mall.model.query.GoodQuery;
import com.yc.mini.mall.service.MallProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 功能描述:商品控制层
 *
 * @Author: xieyc && 紫色年华
 * @Date 2020-04-08
 * @Version: 1.0.0
 */
@RestController
@RequestMapping("/mallProduct")
@Slf4j
public class MallProductController {

    private final MallProductService iMallGoodService;

    @Autowired
    public MallProductController(MallProductService iMallGoodService) {
        this.iMallGoodService = iMallGoodService;
    }

    /**
     * 商品分页查询
     *
     * @param page  分页信息
     * @param query 入参
     * @return page
     */
    @GetMapping("/page")
    public Page<MallProduct> page(Page<MallProduct> page, GoodQuery query) {
        return iMallGoodService.pageMallProduct(page, query);
    }

    /**
     * 商品详情
     *
     * @param mallProductId 商品ID
     * @return 商品详情
     */
    @GetMapping("/get")
    public MallProduct get(String mallProductId) {
        return iMallGoodService.getMallProduct(mallProductId);
    }


}

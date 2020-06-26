package com.yc.practice.mall.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yc.core.mall.entity.MallProduct;
import com.yc.core.mall.model.query.GoodQuery;
import com.yc.practice.mall.service.MallProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 *
 * 功能描述：商品控制层
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
@RestController
@RequestMapping("/mallProduct")
@Slf4j
public class MallProductController {

    private final MallProductService iMallGoodService;

    @Autowired
    public MallProductController(MallProductService iMallGoodService){
        this.iMallGoodService = iMallGoodService;
    }

    /**
     * 商品分页查询
     * @param page 分页信息
     * @param query 入参
     * @return page
     */
    @GetMapping("/page")
    public Page<MallProduct> mallPage(Page<MallProduct> page, GoodQuery query){
        return iMallGoodService.mallPage(page,query);
    }

    /**
     * 增加/更新商品信息
     * @param mallProduct 商品信息
     */
    @PostMapping
    public void add(@RequestBody MallProduct mallProduct){
        iMallGoodService.add(mallProduct);
    }

    /**
     * 删除指定商品
     * @param mallProductId 商品ID
     */
    @DeleteMapping
    public void delete(String mallProductId) {
        iMallGoodService.deleteAlone(mallProductId);
    }

}

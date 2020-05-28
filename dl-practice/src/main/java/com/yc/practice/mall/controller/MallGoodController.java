package com.yc.practice.mall.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yc.core.mall.entity.MallGood;
import com.yc.core.mall.model.query.GoodQuery;
import com.yc.practice.mall.service.MallGoodService;
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
@RequestMapping("/mallGood")
@Slf4j
public class MallGoodController {

    private final MallGoodService iMallGoodService;

    @Autowired
    public MallGoodController(MallGoodService iMallGoodService){
        this.iMallGoodService = iMallGoodService;
    }

    /**
     * 商品分页查询
     * @param page 分页信息
     * @param query 入参
     * @return page
     */
    @GetMapping("/page")
    public Page<MallGood> mallPage(Page<MallGood> page, GoodQuery query){
        return iMallGoodService.mallPage(page,query);
    }

    /**
     * 增加商品信息
     * @param mallGood 商品信息
     */
    @PostMapping
    public void add(@RequestBody MallGood mallGood){
        iMallGoodService.add(mallGood);
    }

    /**
     * 修改商品信息
     * @param mallGood 商品信息
     */
    @PutMapping
    public void updateGood(@RequestBody MallGood mallGood){
        iMallGoodService.updateGood(mallGood);
    }

    /**
     * 删除指定商品
     * @param mallGoodId 商品ID
     */
    @DeleteMapping
    public void delete(@RequestParam("mallGoodId") String mallGoodId) {
        iMallGoodService.deleteAlone(mallGoodId);
    }

    // TODO: 2020/5/25 1.定时器订单取消
    // TODO: 2020/5/24 2.优惠券
    // TODO: 2020/5/24 3.会员
    // TODO: 2020/5/24 4.秒杀
}

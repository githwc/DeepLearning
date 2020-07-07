package com.yc.mini.mall.controller;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yc.core.mall.entity.MallOrder;
import com.yc.core.mall.model.form.OrderForm;
import com.yc.core.mall.model.query.OrderQuery;
import com.yc.mini.mall.service.MallOrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 功能描述:订单控制层
 *
 * @Author: xieyc && 紫色年华
 * @Date 2020-04-08
 * @Version: 1.0.0
 */
@RestController
@RequestMapping("/mallOrder")
@Slf4j
public class MallOrderController {

    private final MallOrderService iMallOrderService;

    @Autowired
    public MallOrderController(MallOrderService iMallOrderService) {
        this.iMallOrderService = iMallOrderService;
    }

    /**
     * 生成订单
     *
     * @param orderForm 订单信息
     * @return 收货地址 & 支付金额
     */
    @PostMapping("/createOrder")
    public JSONObject createOrder(@RequestBody OrderForm orderForm) {
        return iMallOrderService.createOrder(orderForm);
    }

    /**
     * 订单分页查询
     *
     * @param page 分页信息
     * @return page
     */
    @GetMapping("/page")
    public Page<MallOrder> orderPage(Page<MallOrder> page, OrderQuery query) {
        return this.iMallOrderService.orderPage(page, query);
    }

    /**
     * 订单编号
     *
     * @param orderNo 订单编号
     * @return 订单信息
     */
    @GetMapping("/get")
    public MallOrder get(String orderNo) {
        return iMallOrderService.getOne(Wrappers.<MallOrder>lambdaQuery()
                .eq(MallOrder::getOrderNo, orderNo)
        );
    }

}

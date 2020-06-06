package com.yc.practice.mall.controller;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yc.core.mall.entity.MallOrder;
import com.yc.core.mall.model.form.OrderForm;
import com.yc.core.mall.model.query.OrderQuery;
import com.yc.practice.mall.service.MallOrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 *
 * 功能描述：订单控制层
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
@RequestMapping("/mallOrder")
@Slf4j
public class MallOrderController {

    private final MallOrderService iMallOrderService;

    @Autowired
    public MallOrderController(MallOrderService iMallOrderService){
        this.iMallOrderService = iMallOrderService;
    }

    /**
     * 生成订单
     * @param orderForm 订单信息
     * @return 收货地址 & 支付金额
     */
    @PostMapping("/createOrder")
    public JSONObject createOrder(@RequestBody OrderForm orderForm){
        return iMallOrderService.createOrder(orderForm);
    }

    /**
     * 订单分页查询
     * @param page 分页信息
     * @return page
     */
    @GetMapping("/page")
    public Page<MallOrder> orderPage(Page<MallOrder> page, OrderQuery query){
        return this.iMallOrderService.orderPage(page,query);
    }

    /**
     * 取消订单
     * @param mallOrder 订单信息
     */
    @PostMapping("/cancelOrder")
    public void cancelOrder(@RequestBody MallOrder mallOrder){
        this.iMallOrderService.cancelOrder(mallOrder.getMallOrderId());
    }

    /**
     * 支付回调
     * @param request 请求信息
     */
    @PostMapping("/syncCallBackPay")
    public void syncCallBackPay(HttpServletRequest request){
        this.iMallOrderService.syncCallBackPay(request);
    }

    @GetMapping("/aa")
    public String aa(){
        return iMallOrderService.aa();
    }
}

package com.yc.mini.mall.service;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yc.core.mall.entity.MallOrder;
import com.yc.core.mall.model.form.OrderForm;
import com.yc.core.mall.model.query.OrderQuery;

import javax.servlet.http.HttpServletRequest;

/**
 * 功能描述:
 *
 * @Author: xieyc && 紫色年华
 * @Date 2020-04-08
 * @Version: 1.0.0
 */
public interface MallOrderService extends IService<MallOrder> {

    /**
     * 生成订单
     *
     * @param orderForm 订单信息
     * @return 收货地址 & 支付金额
     */
    JSONObject createOrder(OrderForm orderForm);

    /**
     * 订单分页查询
     *
     * @param page 分页信息
     * @return page
     */
    Page<MallOrder> orderPage(Page<MallOrder> page, OrderQuery query);


}

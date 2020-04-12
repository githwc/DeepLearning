package com.yc.practice.mall.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yc.core.mall.entity.MallOrder;
import com.yc.core.mall.model.form.OrderForm;

/**
 * 功能描述：
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
public interface MallOrderService extends IService<MallOrder> {

    /**
     * 生成订单
     * @param orderForm 订单信息
     */
    void createOrder(OrderForm orderForm);

    /**
     * 订单分页查询
     * @param page 分页信息
     * @return page
     */
    IPage<MallOrder> orderIpage(IPage<MallOrder>page);

}

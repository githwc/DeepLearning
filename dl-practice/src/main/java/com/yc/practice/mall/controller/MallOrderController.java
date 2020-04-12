package com.yc.practice.mall.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.yc.core.mall.entity.MallOrder;
import com.yc.core.mall.model.form.OrderForm;
import com.yc.practice.mall.service.MallOrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @Autowired
    public MallOrderService iMallOrderService;

    /**
     * 生成订单
     * @param orderForm 订单信息
     */
    @PostMapping("/createOrder")
    public void createOrder(@RequestBody OrderForm orderForm){
        iMallOrderService.createOrder(orderForm);
    }

    /**
     * 订单分页查询
     * @param page 分页信息
     * @return page
     */
    @GetMapping("/page")
    public IPage<MallOrder> orderIpage(IPage<MallOrder>page){
        return this.iMallOrderService.orderIpage(page);
    }


}

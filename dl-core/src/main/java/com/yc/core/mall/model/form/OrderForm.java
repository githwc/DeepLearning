package com.yc.core.mall.model.form;

import com.yc.core.mall.entity.MallOrder;
import com.yc.core.mall.entity.MallOrderItem;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * 功能描述:订单提交form
 *
 * @Author: xieyc && 紫色年华
 * @Date: 2020-04-09
 * @Version: 1.0.0
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class OrderForm extends MallOrder {

    /**
     * 收货地址
     */
    private String mallShippingId;

    /**
     * 省份code
     */
    private String provinceCode;

    /**
     * 城市code
     */
    private String cityCode;

    /**
     * 区code
     */
    private String areaCode;

    /**
     * 订单商品
     */
    private List<MallOrderItem> goodsInfo;

}

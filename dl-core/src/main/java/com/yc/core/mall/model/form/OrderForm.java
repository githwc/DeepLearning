package com.yc.core.mall.model.form;

import com.yc.core.mall.entity.MallOrder;
import com.yc.core.mall.entity.MallOrderItem;
import lombok.Data;

import java.util.List;

/**
 * 功能描述：订单提交form
 * <p>版权所有：</p>
 * 未经本人许可，不得以任何方式复制或使用本程序任何部分
 *
 * @Company: 紫色年华
 * @Author: xieyc
 * @Datetime: 2020-04-09
 * @Version: 1.0.0
 */
@Data
public class OrderForm extends MallOrder {

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

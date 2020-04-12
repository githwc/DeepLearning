package com.yc.core.mall.model.form;

import com.yc.core.mall.entity.MallOrder;
import com.yc.core.mall.entity.MallOrderGood;
import lombok.Data;

import java.util.List;

/**
 * 功能描述：
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
     * 订单商品
     */
    private List<MallOrderGood> orderGoods;

}

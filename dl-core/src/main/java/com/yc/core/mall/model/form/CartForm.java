package com.yc.core.mall.model.form;

import com.yc.core.mall.entity.MallGood;
import lombok.Data;

import java.util.List;

/**
 * 功能描述：购物车FORM
 * <p>版权所有：</p>
 * 未经本公司许可，不得以任何方式复制或使用本程序任何部分
 *
 * @Company: 紫色年华
 * @Author: xieyc
 * @Datetime: 2020-05-17
 * @Version: 1.0.0
 */
@Data
public class CartForm extends MallGood {

    /**
     * 购买数量
     */
    private Integer num;

    /**
     * 回显商品
     */
    private List<CartForm> goodList;

}

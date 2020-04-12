package com.yc.core.mall.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 功能描述：
 *  <p>版权所有：</p>
 *  未经本人许可，不得以任何方式复制或使用本程序任何部分
 *
 * @Company: 紫色年华
 * @Author xieyc
 * @Date 2020-04-08
 * @Version: 1.0.0
 *
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class MallOrderGood implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * 订单id
     */
    private String mallOrderId;
    /**
     * 订单编号
     */
    private String orderNo;
    /**
     * 商品id
     */
    private String goodId;
    /**
     * 商品图片
     */
    private String goodPic;
    /**
     * 商品名称
     */
    private String goodName;
    /**
     * 销售价格
     */
    private BigDecimal goodPrice;
    /**
     * 购买数量
     */
    private Integer goodNum;

}

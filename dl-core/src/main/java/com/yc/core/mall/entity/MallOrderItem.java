package com.yc.core.mall.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 功能描述:
 *
 * @Author:  xieyc && 紫色年华
 * @Date 2020-05-08
 * @Version: 1.0.0
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class MallOrderItem implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * 主键
     */
    @TableId(value = "mall_order_good_id", type = IdType.ASSIGN_UUID)
    private String mallOrderGoodId;
    /**
     * 用户id
     */
    private String sysUserId;
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
     * 商品名称
     */
    private String goodName;
    /**
     * 商品图片
     */
    private String goodPic;
    /**
     * 单价
     */
    private BigDecimal goodPrice;
    /**
     * 购买数量
     */
    private Integer goodNum;



}

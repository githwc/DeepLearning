package com.yc.core.mall.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 功能描述：
 *  <p>版权所有：</p>
 *  未经本人许可，不得以任何方式复制或使用本程序任何部分
 *
 * @Company: 紫色年华
 * @Author xieyc
 * @Date 2020-05-08
 * @Version: 1.0.0
 *
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class MallOrderItem implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * 主键
     */
    @TableId(value = "mall_order_good_id", type = IdType.UUID)
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

package com.yc.core.mall.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

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
public class MallOrder implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * 订单id
     */
    @TableId(value = "mall_order_id", type = IdType.UUID)
    private String mallOrderId;
    /**
     * 订单编号
     */
    private String orderNo;
    /**
     * 订单总金额
     */
    private BigDecimal totalAmount;
    /**
     * 应付金额（实际支付金额）
     */
    private BigDecimal payAmount;
    /**
     * 支付方式：1->支付宝；2->微信
     */
    private Integer payType;
    /**
     * 订单状态：0->待付款；1->待发货；2->已发货；3->已完成；4->已关闭；5->无效订单
     */
    private Integer state;
    /**
     * 订单类型：0->正常订单；1->秒杀订单
     */
    private Integer orderType;
    /**
     * 收货人姓名
     */
    private String receiverName;
    /**
     * 收货人电话
     */
    private String receiverPhone;
    /**
     * 收货人邮编
     */
    private String receiverPostCode;
    /**
     * 省份/直辖市
     */
    private String receiverProvince;
    /**
     * 城市
     */
    private String receiverCity;
    /**
     * 区
     */
    private String receiverRegion;
    /**
     * 行政编码
     */
    private String regionCode;
    /**
     * 详细地址
     */
    private String receiverDetailAddress;
    /**
     * 订单备注
     */
    private String remark;
    /**
     * 确认收货状态：0->未确认；1->已确认
     */
    private Integer confirmState;
    /**
     * 支付时间
     */
    private LocalDateTime payTime;
    /**
     * 创建人
     */
    private String createUserId;
    /**
     * 创建时间
     */
    private LocalDateTime createTime;
    /**
     * 修改人
     */
    private String updateUserId;
    /**
     * 修改时间
     */
    private LocalDateTime updateTime;

}

package com.yc.core.mall.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 功能描述:
 *
 * @Author:  xieyc && 紫色年华
 * @Date 2020-04-08
 * @Version: 1.0.0
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class MallOrder implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * 订单id
     */
    @TableId(value = "mall_order_id", type = IdType.ASSIGN_UUID)
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
     * 运费
     */
    private BigDecimal postage;
    /**
     * 支付方式：0->支付宝；1->微信
     */
    private Integer payType;
    /**
     * 订单状态：0-已取消-10-未付款，20-已付款(待发货)，40-已发货，50-交易成功，60-交易关闭  70-无效订单
     */
    private Integer state;
    /**
     * 订单类型：0->正常订单；1->秒杀订单
     */
    private Integer orderType;
    /**
     * 收货姓名
     */
    private String receiverName;
    /**
     * 收货电话
     */
    private String receiverPhone;
    /**
     * 省份
     */
    private String receiverProvince;
    /**
     * 城市
     */
    private String receiverCity;
    /**
     * 区/县
     */
    private String receiverArea;
    /**
     * 行政编码
     */
    private String regionCode;
    /**
     * 详细地址
     */
    private String receiverAddress;
    /**
     * 确认收货状态：0->未确认；1->已确认
     */
    private Integer confirmState;
    /**
     * 支付时间
     */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private LocalDateTime payTime;
    /**
     * 发货时间
     */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private LocalDateTime sendTime;
    /**
     * 交易完成时间(确认收货时间)
     */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private LocalDateTime endTime;
    /**
     * 订单未支付关闭时间
     */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private LocalDateTime closeTime;
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

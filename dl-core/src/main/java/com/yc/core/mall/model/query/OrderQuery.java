package com.yc.core.mall.model.query;

import lombok.Data;

/**
 * 功能描述:订单入参
 *
 * @Author: xieyc && 紫色年华
 * @Date: 2020-04-12
 * @Version: 1.0.0
 */
@Data
public class OrderQuery {

    /**
     * 订单号
     */
    private String orderNo;

    /**
     * 支付时间
     */
    private String payStartTime;
    private String payEndTime;

}

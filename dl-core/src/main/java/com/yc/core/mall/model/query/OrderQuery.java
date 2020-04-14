package com.yc.core.mall.model.query;

import lombok.Data;

/**
 * 功能描述：订单入参
 * <p>版权所有：</p>
 * 未经本人许可，不得以任何方式复制或使用本程序任何部分
 *
 * @Company: 紫色年华
 * @Author: xieyc
 * @Datetime: 2020-04-12
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

    /**
     * 创建时间
     */
    private String createStartTime;
    private String createEndTime;
}

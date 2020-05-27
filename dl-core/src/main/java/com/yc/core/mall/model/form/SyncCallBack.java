package com.yc.core.mall.model.form;

import lombok.Data;

/**
 * 功能描述：异步回调支付
 * <p>版权所有：</p>
 * 未经本人许可，不得以任何方式复制或使用本程序任何部分
 *
 * @Company: 紫色年华
 * @Author: xieyc
 * @Datetime: 2020-05-26
 * @Version: 1.0.0
 */
@Data
public class SyncCallBack {

    /**
     * 订单号
     */
    private String orderNo;
    /**
     * 支付方式 支付宝:0 微信:1
     */
    private Integer payType;
    /**
     * 订单支付时间
     */
    private String payTime;
    /**
     * 用户表示
     */
    private String sysUserId;

}

package com.yc.practice.mall.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yc.core.mall.entity.MallOrderLog;

/**
 * 功能描述:
 *
 * @Author:  xieyc && 紫色年华
 * @Date 2020-04-08
 * @Version: 1.0.0
 */
public interface MallOrderLogService extends IService<MallOrderLog> {

    /**
     * 保存订单变更记录
     * @param mallOrderId 订单号
     * @param state 状态
     * @param sysUserId 用户ID
     * @param remark 备注
     */
    void saveOrderLog(String mallOrderId,Integer state,String sysUserId,String remark);
}

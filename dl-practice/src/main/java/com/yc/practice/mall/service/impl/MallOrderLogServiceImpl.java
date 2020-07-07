package com.yc.practice.mall.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yc.core.mall.entity.MallOrderLog;
import com.yc.core.mall.mapper.MallOrderLogMapper;
import com.yc.practice.mall.service.MallOrderLogService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 功能描述:
 *
 * @Author: xieyc && 紫色年华
 * @Date 2020-04-08
 * @Version: 1.0.0
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class MallOrderLogServiceImpl extends ServiceImpl<MallOrderLogMapper, MallOrderLog> implements MallOrderLogService {

    @Override
    public void saveOrderLog(String mallOrderId, Integer state, String sysUserId, String remark) {
        MallOrderLog mallOrderLog = new MallOrderLog();
        mallOrderLog.setMallOrderId(mallOrderId);
        mallOrderLog.setState(state);
        mallOrderLog.setRemark(remark);
        mallOrderLog.setCreateUserId(sysUserId);
        this.baseMapper.insert(mallOrderLog);
    }
}

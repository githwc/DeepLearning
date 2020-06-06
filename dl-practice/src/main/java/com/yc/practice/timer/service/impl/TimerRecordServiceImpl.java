package com.yc.practice.timer.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yc.common.constant.CommonEnum;
import com.yc.core.mall.entity.MallOrder;
import com.yc.core.mall.mapper.MallOrderMapper;
import com.yc.core.timer.entity.TimerRecord;
import com.yc.core.timer.mapper.TimerRecordMapper;
import com.yc.practice.mall.service.MallOrderLogService;
import com.yc.practice.timer.service.TimerRecordService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

/**
* 功能描述：
*
*  <p>版权所有：</p>
*  未经本人许可，不得以任何方式复制或使用本程序任何部分
*
* @Company: 紫色年华
* @Author xieyc
* @Date 2020-04-16
* @Version: 1.0.0
*
*/
@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class TimerRecordServiceImpl extends ServiceImpl<TimerRecordMapper, TimerRecord> implements TimerRecordService {

    private final MallOrderMapper mallOrderMapper;
    private final MallOrderLogService mallOrderLogService;

    @Autowired
    public TimerRecordServiceImpl(MallOrderMapper mallOrderMapper,MallOrderLogService mallOrderLogService){
        this.mallOrderMapper = mallOrderMapper;
        this.mallOrderLogService = mallOrderLogService;
    }


    @Override
    public void orderCheck() {
        // 1.查询从创建订单开始超时30分钟的订单
        List<MallOrder> mallOrders = mallOrderMapper.selectList(new LambdaQueryWrapper<MallOrder>()
            .eq(MallOrder::getState, CommonEnum.OrderState.ORDER_STATE_10.getCode())

        );
        mallOrders.forEach(i->{
            if(Duration.between(i.getCreateTime(),LocalDateTime.now()).toMinutes() > 30 ){
                log.info("=====================订单超时关闭==================");
                log.info("=====================订单超时关闭==================");
                // 2.修改为订单超时
                MallOrder mallOrder = i;
                mallOrder.setState(CommonEnum.OrderState.ORDER_STATE_60.getCode());
                mallOrder.setCloseTime(LocalDateTime.now());
                this.mallOrderMapper.updateById(mallOrder);
                // 3.记录订单日志
                mallOrderLogService.saveOrderLog(i.getMallOrderId(),
                        CommonEnum.OrderLogState.INVALID.getCode(),"admin","订单超时自动关闭");
            }
        });
        // 4.记录定时器日志
        TimerRecord timerRecord = new TimerRecord();
        timerRecord.setTitle("订单超时监测");
        this.baseMapper.insert(timerRecord);
    }
}

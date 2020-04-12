package com.yc.practice.mall.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yc.common.constant.CacheConstant;
import com.yc.core.mall.entity.MallGood;
import com.yc.core.mall.entity.MallOrder;
import com.yc.core.mall.entity.MallOrderGood;
import com.yc.core.mall.mapper.MallGoodMapper;
import com.yc.core.mall.mapper.MallOrderMapper;
import com.yc.core.mall.model.form.OrderForm;
import com.yc.practice.common.UserUtil;
import com.yc.practice.mall.service.MallOrderGoodService;
import com.yc.practice.mall.service.MallOrderService;
import org.apache.tomcat.jni.Local;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

/**
* 功能描述：
*
*  <p>版权所有：</p>
*  未经本人许可，不得以任何方式复制或使用本程序任何部分
*
* @Company: 紫色年华
* @Author xieyc
* @Date 2020-04-08
* @Version: 1.0.0
*
*/
@Service
@Transactional(rollbackFor = Exception.class)
public class MallOrderServiceImpl extends ServiceImpl<MallOrderMapper, MallOrder> implements MallOrderService {

    private MallOrderGoodService mallOrderGoodService;
    private final RedisTemplate redisTemplate;
    private final MallGoodMapper mallGoodMapper;

    @Autowired
    public MallOrderServiceImpl(MallOrderGoodService mallOrderGoodService,RedisTemplate redisTemplate,
                                MallGoodMapper mallGoodMapper){
        this.mallOrderGoodService = mallOrderGoodService;
        this.redisTemplate = redisTemplate;
        this.mallGoodMapper = mallGoodMapper;
    }

    @Override
    public void createOrder(OrderForm orderForm) {
        // ========= 保存订单信息 ========
        MallOrder mallOrder = new MallOrder();
        BeanUtil.copyProperties(orderForm,mallOrder);
        mallOrder.setOrderNo(generateOrderNo());
        mallOrder.setCreateUserId(UserUtil.getUserId());
        // 支付时间
        mallOrder.setPayTime(LocalDateTime.now());
        this.baseMapper.insert(mallOrder);
        // ========= 保存订单商品信息 ========
        List<MallOrderGood> goodList = orderForm.getOrderGoods();
        goodList.forEach(curr->{
            // TODO: 2020/4/9  测试能否取得事务中的其他字段信息
            curr.setOrderNo(mallOrder.getOrderNo());
            curr.setMallOrderId(mallOrder.getMallOrderId());
            // TODO: 2020/4/9 Q1:在循环中且需要先查询出当前商品，然后再增加销量，减少库存
            MallGood mallGood = new MallGood();
            // mallGood.setSale()
            mallGoodMapper.update(mallGood,new LambdaQueryWrapper<MallGood>()
                .eq(MallGood::getMallGoodId,curr.getGoodId())
            );
        });
        this.mallOrderGoodService.saveBatch(goodList);
    }

    @Override
    public IPage<MallOrder> orderIpage(IPage<MallOrder> page) {
        return this.baseMapper.selectPage(page,new LambdaQueryWrapper<MallOrder>()
            .orderByDesc(MallOrder::getCreateTime)
        );
    }

    /**
     * 生成唯一订单号
     * @return 23位订单号[当前时间(毫秒) + 自增id]
     */
    private String generateOrderNo(){
        StringBuilder sb = new StringBuilder();
        Long nowLong = Long.parseLong(new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date()));
        sb.append(nowLong.toString());
        String date = new SimpleDateFormat("yyyyMMdd").format(new Date());
        String key = CacheConstant.ORDER_NO_TODAY_CACHE + date;
        Long increment = redisTemplate.opsForValue().increment(key,1);
        String incrementStr = increment.toString();
        if (incrementStr.length() <= 6) {
            sb.append(String.format("%06d", increment));
        } else {
            sb.append(incrementStr);
        }
        return sb.toString();
    }
}

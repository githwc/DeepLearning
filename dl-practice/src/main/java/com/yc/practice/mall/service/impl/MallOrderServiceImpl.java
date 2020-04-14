package com.yc.practice.mall.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yc.common.constant.CacheConstant;
import com.yc.common.global.error.DlError;
import com.yc.common.global.error.ErrorException;
import com.yc.core.mall.entity.MallGood;
import com.yc.core.mall.entity.MallOrder;
import com.yc.core.mall.entity.MallOrderGood;
import com.yc.core.mall.entity.MallOrderLog;
import com.yc.core.mall.mapper.MallGoodMapper;
import com.yc.core.mall.mapper.MallOrderLogMapper;
import com.yc.core.mall.mapper.MallOrderMapper;
import com.yc.core.mall.model.form.OrderForm;
import com.yc.core.mall.model.query.OrderQuery;
import com.yc.core.region.entity.Region;
import com.yc.core.region.mapper.RegionMapper;
import com.yc.practice.common.UserUtil;
import com.yc.practice.mall.service.MallOrderGoodService;
import com.yc.practice.mall.service.MallOrderService;
import org.apache.commons.lang3.StringUtils;
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

    private final MallOrderGoodService mallOrderGoodService;
    private final RedisTemplate redisTemplate;
    private final MallGoodMapper mallGoodMapper;
    private final RegionMapper regionMapper;
    private final MallOrderLogMapper mallOrderLogMapper;

    @Autowired
    public MallOrderServiceImpl(MallOrderGoodService mallOrderGoodService,RedisTemplate redisTemplate,
                                MallGoodMapper mallGoodMapper,RegionMapper regionMapper,MallOrderLogMapper mallOrderLogMapper){
        this.mallOrderGoodService = mallOrderGoodService;
        this.redisTemplate = redisTemplate;
        this.mallOrderLogMapper = mallOrderLogMapper;
        this.mallGoodMapper = mallGoodMapper;
        this.regionMapper = regionMapper;
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
        // 支付状态
        mallOrder.setState(1);
        mallOrder.setPayType(0);
        // 处理收货地址
        mallOrder.setProvince(getRegionInfo(orderForm.getProvinceCode()).getRegionName());
        mallOrder.setCity(getRegionInfo(orderForm.getCityCode()).getRegionName());
        mallOrder.setArea(getRegionInfo(orderForm.getAreaCode()).getRegionName());
        mallOrder.setRegionCode(orderForm.getAreaCode());
        this.baseMapper.insert(mallOrder);
        // ========= 保存订单商品信息 ========
        List<MallOrderGood> goodList = orderForm.getGoodsInfo();
        goodList.forEach(curr->{
            if(StringUtils.isBlank(curr.getGoodId())){
                throw new ErrorException(DlError.GoodNotFound);
            }
            MallGood mallGood = this.mallGoodMapper.selectById(curr.getGoodId());
            if(mallGood.getStock()<curr.getGoodNum()){
                throw new ErrorException(DlError.StockLow);
            }
            mallGood.setSale(curr.getGoodNum()+mallGood.getSale());
            mallGood.setStock(mallGood.getStock()-curr.getGoodNum());
            mallGoodMapper.update(mallGood,new LambdaQueryWrapper<MallGood>()
                .eq(MallGood::getMallGoodId,curr.getGoodId())
            );
            curr.setMallOrderId(mallOrder.getMallOrderId());
            curr.setOrderNo(mallOrder.getOrderNo());
        });
        this.mallOrderGoodService.saveBatch(goodList);
        // ============= 记录日志 =============
        MallOrderLog mallOrderLog = new MallOrderLog();
        mallOrderLog.setMallOrderId(mallOrder.getMallOrderId());
        mallOrderLog.setState(mallOrder.getState());
        mallOrderLog.setRemark("正常订单");
        mallOrderLog.setCreateUserId(UserUtil.getUserId());
        mallOrderLogMapper.insert(mallOrderLog);
    }

    @Override
    public Page<MallOrder> orderPage(Page<MallOrder> page, OrderQuery query) {
        return this.baseMapper.page(page,query);
    }


    // ======================== 私有方法 ========================
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

    /**
     * 获取region信息
     * @param regionCode region Code
     * @return regionInfo
     */
    private Region getRegionInfo(String regionCode){
        return regionMapper.selectOne(new LambdaQueryWrapper<Region>()
                .eq(Region::getRegionCode,regionCode)
        );
    }
}

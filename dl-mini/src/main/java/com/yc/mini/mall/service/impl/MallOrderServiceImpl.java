package com.yc.mini.mall.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjectUtil;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yc.common.constant.CommonConstant;
import com.yc.common.constant.CommonEnum;
import com.yc.common.global.error.Error;
import com.yc.common.global.error.ErrorException;
import com.yc.common.propertie.EncodeProperties;
import com.yc.core.mall.entity.MallOrder;
import com.yc.core.mall.entity.MallOrderItem;
import com.yc.core.mall.entity.MallProduct;
import com.yc.core.mall.entity.MallShipping;
import com.yc.core.mall.mapper.MallOrderMapper;
import com.yc.core.mall.mapper.MallShippingMapper;
import com.yc.core.mall.model.form.OrderForm;
import com.yc.core.mall.model.query.OrderQuery;
import com.yc.mini.mall.service.MallOrderService;
import com.yc.mini.mall.service.MallProductService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * 功能描述:
 *
 * @Author: xieyc && 紫色年华
 * @Date 2020-04-08
 * @Version: 1.0.0
 */
@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class MallOrderServiceImpl extends ServiceImpl<MallOrderMapper, MallOrder> implements MallOrderService {

    private final RedisTemplate redisTemplate;
    private final MallProductService mallProductService;
    private final MallShippingMapper mallShippingMapper;

    @Autowired
    public MallOrderServiceImpl(RedisTemplate redisTemplate, MallShippingMapper mallShippingMapper, MallProductService mallProductService, EncodeProperties encodeProperties) {
        this.redisTemplate = redisTemplate;
        this.mallProductService = mallProductService;
        this.mallShippingMapper = mallShippingMapper;
    }

    @Override
    public JSONObject createOrder(OrderForm orderForm) {
        // 收货地址校验
        MallShipping mallShipping = mallShippingMapper.selectById(orderForm.getMallShippingId());
        if (ObjectUtil.isNull(mallShipping)) {
            throw new ErrorException(Error.ShippingNotFound);
        }
        // ========= 保存订单信息 ========
        MallOrder mallOrder = new MallOrder();
        BeanUtil.copyProperties(orderForm, mallOrder);
        mallOrder.setOrderNo(generateOrderNo());
        // mallOrder.setCreateUserId(UserUtil.getUserId());
        mallOrder.setState(CommonEnum.OrderState.ORDER_STATE_10.getCode());
        // 处理收货地址
        mallOrder.setReceiverName(mallShipping.getReceiverName());
        mallOrder.setReceiverPhone(mallShipping.getReceiverPhone());
        mallOrder.setRegionCode(mallShipping.getRegionCode());
        mallOrder.setReceiverProvince(mallShipping.getReceiverProvince());
        mallOrder.setReceiverCity(mallShipping.getReceiverCity());
        mallOrder.setReceiverArea(mallShipping.getReceiverArea());
        mallOrder.setReceiverAddress(mallShipping.getReceiverAddress());
        this.baseMapper.insert(mallOrder);
        // ========= 保存订单商品信息 ========
        List<MallOrderItem> orderItemList = orderForm.getGoodsInfo();
        List<MallProduct> goodList = new ArrayList<>();
        BigDecimal amount = BigDecimal.valueOf(0);
        for (MallOrderItem curr : orderItemList) {
            if (StringUtils.isBlank(curr.getGoodId())) {
                throw new ErrorException(Error.GoodNotFound);
            }
            MallProduct mallProduct = this.mallProductService.getBaseMapper().selectById(curr.getGoodId());
            if (mallProduct.getStock() < curr.getGoodNum()) {
                throw new ErrorException(Error.StockLow);
            }
            curr.setMallOrderId(mallOrder.getMallOrderId());
            curr.setOrderNo(mallOrder.getOrderNo());
            // curr.setSysUserId(UserUtil.getUserId());
            amount = amount.add(mallProduct.getPrice().multiply(BigDecimal.valueOf(curr.getGoodNum())));
            mallProduct.setSale(curr.getGoodNum() + mallProduct.getSale());
            mallProduct.setStock(mallProduct.getStock() - curr.getGoodNum());
            goodList.add(mallProduct);
        }
        // 校验总价
        if (amount.compareTo(orderForm.getPayAmount()) != 0) {
            throw new ErrorException(Error.AmountError);
        }
        // this.mallOrderItemService.saveBatch(orderItemList);
        // 减库存 加销量
        this.mallProductService.updateBatchById(goodList);
        // 订单变更记录
        // mallOrderLogService.saveOrderLog(mallOrder.getMallOrderId(),CommonEnum.OrderLogState.WAIT_PAY.getCode(),
        //         UserUtil.getUserId(),"正常订单");
        JSONObject jsonObject = new JSONObject();
        String shipping =
                mallShipping.getReceiverName() + " " + mallShipping.getReceiverPhone() + " " +
                        mallShipping.getReceiverProvince() + " " + mallShipping.getReceiverCity() + " " +
                        mallShipping.getReceiverArea() + " " + mallShipping.getReceiverAddress();
        jsonObject.put("shipping", shipping);
        jsonObject.put("payAmount", mallOrder.getPayAmount());
        jsonObject.put("orderNo", mallOrder.getOrderNo());
        jsonObject.put("sysUserId", mallOrder.getCreateUserId());
        return jsonObject;
    }

    @Override
    public Page<MallOrder> orderPage(Page<MallOrder> page, OrderQuery query) {
        return this.baseMapper.page(page, query);
    }

    // ======================== 私有方法 ========================

    /**
     * 生成唯一订单号
     *
     * @return 23位订单号[当前时间(毫秒) + 自增id]
     */
    private String generateOrderNo() {
        StringBuilder sb = new StringBuilder();
        Long nowLong = Long.parseLong(new SimpleDateFormat(CommonConstant.yyyyMMddHHmmssSSS).format(new Date()));
        sb.append(nowLong.toString());
        String date = new SimpleDateFormat(CommonConstant.yyyyMMddHHmm).format(new Date());
        String key = CommonConstant.TODAY_ORDER_NO + date;
        if (!redisTemplate.hasKey(key)) {
            redisTemplate.opsForValue().set(key, 0, 5, TimeUnit.MINUTES);
        }
        Long increment = redisTemplate.opsForValue().increment(key, 1);
        String incrementStr = increment.toString();
        if (incrementStr.length() <= 6) {
            sb.append(String.format("%06d", increment));
        } else {
            sb.append(incrementStr);
        }
        return sb.toString();
    }


}

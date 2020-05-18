package com.yc.practice.mall.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.alibaba.fastjson.JSONObject;
import com.yc.common.constant.CommonConstant;
import com.yc.common.constant.CommonEnum;
import com.yc.common.global.error.Error;
import com.yc.common.global.error.ErrorException;
import com.yc.core.mall.entity.MallGood;
import com.yc.core.mall.mapper.MallGoodMapper;
import com.yc.core.mall.model.form.CartForm;
import com.yc.practice.common.UserUtil;
import com.yc.practice.mall.service.MallCartService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 功能描述：
 * <p>版权所有：</p>
 * 未经本人许可，不得以任何方式复制或使用本程序任何部分
 *
 * @Company: 紫色年华
 * @Author: xieyc
 * @Datetime: 2020-05-17
 * @Version: 1.0.0
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class MallCartServiceImpl implements MallCartService {

    private final MallGoodMapper mallGoodMapper;
    private StringRedisTemplate redisTemplate;


    @Autowired
    public MallCartServiceImpl(MallGoodMapper mallGoodMapper, StringRedisTemplate redisTemplate) {
        this.mallGoodMapper = mallGoodMapper;
        this.redisTemplate = redisTemplate;
    }

    @Override
    public CartForm list() {
        CartForm cartForm = new CartForm();
        HashOperations<String, String, String> opsForHash = redisTemplate.opsForHash();
        String redisKey = CommonConstant.CART + UserUtil.getUserId();
        Map<String, String> entries = opsForHash.entries(redisKey);
        List<CartForm> list = new ArrayList<>();
        for (Map.Entry<String, String> entry : entries.entrySet()) {
            CartForm item = JSONObject.parseObject(entry.getValue(),CartForm.class);
            // 查询商品实时信息
            list.add(item);
        }
        cartForm.setGoodList(list);
        return cartForm;
    }

    @Override
    public CartForm add(CartForm form) {
        MallGood dbMallGood = this.mallGoodMapper.selectById(form.getMallGoodId());
        // 判断商品是否存在
        if (ObjectUtil.isNull(dbMallGood)) {
            throw new ErrorException(Error.GoodError);
        }
        //  判断商品状态是否正常
        if (!StringUtils.equals(dbMallGood.getState(), CommonEnum.GoodState.GOOD_UP.getCode())) {
            throw new ErrorException(Error.GoodNotUp);
        }
        //    判断商品库存是否充足
        if (dbMallGood.getStock() <= 0) {
            throw new ErrorException(Error.GoodStock);
        }
        // 写入redis
        HashOperations<String, String, String> opsForHash = redisTemplate.opsForHash();
        String key = CommonConstant.CART + UserUtil.getUserId();

        String value = opsForHash.get(key,form.getMallGoodId());
        if(StringUtils.isNotEmpty(value)){
            form = JSONObject.parseObject(value,CartForm.class);
            form.setNum(form.getNum()+1);
        }
        opsForHash.put(key,form.getMallGoodId(),JSONObject.toJSONString(form));
        return this.list();
    }

    @Override
    public CartForm update(CartForm cartForm) {
        HashOperations<String, String, String> opsForHash = redisTemplate.opsForHash();
        String redisKey = CommonConstant.CART + UserUtil.getUserId();
        String value = opsForHash.get(redisKey, cartForm.getMallGoodId());
        if (StringUtils.isEmpty(value)) {
            throw new ErrorException(Error.GoodError);
        }
        CartForm cart = JSONObject.parseObject(value,CartForm.class);
        opsForHash.put(redisKey, cartForm.getMallGoodId(), JSONObject.toJSONString(cart));
        return list();
    }

    @Override
    public CartForm delete(String mallGoodId) {
        HashOperations<String, String, String> opsForHash = redisTemplate.opsForHash();
        String redisKey = CommonConstant.CART + UserUtil.getUserId();
        String value = opsForHash.get(redisKey, String.valueOf(mallGoodId));
        if(StringUtils.isEmpty(value)){
            throw new ErrorException(Error.GoodError);
        }
        opsForHash.delete(redisKey, String.valueOf(mallGoodId));
        return this.list();
    }


}

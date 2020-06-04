package com.yc.practice.mall.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yc.core.mall.entity.MallSeckill;
import com.yc.core.mall.mapper.MallSeckillMapper;
import com.yc.practice.common.UserUtil;
import com.yc.practice.mall.service.MallSeckillService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
* 功能描述：
*
*  <p>版权所有：</p>
*  未经本人许可，不得以任何方式复制或使用本程序任何部分
*
* @Company: 紫色年华
* @Author xieyc
* @Date 2020-06-01
* @Version: 1.0.0
*
*/
@Service
@Transactional(rollbackFor = Exception.class)
public class MallSeckillServiceImpl extends ServiceImpl<MallSeckillMapper, MallSeckill> implements MallSeckillService {

    @Override
    public Page<MallSeckill> mallSeckillPage(Page<MallSeckill> page) {
        return baseMapper.mallSeckillPage(page);
    }

    @Override
    public void add(MallSeckill mallSeckill) {
        mallSeckill.setCreateUserId(UserUtil.getUserId());
        this.baseMapper.insert(mallSeckill);
    }

    @Override
    public void updateSeckill(MallSeckill mallSeckill) {
        mallSeckill.setUpdateUserId(UserUtil.getUserId());
        this.baseMapper.updateById(mallSeckill);
    }

    @Override
    public void deleteAlone(String mallSeckillId) {
        MallSeckill mallSeckill = new MallSeckill();
        mallSeckill.setMallSeckillId(mallSeckillId);
        mallSeckill.setDelFlag(1);
        this.baseMapper.updateById(mallSeckill);
    }

    @Override
    public void cutSeckill(String mallSeckillId) {
        MallSeckill mallSeckill = new MallSeckill();
        mallSeckill.setMallSeckillId(mallSeckillId);
        // TODO: 2020/6/1
        // mallSeckill.setStock()
    }

    @Override
    public MallSeckill mallSeckill(String mallSeckillId) {
        return this.baseMapper.selectById(mallSeckillId);
    }
}

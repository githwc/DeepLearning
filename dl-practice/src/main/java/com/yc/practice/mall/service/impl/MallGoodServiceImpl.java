package com.yc.practice.mall.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yc.core.mall.entity.MallGood;
import com.yc.core.mall.mapper.MallGoodMapper;
import com.yc.core.mall.model.query.GoodQuery;
import com.yc.practice.mall.service.MallGoodService;
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
* @Date 2020-04-08
* @Version: 1.0.0
*
*/
@Service
@Transactional(rollbackFor = Exception.class)
public class MallGoodServiceImpl extends ServiceImpl<MallGoodMapper, MallGood> implements MallGoodService {

    @Override
    public Page<MallGood> mallPage(Page<MallGood> page, GoodQuery query) {
        return this.baseMapper.goodPage(page,query);
    }

    @Override
    public void add(MallGood mallGood) {
        this.baseMapper.insert(mallGood);
    }

    @Override
    public void updateGood(MallGood mallGood) {
        this.baseMapper.updateById(mallGood);
    }

    @Override
    public void deleteAlone(String mallGoodId) {
        this.baseMapper.deleteById(mallGoodId);
    }


}

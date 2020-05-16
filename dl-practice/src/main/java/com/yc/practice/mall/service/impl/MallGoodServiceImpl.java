package com.yc.practice.mall.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yc.core.mall.entity.MallGood;
import com.yc.core.mall.entity.MallGoodClass;
import com.yc.core.mall.mapper.MallGoodClassMapper;
import com.yc.core.mall.mapper.MallGoodMapper;
import com.yc.core.mall.model.query.GoodQuery;
import com.yc.practice.mall.service.MallGoodService;
import org.springframework.beans.factory.annotation.Autowired;
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

    private final MallGoodClassMapper mallGoodClassMapper;

    @Autowired
    public MallGoodServiceImpl(MallGoodClassMapper mallGoodClassMapper){
        this.mallGoodClassMapper = mallGoodClassMapper;
    }
    @Override
    public Page<MallGood> mallPage(Page<MallGood> page, GoodQuery query) {
        Page<MallGood> goodPage = this.baseMapper.goodPage(page,query);
        goodPage.getRecords().forEach(i->{
            MallGoodClass mallGoodClass = this.mallGoodClassMapper.selectById(i.getClassId());
            i.setPClassId(mallGoodClass.getParentId());
        });
        return goodPage;
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

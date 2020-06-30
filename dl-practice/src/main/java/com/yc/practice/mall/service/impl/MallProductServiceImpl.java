package com.yc.practice.mall.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yc.core.mall.entity.MallProduct;
import com.yc.core.mall.entity.MallProductCategory;
import com.yc.core.mall.mapper.MallProductCategoryClassMapper;
import com.yc.core.mall.mapper.MallProductMapper;
import com.yc.core.mall.model.query.GoodQuery;
import com.yc.practice.mall.service.MallProductService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
* 功能描述:
*
* @Author:  xieyc && 紫色年华
* @Date 2020-04-08
* @Version: 1.0.0
*/
@Service
@Transactional(rollbackFor = Exception.class)
public class MallProductServiceImpl extends ServiceImpl<MallProductMapper, MallProduct> implements MallProductService {

    private final MallProductCategoryClassMapper malProductCategoryMapper;

    @Autowired
    public MallProductServiceImpl(MallProductCategoryClassMapper malProductCategoryMapper){
        this.malProductCategoryMapper = malProductCategoryMapper;
    }
    @Override
    public Page<MallProduct> mallPage(Page<MallProduct> page, GoodQuery query) {
        Page<MallProduct> goodPage = this.baseMapper.goodPage(page,query);
        goodPage.getRecords().forEach(i->{
            MallProductCategory mallProductCategory = this.malProductCategoryMapper.selectById(i.getClassId());
            i.setPClassId(mallProductCategory.getParentId());
        });
        return goodPage;
    }

    @Override
    public void add(MallProduct mallProduct) {
        if(StringUtils.isNotBlank(mallProduct.getMallProductId())){
            this.baseMapper.updateById(mallProduct);
        } else {
            this.baseMapper.insert(mallProduct);
        }
    }

    @Override
    public void deleteAlone(String mallProductId) {
        this.baseMapper.deleteById(mallProductId);
    }


}

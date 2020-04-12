package com.yc.practice.mall.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yc.core.mall.entity.MallBrand;
import com.yc.core.mall.mapper.MallBrandMapper;
import com.yc.core.mall.model.query.BrandQuery;
import com.yc.practice.common.UserUtil;
import com.yc.practice.mall.service.MallBrandService;
import org.apache.commons.lang3.StringUtils;
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
* @Date 2020-03-17
* @Version: 1.0.0
*
*/
@Service
@Transactional(rollbackFor = Exception.class)
public class MallBrandServiceImpl extends ServiceImpl<MallBrandMapper, MallBrand> implements MallBrandService {

    @Override
    public Page<MallBrand> pageList(Page<MallBrand> page, BrandQuery query) {
        return this.baseMapper.page(page,query);
    }

    @Override
    public MallBrand detail(String mallBrandId) {
        return this.baseMapper.selectById(mallBrandId);
    }

    @Override
    public void updateBrand(MallBrand mallBrand) {
        if(StringUtils.isEmpty(mallBrand.getFirstLetter())){
            mallBrand.setFirstLetter(mallBrand.getName().substring(0,1));
        }
        mallBrand.setUpdateUserId(UserUtil.getUserId());
        this.baseMapper.updateById(mallBrand);
    }

    @Override
    public void addBrand(MallBrand mallBrand) {
        mallBrand.setCreateUserId(UserUtil.getUserId());
        if(StringUtils.isEmpty(mallBrand.getFirstLetter())){
            mallBrand.setFirstLetter(mallBrand.getName().substring(0,1));
        }
        this.baseMapper.insert(mallBrand);
    }

    @Override
    public void delBrand(String brandId) {
        this.baseMapper.deleteById(brandId);
    }
}

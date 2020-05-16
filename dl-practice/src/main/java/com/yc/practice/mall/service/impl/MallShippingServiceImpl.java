package com.yc.practice.mall.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yc.core.mall.entity.MallShipping;
import com.yc.core.mall.mapper.MallShippingMapper;
import com.yc.core.region.entity.Region;
import com.yc.core.region.mapper.RegionMapper;
import com.yc.practice.common.UserUtil;
import com.yc.practice.mall.service.MallShippingService;
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
* @Date 2020-05-08
* @Version: 1.0.0
*
*/
@Service
@Transactional(rollbackFor = Exception.class)
public class MallShippingServiceImpl extends ServiceImpl<MallShippingMapper, MallShipping> implements MallShippingService {

    private final RegionMapper regionMapper;

    @Autowired
    public MallShippingServiceImpl(RegionMapper regionMapper){
        this.regionMapper = regionMapper;
    }
    @Override
    public Page<MallShipping> shippingPage(Page<MallShipping> page) {
        Page<MallShipping> record = baseMapper.shippingPage(page,UserUtil.getUserId());
        record.getRecords().forEach(i->{
            Region region = this.regionMapper.selectOne(new LambdaQueryWrapper<Region>()
                    .eq(Region::getRegionCode,i.getRegionCode())
            );
            i.setReceiverAreaCode(i.getRegionCode());
            Region cityRegion = this.regionMapper.selectOne(new LambdaQueryWrapper<Region>()
                .eq(Region::getRegionCode,region.getRegionPcode())
            );
            i.setReceiverCityCode(cityRegion.getRegionCode());
            Region provinceRegion = this.regionMapper.selectOne(new LambdaQueryWrapper<Region>()
                    .eq(Region::getRegionCode,cityRegion.getRegionPcode())
            );
            i.setReceiverProvinceCode(provinceRegion.getRegionCode());
        });
        return record;
    }

    @Override
    public void add(MallShipping mallShipping) {
        mallShipping.setSysUserId(UserUtil.getUserId());
        Region region = regionMapper.selectOne(new LambdaQueryWrapper<Region>()
            .eq(Region::getRegionCode,mallShipping.getReceiverProvince())
        );
        mallShipping.setReceiverProvince(region.getRegionName());
        region = regionMapper.selectOne(new LambdaQueryWrapper<Region>()
                .eq(Region::getRegionCode,mallShipping.getReceiverCity())
        );
        mallShipping.setReceiverCity(region.getRegionName());
        region = regionMapper.selectOne(new LambdaQueryWrapper<Region>()
                .eq(Region::getRegionCode,mallShipping.getReceiverArea())
        );
        mallShipping.setReceiverArea(region.getRegionName());
        this.baseMapper.insert(mallShipping);
    }

    @Override
    public void updateShipping(MallShipping mallShipping) {
        this.baseMapper.updateById(mallShipping);
    }

    @Override
    public void deleteAlone(String mallShippingId) {
        MallShipping mallShipping = new MallShipping();
        mallShipping.setDelFlag(1);
        mallShipping.setMallShippingId(mallShippingId);
        this.baseMapper.updateById(mallShipping);
    }
}

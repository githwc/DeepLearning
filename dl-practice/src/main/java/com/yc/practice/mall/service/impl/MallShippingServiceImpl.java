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
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
* 功能描述:
*
* @Author:  xieyc && 紫色年华
* @Date 2020-05-08
* @Version: 1.0.0
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
    public void saveShipping(MallShipping mallShipping) {
        if(StringUtils.isNotBlank(mallShipping.getMallShippingId())){
            dealRegion(mallShipping);
            this.baseMapper.updateById(mallShipping);
        } else {
            mallShipping.setSysUserId(UserUtil.getUserId());
            dealRegion(mallShipping);
            this.baseMapper.insert(mallShipping);
        }
    }

    private void dealRegion(MallShipping mallShipping){
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
    }

    @Override
    public void deleteAlone(String mallShippingId) {
        MallShipping mallShipping = new MallShipping();
        mallShipping.setDelFlag(1);
        mallShipping.setMallShippingId(mallShippingId);
        this.baseMapper.updateById(mallShipping);
    }

    @Override
    public List<MallShipping> shipingList() {
        return this.baseMapper.selectList(new LambdaQueryWrapper<MallShipping>()
            .eq(MallShipping::getSysUserId,UserUtil.getUserId())
                .eq(MallShipping::getDelFlag,0)
        );
    }
}

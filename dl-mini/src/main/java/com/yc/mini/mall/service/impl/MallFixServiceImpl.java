package com.yc.mini.mall.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yc.core.mall.entity.MallFix;
import com.yc.core.mall.mapper.MallFixMapper;
import com.yc.mini.mall.service.MallFixService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
* 功能描述:
*
* @Author:  xieyc && 紫色年华
* @Date 2020-06-26
* @Version: 1.0.0
*/
@Service
@Transactional(rollbackFor = Exception.class)
public class MallFixServiceImpl extends ServiceImpl<MallFixMapper, MallFix> implements MallFixService {

    @Override
    public List<MallFix> listFix(String type) {
        return baseMapper.selectList(Wrappers.<MallFix>lambdaQuery()
            .eq(MallFix::getType,type)
        );
    }
}

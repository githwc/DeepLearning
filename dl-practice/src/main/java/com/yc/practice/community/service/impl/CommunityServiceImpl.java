package com.yc.practice.community.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yc.core.community.entity.Community;
import com.yc.core.community.mapper.CommunityMapper;
import com.yc.practice.community.service.CommunityService;
import com.yc.practice.system.service.SysDeptService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
* 功能描述:
*
* @Author:  xieyc && 紫色年华
* @Date 2019-09-20
* @Version: 1.0.0
*/
@Service
@Transactional(rollbackFor = Exception.class)
public class CommunityServiceImpl extends ServiceImpl<CommunityMapper, Community> implements CommunityService {

    private SysDeptService sysDeptService;

    public CommunityServiceImpl(SysDeptService sysDeptService){
        this.sysDeptService = sysDeptService;
    }
}

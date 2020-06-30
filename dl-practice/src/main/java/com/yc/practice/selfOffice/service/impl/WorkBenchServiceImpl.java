package com.yc.practice.selfOffice.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yc.core.selfoffice.entity.WorkBench;
import com.yc.core.selfoffice.mapper.WorkBenchMapper;
import com.yc.practice.selfOffice.service.WorkBenchService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
* 功能描述:
*
* @Author:  xieyc && 紫色年华
* @Date 2020-04-15
* @Version: 1.0.0
*/
@Service
@Transactional(rollbackFor = Exception.class)
public class WorkBenchServiceImpl extends ServiceImpl<WorkBenchMapper, WorkBench> implements WorkBenchService {

}

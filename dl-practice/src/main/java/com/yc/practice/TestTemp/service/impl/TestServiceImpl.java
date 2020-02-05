package com.yc.practice.TestTemp.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yc.core.TestTemp.entity.Test;
import com.yc.core.TestTemp.mapper.TestMapper;
import com.yc.practice.TestTemp.service.TestService;
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
* @Date 2019-09-29
* @Version: 1.0.0
*
*/
@Service
@Transactional(rollbackFor = Exception.class)
public class TestServiceImpl extends ServiceImpl<TestMapper, Test> implements TestService {

    @Override
    public void updateAll() {
        Test test = new Test();
        this.baseMapper.update(test,new QueryWrapper<Test>(null));
    }

}

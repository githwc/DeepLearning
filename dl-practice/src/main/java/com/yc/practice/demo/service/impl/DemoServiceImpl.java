package com.yc.practice.demo.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yc.core.demo.entity.Demo;
import com.yc.core.demo.mapper.DemoMapper;
import com.yc.core.demo.model.DemoQuery;
import com.yc.practice.demo.service.DemoService;
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
* @Date 2020-04-17
* @Version: 1.0.0
*
*/
@Service
@Transactional(rollbackFor = Exception.class)
public class DemoServiceImpl extends ServiceImpl<DemoMapper, Demo> implements DemoService {

    @Override
    public Page<Demo> demoPage(Page<Demo> page, DemoQuery query) {
        return this.baseMapper.demoPage(page,query);
    }

    @Override
    public void saveDemo(Demo demo) {
        if(StringUtils.isBlank(demo.getDemoId())){
            baseMapper.insert(demo);
        } else{
            baseMapper.updateById(demo);
        }
    }

    @Override
    public void deleteAlone(String demoId) {
        this.baseMapper.deleteById(demoId);
    }


}

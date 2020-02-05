package com.yc.practice.community.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yc.core.community.entity.Community;
import com.yc.core.community.mapper.CommunityMapper;
import com.yc.practice.community.service.CommunityService;
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
* @Date 2019-09-20
* @Version: 1.0.0
*
*/
@Service
@Transactional(rollbackFor = Exception.class)
public class CommunityServiceImpl extends ServiceImpl<CommunityMapper, Community> implements CommunityService {

}

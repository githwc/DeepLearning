package com.yc.mini.user.service.impl;

import com.yc.core.mini.entity.MiniUser;
import com.yc.core.mini.mapper.MiniUserMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yc.mini.user.service.MiniUserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
* 功能描述:
*
* @Author xieyc && 紫色年华
* @Date 2020-07-09
* @Version: 1.0.0
*/
@Service
@Transactional(rollbackFor = Exception.class)
public class MiniUserServiceImpl extends ServiceImpl<MiniUserMapper, MiniUser> implements MiniUserService {

}

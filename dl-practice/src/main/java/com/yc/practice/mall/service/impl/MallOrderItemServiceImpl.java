package com.yc.practice.mall.service.impl;

import com.yc.core.mall.entity.MallOrderItem;
import com.yc.core.mall.mapper.MallOrderItemMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yc.practice.mall.service.MallOrderItemService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
* 功能描述:
*
* @Author:  xieyc && 紫色年华
* @Date 2020-05-08
* @Version: 1.0.0
*/
@Service
@Transactional(rollbackFor = Exception.class)
public class MallOrderItemServiceImpl extends ServiceImpl<MallOrderItemMapper, MallOrderItem> implements MallOrderItemService {

}

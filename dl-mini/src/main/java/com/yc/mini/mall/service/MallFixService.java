package com.yc.mini.mall.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yc.core.mini.entity.MallFix;

import java.util.List;

/**
 * 功能描述:
 *
 * @Author: xieyc && 紫色年华
 * @Date 2020-06-26
 * @Version: 1.0.0
 */
public interface MallFixService extends IService<MallFix> {

    /**
     * 获取轮播图或导航栏
     *
     * @return list
     */
    List<MallFix> listFix();
}

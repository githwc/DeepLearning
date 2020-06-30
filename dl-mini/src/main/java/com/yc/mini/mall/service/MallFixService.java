package com.yc.mini.mall.service;

import com.yc.core.mall.entity.MallFix;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

/**
 * 功能描述:
 *
 * @Author:  xieyc && 紫色年华
 * @Date 2020-06-26
 * @Version: 1.0.0
 */
public interface MallFixService extends IService<MallFix> {

    /**
     * 获取轮播图或导航栏
     * @param type 类型 0:轮播图 1:导航栏
     * @return list
     */
    List<MallFix> listFix(String type);
}

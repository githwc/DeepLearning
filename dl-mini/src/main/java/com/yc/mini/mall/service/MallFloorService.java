package com.yc.mini.mall.service;

import cn.hutool.core.lang.tree.Tree;
import com.yc.core.mini.entity.MallFloor;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * 功能描述:
 *
 * @Author: xieyc && 紫色年华
 * @Date 2020-06-26
 * @Version: 1.0.0
 */
public interface MallFloorService extends IService<MallFloor> {

    /**
     * 楼层树
     *
     * @return tree
     */
    List<Tree<String>> tree();
}

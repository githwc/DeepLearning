package com.yc.mini.mall.service;

import cn.hutool.core.lang.tree.Tree;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yc.core.mall.entity.MallProductCategory;

import java.util.List;

/**
 * 功能描述:
 *
 * @Author: xieyc && 紫色年华
 * @Date 2020-05-08
 * @Version: 1.0.0
 */
public interface MallProductCategoryService extends IService<MallProductCategory> {

    /**
     * 类目树
     *
     * @return 树
     */
    List<Tree<String>> mallProductTree();
}

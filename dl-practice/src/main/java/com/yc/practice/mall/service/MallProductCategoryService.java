package com.yc.practice.mall.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yc.core.mall.entity.MallProductCategory;
import com.yc.core.tree.TreeNode;

import java.util.List;

/**
 * 功能描述:
 *
 * @Author:  xieyc && 紫色年华
 * @Date 2020-05-08
 * @Version: 1.0.0
 */
public interface MallProductCategoryService extends IService<MallProductCategory> {

    /**
     * 类目树
     * @return 树
     */
    List<TreeNode> classTree();

    /**
     * 添加类目
     * @param mallProductCategory 类目信息
     */
    void add(MallProductCategory mallProductCategory);

    /**
     * 查询子级类目
     * @param page 分页信息
     * @param parentId 父级类别ID
     * @return page
     */
    Page<MallProductCategory> childrenClass(Page<MallProductCategory> page, String parentId);

    /**
     * 删除类目
     * @param mallProductCategoryId 类目ID
     */
    void deleteAlone(String mallProductCategoryId);

    /**
     * 类目级联信息
     * @return case list
     */
    List<MallProductCategory> classList();
}

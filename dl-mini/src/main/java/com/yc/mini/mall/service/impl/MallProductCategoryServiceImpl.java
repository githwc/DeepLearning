package com.yc.mini.mall.service.impl;

import cn.hutool.core.lang.tree.Tree;
import cn.hutool.core.lang.tree.TreeNodeConfig;
import cn.hutool.core.lang.tree.TreeUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yc.common.global.error.Error;
import com.yc.common.global.error.ErrorException;
import com.yc.core.mall.entity.MallProductCategory;
import com.yc.core.mall.mapper.MallProductCategoryClassMapper;
import com.yc.mini.mall.service.MallProductCategoryService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 功能描述:
 *
 * @Author: xieyc && 紫色年华
 * @Date 2020-05-08
 * @Version: 1.0.0
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class MallProductCategoryServiceImpl extends ServiceImpl<MallProductCategoryClassMapper, MallProductCategory> implements MallProductCategoryService {

    @Override
    public List<Tree<String>> mallProductTree() {
        List<MallProductCategory> list = this.baseMapper.selectList(new LambdaQueryWrapper<MallProductCategory>()
                .orderByAsc(MallProductCategory::getSort)
        );
        TreeNodeConfig treeNodeConfig = new TreeNodeConfig();
        return TreeUtil.build(list, "root", treeNodeConfig, (category, treeNode) -> {
            treeNode.setId(category.getMallProductCategoryId());
            treeNode.setParentId(category.getParentId());
            treeNode.setName(category.getName());
        });
    }

}

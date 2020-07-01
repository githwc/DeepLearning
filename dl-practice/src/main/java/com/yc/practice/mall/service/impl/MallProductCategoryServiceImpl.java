package com.yc.practice.mall.service.impl;

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
import com.yc.practice.mall.service.MallProductCategoryService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
* 功能描述:
*
* @Author:  xieyc && 紫色年华
* @Date 2020-05-08
* @Version: 1.0.0
*/
@Service
@Transactional(rollbackFor = Exception.class)
public class MallProductCategoryServiceImpl extends ServiceImpl<MallProductCategoryClassMapper, MallProductCategory> implements MallProductCategoryService {

    @Override
    public List<Tree<String>> mallProductTree() {
        List<MallProductCategory> list = this.baseMapper.selectList(new LambdaQueryWrapper<MallProductCategory>()
                .eq(MallProductCategory::getState,"0")
                .orderByAsc(MallProductCategory::getSort)
        );
        TreeNodeConfig treeNodeConfig = new TreeNodeConfig();
        treeNodeConfig.setNameKey("title");
        return TreeUtil.build(list,"#",treeNodeConfig,(category, treeNode)->{
            treeNode.setId(category.getMallProductCategoryId());
            treeNode.setParentId(category.getParentId());
            treeNode.setName(category.getName());
            treeNode.putExtra("orderNum",category.getSort());
        });
    }

    @Override
    public void saveProductCategory(MallProductCategory mallProductCategory) {
        if(StringUtils.isNotBlank(mallProductCategory.getMallProductCategoryId())){
            this.updateById(mallProductCategory);
        } else {
            if (StringUtils.isBlank(mallProductCategory.getParentId())) {
                mallProductCategory.setParentId("root");
            }
            this.save(mallProductCategory);
        }
    }

    @Override
    public Page<MallProductCategory> childrenClass(Page<MallProductCategory> page, String parentId) {
        return baseMapper.selectPage(page, Wrappers.<MallProductCategory>lambdaQuery()
            .eq(MallProductCategory::getParentId,parentId)
        );
    }

    @Override
    public void deleteAlone(String mallProductCategoryId) {
        MallProductCategory mallProductCategory = this.getById(mallProductCategoryId);
        if(mallProductCategory == null) {
            throw new ErrorException(Error.ParameterNotFound);
        }else {
            // 逻辑删除子类目
            List<MallProductCategory> list = this.baseMapper.selectList(new LambdaQueryWrapper<MallProductCategory>()
                .eq(MallProductCategory::getParentId,mallProductCategoryId)
                    .eq(MallProductCategory::getState,0)
            );
            list.forEach(i->{
                MallProductCategory item = new MallProductCategory();
                item.setMallProductCategoryId(i.getMallProductCategoryId());
                item.setState(1);
                this.updateById(item);
            });
        }
        mallProductCategory.setState(1);
        this.baseMapper.updateById(mallProductCategory);
    }

    @Override
    public List<Tree<String>> listProductCategory() {
        List<MallProductCategory> list = this.baseMapper.selectList(new LambdaQueryWrapper<MallProductCategory>()
                .eq(MallProductCategory::getState,"0")
                .orderByAsc(MallProductCategory::getSort)
        );
        TreeNodeConfig treeNodeConfig = new TreeNodeConfig();
        treeNodeConfig.setIdKey("mallProductCategoryId");
        return TreeUtil.build(list,"root",treeNodeConfig,(category, treeNode)->{
            treeNode.setId(category.getMallProductCategoryId());
            treeNode.setParentId(category.getParentId());
            treeNode.setName(category.getName());
            treeNode.putExtra("sort",category.getSort());
        });
    }


}

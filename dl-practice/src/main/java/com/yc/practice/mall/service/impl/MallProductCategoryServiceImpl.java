package com.yc.practice.mall.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yc.common.global.error.Error;
import com.yc.common.global.error.ErrorException;
import com.yc.core.mall.entity.MallProductCategory;
import com.yc.core.mall.mapper.MallProductCategoryClassMapper;
import com.yc.core.tree.Tree;
import com.yc.core.tree.TreeNode;
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
    public List<TreeNode> classTree() {
        List<TreeNode> list = this.baseMapper.classTree();
        return Tree.getTreeList("#", list);
    }

    @Override
    public void add(MallProductCategory mallProductCategory) {
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
        return this.baseMapper.childrenClass(page,parentId);
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
    public List<MallProductCategory> classList() {
        List<MallProductCategory> list = this.baseMapper.selectList(new LambdaQueryWrapper<MallProductCategory>()
            .eq(MallProductCategory::getParentId,"root")
                .eq(MallProductCategory::getState,"0")
        );
        list.forEach(i->{
            List<MallProductCategory> item = this.baseMapper.selectList(new LambdaQueryWrapper<MallProductCategory>()
                .eq(MallProductCategory::getParentId,i.getMallProductCategoryId())
                    .eq(MallProductCategory::getState,"0")
            );
            i.setChildren(item);
        });
        return list;
    }


}

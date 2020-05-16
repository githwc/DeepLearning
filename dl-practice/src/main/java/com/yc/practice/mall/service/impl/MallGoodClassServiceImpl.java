package com.yc.practice.mall.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yc.common.global.error.Error;
import com.yc.common.global.error.ErrorException;
import com.yc.core.mall.entity.MallGoodClass;
import com.yc.core.mall.mapper.MallGoodClassMapper;
import com.yc.core.tree.Tree;
import com.yc.core.tree.TreeNode;
import com.yc.practice.mall.service.MallGoodClassService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
* 功能描述：
*
*  <p>版权所有：</p>
*  未经本人许可，不得以任何方式复制或使用本程序任何部分
*
* @Company: 紫色年华
* @Author xieyc
* @Date 2020-05-08
* @Version: 1.0.0
*
*/
@Service
@Transactional(rollbackFor = Exception.class)
public class MallGoodClassServiceImpl extends ServiceImpl<MallGoodClassMapper, MallGoodClass> implements MallGoodClassService {

    @Override
    public List<TreeNode> classTree() {
        List<TreeNode> list = this.baseMapper.classTree();
        return Tree.getTreeList("#", list);
    }

    @Override
    public void add(MallGoodClass mallGoodClass) {
        if (StringUtils.isBlank(mallGoodClass.getParentId())) {
            mallGoodClass.setParentId("root");
        }
        this.save(mallGoodClass);
    }

    @Override
    public Page<MallGoodClass> childrenClass(Page<MallGoodClass> page, String parentId) {
        return this.baseMapper.childrenClass(page,parentId);
    }

    @Override
    public void editById(MallGoodClass mallGoodClass) {
        this.updateById(mallGoodClass);
    }

    @Override
    public void deleteAlone(String mallGoodClassId) {
        MallGoodClass mallGoodClass = this.getById(mallGoodClassId);
        if(mallGoodClass == null) {
            throw new ErrorException(Error.paramError);
        }else {
            //逻辑删除子类目
            List<MallGoodClass> list = this.baseMapper.selectList(new LambdaQueryWrapper<MallGoodClass>()
                .eq(MallGoodClass::getParentId,mallGoodClassId)
                    .eq(MallGoodClass::getState,0)
            );
            list.forEach(i->{
                MallGoodClass item = new MallGoodClass();
                item.setMallGoodClassId(i.getMallGoodClassId());
                item.setState(1);
                this.updateById(item);
            });
        }
    }

    @Override
    public List<MallGoodClass> classList() {
        List<MallGoodClass> list = this.baseMapper.selectList(new LambdaQueryWrapper<MallGoodClass>()
            .eq(MallGoodClass::getParentId,"root")
                .eq(MallGoodClass::getState,"0")
        );
        list.forEach(i->{
            List<MallGoodClass> item = this.baseMapper.selectList(new LambdaQueryWrapper<MallGoodClass>()
                .eq(MallGoodClass::getParentId,i.getMallGoodClassId())
                    .eq(MallGoodClass::getState,"0")
            );
            i.setChildren(item);
        });
        return list;
    }


}

package com.yc.practice.mall.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yc.core.mall.entity.MallGoodClass;
import com.yc.core.tree.TreeNode;

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
public interface MallGoodClassService extends IService<MallGoodClass> {

    /**
     * 类目树
     * @return 树
     */
    List<TreeNode> classTree();

    /**
     * 添加类目
     * @param mallGoodClass 类目信息
     */
    void add(MallGoodClass mallGoodClass);

    /**
     * 查询子级类目
     * @param page 分页信息
     * @param parentId 父级类别ID
     * @return page
     */
    Page<MallGoodClass> childrenClass(Page<MallGoodClass> page, String parentId);

    /**
     * 编辑商品类目
     * @param mallGoodClass 类目信息
     */
    void editById(MallGoodClass mallGoodClass);

    /**
     * 删除类目
     * @param mallGoodClassId 类目ID
     */
    void deleteAlone(String mallGoodClassId);

    /**
     * 类目级联信息
     * @return case list
     */
    List<MallGoodClass> classList();
}

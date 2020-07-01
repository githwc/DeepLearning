package com.yc.practice.system.service;

import cn.hutool.core.lang.tree.Tree;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yc.core.system.entity.SysDict;
import com.yc.core.system.model.query.DictQuery;
import com.yc.core.tree.TreeNode;

import java.util.List;

/**
 * 功能描述:
 *
 * @Author:  xieyc && 紫色年华
 * @Date 2019-09-20
 * @Version: 1.0.0
 */
public interface SysDictService extends IService<SysDict> {

    /**
     * 查询所有字典,并以树结构格式返回前端
     * @param name [搜索条件] 字典名称
     * @return tree
     */
    List<Tree<String>> dictTree(String name);

    /**
     * 查询子级字典
     * @param page 分页信息
     * @param dictQuery 父级字典ID
     * @return deptList
     */
    Page<SysDict> childrenDict(Page<SysDict> page, DictQuery dictQuery);

    /**
     * 根据字典ID删除
     * @param id 标识
     */
    void deleteAlone(String id);

    /**
     * 字典批量删除
     * @param ids 批量标识
     */
    void deleteBatch(String ids);

    /**
     * 创建字典
     * @param sysDict 字典信息
     */
    void saveDict(SysDict sysDict);

    /**
     * 根据指定路径读取子级字典
     * @param skeys 字典路径，分隔符">"
     * @return list
     */
    List<SysDict> getDict(String skeys);

}

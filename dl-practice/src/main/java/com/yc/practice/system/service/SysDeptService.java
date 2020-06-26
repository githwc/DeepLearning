package com.yc.practice.system.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yc.core.system.entity.SysDept;
import com.yc.core.system.model.query.DeptQuery;
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
 * @Date 2019-09-20
 * @Version: 1.0.0
 *
 */
public interface SysDeptService extends IService<SysDept> {

    /**
     * 查询所有部门,并以树结构格式返回前端
     * @param departName [搜索条件] 部门名称
     * @return tree
     */
    List<TreeNode> departTree(String departName);

    /**
     * 查询子级部门
     * @param page 分页信息
     * @param deptQuery 父级部门ID
     * @return deptList
     */
    Page<SysDept> childrenDept(Page<SysDept> page, DeptQuery deptQuery);

    /**
     * 根据部门ID删除
     * @param id 部門ID
     */
    void deleteAlone(String id);

    /**
     * 部门批量删除
     * @param ids ids
     */
    void deleteBatch(String ids);

    /**
     * 创建/更新部门
     * @param sysDept 部门信息
     */
    void saveDept(SysDept sysDept);
}

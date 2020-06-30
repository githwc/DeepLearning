package com.yc.core.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yc.core.system.entity.SysDept;
import com.yc.core.tree.TreeNode;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 *
 * 功能描述:
 *
 *

 * @Author:  xieyc && 紫色年华
 * @Date 2019-09-20
 * @Version: 1.0.0
 */
@Repository
public interface SysDeptMapper extends BaseMapper<SysDept> {

    /**
     * 部门树
     * @param departName 搜索条件
     * @return tree
     */
    List<TreeNode> departTree(@Param("departName") String departName);

}

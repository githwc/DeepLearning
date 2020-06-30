package com.yc.core.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yc.core.system.entity.SysDict;
import com.yc.core.tree.TreeNode;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 *
 * 功能描述:
 *
 * @Author:  xieyc && 紫色年华
 * @Date 2019-09-20
 * @Version: 1.0.0
 */
@Repository
public interface SysDictMapper extends BaseMapper<SysDict> {

    /**
     * 字典树
     * @param name 搜索条件
     * @return tree
     */
    List<TreeNode> dictTree(@Param("name") String name);

    /**
     * 根据字典路径查询子集
     *  # 弃用
     * @param hqls hql
     * @return
     */
    List<SysDict> getDict(@Param("hqls") String hqls);

    /**
     * 根据字典路径查询数据
     * @param firstName 一级name
     * @param secondName 二级name
     * @return
     */
    List<SysDict> getDictByRoute(@Param("firstName") Object firstName,
                                 @Param("secondName") Object secondName);

}

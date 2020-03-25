package com.yc.core.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yc.core.system.entity.SysDict;
import com.yc.core.system.model.query.DictQuery;
import com.yc.core.tree.TreeNode2;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 *
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
@Repository
public interface SysDictMapper extends BaseMapper<SysDict> {

    /**
     * 字典树
     * @param name 搜索条件
     * @return tree
     */
    List<TreeNode2> dictTree(@Param("name") String name);

    /**
     * 子级字典
     * @param page 分页
     * @param dictQuery 父级ID
     * @return dept list
     */
    Page<SysDict> childrenDict(@Param("page") Page<SysDict> page, @Param("query") DictQuery dictQuery);

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

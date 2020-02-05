package com.yc.practice.system.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yc.core.system.entity.SysDict;
import com.yc.core.system.model.query.DictQuery;
import com.yc.core.tree.TreeNode;

import java.util.LinkedHashMap;
import java.util.List;

/**
 * 功能描述：
 *
 *  <p>版权所有：</p>
 *  未经本人许可，不得以任何方式复制或使用本程序任何部分
 *
 * @Company: LionHerding
 * @Author xieyc
 * @Date 2019-09-20
 * @Version: 1.0.0
 *
 */
public interface SysDictService extends IService<SysDict> {

    /**
     * 查询所有字典,并以树结构格式返回前端
     * @param name [搜索条件] 字典名称
     * @return tree
     */
    List<TreeNode> dictTree(String name);

    /**
     * 查询子级字典
     * @param page 分页信息
     * @param dictQuery 父级字典ID
     * @return deptList
     */
    Page<SysDict> childrenDict(Page<SysDict> page, DictQuery dictQuery);

    /**
     * 根据字典Id修改
     * @param sysDict
     */
    void editByDictId(SysDict sysDict);

    /**
     * 根据字典ID删除
     * @param id
     */
    void deleteAlone(String id);

    /**
     * 字典批量删除
     * @param ids
     */
    void deleteBatch(String ids);

    /**
     * 创建字典
     * @param sysDict
     */
    void create(SysDict sysDict);

    /**
     * 读取字典，返回字典集合
     *
     * @param sKey : 字典路径，分隔符">"
     * @param mode : 字典模式 0,1
     * @return LinkedHashMap&gt;String, Object&lt;
     */
    LinkedHashMap<String, Object> getDict(String sKey, String mode);

    /**
     * 读取字典，返回字典字符串
     *
     * @param sKey : 字典路径，分隔符">"
     * @param mode : 字典模式
     * @return String
     */
    String getDictString(String sKey, String mode);

    /**
     * 读取字典(Key-Value)
     *
     * @param sKey : 字典路径，分隔符">"
     * @return LinkedHashMap&gt;String, Object&lt;
     */
    LinkedHashMap<String, Object> getKeyValue(String sKey);

    /**
     * 读取字典(Key-Value)
     *
     * @param sKey : 字典路径，分隔符">"
     * @return String
     */
    String getKeyValueString(String sKey);

    /**
     * 读取字典(Value-Key)
     *
     * @param sKey : 字典路径，分隔符">"
     * @return LinkedHashMap&gt;String, Object&lt;
     */
    LinkedHashMap<String, Object> getValueKey(String sKey);

    /**
     * 读取字典(Value-Key)
     *
     * @param sKey : 字典路径，分隔符">"
     * @return String
     */
    String getValueKeyString(String sKey);

    /**
     * 根据指定路径读取子级字典
     * @param skeys 字典路径，分隔符">"
     * @return
     */
    List<SysDict> getDict(String skeys);

}

package com.yc.core.tree;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 功能描述：树节点数据
 * <p>版权所有：</p>
 * 未经本人许可，不得以任何方式复制或使用本程序任何部分
 *
 * @Company: 紫色年华
 * @Author: xieyc
 * @Datetime: 2020-03-25
 * @Version: 1.0.0
 */
@Data
public class TreeNode implements TreeEntity<TreeNode>, Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * Key
     */
    @JSONField(ordinal = 1)
    public String id;

    /**
     * value
     */
    @JSONField(ordinal = 2)
    private String value;

    /**
     * 父级ID
     */
    @JSONField(ordinal = 3)
    public String parentId;

    /**
     * 节点名称
     */
    @JSONField(ordinal = 4)
    public String title;

    /**
     * 树节点层级
     */
    @JSONField(ordinal = 5)
    private Integer level;

    /**
     * 树节点排序号
     */
    @JSONField(ordinal = 6)
    private Integer orderNum;

    /**
     * 树节点是否禁用checkBox
     */
    @JSONField(ordinal = 7)
    private Boolean disableCheckbox;

    /**
     * 子级
     */
    @JSONField(ordinal = 8)
    public List<TreeNode> children;

    //  ======================== 类方法 ==================
}

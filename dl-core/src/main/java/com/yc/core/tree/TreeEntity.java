package com.yc.core.tree;

import java.util.List;

/**
 * 功能描述:
 *
 * @Author: xieyc && 紫色年华
 * @Date: 2020-03-25
 * @Version: 1.0.0
 */
public interface TreeEntity<E> {

    /**
     * key
     * @return id
     */
    String getId();

    /**
     * value
     * @return value
     */
    String getValue();

    /**
     * pid
     * @return 父级ID
     */
    String getParentId();

    /**
     * 节点名称
     * @return title
     */
    String getTitle();

    /**
     * 等级
     * @return 等级
     */
    Integer getLevel();

    /**
     * 序号
     * @return 序号
     */
    Integer getOrderNum();

    /**
     * 是否禁用
     * @return boolean
     */
    Boolean getDisableCheckbox();

    /**
     * 子级
     * @param children 子级
     */
    void setChildren(List<E> children);

}

package com.yc.core.tree;

import java.util.List;

/**
 * 功能描述：
 * <p>版权所有：</p>
 * 未经本人许可，不得以任何方式复制或使用本程序任何部分
 *
 * @Company: 紫色年华
 * @Author: xieyc
 * @Datetime: 2020-03-25
 * @Version: 1.0.0
 */
public interface TreeEntity<E> {

    String getId();

    String getValue();

    String getParentId();

    String getTitle();

    Integer getLevel();

    Integer getOrderNum();

    Boolean getDisableCheckbox();

    void setChildren(List<E> children);

}

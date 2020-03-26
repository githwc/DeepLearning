package com.yc.core.tree;

import java.util.ArrayList;
import java.util.List;

/**
 * 功能描述：
 *  构建树形数据
 *
 * <p>版权所有：</p>
 * 未经本人许可，不得以任何方式复制或使用本程序任何部分
 *
 * @Company: 紫色年华
 * @Author: xieyc
 * @Datetime: 2020-03-25
 * @Version: 1.0.0
 */
public class Tree {

    /**
     * 构建树形数据
     *
     * @param topId 顶级元素父ID
     * @param entityList list
     * @return tree list
     */
    public static <E extends TreeEntity<E>> List<E> getTreeList(String topId, List<E> entityList) {
        List<E> resultList = new ArrayList<>();
        // 获取顶层元素集合
        String parentId;
        for (E entity : entityList) {
            parentId = entity.getParentId();
            if (parentId == null || topId.equals(parentId)) {
                resultList.add(entity);
            }
        }
        // 获取子节点
        for (E entity : resultList) {
            entity.setChildren(getSubList(entity.getId(), entityList));
        }
        return resultList;
    }

    /**
     * 获取子数据集合
     *
     * @param id id
     * @param entityList list
     * @return 子集合
     */
    private static <E extends TreeEntity<E>> List<E> getSubList(String id, List<E> entityList) {
        List<E> childList = new ArrayList<>();
        String parentId;
        // 子集的直接子对象
        for (E entity : entityList) {
            parentId = entity.getParentId();
            if (id.equals(parentId)) {
                childList.add(entity);
            }
        }
        // 子集的间接子对象
        for (E entity : childList) {
            entity.setChildren(getSubList(entity.getId(), entityList));
        }
        // 递归退出条件
        if (childList.size() == 0) {
            return null;
        }
        return childList;
    }

}

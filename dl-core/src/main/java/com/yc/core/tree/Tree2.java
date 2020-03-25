package com.yc.core.tree;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;

import java.util.*;

/**
 * 功能描述：
 *
 * <p>版权所有：</p>
 * 未经本人许可，不得以任何方式复制或使用本程序任何部分
 *
 * @Company: 紫色年华
 * @Author: xieyc
 * @Datetime: 2019-12-26 18:05
 * @Version: 1.0.0
 */
public class Tree2 {
    private Map<String, TreeNode2> treeNodeMap = new LinkedHashMap<>();
    private List<TreeNode2> treeNodeList;
    private JSON treeJson;

    /**
     * 根节点编码
     */
    public static final String ROOT_NODE_CODE = "0";

    /**
     * 初始化树对象
     *
     * @param treeNodes 树简单数据集合对象
     */
    public Tree2(List<TreeNode2> treeNodes) {
        this.treeNodeList = treeNodes;
    }

    /**
     * 初始化树对象
     *
     * @param treeJson 树JSONObject对象
     */
    public Tree2(JSON treeJson) {
        this.treeJson = treeJson;
    }

    /**
     * 设置根节点
     * 根节点的选中状态会根据第一层节点的checked选中情况进行勾选，如果下级节点全部选中则副节点也全部选中，反之不选中
     *
     * @param rootNodeName 根节点名称
     * @return
     */
    public Tree2 setRoot(String rootNodeName) {
        TreeNode2 rootNode = treeNodeMap.get(ROOT_NODE_CODE);
        TreeNode2 treeNode;
        if (rootNode == null || rootNode.getChildren().isEmpty()) {
            treeNode = new TreeNode2("0","#", rootNodeName, 0, true);
        } else {
            boolean rootChecked = true;
            for (TreeNode2 child : rootNode.getChildren()) {
                if (child != null && !child.getChecked()) {
                    rootChecked = false;
                }
            }
            treeNode = new TreeNode2("0","#", rootNodeName, 0, rootChecked);
        }
        this.treeNodeMap.put(ROOT_NODE_CODE, treeNode);
        return this;
    }

    /**
     * 使用简单数据集合构建tree
     *
     * @return
     */
    public Tree2 build() {
        if (!treeNodeList.isEmpty()) {
            buildList();
        }
        if (treeJson != null) {
            buildJson(treeJson);
        }
        return this;
    }

    /**
     * 构建list to map
     */
    private void buildList() {
        // 倒叙排序list
        treeNodeList.sort((o1, o2) -> {
            int o1OrderNum = o1.getOrderNum() == null ? 0 : o1.getOrderNum();
            int o2OrderNum = o2.getOrderNum() == null ? 0 : o2.getOrderNum();
            return o2OrderNum - o1OrderNum;
        });

        for (TreeNode2 treeNode : treeNodeList) {
            treeNodeMap.put(treeNode.getId(), treeNode);
        }

        for (Map.Entry<String, TreeNode2> entry : treeNodeMap.entrySet()) {
            if (StringUtils.isBlank(entry.getValue().getParentId())) {
                continue;
            }
            TreeNode2 treeNodeParent = treeNodeMap.get(entry.getValue().getParentId());
            if (treeNodeParent != null) {
                entry.getValue().setParent(treeNodeParent);
                treeNodeParent.addChildren(entry.getValue());
            }
        }
    }

    /**
     * 构建json to map
     *
     * @param json
     */
    private void buildJson(JSON json) {
        if (json instanceof JSONObject) {
            JSONObject treeNodeJsonObject = (JSONObject) json;
            treeNodeMap.put(treeNodeJsonObject.getString("id"), treeNodeJsonObject.toJavaObject(TreeNode2.class));
            if (treeNodeJsonObject.containsKey("children")) {
                buildJson(treeNodeJsonObject.getJSONArray("children"));
            }
        } else if (json instanceof JSONArray) {
            JSONArray treeNodeJsonArray = (JSONArray) json;
            JSONObject treeNodeJsonObject;
            for (int i = 0; i < treeNodeJsonArray.size(); i++) {
                treeNodeJsonObject = treeNodeJsonArray.getJSONObject(i);
                treeNodeMap.put(treeNodeJsonObject.getString("id"), treeNodeJsonObject.toJavaObject(TreeNode2.class));
                if (treeNodeJsonObject.containsKey("children")) {
                    buildJson(treeNodeJsonObject.getJSONArray("children"));
                }
            }
        }
    }

    /**
     * 获取根节点，本方法要求根节点只有一个
     *
     * @return
     */
    public TreeNode2 getRootNode() {
        if (treeNodeMap.containsKey(ROOT_NODE_CODE)) {
            return treeNodeMap.get(ROOT_NODE_CODE);
        }
        for (Map.Entry<String, TreeNode2> entry : treeNodeMap.entrySet()) {
            if (entry.getValue().getParent() == null) {
                return entry.getValue();
            }
        }
        return null;
    }

    /**
     * 获取根节点数据，根节点可能会存在多个
     *
     * @return
     */
    public List<TreeNode2> getRootNodes() {
        List<TreeNode2> list = new LinkedList<>();
        for (Map.Entry<String, TreeNode2> entry : treeNodeMap.entrySet()) {
            if (entry.getValue().getParent() == null) {
                list.add(entry.getValue());
            }
        }
        return list;
    }

    /**
     * 获取树集合选中节点
     *
     * @return
     */
    public List<TreeNode2> getTreeCheckedList() {
        // 获取tree选中项目
        List<TreeNode2> treeNodeCheckeds = new ArrayList<>();
        for (TreeNode2 treeNode : treeNodeMap.values()) {
            if (treeNode.getChecked()) {
                treeNodeCheckeds.add(treeNode);
            }
        }
        return treeNodeCheckeds;
    }
}

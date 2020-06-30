package com.yc.core.system.model.vo;

import com.yc.core.system.entity.SysPermission;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 功能描述:树形列表VO

 *

 * @Author: xieyc && 紫色年华
 * @Date: 2019-10-28
 * @Version: 1.0.0
 */
@Data
public class TreeModel implements Serializable {

    private static final long serialVersionUID = 4013193970046502756L;

    private String key;

    private String title;

    private String slotTitle;

    private boolean isLeaf;

    private String icon;

    private Integer ruleFlag;

    private Map<String,String> scopedSlots;

    private String parentId;

    private String label;

    private String value;

    private List<TreeModel> children;

    public TreeModel() { }

    public TreeModel(SysPermission permission) {
        this.key = permission.getSysPermissionId();
        this.icon = permission.getIcon();
        this.parentId = permission.getParentId();
        this.title = permission.getName();
        this.slotTitle =  permission.getName();
        this.value = permission.getSysPermissionId();
        this.isLeaf = permission.getIsLeaf();
        this.label = permission.getName();
        if(!permission.getIsLeaf()) {
            this.children = new ArrayList<TreeModel>();
        }
    }

    public TreeModel(String key,String parentId,String slotTitle,Integer ruleFlag,boolean isLeaf) {
        this.key = key;
        this.parentId = parentId;
        this.ruleFlag=ruleFlag;
        this.slotTitle =  slotTitle;
        Map<String,String> map = new HashMap<String,String>();
        map.put("title", "hasDatarule");
        this.scopedSlots = map;
        this.isLeaf = isLeaf;
        this.value = key;
        if(!isLeaf) {
            this.children = new ArrayList<TreeModel>();
        }
    }


}

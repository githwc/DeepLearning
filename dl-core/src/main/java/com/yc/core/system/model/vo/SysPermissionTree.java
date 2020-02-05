package com.yc.core.system.model.vo;

import com.yc.core.system.entity.SysPermission;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * 功能描述：菜单按钮VO类
 * <p>版权所有：</p>
 * 未经本人许可，不得以任何方式复制或使用本程序任何部分
 *
 * @Company: 紫色年华
 * @Author: xieyc
 * @Datetime: 2019-10-25
 * @Version: 1.0.0
 */
@Data
public class SysPermissionTree implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    private String id;

    private String key;
    private String title;

    /**
     * 父id
     */
    private String parentId;

    /**
     * 菜单名称
     */
    private String name;

    /**
     * 菜单权限编码
     */
    private String permsCode;
    /**
     * 权限策略1显示2禁用
     */
    private String permsType;

    /**
     * 菜单图标
     */
    private String icon;

    /**
     * 组件
     */
    private String component;

    /**
     * 跳转网页链接
     */
    private String url;

    /**
     * 一级菜单跳转地址
     */
    private String redirect;

    /**
     * 菜单排序
     */
    private Integer sort;

    /**
     * 类型（0：一级菜单；1：子菜单 ；2：按钮权限）
     */
    private Integer menuType;

    /**
     * 是否叶子节点: 1:是 0:不是
     */
    private boolean isLeaf;

    /**
     * 是否路由菜单: 0:不是  1:是（默认值1）
     */
    private boolean isRoute;

    /**
     * 是否路缓存页面: 0:不是  1:是（默认值1）
     */
    private Boolean keepAlive;

    /**
     * 描述
     */
    private String description;

    /**
     * 删除状态 0正常 1已删除
     */
    private Integer delFlag;

    /**
     * 创建人
     */
    private String createuserId;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新人
     */
    private String updateUserId;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;

    /**
     * 是否聚合路由
     */
    private Boolean alwaysShow;

    /**是否隐藏路由菜单: 0否,1是（默认值0）*/
    private Integer hidden;

    /**按钮权限状态(0无效1有效)*/
    private String status;

    private List<SysPermissionTree> children;

    public SysPermissionTree() {
    }

    /**
     * 将SysPermission对象转换成SysPermissionTree对象
     * @param permission
     */
    public SysPermissionTree(SysPermission permission) {
        this.key = permission.getSysPermissionId();
        this.id = permission.getSysPermissionId();
        this.permsCode = permission.getPermsCode();
        this.permsType = permission.getPermsType();
        this.component = permission.getComponent();
        this.createuserId = permission.getCreateUserId();
        this.createTime = permission.getCreateTime();
        this.delFlag = permission.getDelFlag();
        this.description = permission.getDescription();
        this.icon = permission.getIcon();
        this.isLeaf = permission.getIsLeaf();
        this.menuType = permission.getMenuType();
        this.name = permission.getName();
        this.parentId = permission.getParentId();
        this.sort = permission.getSort();
        this.updateUserId = permission.getUpdateUserId();
        this.updateTime = permission.getUpdateTime();
        this.redirect = permission.getRedirect();
        this.url = permission.getUrl();
        this.hidden = permission.getHidden();
        this.isRoute = permission.getIsRoute();
        this.keepAlive = permission.getKeepAlive();
        this.alwaysShow= permission.getAlwaysShow();
        this.title=permission.getName();
        if (!permission.getIsLeaf()) {
            this.children = new ArrayList<SysPermissionTree>();
        }
        this.status = permission.getStatus();
    }

}

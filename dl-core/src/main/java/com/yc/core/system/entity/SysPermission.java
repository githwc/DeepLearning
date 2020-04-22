package com.yc.core.system.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 功能描述：权限
 *  <p>版权所有：</p>
 *  未经本人许可，不得以任何方式复制或使用本程序任何部分
 *
 * @Company: 紫色年华
 * @Author xieyc
 * @Date 2019-09-20
 * @Version: 1.0.0
 *
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class SysPermission implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * 主键id
     */
    @TableId(value = "sys_permission_id", type = IdType.UUID)
    private String sysPermissionId;
    /**
     * 父id
     */
    private String parentId;
    /**
     * 菜单标题
     */
    private String name;
    /**
     * 路径
     */
    private String url;
    /**
     * 组件
     */
    private String component;
    /**
     * 菜单类型(0:一级菜单; 1:子菜单:2:按钮)
     */
    private Integer menuType;
    /**
     * 菜单权限编码
     */
    private String permsCode;
    /**
     * 菜单排序
     */
    private Integer sort;
    /**
     * 菜单图标
     */
    private String icon;
    /**
     * 是否路由菜单: 0:不是  1:是（默认值1）
     */
    private Boolean isRoute;
    /**
     * 是否叶子结点:    1:是   0:不是
     */
    private Boolean isLeaf;
    /**
     * 是否缓存页面: 0:不是  1:是（默认值1）
     */
    private Boolean keepAlive;
    /**
     * 删除状态 0正常 1已删除
     */
    private Integer delFlag;
    /**
     * 创建人
     */
    private String createUserId;
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


    /////////////////////////////// 非表字段 ///////////////////////////////

    public SysPermission() {}

    public SysPermission(boolean index) {
        if(index) {
            this.sysPermissionId = "9502685863ab87f0ad1134142788a385";
            this.name="首页";
            this.component="dashboard/Analysis";
            this.url="/dashboard/analysis";
            this.icon="home";
            this.menuType=0;
            this.sort=0;
            this.delFlag=0;
            this.isRoute=true;
            this.isLeaf=true;
        }
    }

}

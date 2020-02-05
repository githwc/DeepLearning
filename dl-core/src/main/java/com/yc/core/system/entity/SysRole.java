package com.yc.core.system.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 功能描述：
 *  <p>版权所有：</p>
 *  未经本人许可，不得以任何方式复制或使用本程序任何部分
 *
 * @Company: 紫色年华
 * @Author   xieyc
 * @Date 2019-09-19
 * @Version: 1.0.0
 *
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class SysRole implements Serializable {

    private static final long serialVersionUID = 1L;
    @TableId(value = "sys_role_id", type = IdType.UUID)
    private String sysRoleId;
    /**
     * 角色名称
     */
    private String roleName;
    /**
     * 角色代码
     */
    private String roleCode;
    /**
     * 状态(0启用 1不启用))
     */
    private Integer state;
    /**
     * 删除状态(0正常，1删除)
     */
    private Integer delFlag;
    /**
     * 排序
     */
    private Integer sort;
    /**
     * 备注
     */
    private String remark;
    /**
     * 创建人
     */
    private String createUserId;
    /**
     * 创建时间
     */
    private LocalDateTime createTime;
    /**
     * 修改人
     */
    private String updateUserId;
    /**
     * 修改时间
     */
    private LocalDateTime updateTime;


    /////////////////////////////// 非表字段 ///////////////////////////////

}

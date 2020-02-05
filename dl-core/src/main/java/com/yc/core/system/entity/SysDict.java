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
 * @Company: LionHerding
 * @Author xieyc
 * @Date 2019-09-20
 * @Version: 1.0.0
 *
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class SysDict implements Serializable {

    private static final long serialVersionUID = 1L;
    @TableId(value = "sys_dict_id", type = IdType.UUID)
    private String sysDictId;
    /**
     * 父级ID
     */
    private String parentId;
    /**
     * 字典项文本
     */
    private String name;
    /**
     * 字典项值
     */
    private String value;
    /**
     * 备注
     */
    private String remark;
    /**
     * 状态(0启用 1不启用)
     */
    private Integer state;
    /**
     * 删除状态(0:未删除 1:已删除)
     */
    private Integer delFlag;
    /**
     * 排序
     */
    private Integer sort;
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

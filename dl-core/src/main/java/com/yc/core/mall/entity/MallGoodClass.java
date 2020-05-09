package com.yc.core.mall.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 功能描述：商品类别
 *  <p>版权所有：</p>
 *  未经本人许可，不得以任何方式复制或使用本程序任何部分
 *
 * @Company: 紫色年华
 * @Author xieyc
 * @Date 2020-05-08
 * @Version: 1.0.0
 *
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class MallGoodClass implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * 类别Id
     */
    @TableId(value = "mall_good_class_id", type = IdType.UUID)
    private String mallGoodClassId;
    /**
     * 父类别id当id=0时说明是根节点,一级类别
     */
    private String parentId;
    /**
     * 类别名称
     */
    private String name;
    /**
     * 类别状态0-正常,1-已废弃
     */
    private Boolean state;
    /**
     * 排序
     */
    private Integer sort;
    /**
     * 创建时间
     */
    private LocalDateTime createTime;
    /**
     * 更新时间
     */
    private LocalDateTime updateTime;



}

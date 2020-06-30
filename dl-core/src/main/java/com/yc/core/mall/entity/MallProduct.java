package com.yc.core.mall.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 功能描述:
 *
 * @Author:  xieyc && 紫色年华
 * @Date 2020-04-08
 * @Version: 1.0.0
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class MallProduct implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * 主键
     */
    @TableId(value = "mall_product_id", type = IdType.ASSIGN_UUID)
    private String mallProductId;
    /**
     * 商品名称
     */
    private String name;
    /**
     * 分类ID
     */
    private String classId;
    /**
     * 商品状态(0:待审核 1:已通过 2：已拒绝 3:已上架 4-已下架 5-删除)
     */
    private String state;
    /**
     * 图片
     */
    private String pic;
    /**
     * 排序
     */
    private Integer sort;
    /**
     * 销量
     */
    private Integer sale;
    /**
     * 价格
     */
    private BigDecimal price;
    /**
     * 商品描述
     */
    private String description;
    /**
     * 库存
     */
    private Integer stock;
    /**
     * 备注
     */
    private String remark;

    // ============= 非表字段 ===============

    /**
     * 父级类目
     */
    @TableField(exist = false)
    private String pClassId;

}

package com.yc.core.mall.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 商品类目
 * </p>
 *
 * @author xieyc && 紫色年华
 * @date 2020-06-23
 * @Version: 1.0.0
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class ProductCategory implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * 商品类目ID
     */
    @TableId(value = "product_category_id", type = IdType.ASSIGN_UUID)
    private String productCategoryId;
    /**
     * 商品类目PID
     */
    private String productCategoryPid;
    /**
     * 类目名称
     */
    private String name;
    /**
     * 类目级别
     */
    private Integer level;
    /**
     * 是否有下级
     */
    private Boolean childrenFlag;
    /**
     * 排序号
     */
    private Integer seqNum;

    private String id;
    private String pid;
}

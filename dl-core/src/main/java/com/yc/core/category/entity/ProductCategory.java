package com.yc.core.category.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

import com.sun.org.apache.xpath.internal.operations.Bool;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 功能描述：
 *  <p>版权所有：</p>
 *  未经本人许可，不得以任何方式复制或使用本程序任何部分
 *
 * @Company: 紫色年华
 * @Author xieyc
 * @Date 2020-06-18
 * @Version: 1.0.0
 *
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class ProductCategory implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * 商品类目ID
     */
    @TableId(value = "product_category_id", type = IdType.UUID)
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
     * 类目等级
     */
    private Integer level;
    /**
     * 是否存在下级
     */
    private Boolean childrenFlag;
    /**
     * 排序号
     */
    private Integer seqNum;

}

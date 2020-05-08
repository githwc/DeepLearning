package com.yc.core.mall.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
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
 * @Date 2020-04-08
 * @Version: 1.0.0
 *
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class MallGood implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * 主键
     */
    @TableId(value = "mall_good_id", type = IdType.UUID)
    private String mallGoodId;
    /**
     * 商品名称
     */
    private String name;
    /**
     * 分类ID
     */
    private String classId;
    /**
     * 商品状态(0:待售 1:上架 2-下架 3-删除)
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
     * 库存预警值
     */
    private Integer lowStock;
    /**
     * 备注
     */
    private String remark;



}

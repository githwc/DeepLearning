package com.yc.core.mall.model.query;

import lombok.Data;

/**
 * 功能描述:商品查询入参
 *
 * @Author: xieyc && 紫色年华
 * @Date: 2020-04-11
 * @Version: 1.0.0
 */
@Data
public class GoodQuery {

    /**
     * 商品名称
     */
    private String name;

    /**
     * 查询有库存商品
     */
    private String stockFlag;

    /**
     * 商品状态
     */
    private String state;

    /**
     * 商品类目
     */
    private String mallProductCategoryId;

}

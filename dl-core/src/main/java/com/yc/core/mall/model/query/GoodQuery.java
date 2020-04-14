package com.yc.core.mall.model.query;

import lombok.Data;

/**
 * 功能描述：商品查询入参
 * <p>版权所有：</p>
 * 未经本公司许可，不得以任何方式复制或使用本程序任何部分
 *
 * @Company: 紫色年华
 * @Author: xieyc
 * @Datetime: 2020-04-11
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

}

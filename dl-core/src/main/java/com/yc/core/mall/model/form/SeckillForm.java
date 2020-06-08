package com.yc.core.mall.model.form;

import lombok.Data;

/**
 * 功能描述：秒杀form
 * <p>版权所有：</p>
 * 未经本人许可，不得以任何方式复制或使用本程序任何部分
 *
 * @Company: 紫色年华
 * @Author: xieyc
 * @Datetime: 2020-06-08
 * @Version: 1.0.0
 */
@Data
public class SeckillForm {

    /**
     * 凭证
     */
    private String md5;

    /**
     * 秒杀项目
     */
    private String mallSeckillId;

    /**
     * 执行时间
     */
    private String killTime;

}

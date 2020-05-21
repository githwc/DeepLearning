package com.yc.core.message.model.query;

import lombok.Data;

/**
 * 功能描述：
 * <p>版权所有：</p>
 * 未经本人许可，不得以任何方式复制或使用本程序任何部分
 *
 * @Company: 紫色年华
 * @Author: xieyc
 * @Datetime: 2020-05-21
 * @Version: 1.0.0
 */
@Data
public class MessageReceiveQuery {

    /**
     * 标题
     */
    private String title;

    /**
     * 用户ID
     */
    private String userId;
}

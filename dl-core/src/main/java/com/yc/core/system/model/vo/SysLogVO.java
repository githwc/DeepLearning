package com.yc.core.system.model.vo;

import com.yc.core.system.entity.SysLog;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 功能描述: 日志列表
 *
 * <p>版权所有：</p>
 * 未经本人许可，不得以任何方式复制或使用本程序任何部分
 *
 * @Company: 紫色年华
 * @Author: xieyc
 * @Datetime: 2020-01-08 17:01
 * @Version: 1.0.0
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class SysLogVO extends SysLog {

    /**
     * 登录账号
     */
    private String loginName;

    /**
     * 用户姓名
     */
    private String userName;
}

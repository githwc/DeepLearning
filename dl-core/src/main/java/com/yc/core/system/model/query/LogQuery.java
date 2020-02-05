package com.yc.core.system.model.query;

import com.yc.core.system.entity.SysLog;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 功能描述:日志查询入参
 *
 * <p>版权所有：</p>
 * 未经本人许可，不得以任何方式复制或使用本程序任何部分
 *
 * @Company: 紫色年华
 * @Author: xieyc
 * @Datetime: 2020-01-08 17:21
 * @Version: 1.0.0
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class LogQuery extends SysLog {

    /**
     * 登录账号
     */
    private String loginName;

    /**
     * 用户姓名
     */
    private String userName;

    /**
     * 开始时间
     */
    private String beginTime;

    /**
     * 结束时间
     */
    private String endTime;



}

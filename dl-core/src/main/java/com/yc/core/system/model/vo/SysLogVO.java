package com.yc.core.system.model.vo;

import com.yc.core.system.entity.SysLog;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 功能描述: 日志列表
 *

 *

 * @Author: xieyc && 紫色年华
 * @Date: 2020-01-08 17:01
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

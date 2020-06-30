package com.yc.core.system.model.query;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 功能描述:用户查询入参
 *
 * @Author: xieyc && 紫色年华
 * @Date: 2020-01-05
 * @Version: 1.0.0
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class UserQuery implements Serializable {

    /**
     * 用户名称
     */
    private String userName;

    /**
     * 部门ID
     */
    private String deptId;

}

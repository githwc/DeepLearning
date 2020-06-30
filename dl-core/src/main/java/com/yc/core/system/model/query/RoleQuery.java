package com.yc.core.system.model.query;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 功能描述:角色查询入参

 *

 * @Author: xieyc && 紫色年华
 * @Date: 2020-01-05
 * @Version: 1.0.0
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class RoleQuery implements Serializable {

    /**
     * 角色名称
     */
    private String roleName;
}

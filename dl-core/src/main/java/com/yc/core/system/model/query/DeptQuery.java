package com.yc.core.system.model.query;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 功能描述: 部门查询入参
 *

 *

 * @Author: xieyc && 紫色年华
 * @Date: 2020-01-04 09:34
 * @Version: 1.0.0
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class DeptQuery implements Serializable {

    /**
     * 父级ID
     */
    private String parentId;

    /**
     * 部门名称
     */
    private String departName;

}

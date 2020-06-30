package com.yc.core.cascade;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 功能描述:顶级级联 List
 *
 * @Author: xieyc && 紫色年华
 * @Date: 2020-04-10
 * @Version: 1.0.0
 */
@Data
public class CaseTopLevel implements Serializable {

    /**
     * 业务主键
     */
    private String id;

    /**
     * 显示
     */
    private String title;

    /**
     * 传输
     */
    private String key;

    /**
     * 子级
     */
    private List<CaseSecondLevel> children;

    /**
     * 是否叶子结点:  1:是   0:不是
     */
    private Boolean leaf;

}

package com.yc.core.cascade;

import lombok.Data;

import java.io.Serializable;

/**
 * 功能描述:
 *
 * @Author: xieyc && 紫色年华
 * @Date: 2020-04-10
 * @Version: 1.0.0
 */
@Data
public class CaseLastLevel implements Serializable {

    /**
     * 显示
     */
    private String title;

    /**
     * 传输
     */
    private String key;

    /**
     * 是否叶子结点: 1:是   0:不是
     */
    private Boolean leaf;

}

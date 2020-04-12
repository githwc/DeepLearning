package com.yc.core.cascadeList;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 功能描述：次级级联List
 * <p>版权所有：</p>
 * 未经本公司许可，不得以任何方式复制或使用本程序任何部分
 *
 * @Company: 紫色年华
 * @Author: xieyc
 * @Datetime: 2020-04-10
 * @Version: 1.0.0
 */
@Data
public class CaseSecondLevel implements Serializable {

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
    private List<CaseLastLevel> children;

    /**
     * 是否叶子结点: 1:是   0:不是
     */
    private Boolean leaf;
}

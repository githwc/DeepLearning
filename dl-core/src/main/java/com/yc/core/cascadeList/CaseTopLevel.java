package com.yc.core.cascadeList;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 功能描述：顶级级联 List
 * <p>版权所有：</p>
 * 未经本人许可，不得以任何方式复制或使用本程序任何部分
 *
 * @Company: 紫色年华
 * @Author: xieyc
 * @Datetime: 2020-04-10
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

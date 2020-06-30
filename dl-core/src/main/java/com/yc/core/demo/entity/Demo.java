package com.yc.core.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 功能描述:
 *
 * @Author:  xieyc && 紫色年华
 * @Date 2020-04-17
 * @Version: 1.0.0
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Demo implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * 主键
     */
    @TableId(value = "demo_id", type = IdType.ASSIGN_UUID)
    private String demoId;
    /**
     * 名称
     */
    private String name;
    /**
     * 年龄
     */
    private Integer age;
    /**
     * 文件名称
     */
    private String documentName;
    /**
     * 文件路径
     */
    private String documentUrl;
    /**
     * 地址
     */
    private String address;
    /**
     * 排序
     */
    private Integer sort;
    /**
     * 备注
     */
    private String remark;

}

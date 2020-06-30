package com.yc.core.selfoffice.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 功能描述:
 *

 * @Author:  xieyc && 紫色年华
 * @Date 2020-04-15
 * @Version: 1.0.0
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class WorkBench implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * 主键
     */
    @TableId(value = "work_bench_id", type = IdType.ASSIGN_UUID)
    private String workBenchId;
    /**
     * 标题
     */
    private String title;
    /**
     * 图标
     */
    private String avatar;
    /**
     * 描述
     */
    private String content;
    /**
     * 排序
     */
    private Integer sort;



}

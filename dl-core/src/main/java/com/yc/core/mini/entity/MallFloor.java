package com.yc.core.mini.entity;

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
 * @Date 2020-06-26
 * @Version: 1.0.0
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class MallFloor implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * 主键
     */
    @TableId(value = "mall_floor_id", type = IdType.ASSIGN_UUID)
    private String mallFloorId;
    /**
     * 父级ID
     */
    private String mallFloorPid;
    /**
     * 名称
     */
    private String name;
    /**
     * 级别(0:一级:1:二级)
     */
    private Integer level;
    /**
     * 图片路径
     */
    private String image;
    /**
     * 图片宽度
     */
    private Double imageWidth;
    /**
     * 打开方式
     */
    private String openType;
    /**
     * 导航链接
     */
    private String navigatorUrl;

    /**
     * 排序
     */
    private Integer sort;

}

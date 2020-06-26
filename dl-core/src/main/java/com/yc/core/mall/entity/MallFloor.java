package com.yc.core.mall.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 功能描述：
 *  <p>版权所有：</p>
 *  未经本人许可，不得以任何方式复制或使用本程序任何部分
 *
 * @Company: 紫色年华
 * @Author xieyc
 * @Date 2020-06-26
 * @Version: 1.0.0
 *
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class MallFloor implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * 主键
     */
    @TableId(value = "mall_floor_id", type = IdType.UUID)
    private String mallFloorId;
    /**
     * 父级ID
     */
    private String mallFloorPid;
    /**
     * 级别(0:一级:1:二级)
     */
    private Boolean level;
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
     * 创建人
     */
    private String createUserId;
    /**
     * 创建时间
     */
    private LocalDateTime createTime;
    /**
     * 修改人
     */
    private String updateUserId;
    /**
     * 修改时间
     */
    private LocalDateTime updateTime;



}

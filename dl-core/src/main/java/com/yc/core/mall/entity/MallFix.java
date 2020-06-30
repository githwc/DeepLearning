package com.yc.core.mall.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

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
public class MallFix implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * 主键
     */
    @TableId(value = "mall_fix_id", type = IdType.ASSIGN_UUID)
    private String mallFixId;
    /**
     * 导航名称
     */
    private String name;
    /**
     * 图片
     */
    private String image;
    /**
     * 打开方式
     */
    private String openType;
    /**
     * 导航链接
     */
    private String navigatorUrl;
    /**
     * 分类(0:轮播图 1:导航）
     */
    private Boolean type;
    /**
     * 关联商品
     */
    private String mallProductId;
    /**
     * 是否删除
     */
    private Boolean delFlag;
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

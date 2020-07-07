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

}

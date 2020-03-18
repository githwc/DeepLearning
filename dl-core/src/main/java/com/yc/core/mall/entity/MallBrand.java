package com.yc.core.mall.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 功能描述：
 *  <p>版权所有：</p>
 *  未经本人许可，不得以任何方式复制或使用本程序任何部分
 *
 * @Company: 紫色年华
 * @Author xieyc
 * @Date 2020-03-17
 * @Version: 1.0.0
 *
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class MallBrand implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "mall_brand_id", type = IdType.UUID)
    private String mallBrandId;
    /**
     * 名称
     */
    private String name;
    /**
     * 首字母
     */
    private String firstLetter;
    /**
     * 排序
     */
    private Integer sort;
    /**
     * 是否为品牌制造商：0->不是；1->是
     */
    private Integer factoryStatus;
    /**
     * 是否显示: 0->不是；1->是
     */
    private Integer showStatus;
    /**
     * 产品数量
     */
    private Integer productCount;
    /**
     * 产品评论数量
     */
    private Integer productCommentCount;
    /**
     * 品牌logo
     */
    private String logo;
    /**
     * 专区大图
     */
    private String bigPic;
    /**
     * 品牌故事
     */
    private String brandStory;

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

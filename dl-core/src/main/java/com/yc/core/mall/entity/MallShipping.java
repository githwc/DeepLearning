package com.yc.core.mall.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 功能描述：收货地址
 *  <p>版权所有：</p>
 *  未经本人许可，不得以任何方式复制或使用本程序任何部分
 *
 * @Company: 紫色年华
 * @Author xieyc
 * @Date 2020-05-08
 * @Version: 1.0.0
 *
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class MallShipping implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * 主键
     */
    @TableId(value = "mall_shipping_id", type = IdType.UUID)
    private String mallShippingId;
    /**
     * 用户id
     */
    private String sysUserId;
    /**
     * 收货姓名
     */
    private String receiverName;
    /**
     * 收货电话
     */
    private String receiverPhone;
    /**
     * 省份
     */
    private String receiverProvince;
    /**
     * 城市
     */
    private String receiverCity;
    /**
     * 区/县
     */
    private String receiverArea;
    /**
     * 行政编码
     */
    private String regionCode;
    /**
     * 详细地址
     */
    private String receiverAddress;
    /**
     * 删除状态
     */
    private Integer delFlag;
    /**
     * 创建时间
     */
    private LocalDateTime createTime;
    /**
     * 修改时间
     */
    private LocalDateTime updateTime;

    // ================= 非标字段 ===============

    /**
     * 省份编码
     */
    @TableField(exist = false)
    private String receiverProvinceCode;

    /**
     * 市区编码
     */
    @TableField(exist = false)
    private String receiverCityCode;

    /**
     * 区编码
     */
    @TableField(exist = false)
    private String receiverAreaCode;

}

package com.yc.core.community.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 功能描述:
 *
 * @Author:  xieyc && 紫色年华
 * @Date 2019-09-20
 * @Version: 1.0.0
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Community implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * 社区ID
     */
    @TableId(value = "community_id", type = IdType.ASSIGN_UUID)
    private String communityId;
    /**
     * 集团ID
     */
    private String groupId;
    /**
     * TreeCode
     */
    private String treeCode;
    /**
     * 小区名称
     */
    private String name;
    /**
     * 省
     */
    private String province;
    /**
     * 市
     */
    private String city;
    /**
     * 区
     */
    private String county;
    /**
     * 行政区划编码
     */
    private String regionCode;
    /**
     * 经度
     */
    private String lng;
    /**
     * 纬度
     */
    private String lat;
    /**
     * 详细地址
     */
    private String addr;
    /**
     * 建筑总面积
     */
    private String buildArea;
    /**
     * 楼栋数
     */
    private String buildNum;
    /**
     * 服务电话
     */
    private String serviceTel;
    /**
     * 计费方式(1周期性2一次性)
     */
    private String billingMode;
    /**
     * 有效期开始时间
     */
    private LocalDateTime effectiveStime;
    /**
     * 有效期结束时间
     */
    private LocalDateTime effectiveEtime;
    /**
     * 短信条数
     */
    private Integer smsNumber;
    /**
     * 是否初始化
     */
    private Boolean isInit;
    /**
     * 当前初始化步骤
     */
    private Integer initNo;
    /**
     * 状态(-1未开通-2软通初始化0未审核1正常使用2审核拒绝4停用)
     */
    private Integer status;
    /**
     * 警务平台开通状态(0未开通1已开通)
     */
    private Integer jwStatus;
    /**
     * 审核原因
     */
    private String checkCause;
    /**
     * 是否线上支付
     */
    private String isLinePay;
    /**
     * APPLOGO
     */
    private String appLogo;
    /**
     * APPBanner
     */
    private String appBanner;
    /**
     * 交房落款
     */
    private String jfLk;
    /**
     * 交房文案
     */
    private String jfCase;
    /**
     * 排序号
     */
    private BigDecimal seqNum;
    /**
     * 备注
     */
    private String remark;
    /**
     * 创建人
     */
    private String createUser;
    /**
     * 创建时间
     */
    private LocalDateTime createTime;
    /**
     * 更新人
     */
    private String updateUser;
    /**
     * 更新时间
     */
    private LocalDateTime updateTime;


    /////////////////////////////// 非表字段 ///////////////////////////////

}

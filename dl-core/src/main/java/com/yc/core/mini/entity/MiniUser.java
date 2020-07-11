package com.yc.core.mini.entity;

import java.math.BigDecimal;
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
 * @Author xieyc && 紫色年华
 * @Date 2020-07-09
 * @Version: 1.0.0
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class MiniUser implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * 用户ID
     */
    @TableId(value = "mini_user_id", type = IdType.ASSIGN_UUID)
    private String miniUserId;
    /**
     * 真实名称
     */
    private String name;
    /**
     * 联系电话
     */
    private String phone;
    /**
     * 昵称
     */
    private String nickName;
    /**
     * 微信OPENID
     */
    private String wxOpenId;
    /**
     * 会话秘钥
     */
    private String sessionKey;
    /**
     * 账号
     */
    private String loginName;
    /**
     * 密码
     */
    private String loginPwd;
    /**
     * 设备类型
     */
    private Integer deviceType;
    /**
     * 设备号
     */
    private String channelId;
    /**
     * 性别
     */
    private Boolean gender;
    /**
     * 头像
     */
    private String headerImg;
    /**
     * 证件类型
     */
    private String cardType;
    /**
     * 证件号码
     */
    private String cardCode;
    /**
     * 是否有效
     */
    private Boolean validFlag;
    /**
     * 最后登录时间
     */
    private LocalDateTime lastLoginTime;
    /**
     * 用户积分
     */
    private Integer pointNumber;
    /**
     * 余额
     */
    private BigDecimal balance;
    /**
     * 备注
     */
    private String remark;
    /**
     * 创建时间
     */
    private LocalDateTime createTime;
    /**
     * 更新时间
     */
    private LocalDateTime updateTime;



}

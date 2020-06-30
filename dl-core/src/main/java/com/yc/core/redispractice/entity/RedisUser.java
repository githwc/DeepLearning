package com.yc.core.redispractice.entity;

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
 * @Date 2020-01-19
 * @Version: 1.0.0
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class RedisUser implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * 主键
     */
    @TableId(value = "redis_user_id", type = IdType.ASSIGN_UUID)
    private String redisUserId;
    /**
     * 姓名
     */
    private String name;
    /**
     * 年龄
     */
    private Integer age;
    /**
     * 地址
     */
    private String address;
    /**
     * 性别
     */
    private String sex;
    /**
     * 排序
     */
    private Integer sort;
    /**
     * 删除状态(0:未删除 1:已删除)
     */
    private Integer delFlag;
    /**
     * 备注
     */
    private String remark;


}

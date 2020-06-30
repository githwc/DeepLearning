package com.yc.core.redispractice.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 功能描述:发布订阅
 *
 * @Author:  xieyc && 紫色年华
 * @Date 2020-01-29
 * @Version: 1.0.0
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class RedisPubSub implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * 主键
     */
    @TableId(value = "redis_pub_sub_id", type = IdType.ASSIGN_UUID)
    private String redisPubSubId;
    /**
     * 通道
     */
    private String channel;
    /**
     * 内容
     */
    private String content;
    /**
     * 创建人
     */
    private String createUserId;
    /**
     * 创建时间
     */
    private LocalDateTime createTime;

}

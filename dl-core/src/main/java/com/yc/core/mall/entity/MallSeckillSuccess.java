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
 * @Date 2020-06-01
 * @Version: 1.0.0
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class MallSeckillSuccess implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * 秒杀商品主键(联合主键)
     */
    @TableId(value = "mall_seckill_id", type = IdType.ASSIGN_UUID)
    private String mallSeckillId;
    /**
     * 用户ID(联合主键）
     */
    private String sysUserId;
    /**
     * 状态(-1：无效 0:成功 1:已付款)
     */
    private Integer state;
    private LocalDateTime createTime;



}

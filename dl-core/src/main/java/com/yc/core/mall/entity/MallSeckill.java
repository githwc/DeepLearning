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
 * @Date 2020-06-01
 * @Version: 1.0.0
 *
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class MallSeckill implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * 主键
     */
    @TableId(value = "mall_seckill_id", type = IdType.UUID)
    private String mallSeckillId;
    /**
     * 商品ID
     */
    private String mallGoodId;
    /**
     * 商品名称
     */
    private String mallGoodName;
    /**
     * 库存
     */
    private Long stock;
    /**
     * 秒杀开启时间
     */
    private LocalDateTime seckillStartTime;
    /**
     * 秒杀结束时间
     */
    private LocalDateTime seckillEndTime;
    /**
     * 创建人ID
     */
    private String createUserId;
    /**
     * 创建时间
     */
    private LocalDateTime createTime;
    /**
     * 修改时间
     */
    private LocalDateTime updateTime;
    /**
     * 修改人
     */
    private String updateUserId;



}

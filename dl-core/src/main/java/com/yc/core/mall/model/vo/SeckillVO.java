package com.yc.core.mall.model.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.yc.core.mall.entity.MallSeckill;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * 功能描述:
 *
 * @Author: xieyc && 紫色年华
 * @Date: 2020-06-05
 * @Version: 1.0.0
 */
@Data
public class SeckillVO extends MallSeckill implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 凭证
     */
    private String md5;

    /**
     * 系统当前时间
     */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private LocalDateTime localDateTime;

    /**
     * 状态(0:未开始 1:开始秒杀 2:已结束)
     */
    private String state;

}

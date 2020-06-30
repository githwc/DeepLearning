package com.yc.core.timer.entity;

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
 * @Date 2020-04-16
 * @Version: 1.0.0
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class TimerRecord implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * 主键
     */
    @TableId(value = "timer_record_id", type = IdType.ASSIGN_UUID)
    private String timerRecordId;
    /**
     * 任务名称
     */
    private String title;
    /**
     * 业务主键
     */
    private String rId;
    /**
     * 创建时间
     */
    private LocalDateTime createTime;

}

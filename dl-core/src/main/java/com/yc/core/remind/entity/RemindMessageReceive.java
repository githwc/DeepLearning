package com.yc.core.remind.entity;

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
 * @Company: LionHerding
 * @Author xieyc
 * @Date 2019-10-08
 * @Version: 1.0.0
 *
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class RemindMessageReceive implements Serializable {

    private static final long serialVersionUID = 1L;
    @TableId(value = "remind_message_receive_id", type = IdType.UUID)
    private String remindMessageReceiveId;
    /**
     * 用户ID
     */
    private String userId;
    /**
     * 阅读状态(0: 未读 1:已读)
     */
    private String readFlag;
    /**
     * 阅读时间
     */
    private LocalDateTime readTime;
    /**
     * 接收状态
     */
    private boolean receiveFlag;
    /**
     * 消息ID
     */
    private String messageId;

}

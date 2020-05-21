package com.yc.core.message.model.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 功能描述：我的通知公告
 * <p>版权所有：</p>
 * 未经本人许可，不得以任何方式复制或使用本程序任何部分
 *
 * @Company: 紫色年华
 * @Author: xieyc
 * @Datetime: 2020-05-21
 * @Version: 1.0.0
 */
@Data
public class NoticeVO {

    /**
     * 唯一标识
     */
    private String id;
    /**
     * 标题
     */
    private String title;
    /**
     * 内容
     */
    private String content;
    /**
     * 消息类型(0:通知公告 1: 系统消息)
     */
    private Integer type;
    /**
     * 消息级别(0:INFO 1.WARNING 2.ERROR)
     */
    private Integer level;
    /**
     * 发布时间
     */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private LocalDateTime sendTime;
    /**
     * 发布人
     */
    private String createUser;
    /**
     * 已读标识(0: 未读 1:已读)
     */
    private Integer readState;
    /**
     * 阅读时间
     */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private LocalDateTime readTime;
}

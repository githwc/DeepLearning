package com.yc.core.message.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.naming.Name;
import java.io.Serializable;
import java.sql.Clob;
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
public class Message implements Serializable {

    private static final long serialVersionUID = 1L;
    @TableId(value = "message_id", type = IdType.UUID)
    private String messageId;
    /**
     * 标题
     */
    private String title;
    /**
     * 内容
     */
    private String content;
    /**
     * 消息级别(0:INFO 1.WARNING 2.ERROR)
     */
    private Integer level;
    /**
     * 消息类型(0:通知公告 1: 系统消息)
     */
    private Integer type;
    /**
     * 接收类型(0:指定用户 1:全体用户)
     */
    private Integer receiveType;
    /**
     * 发布状态(0未发布 1.已发布 2.已撤销)
     */
    private Integer sendState;
    /**
     * 发布时间
     */
    private LocalDateTime sendTime;
    /**
     * 关联记录的标识
     */
    private String rid;
    /**
     * 发布人ID
     */
    private String createUserId;
    /**
     * 发布人
     */
    private String createUser;
    /**
     * 创建时间
     */
    private LocalDateTime createTime;


    /////////////////////////////// 非表字段 ///////////////////////////////

}

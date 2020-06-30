package com.yc.core.system.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 功能描述:
 *
 * @Author:  xieyc && 紫色年华
 * @Date 2019-09-21
 * @Version: 1.0.0
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class SysLog implements Serializable {

    private static final long serialVersionUID = 1L;
    @TableId(value = "sys_log_id", type = IdType.ASSIGN_UUID)
    private String sysLogId;
    /**
     * 操作类型(0:增 1：删  2：改 3：查)
     */
    private Integer opType;
    /**
     * 日志类型(0.操作日志 1.登录登出日志 2.定时任务）
     */
    private Integer logType;
    /**
     * 日志内容
     */
    private String logContent;
    /**
     * 请求方法
     */
    private String requestMethod;
    /**
     * 请求参数
     */
    private String requestParam;
    /**
     * 请求路径
     */
    private String requestUrl;
    /**
     * 请求类型
     */
    private String requestType;
    /**
     * IP地址
     */
    private String ipAddress;
    /**
     * 耗时
     */
    private Long costTime;
    /**
     * 备注
     */
    private String remark;
    /**
     * 创建人
     */
    private String createUserId;
    /**
     * 创建时间
     */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private LocalDateTime createTime;


    /////////////////////////////// 非表字段 ///////////////////////////////

}

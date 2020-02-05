package com.yc.core.system.entity;

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
 * @Date 2019-09-21
 * @Version: 1.0.0
 *
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class SysLog implements Serializable {

    private static final long serialVersionUID = 1L;
    @TableId(value = "sys_log_id", type = IdType.UUID)
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
    private LocalDateTime createTime;


    /////////////////////////////// 非表字段 ///////////////////////////////

}

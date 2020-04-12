package com.yc.practice.system.service;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yc.core.system.entity.SysLog;
import com.yc.core.system.model.query.LogQuery;
import com.yc.core.system.model.vo.SysLogVO;

import javax.servlet.http.HttpServletRequest;

/**
 * 功能描述：
 *
 *  <p>版权所有：</p>
 *  未经本人许可，不得以任何方式复制或使用本程序任何部分
 *
 * @Company: 紫色年华
 * @Author xieyc
 * @Date 2019-09-21
 * @Version: 1.0.0
 *
 */
public interface SysLogService extends IService<SysLog> {

    /**
     * 日志添加
     * @param request 请求信息
     * @param logContent 内容
     * @param logType  日志类型(0.操作日志 1.登录日志 2.定时任务）
     * @param requestMethod 请求方法
     * @param requestParams 请求参数
     */
    void addLog(HttpServletRequest request,String logContent, Integer logType, String loginName, String requestMethod,
                String requestParams);

    /**
     * 获取系统日志
     * @return
     */
    JSONObject logInfo();

    /**
     * 分页查询系统日志
     * @param page 分页信息
     * @param logQuery 查询条件
     * @return
     */
    Page<SysLogVO> logPage(Page<SysLogVO> page, LogQuery logQuery);

}

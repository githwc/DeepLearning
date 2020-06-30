package com.yc.practice.system.controller;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yc.core.system.model.query.LogQuery;
import com.yc.core.system.model.vo.SysLogVO;
import com.yc.practice.common.log.WriteLog;
import com.yc.practice.system.service.SysLogService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 *
 * 功能描述:日志前端控制器
 *
 * @Author:  xieyc && 紫色年华
 * @Date 2019-09-21
 * @Version: 1.0.0
 */
@RestController
@RequestMapping("/sysLog")
@Slf4j
@Api(tags = "系统日志")
public class SysLogController {

    private final SysLogService service;

    @Autowired
    public SysLogController(SysLogService service) {
        this.service = service;
    }

    @GetMapping("/logInfo")
    @ApiOperation(value = "首页获取系统访问数据",notes = "首页获取系统访问数据")
    @WriteLog(opPosition = "首页获取系统访问数据")
    public JSONObject logInfo(HttpServletRequest request){
        return service.logInfo(request);
    }

    @GetMapping("/logPage")
    @ApiOperation(value = "分页查询系统日志",notes = "分页查询系统日志")
    @WriteLog(opPosition = "查询系统日志")
    public Page<SysLogVO> logPage(Page<SysLogVO> page, LogQuery logQuery){
        return service.logPage(page,logQuery);
    }

}

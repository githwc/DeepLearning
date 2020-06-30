package com.yc.practice.system.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yc.common.utils.LocalHostUtil;
import com.yc.core.system.entity.SysLog;
import com.yc.core.system.mapper.SysLogMapper;
import com.yc.core.system.model.query.LogQuery;
import com.yc.core.system.model.vo.SysLogVO;
import com.yc.practice.common.UserUtil;
import com.yc.practice.system.service.SysLogService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
* 功能描述:
*
* @Author:  xieyc && 紫色年华
* @Date 2019-09-21
* @Version: 1.0.0
*/
@Service
@Transactional(rollbackFor = Exception.class)
public class SysLogServiceImpl extends ServiceImpl<SysLogMapper, SysLog> implements SysLogService {

    @Override
    public void addLog(HttpServletRequest request,String LogContent, Integer logType,String loginName,
                       String requestMethod,String requestParams) {
        SysLog sysLog = new SysLog();
        sysLog.setLogContent(LogContent);
        sysLog.setLogType(logType);
        sysLog.setRequestMethod(requestMethod);
        sysLog.setRequestParam(requestParams);
        try {
            sysLog.setIpAddress(LocalHostUtil.getIpAddress(request));
        } catch (Exception e) {
            sysLog.setIpAddress("异常地址");
        }
        sysLog.setCostTime(0L);
        sysLog.setRequestType(request.getMethod());
        sysLog.setRequestUrl(request.getRequestURI());
        sysLog.setCreateUserId(loginName);
        this.baseMapper.insert(sysLog);
    }

    @Override
    public JSONObject logInfo(HttpServletRequest request) {
        JSONObject jsonObject = new JSONObject();
        Map<String,Object> map = new HashMap<String, Object>();
        SysLog syslog = this.baseMapper.selectOne(new LambdaQueryWrapper<SysLog>()
            .like(SysLog::getLogContent,UserUtil.getUser().getLoginName())
                .orderByDesc(SysLog::getCreateTime)
                .last("limit 1")
        );
        map.put("lastLoginTime", syslog.getCreateTime());
        int visitCount = this.baseMapper.selectCount(new LambdaQueryWrapper<SysLog>()
            .eq(SysLog::getCreateUserId, UserUtil.getUserId())
        );
        map.put("visitCount", visitCount);
        map.put("ipAddress", LocalHostUtil.getIpAddress(request));
        jsonObject.put("logInfo",map);
        return jsonObject;
    }

    @Override
    public Page<SysLogVO> logPage(Page<SysLogVO> page, LogQuery logQuery) {
        return this.baseMapper.logPage(page,logQuery);
    }


}

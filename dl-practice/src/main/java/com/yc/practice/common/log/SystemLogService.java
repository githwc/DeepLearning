package com.yc.practice.common.log;

import com.alibaba.fastjson.JSONObject;
import com.yc.common.utils.LocalHostUtil;
import com.yc.core.system.entity.SysLog;
import com.yc.core.system.entity.SysUser;
import com.yc.core.system.mapper.SysLogMapper;
import com.yc.practice.common.UserUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

/**
 *
 * 功能描述: 架构日志服务类
 *
 * <p>版权所有：</p>
 * 未经本人许可，不得以任何方式复制或使用本程序任何部分
 *
 * @Company  紫色年华
 * @Author   xieyc
 * @Datetime 2019-05-31 10:58
 */
@Service
@Slf4j
public class SystemLogService {

    private final SysLogMapper sysLogMapper;

    @Autowired
    public SystemLogService(SysLogMapper sysLogMapper) {
        this.sysLogMapper = sysLogMapper;
    }

    boolean write(HttpServletRequest request,String opPosition,int opType,int logType,String requestMethod,
                  long costTimeMillis,int opResult) {
        String message = new String[] { "创建", "删除", "更新", "读取" }[opType] + "位置【" + opPosition + "】" + (opResult != 1
                ? "成功" : "失败");
        String requestParams = JSONObject.toJSONString(request.getParameterMap());
        return write(UserUtil.getUser(),opType,logType,requestMethod,request.getRequestURI(),
                request.getMethod(),requestParams,costTimeMillis,message);
    }

    private boolean write(SysUser sysUser,int opType,int logType,
                          String requestMethod,String requestUrl,String requestType,String requestParams,
                          long costTimeMillis,String... describe) {
        sysUser = sysUser !=null ? sysUser : new SysUser();
        SysLog log = new SysLog();
        log.setRequestMethod(requestMethod);
        log.setRequestUrl(requestUrl);
        log.setRequestType(requestType);
        log.setRequestParam(requestParams.trim());
        log.setCreateTime(LocalDateTime.now());
        log.setCreateUserId(sysUser.getSysUserId());
        log.setIpAddress(LocalHostUtil.getIpAddress());
        log.setOpType(opType);
        log.setCostTime(costTimeMillis);
        log.setLogType(logType);
        String tmpDesc = "";
        for(int i = 0; i<describe.length; i++){
            tmpDesc += describe[i] + (i<describe.length-1?"\r\n":"");
        }
        log.setLogContent(tmpDesc);
        int result = sysLogMapper.insert(log);
        return result >0 ? true : false;
    }
}

package com.yc.common.log;

import com.alibaba.fastjson.JSONObject;
import com.yc.common.dao.DaoApi;
import com.yc.common.utils.LocalHostUtil;
import com.yc.core.system.entity.SysLog;
import com.yc.core.system.entity.SysUser;
import com.yc.core.system.mapper.SysLogMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

/**
 *
 * 功能描述： 架构日志服务类
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
    private DaoApi daoApi;

    @Autowired
    public SystemLogService(SysLogMapper sysLogMapper,DaoApi daoApi) {
        this.sysLogMapper = sysLogMapper;
        this.daoApi = daoApi;
    }

    /**
     * @Description:方法重写：仅仅记录用户系统操作日志，不进行系统文件日志写入
     * @Date: 2019/5/31 11:15
     * @param request	: HttpServletRequest
     * @param opPosition	: 操作位置
     * @param opType	: 操作类型
     * @param opResult	: 操作执行情况
     * @return 是否成功
     */
    boolean write(HttpServletRequest request,String opPosition,int opType,int logType,String requestMethod,
                  long costTimeMillis,int opResult) {
        String message = new String[] { "创建", "删除", "更新", "读取" }[opType] + "位置(" + opPosition + ")" + (opResult != 1 ? "成功" : "失败");
        String requestParams = JSONObject.toJSONString(request.getParameterMap());
        return write(daoApi.getCurrentUser(),opType,logType, requestMethod,request.getRequestURI(),
                request.getMethod(),requestParams,costTimeMillis,message);
    }

    /**
     * @Description:方法重写：仅仅记录用户系统操作日志，不进行系统文件日志写入
     * @Date: 2019/5/31 11:15
     * @param request	: HttpServletRequest
     * @param opPosition	: 操作位置
     * @param opType	: 操作类型
     * @param opResult	: 操作执行情况
     * @return 是否成功
     */
    boolean write(HttpServletRequest request,String opPosition,int opType,int logType,String requestMethod,
                  int opResult) {
        String message = new String[] { "创建", "删除", "更新", "读取" }[opType] + "位置(" + opPosition + ")" + (opResult != 1 ? "成功" : "失败");
        String requestParams = JSONObject.toJSONString(request.getParameterMap());
        return write(daoApi.getCurrentUser(),opType,logType,requestMethod,request.getRequestURI(),request.getMethod()
                ,requestParams,0,message);
    }

    /**
     * @Description:写系统日志(原始方法)
     * @Date: 2019/5/31 11:14
     * @param sysUser	    : HttpSession中的User
     * @param opType	: 操作类型
     * @param describe	: 描述(可选)
     *      String...:String类型的可变长度的数组,固定长度的数组是String[] str={};这样写,可变的就String... str.
     * @return 是否成功
     */
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

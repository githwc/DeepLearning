package com.yc.practice.common.log;

import com.yc.common.constant.CommonConstant;
import com.yc.common.global.error.ErrorException;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 *
 * 功能描述: 系统日志切面
 *
 * @Company  紫色年华
 * @Author   xieyc
 * @Datetime 2019-05-31 10:58
 */
@Aspect
@Component
public class LogAspect {

	@Autowired
	private SystemLogService service;

    /**
     * 环绕通知
     *      ("within(com.yc..*) && @annotation(log)")[清楚记录操作痕迹,需添加@writeLog()注解]
     *      ("within(com.yc.*.controller..*)")[只记录方法名称,无需修改原代码]
     *
     * @param jp 连接点
     * @param log 注解
     * @return 对象
     */
	@Around("within(com.yc..*) && @annotation(log)")
    public Object around (ProceedingJoinPoint jp,WriteLog log){
        long startTimeMillis = System.currentTimeMillis();
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        try{
            Object result = jp.proceed();
            long costTimeMillis = System.currentTimeMillis() - startTimeMillis;
            service.write(request,log.opPosition(),log.optype(),log.logType(),
                    jp.getSignature().getDeclaringTypeName()+"."+jp.getSignature().getName(),
                    costTimeMillis,CommonConstant.OPSTATE_SUCCESS);
            return result;
        }catch (Throwable throwable) {
            long costTimeMillis = System.currentTimeMillis() - startTimeMillis;
            service.write(request,log.opPosition(),log.optype(),log.logType(),
                    jp.getSignature().getDeclaringTypeName()+"."+jp.getSignature().getName(),
                    costTimeMillis,CommonConstant.OPSTATE_FAILURE);
            ErrorException error = (ErrorException)throwable;
            throw new ErrorException(error.getHttpStatusCode(),error.getCode(),error.getMsg());
        }
    }


}

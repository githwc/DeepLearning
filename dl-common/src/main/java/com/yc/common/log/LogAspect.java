package com.yc.common.log;

import com.yc.common.constant.CommonConstant;
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
 * 功能描述： 系统日志切面
 *
 * <p>版权所有：</p>
 * 未经本人许可，不得以任何方式复制或使用本程序任何部分
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

	@Around("within(com.yc..*) && @annotation(log)")
    public Object around (ProceedingJoinPoint jp,WriteLog log){
        long startTimeMillis = System.currentTimeMillis();
        try{
            Object result = jp.proceed();
            long costTimeMillis = System.currentTimeMillis() - startTimeMillis;
            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
            service.write(request,log.opPosition(),log.optype(),log.logType(),
                    jp.getSignature().getDeclaringTypeName()+"."+jp.getSignature().getName(),
                    costTimeMillis,CommonConstant.OPSTATE_SUCCESS);
            return result;
        }catch (Throwable throwable) {
            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
            service.write(request,log.opPosition(),log.optype(),log.logType(),
                    jp.getSignature().getDeclaringTypeName()+"."+jp.getSignature().getName(),
                    CommonConstant.OPSTATE_FAILURE);
            throwable.printStackTrace();
            throw new RuntimeException(throwable.getMessage());
        }
    }


}

package com.yc.common.log;

import com.yc.common.constant.CommonConstant;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
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

    /**
     * @Description:标注为后置通知，当目标方法执行成功后执行该函数
     * @Date: 2019/5/31 11:04
     * @Param: within：扫描目标方法   annotation：标记为log的方法
     * @Return:
     */
	@AfterReturning("within(com.yc..*) && @annotation(log)")
	public void addLogSuccess(JoinPoint jp, WriteLog log) {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		service.write(request, log.opPosition(), log.optype(),log.logType(), jp.getSignature().getDeclaringTypeName(), CommonConstant.OPSTATE_SUCCESS);
	}

    /**
     * @Description:标注为异常通知，当目标方法出现异常，执行该方法体
     * @Date: 2019/5/31 11:07
     * @Param:  within：扫描目标方法   annotation：标记为log的方法
     * @Return:
     */
	@AfterThrowing(pointcut="within(com.yc..*) && @annotation(log)", throwing="ex")
	public void addLogFail(JoinPoint jp, WriteLog log, Exception ex) {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		service.write(request, log.opPosition(), log.optype(), log.logType(),jp.getSignature().getDeclaringTypeName(), CommonConstant.OPSTATE_FAILURE, ex);
	}

}

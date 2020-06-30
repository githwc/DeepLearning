package com.yc.practice.common.log;

import com.yc.common.constant.CommonConstant;

import java.lang.annotation.*;

/**
 *
 * 功能描述: 写日志注解接口定义
 *          @Target(ElementType.METHOD):用于限定注解使用范围，method:用于方法上
 *          @Retention(RetentionPolicy.RUNTIME):指定注解不仅保存在class文件中，JVM加载class文件之后，仍然存在
 *          @Documented:表明使用这个注解会被javadoc记录，注解类型信息会被记录在生成的文档中
 *          @Inherited：该注解会被子类继承
 *
 *
 * @Company  紫色年华
 * @Author   xieyc
 * @Datetime 2019-05-31 10:58
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface WriteLog {

    /**
     * 操作位置
     * @return 所处位置
     */
	String opPosition();

    /**
     * 操作类型(0:增 1：删  2：改 3：查)
     * @return 操作类型
     */
	int optype() default CommonConstant.OPTYPE_READ;

    /**
     * 日志类型
     *     0:操作日志;1:登录日志;2:定时任务;
     *
     * @return 日志类型
     */
    int logType() default CommonConstant.LOG_TYPE_0;

}

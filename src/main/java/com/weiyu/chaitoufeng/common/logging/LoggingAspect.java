package com.weiyu.chaitoufeng.common.logging;

import com.weiyu.chaitoufeng.common.tools.SequenceUtil;
import com.weiyu.chaitoufeng.domain.system.SysLog;
import com.weiyu.chaitoufeng.service.ISysLogService;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.lang.reflect.Method;

/**
 * Description: 日 志 切 面 实 现
 * Since: 2022-03-26 13:00
 * Author: wish_dq
 */
@Aspect
@Component
public class LoggingAspect {

    @Resource
    private ISysLogService logService;

    /**
     * 切 面 编 程
     */
    @Pointcut("@annotation(com.weiyu.chaitoufeng.common.logging.Logging) || @within(com.weiyu.chaitoufeng.common.logging.Logging)")
    public void dsPointCut() {
    }

    /**
     * 处 理 系 统 日 志
     */
    @Around("dsPointCut()")
    private Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        SysLog sysLog = new SysLog();
        Object result;
        try {
            Logging loggingAnnotation = getLogging(joinPoint);
            sysLog.setId(SequenceUtil.makeStringId());
            sysLog.setTitle(loggingAnnotation.value());
            sysLog.setTitle(loggingAnnotation.title());
            sysLog.setDescription(loggingAnnotation.describe());
            sysLog.setBusinessType(loggingAnnotation.type());
            sysLog.setSuccess(true);
            sysLog.setLoggingType(LoggingType.OPERATE);
            result = joinPoint.proceed();
        } catch (Exception exception) {
            sysLog.setSuccess(false);
            sysLog.setErrorMsg(exception.getMessage());
            throw exception;
        } finally {
            logService.aopSaveLog(sysLog);
        }
        return result;
    }

    /**
     * 获 取 注 解
     */
    public com.weiyu.chaitoufeng.common.logging.Logging getLogging(ProceedingJoinPoint point) {
        MethodSignature signature = (MethodSignature) point.getSignature();
        Class<? extends Object> targetClass = point.getTarget().getClass();
        com.weiyu.chaitoufeng.common.logging.Logging targetLogging = targetClass.getAnnotation(com.weiyu.chaitoufeng.common.logging.Logging.class);
        if (targetLogging != null) {
            return targetLogging;
        } else {
            Method method = signature.getMethod();
            com.weiyu.chaitoufeng.common.logging.Logging logging = method.getAnnotation(com.weiyu.chaitoufeng.common.logging.Logging.class);
            return logging;
        }
    }
}


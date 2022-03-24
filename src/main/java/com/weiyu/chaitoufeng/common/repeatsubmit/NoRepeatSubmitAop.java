package com.weiyu.chaitoufeng.common.repeatsubmit;

import org.apache.ibatis.logging.Log;
import org.apache.ibatis.logging.LogFactory;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Description:
 * Since: 2022-03-20 10:57
 * Author: wish_dq
 */

//@Aspect
//@Component
//public class NoRepeatSubmitAop {
//
//    private Log logger = LogFactory.getLog(getClass());
//
//    @Resource
//    private RedisTemplate<String, Serializable> template;
//
//    @Around("execution(* com.weiyu..*Controller.*(..)) && @annotation(nrs)")
//    public Object arround(ProceedingJoinPoint pjp, NoRepeatSubmit nrs) {
//        ValueOperations<String, Serializable> opsForValue = template.opsForValue();
//        try {
//            ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
//            String sessionId = RequestContextHolder.getRequestAttributes().getSessionId();
//            HttpServletRequest request = attributes.getRequest();
//            String key = sessionId + "-" + request.getServletPath();
//            if (opsForValue.get(key) == null) {// 如果缓存中有这个url视为重复提交
//                Object o = pjp.proceed();
//                opsForValue.set(key, 0, 2, TimeUnit.SECONDS);
//                return o;
//            } else {
//                logger.error("重复提交");
//                return null;
//            }
//        } catch (Throwable e) {
//            e.printStackTrace();
//            logger.error("验证重复提交时出现未知异常!");
//            return "{\"code\":-889,\"message\":\"验证重复提交时出现未知异常!\"}";
//        }
//
//    }
//
//}
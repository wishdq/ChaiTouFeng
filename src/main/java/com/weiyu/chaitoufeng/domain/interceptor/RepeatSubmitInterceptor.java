package com.weiyu.chaitoufeng.domain.interceptor;

import com.alibaba.fastjson.JSON;
import com.weiyu.chaitoufeng.common.repeatsubmit.NoRepeatSubmit;
import com.weiyu.chaitoufeng.common.result.Result;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * Description: 防止重复提交拦截器
 * Since: 2022-03-19 23:48
 * Author: wish_dq
 */
@Component
public abstract class RepeatSubmitInterceptor implements HandlerInterceptor {

    /**
     * 前置拦截,进入处理活力前判断当前提交的内容是否重复
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            Method method = handlerMethod.getMethod();
            NoRepeatSubmit annotation = method.getAnnotation(NoRepeatSubmit.class);
            if (annotation != null) {
                if (this.isRepeatSubmit(request)) {
                    Result result = new Result();
                    result.setSuccess(false);
                    result.setMsg("不允许重复提交，请稍后再试");
                    result.setCode(500);
                    response.setHeader("Content-type", "application/json;charset=UTF-8");
                    response.setCharacterEncoding("UTF-8");
                    response.getWriter().write(JSON.toJSONString(result));
                    return false;
                }
            }
            return true;
        } else {
            return false;
            //Result result = Result.failure(404,"未知错误");
            //return response.getWriter().write(JSONObject.toJSONString(result));
            //return preHandle(request, response, handler);
        }
    }

    /**
     * 验证是否重复提交由子类实现具体的防重复提交的规则
     *
     * @param request
     * @return
     *
     * @throws Exception
     */
    public abstract boolean isRepeatSubmit(HttpServletRequest request);
}


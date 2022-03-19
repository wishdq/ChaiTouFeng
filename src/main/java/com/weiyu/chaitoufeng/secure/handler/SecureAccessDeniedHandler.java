package com.weiyu.chaitoufeng.secure.handler;

import com.alibaba.fastjson.JSON;
import com.weiyu.chaitoufeng.common.result.Result;
import com.weiyu.chaitoufeng.common.tools.ServletUtil;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Description: 自定义 Security 用户暂无权限处理类
 * Since: 2022-03-14 17:49
 * Author: wish_dq
 */
@Component
public class SecureAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AccessDeniedException e) throws IOException, ServletException {
        if (ServletUtil.isAjax(httpServletRequest)) {
            Result result = Result.failure(403, "暂无权限");
            ServletUtil.write(JSON.toJSONString(result));
        } else {
            httpServletResponse.sendRedirect("/error/403");
        }
    }
}

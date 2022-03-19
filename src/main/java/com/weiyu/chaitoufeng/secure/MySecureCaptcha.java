package com.weiyu.chaitoufeng.secure;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.util.StringUtil;
import com.weiyu.chaitoufeng.common.result.Result;
import com.weiyu.chaitoufeng.common.tools.ServletUtil;
import com.wf.captcha.utils.CaptchaUtil;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Description: 登 录 验 证 码 过 滤 器
 * Since: 2022-03-13 16:14
 * Author: wish_dq
 */
@Component
public class MySecureCaptcha extends OncePerRequestFilter implements Filter {

    private String defaultFilterProcessUrl = "/login";
    private String method = "POST";

    /**
     * 验 证 码    验 证
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
        if (method.equalsIgnoreCase(request.getMethod()) && defaultFilterProcessUrl.equals(request.getServletPath())) {
            String captcha = ServletUtil.getRequest().getParameter("captcha");
            response.setContentType("application/json;charset=UTF-8");
            if (StringUtil.isEmpty(captcha)) {
                response.getWriter().write(JSON.toJSONString(Result.failure("验证码不能为空!")));
                return;
            }
            if (!CaptchaUtil.ver(ServletUtil.getRequest().getParameter("captcha"), ServletUtil.getRequest())) {
                response.getWriter().write(JSON.toJSONString(Result.failure("验证码错误!")));
                return;
            }
        }
        chain.doFilter(request, response);
    }
}
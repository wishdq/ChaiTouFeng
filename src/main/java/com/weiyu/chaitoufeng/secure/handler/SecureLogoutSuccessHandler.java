package com.weiyu.chaitoufeng.secure.handler;

import com.alibaba.fastjson.JSON;
import com.weiyu.chaitoufeng.common.result.Result;
import com.weiyu.chaitoufeng.common.tools.ServletUtil;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Description: 自定义 Security ===>  注销成功处理类
 * Since: 2022-03-14 16:51
 * Author: wish_dq
 */
@Component
public class SecureLogoutSuccessHandler implements LogoutSuccessHandler {

    @Override
    public void onLogoutSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        SecurityContextHolder.clearContext();
        Result result = Result.success(200, "注销成功");
        ServletUtil.write(JSON.toJSONString(result));
    }
}
package com.weiyu.chaitoufeng.secure.handler;

import com.alibaba.fastjson.JSON;
import com.weiyu.chaitoufeng.common.exception.MyCaptchaException;
import com.weiyu.chaitoufeng.common.logging.BusinessType;
import com.weiyu.chaitoufeng.common.logging.LoggingType;
import com.weiyu.chaitoufeng.common.result.Result;
import com.weiyu.chaitoufeng.common.tools.SequenceUtil;
import com.weiyu.chaitoufeng.common.tools.ServletUtil;
import com.weiyu.chaitoufeng.domain.system.SysLog;
import com.weiyu.chaitoufeng.service.system.ISysLogService;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Description: 自定义 Security 用户 ==> 登录验证失败处理类
 * Since: 2022-03-13 17:23
 * Author: wish_dq
 */
@Component
public class SecureAuthenticationFailureHandler implements AuthenticationFailureHandler {

    /**
     * 引 入 日 志 服 务
     */
    @Resource
    private ISysLogService sysLogService;

    @Override
    public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException {

        Result result = Result.failure(500, "登录失败");

        if (e instanceof MyCaptchaException) {
            result.setMsg("验证码有误");
        }
        if (e instanceof UsernameNotFoundException) {
            result.setMsg("用户名不存在");
        }
        if (e instanceof LockedException) {
            result.setMsg("用户冻结");
        }
        if (e instanceof BadCredentialsException) {
            result.setMsg("账户密码不正确");
        }
        if (e instanceof DisabledException) {
            result.setMsg("用户未启用");
        }

        SysLog sysLog = new SysLog();
        sysLog.setId(SequenceUtil.makeStringId());
        sysLog.setTitle("登录");
        sysLog.setDescription(result.getMsg());
        sysLog.setBusinessType(BusinessType.OTHER);
        sysLog.setSuccess(false);
        sysLog.setLoggingType(LoggingType.LOGIN);
        sysLogService.save(sysLog);

        ServletUtil.write(JSON.toJSONString(result));
    }
}

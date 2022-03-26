package com.weiyu.chaitoufeng.secure.handler;

import com.weiyu.chaitoufeng.common.logging.BusinessType;
import com.weiyu.chaitoufeng.common.logging.LoggingType;
import com.weiyu.chaitoufeng.common.tools.SequenceUtil;
import com.weiyu.chaitoufeng.domain.system.SysLog;
import com.weiyu.chaitoufeng.secure.session.SecureSessionService;
import com.weiyu.chaitoufeng.common.tools.SecurityUtil;
import com.weiyu.chaitoufeng.domain.system.SysUser;
import com.weiyu.chaitoufeng.service.ISysLogService;
import com.weiyu.chaitoufeng.service.ISysUserService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;

/**
 * Description: 注册 记住我 --> Remember  组件
 * Since: 2022-03-14 18:05
 * Author: wish_dq
 */
@Component
public class SecureRememberMeHandler implements AuthenticationSuccessHandler {

    @Resource
    private ISysLogService sysLogService;

    @Resource
    private ISysUserService sysUserService;

    @Resource
    private SessionRegistry sessionRegistry;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {

        // 记录日志
        SysLog sysLog = new SysLog();
        sysLog.setId(SequenceUtil.makeStringId());
        sysLog.setTitle("Remember Me");
        sysLog.setDescription("登录成功");
        sysLog.setBusinessType(BusinessType.OTHER);
        sysLog.setSuccess(true);
        sysLog.setLoggingType(LoggingType.LOGIN);
        sysLogService.save(sysLog);

        LocalDateTime now = LocalDateTime.now();
        // 根据id  更新用户 最近登录时间
        SysUser sysUser = new SysUser();
        sysUser.setUserId(((SysUser) SecurityUtil.currentUser()).getUserId());
        sysUser.setLastTime(now);
        sysUserService.update(sysUser);

        SysUser currentUser = (SysUser) authentication.getPrincipal();
        currentUser.setLastTime(now);
        // 保存当前 session
        request.getSession().setAttribute("currentUser", currentUser);
        // 根据当前req 清除过期 sessionRegistry
        SecureSessionService.expiredSession(request, sessionRegistry);

        // 注册新的SessionInformation
        sessionRegistry.registerNewSession(request.getSession().getId(), authentication.getPrincipal());
    }
}

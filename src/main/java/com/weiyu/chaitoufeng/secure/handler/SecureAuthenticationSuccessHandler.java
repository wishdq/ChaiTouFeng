package com.weiyu.chaitoufeng.secure.handler;

import com.alibaba.fastjson.JSON;
import com.weiyu.chaitoufeng.common.logging.BusinessType;
import com.weiyu.chaitoufeng.common.logging.LoggingType;
import com.weiyu.chaitoufeng.common.result.Result;
import com.weiyu.chaitoufeng.common.tools.SecurityUtil;
import com.weiyu.chaitoufeng.common.tools.SequenceUtil;
import com.weiyu.chaitoufeng.common.tools.ServletUtil;
import com.weiyu.chaitoufeng.domain.system.SysLog;
import com.weiyu.chaitoufeng.domain.system.SysUser;
import com.weiyu.chaitoufeng.service.ISysLogService;
import com.weiyu.chaitoufeng.service.ISysUserService;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;

/**
 * Description: 自定义 Security 用户： 登陆认证成功处理类
 * Since: 2022-03-13 17:15
 * Author: wish_dq
 */
@Component
public class SecureAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Resource
    private ISysLogService sysLogService;

    @Resource
    private ISysUserService sysUserService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {

        SysLog sysLog = new SysLog();
        sysLog.setId(SequenceUtil.makeStringId());
        sysLog.setTitle("登录");
        sysLog.setDescription("登录成功");
        sysLog.setBusinessType(BusinessType.OTHER);
        sysLog.setSuccess(true);
        sysLog.setLoggingType(LoggingType.LOGIN);
        sysLogService.save(sysLog);

        // 通过id更新登录用户信息登录时间
        SysUser sysUser = new SysUser();
        sysUser.setUserId(((SysUser) SecurityUtil.currentUser()).getUserId());
        sysUser.setLastTime(LocalDateTime.now());
        //sysUser.setLogin("1");
        sysUserService.update(sysUser);

        // 保存本地当前用户的session。并响应消息
        SysUser currentUser = (SysUser) authentication.getPrincipal();
        currentUser.setLastTime(LocalDateTime.now());
        request.getSession().setAttribute("currentUser", authentication.getPrincipal());
        Result<Object> result = Result.success("登录成功");
        ServletUtil.write(JSON.toJSONString(result));
    }
}

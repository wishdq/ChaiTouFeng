package com.weiyu.chaitoufeng.secure.session;

import cn.hutool.core.collection.CollectionUtil;
import com.weiyu.chaitoufeng.common.session.HttpSessionContextHolder;
import com.weiyu.chaitoufeng.domain.system.SysUser;
import org.springframework.security.core.session.SessionInformation;
import org.springframework.security.core.session.SessionRegistry;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Description: Security Session 操作服务
 * Since: 2022-03-13 23:22
 * Author: wish_dq
 */
public class SecureSessionService {

    public static void expiredSession(HttpServletRequest request, SessionRegistry sessionRegistry) {
        SysUser currentUser = (SysUser) request.getSession().getAttribute("currentUser");
        String sessionId = request.getSession().getId();
        // 从sessionRegistry中 获取到的已经登录的唯一用户信息集合
        List<Object> principals = sessionRegistry.getAllPrincipals();
        for (Object principal : principals) {
            SysUser userDetails = (SysUser) principal;
            // 若sessionRegistry中的userDetails的用户名与当前用户的用户名相同
            if (userDetails != null && userDetails.getUsername().equals(currentUser.getUsername())) {
                // 获取指定主体的所有已知会话 SessionInformation，包括过期session
                List<SessionInformation> sessionInformationList = sessionRegistry.getAllSessions(userDetails, true);
                if (CollectionUtil.isNotEmpty(sessionInformationList)) {
                    for (SessionInformation sessionInformation : sessionInformationList) {
                        // 不处理当前用户的session
                        if (sessionId.equals(sessionInformation.getSessionId())) {
                            continue;
                        }
                        // 从sessionRegistry中清除session信息
                        sessionInformation.expireNow();
                        sessionRegistry.removeSessionInformation(sessionInformation.getSessionId());
                        // 从HttpSessionContext中清除session信息
                        HttpSession httpSession = HttpSessionContextHolder.currentSessionContext().getSession(sessionInformation.getSessionId());
                        HttpSessionContextHolder.currentSessionContext().removeSession(httpSession);
                    }
                }
            }
        }
    }
}
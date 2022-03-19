package com.weiyu.chaitoufeng.secure.handler;

import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.session.SessionInformationExpiredEvent;
import org.springframework.security.web.session.SessionInformationExpiredStrategy;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * Description: 自定义 Security 同账号多端登录挤下线 跳转地址
 * Since: 2022-03-14 20:13
 * Author: wish_dq
 */
@Component
public class SecureSessionExpiredHandler implements SessionInformationExpiredStrategy {

    private final RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    @Override
    public void onExpiredSessionDetected(SessionInformationExpiredEvent event) throws IOException {
        redirectStrategy.sendRedirect(event.getRequest(), event.getResponse(), "/login?abnormalout=1");
    }
}

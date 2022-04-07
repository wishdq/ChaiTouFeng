package com.weiyu.chaitoufeng.secure.session;

import com.weiyu.chaitoufeng.common.session.HttpSessionContext;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * Description: 保存session
 * Since: 2022-04-07 18:17
 * Author: wish_dq
 */
@WebListener
public class SessionListener implements HttpSessionListener {

    private HttpSessionContext sessionContext = HttpSessionContext.getInstance();

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        sessionContext.addSession(se.getSession());
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        sessionContext.removeSession(se.getSession());
    }

}
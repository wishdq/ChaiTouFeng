package com.weiyu.chaitoufeng.common.session;

import javax.servlet.http.HttpSession;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Description: 单例模式 HttpSessionContext
 * Since: 2022-03-13 9:43
 * Author: wish_dq
 */
public class HttpSessionContext {
    // volatile:存储在主内存中
    private static volatile HttpSessionContext sessionContext;

    private Map<String, HttpSession> map;

    private HttpSessionContext() {
        this.map = new ConcurrentHashMap<>();
    }

    public static HttpSessionContext getInstance() {
        if (sessionContext == null) {
            synchronized (HttpSessionContext.class) {
                if (sessionContext == null) {
                    sessionContext = new HttpSessionContext();
                }
            }
        }
        return sessionContext;
    }

    public void addSession(HttpSession session) {
        if (session != null) {
            map.put(session.getId(), session);
        }
    }

    public void removeSession(HttpSession session) {
        if (session != null) {
            map.remove(session.getId());
        }
    }

    public HttpSession getSession(String sessionId) {
        if (sessionId != null && sessionId.length() == 0) {
            return null;
        } else {
            return map.get(sessionId);
        }
    }

}


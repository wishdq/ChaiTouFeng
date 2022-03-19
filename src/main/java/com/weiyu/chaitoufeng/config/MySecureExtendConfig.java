package com.weiyu.chaitoufeng.config;

import com.weiyu.chaitoufeng.secure.handler.SecureLogoutHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.session.HttpSessionEventPublisher;

import java.util.concurrent.ScheduledThreadPoolExecutor;

/**
 * Description: Security 扩展配置类
 * Since: 2022-03-13 23:18
 * Author: wish_dq
 */
@Configuration
public class MySecureExtendConfig {

    /**
     * 注册：SessionRegistry  即session注册表
     */
    @Bean
    public SessionRegistry sessionRegistry() {
        return new SessionRegistryImpl();
    }

    /**
     * 注册：加密方式
     */
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    /**
     * 注册：HttpSessionEventPublisher，发布HttpSessionEvent
     */
    @Bean
    public HttpSessionEventPublisher httpSessionEventPublisher() {
        return new HttpSessionEventPublisher();
    }
    /**
     * 注册：自定义的LogoutHandler
     */
    @Bean
    public SecureLogoutHandler securityLogoutHandler(HttpSessionEventPublisher httpSessionEventPublisher) {
        return new SecureLogoutHandler(httpSessionEventPublisher);
    }

    /**
     * 注册:ScheduledThreadPoolExecutor，进行在线用户用户检测，清除过期Session
     *      主要用来在给定的延迟之后执行任务，或者定期执行任务。
     */
    @Bean
    public ScheduledThreadPoolExecutor manageSessionThreadPool() {
        return new ScheduledThreadPoolExecutor(1, r -> {
            Thread t = new Thread(r);
            t.setName("removeSession");
            // 将线程设置为守护线程
            t.setDaemon(true);
            return t;
        });
    }
}

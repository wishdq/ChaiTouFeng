package com.weiyu.chaitoufeng.config;

import com.weiyu.chaitoufeng.config.property.SecurityProperty;
import com.weiyu.chaitoufeng.secure.MySecureCaptcha;
import com.weiyu.chaitoufeng.secure.handler.*;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import javax.annotation.Resource;

/**
 * Description: 自定义 安全 配置类
 *      @EnableGlobalMethodSecurity 启用方法级别的注解
 * Since: 2022-03-13 15:41
 * Author: wish_dq
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableConfigurationProperties(SecurityProperty.class)
public class MySecurityConfig extends WebSecurityConfigurerAdapter {
    /**
     * 未登录自定义处理类
     */
    @Resource
    private SecureAuthenticationEntryPoint securityAuthenticationEntryPoint;
    /**
     * 登录成功处理类
     */
    @Resource
    private SecureAuthenticationSuccessHandler securityAccessSuccessHandler;
    /**
     * 登录失败处理类
     */
    @Resource
    private SecureAuthenticationFailureHandler securityAccessFailureHandler;
    /**
     * 注销处理类
     */
    @Resource
    private SecureLogoutHandler securityLogoutHandler;
    /**
     * 注销成功处理类
     */
    @Resource
    private SecureLogoutSuccessHandler logoutSuccessHandler;

    /**
     * 没有权限处理类
     */
    @Resource
    private SecureAccessDeniedHandler securityAccessDeniedHandler;

    /**
     * 配置不拦截url
     */
    @Resource
    private SecurityProperty securityProperty;

    /**
     * 验证码验证
     */
    @Resource
    private MySecureCaptcha mySecureCaptcha;

    /**
     * UserDetailsService 实现对象
     */
    @Resource
    private UserDetailsService securityUserDetailsService;

    /**
     * 注入密码加密方式
     */
    @Resource
    private PasswordEncoder passwordEncoder;

    /**
     * 用于统计用户在线
     */
    @Resource
    private SecureRememberMeHandler rememberMeAuthenticationSuccessHandler;
    @Resource
    private SessionRegistry sessionRegistry;
    /**
     * remember me redis持久化
     */
    @Resource
    private PersistentTokenRepository securityUserTokenService;
    // session 过期操作
    @Resource
    private SecureSessionExpiredHandler securityExpiredSessionHandler;

    // 身份认证实现
    @Bean
    public AuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(securityUserDetailsService);
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder);
        daoAuthenticationProvider.setHideUserNotFoundExceptions(false);
        return daoAuthenticationProvider;
    }

    /**
     * 身份认证接口
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(daoAuthenticationProvider());
        //auth.userDetailsService(securityUserDetailsService).passwordEncoder(passwordEncoder);
    }

    /**
     *  配置 Security 控制逻辑
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/admin").authenticated()
                .antMatchers(securityProperty.getOpenApi()).permitAll()
                // 其他的需要登录后才能访问
                .anyRequest().authenticated()
                .and()
                // 先添加验证码验证类
                .addFilterBefore(mySecureCaptcha, UsernamePasswordAuthenticationFilter.class)
                .httpBasic()
                .authenticationEntryPoint(securityAuthenticationEntryPoint)
                .and()
                .formLogin()
                // 添加登录验证
                .loginPage("/login")            // 登录页面
                .loginProcessingUrl("/login")   // 登录页面表单接口
                .successHandler(securityAccessSuccessHandler)  // 配置登录成功自定义处理类
                .failureHandler(securityAccessFailureHandler)  // 配置登录失败自定义处理类
                .and()
                //开启注销功能
                .logout()
                .addLogoutHandler(securityLogoutHandler)
                .deleteCookies("JSESSIONID")                // 退出登录删除 cookie缓存
                .logoutSuccessHandler(logoutSuccessHandler) // 配置用户登出自定义处理类
                .and()
                .exceptionHandling()
                .accessDeniedHandler(securityAccessDeniedHandler) // 配置没有权限自定义处理类
                .and()
                //开启记住我功能，有效期两周
                .rememberMe()
                .rememberMeParameter("remember-me")
                .rememberMeCookieName("rememberme-token")
                .authenticationSuccessHandler(rememberMeAuthenticationSuccessHandler)
                .tokenRepository(securityUserTokenService)
                .key(securityProperty.getRememberKey())
                .and()
                .sessionManagement()
                .sessionFixation()
                .migrateSession()
                // 在需要使用到session时才创建session
                .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
                // 允许同时登陆的个数
                .maximumSessions(securityProperty.getMaximum())
                .maxSessionsPreventsLogin(false)
                // 踢出用户操作
                .expiredSessionStrategy(securityExpiredSessionHandler)
                // 用于统计在线
                .sessionRegistry(sessionRegistry);

        // 取消跨站请求伪造防护
        http.csrf().disable();
        // 防止iframe 造成跨域
        http.headers().frameOptions().disable();

    }

}

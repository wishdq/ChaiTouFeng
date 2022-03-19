package com.weiyu.chaitoufeng.common.tools;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Objects;

/**
 * Description:
 * Since: 2022-03-12 23:06
 * Author: wish_dq
 */
public class SecurityUtil {
    /**
     * 获取当前登录用户的信息
     * @return Object 当前登录用户
     */
    public static Object currentUser() {
        return Objects.requireNonNull(getAuthentication()).getPrincipal();
    }

    /**
     * 获取当前登录用户的信息
     * @return Authentication 权鉴对象
     */
    public static Authentication getAuthentication() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken))
            return authentication;
        return null;
    }
    /**
     * 验证当前用户是否登录
     * @return boolean 是否登录
     */
    public static boolean isAuthentication() {
        // if security session eq s-id is not null to index
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return !(auth instanceof AnonymousAuthenticationToken);
    }
}

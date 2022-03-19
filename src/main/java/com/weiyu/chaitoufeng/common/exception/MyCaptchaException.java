package com.weiyu.chaitoufeng.common.exception;

import org.springframework.security.core.AuthenticationException;

/**
 * Description: 验 证 码 验 证 异 常 类
 * Since: 2022-03-13 9:31
 * Author: wish_dq
 */
public class MyCaptchaException extends AuthenticationException {

    /**
     * 验 证 码 异 常
     */
    public MyCaptchaException(String message) {
        super(message);
    }

}
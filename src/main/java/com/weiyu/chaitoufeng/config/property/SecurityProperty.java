package com.weiyu.chaitoufeng.config.property;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Description: 系 统 权 限 配 置 类
 * Since: 2022-03-12 23:33
 * Author: wish_dq
 */


@Data
@ConfigurationProperties(prefix = "chaitoufeng.security")
public class SecurityProperty {

    // 超级管理员不认证
    private boolean superAuthOpen;

    // 不验证权限用户名
    private String superAdmin;

    // 记住密码标识
    private String rememberKey;

    // 开放接口列表
    private String[] openApi;

    // 允许同时登陆的个数
    private Integer maximum = 1;

}

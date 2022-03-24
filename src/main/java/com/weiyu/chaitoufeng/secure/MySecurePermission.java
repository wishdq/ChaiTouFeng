package com.weiyu.chaitoufeng.secure;

import com.weiyu.chaitoufeng.config.property.SecurityProperty;
import com.weiyu.chaitoufeng.domain.system.SysPower;
import com.weiyu.chaitoufeng.domain.system.SysUser;
import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Description: 自定义 Security
 *                    hasPermission权限  注解实现
 * Since: 2022-03-17 21:43
 * Author: wish_dq
 */
@Component
public class MySecurePermission implements PermissionEvaluator {

    @Resource
    private SecurityProperty securityProperty;

    /**
     * Describe: 自定义 Security 权限认证 @hasPermission
     */
    @Override
    public boolean hasPermission(Authentication authentication, Object targetDomainObject, Object permission) {
        SysUser securityUserDetails = (SysUser) authentication.getPrincipal();
        // 超级管理员
        if (securityProperty.isSuperAuthOpen() && securityProperty.getSuperAdmin().equals(securityUserDetails.getUsername())) {
            return true;
        }
        List<SysPower> powerList = securityUserDetails.getPowerList();
        Set<String> permissions = new HashSet<>();
        for (SysPower sysPower : powerList) {
            permissions.add(sysPower.getPowerCode());
        }
        return permissions.contains(permission);
    }

    @Override
    public boolean hasPermission(Authentication authentication, Serializable targetId, String targetType, Object permission) {
        return false;
    }
}

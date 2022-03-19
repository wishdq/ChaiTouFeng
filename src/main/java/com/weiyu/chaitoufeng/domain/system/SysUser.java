package com.weiyu.chaitoufeng.domain.system;

import com.weiyu.chaitoufeng.domain.BaseDomain;
import lombok.Getter;
import lombok.Setter;
import org.apache.ibatis.type.Alias;
import org.springframework.security.core.CredentialsContainer;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

/**
 * Description: 用户领域模型
 *              ：UserDetails接口实现类
 * Since: 2022-03-13 17:18
 * Author: wish_dq
 */
@Getter
@Setter
@Alias("SysUser")
public class SysUser extends BaseDomain implements UserDetails, CredentialsContainer {

    private static final long serialVersionUID = 1L;
    // 编号
    private String userId;
    // 账户名称
    private String username;
    // 密码
    private String password;
    // 邮箱
    private String email;
    // 盐
    private String salt;
    // 状态
    private String status;
    // 姓名
    private String realName;
    // 头像
    private String avatar;
    // 性别
    private String sex;
    // 电话
    private String phone;
    // 所属部门
    private String deptId;
    // 是否启用
    private String enable;
    // 是否登录
    private String login;
    // 计算列
    private String roleIds;
    // 最后一次登录时间
    private LocalDateTime lastTime;

    /**
     * 权限 这里暂时不用 security 的 Authorities
     */
    private List<SysPower> powerList;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return "1".equals(this.getStatus()) ? true : false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return "1".equals(this.getEnable()) ? true : false;
    }

    @Override
    public void eraseCredentials() {
        this.password = null;
    }
}

/** 笔记
 * UserDetails接口中有一个getAuthorities方法，这个方法返回的是权限，
 * 但是我们返回的权限必须带有“ROLE_”开头才可以，spring会自己截取ROLE_后边的字符串，也就是说，
 * 比如：我的权限叫ADMIN，那么，我返回告诉spring security的时候，必须告诉他权限是ROLE_ADMIN，
 * 这样spring security才会认为权限是ADMIN
 */
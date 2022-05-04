package com.weiyu.chaitoufeng.domain.secure;

import com.weiyu.chaitoufeng.domain.system.SysPower;
import com.weiyu.chaitoufeng.domain.system.SysRole;
import com.weiyu.chaitoufeng.domain.system.SysUser;
import com.weiyu.chaitoufeng.mapper.system.SysPowerMapper;
import com.weiyu.chaitoufeng.mapper.system.SysRoleMapper;
import com.weiyu.chaitoufeng.mapper.system.SysUserMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Description:  Security 用户服务
 *              安全用户处理 ：根据 用户名 定位
 *              赋予用户权限
 * Since: 2022-03-13 19:01
 * Author: wish_dq
 */
@Component
public class SecureUserDetailsServiceImpl implements UserDetailsService {


    @Resource
    private SysUserMapper sysUserMapper;

    @Resource
    private SysPowerMapper sysPowerMapper;

    @Resource
    SysRoleMapper sysRoleMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SysUser sysUser = sysUserMapper.selectByUsername(username);
        if (sysUser == null) {
            throw new UsernameNotFoundException("Account Not Found");
        }
        //添加权限
        List<SysPower> powerList = sysPowerMapper.selectByUsername(username);
        sysUser.setPowerList(powerList);

        List<String> roleList = new ArrayList<>();
        List<SysUser> userRoles = sysRoleMapper.selectByUsername(username);
        userRoles.forEach(role -> {
            roleList.add(role.getRoleIds());
        });

        sysUser.setRoleIds(StringUtils.join(roleList,","));
        return sysUser;
    }

}
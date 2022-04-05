package com.weiyu.chaitoufeng.service.system;

import com.github.pagehelper.PageInfo;
import com.weiyu.chaitoufeng.domain.system.SysMenu;
import com.weiyu.chaitoufeng.domain.build.PageDomain;
import com.weiyu.chaitoufeng.domain.system.SysRole;
import com.weiyu.chaitoufeng.domain.system.SysUser;

import java.util.List;

/**
 * Description: 用户服务接口类
 * Since: 2022-03-13 11:13
 * Author: wish_dq
 */
public interface ISysUserService {

    /**
     * Describe: 根据条件查询用户列表数据
     */
    List<SysUser> list(SysUser param);

    boolean isRegister(String username);

    /**
     * Describe: 根据条件查询用户列表数据  分页
     */
    PageInfo<SysUser> page(SysUser param, PageDomain pageDomain);

    /**
     * Describe: 根据 id 获取用户数据
     */
    SysUser getById(String id);

    /**
     * Describe: 根据 id 删除用户数据
     */
    boolean remove(String id);

    /**
     * Describe: 根据 id 修改用户数据
     */
    boolean update(SysUser sysUser);

    /**
     * Describe: 根据 id 删除用户数据
     */
    boolean batchRemove(String[] ids);

    /**
     * Describe: 保存用户数据
     */
    boolean save(SysUser sysUser);

    /**
     * Describe: 保存用户角色数据
     */
    boolean saveUserRole(String userId, List<String> roleIds);

    /**
     * 保存注册用户的 ，用户角色数据
     */
    boolean saveRegisterUserRole(String userId,String roleId);

    /**
     * Describe: 获取用户角色数据
     */
    List<SysRole> getUserRole(String userId);

    /**
     * Describe: 获取用户菜单数据
     */
    List<SysMenu> getUserMenu(String username);

    /**
     * Describe: 递归获取菜单tree
     */
    List<SysMenu> toUserMenu(List<SysMenu> sysMenus, String parentId);

}
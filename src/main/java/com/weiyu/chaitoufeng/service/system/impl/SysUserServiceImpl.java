package com.weiyu.chaitoufeng.service.system.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.weiyu.chaitoufeng.common.tools.SequenceUtil;
import com.weiyu.chaitoufeng.config.property.SecurityProperty;
import com.weiyu.chaitoufeng.domain.system.SysMenu;
import com.weiyu.chaitoufeng.domain.build.PageDomain;
import com.weiyu.chaitoufeng.domain.system.SysRole;
import com.weiyu.chaitoufeng.domain.system.SysUser;
import com.weiyu.chaitoufeng.domain.system.SysUserRole;
import com.weiyu.chaitoufeng.mapper.system.SysPowerMapper;
import com.weiyu.chaitoufeng.mapper.system.SysRoleMapper;
import com.weiyu.chaitoufeng.mapper.system.SysUserMapper;
import com.weiyu.chaitoufeng.mapper.system.SysUserRoleMapper;
import com.weiyu.chaitoufeng.service.system.ISysUserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Description: 用户服务接口实现类
 * Since: 2022-03-13 11:14
 * Author: wish_dq
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper,SysUser> implements ISysUserService {

    /**
     * 注入用户服务
     */
    @Resource
    private SysUserMapper sysUserMapper;

    /**
     * 注入用户角色服务
     */
    @Resource
    private SysUserRoleMapper sysUserRoleMapper;

    /**
     * 注入角色服务
     */
    @Resource
    private SysRoleMapper sysRoleMapper;

    /**
     * 注入权限服务
     */
    @Resource
    private SysPowerMapper sysPowerMapper;

    /**
     * 超级管理员配置
     */
    @Resource
    private SecurityProperty securityProperty;

    /**
     * Describe: 根据条件查询用户列表数据
     */
    @Override
    public List<SysUser> list(SysUser param) {
        return sysUserMapper.selectList(param);
    }

    /**
     * 查看用户名是否注册
     */
    public boolean isRegister(String username){
        return sysUserMapper.selectRegisterByUsername(username).size()>=1;
    }

    /**
     * Describe: 根据条件查询用户列表数据  分页
     */
    @Override
    public PageInfo<SysUser> page(SysUser param, PageDomain pageDomain) {
        PageHelper.startPage(pageDomain.getPage(), pageDomain.getLimit());
        List<SysUser> sysUsers = sysUserMapper.selectList(param);
        return new PageInfo<>(sysUsers);
    }

    /**
     * Describe: 根据 ID 查询用户
     */
    @Override
    public SysUser getById(String id) {
        return sysUserMapper.selectById(id);
    }

    /**
     * Describe: 根据 id 删除用户数据
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean remove(String id) {
        sysUserRoleMapper.deleteByUserId(id);
        sysUserMapper.deleteById(id);
        return true;
    }

    /**
     * Describe: 根据 id 批量删除用户数据
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean batchRemove(String[] ids) {
        sysUserMapper.deleteByIds(ids);
        sysUserRoleMapper.deleteByUserIds(ids);
        return true;
    }

    /**
     * Describe: 保存用户数据
     */
    @Override
    public boolean save(SysUser sysUser) {
        SysUser user = new SysUser();
        user.setUsername(sysUser.getUsername());
        if (sysUserMapper.count(user) > 0) {
            return false;
        }
        int result = sysUserMapper.insert(sysUser);
        return result > 0;
    }

    /**
     * Describe: 修改用户数据
     */
    @Override
    public boolean update(SysUser sysUser) {
        Integer result = sysUserMapper.updateById(sysUser);
        return result > 0;
    }

    /**
     * Describe: 保存用户角色数据
     */
    @Override
    public boolean saveUserRole(String userId, List<String> roleIds) {
        sysUserRoleMapper.deleteByUserId(userId);
        List<SysUserRole> sysUserRoles = new ArrayList<>();
        roleIds.forEach(roleId -> {
            SysUserRole sysUserRole = new SysUserRole();
            sysUserRole.setId(SequenceUtil.makeStringId());
            sysUserRole.setRoleId(roleId);
            sysUserRole.setUserId(userId);
            sysUserRoles.add(sysUserRole);
        });
        int i = sysUserRoleMapper.batchInsert(sysUserRoles);
        return i > 0;
    }

    /**
     * Describe: 保存注册用户的 ，用户角色数据
     */
    @Override
    public boolean saveRegisterUserRole(String userId,String roleId){
        SysUserRole sysUserRole = new SysUserRole();
        sysUserRole.setId(SequenceUtil.makeStringId());
        sysUserRole.setRoleId(roleId);
        sysUserRole.setUserId(userId);
        return sysUserRoleMapper.insert(sysUserRole)>0;
    }

    /**
     * Describe: 获取
     */
    @Override
    public List<SysRole> getUserRole(String userId) {
        List<SysRole> allRole = sysRoleMapper.selectList(null);
        List<SysUserRole> myRole = sysUserRoleMapper.selectByUserId(userId);
        allRole.forEach(sysRole -> {
            myRole.forEach(sysUserRole -> {
                if (sysRole.getRoleId().equals(sysUserRole.getRoleId())) {
                    sysRole.setChecked(true);
                }
            });
        });
        return allRole;
    }

    /**
     * Describe: 获取用户菜单
     */
    @Override
    public List<SysMenu> getUserMenu(String username) {
        String name = !(securityProperty.isSuperAuthOpen() && username.equals(securityProperty.getSuperAdmin())) ? username : "";
        return sysPowerMapper.selectMenuByUsername(name);
    }

    /**
     * Describe: 递归获取菜单tree
     */
    @Override
    public List<SysMenu> toUserMenu(List<SysMenu> sysMenus, String parentId) {
        List<SysMenu> list = new ArrayList<>();
        for (SysMenu menu : sysMenus) {
            if (parentId.equals(menu.getParentId())) {
                menu.setChildren(toUserMenu(sysMenus, menu.getId()));
                list.add(menu);
            }
        }
        return list;
    }

    /**
     * 前台用户
     */
    @Override
    public PageInfo<SysUser> getHomePage(PageDomain pageDomain, SysUser homeUser) {
        PageHelper.startPage(pageDomain.getPage(), pageDomain.getLimit());
        List<SysUser> homeUsers = sysUserMapper.selectHomePageUsers(homeUser);
        return new PageInfo<>(homeUsers);
    }
}


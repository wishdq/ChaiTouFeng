package com.weiyu.chaitoufeng.service.system;

import com.github.pagehelper.PageInfo;
import com.weiyu.chaitoufeng.domain.build.PageDomain;
import com.weiyu.chaitoufeng.domain.system.SysPower;
import com.weiyu.chaitoufeng.domain.system.SysRole;

import java.util.List;

/**
 * Description: 角色服务接口类
 * Since: 2022-03-18 20:54
 * Author: wish_dq
 */
public interface ISysRoleService {

    /**
     * Describe: 查询角色数据
     */
    List<SysRole> list(SysRole queryRoleParam);

    /**
     * Describe: 分页查询角色数据
     */
    PageInfo<SysRole> page(SysRole param, PageDomain pageDomain);

    /**
     * Describe: 保存角色数据
     */
    boolean save(SysRole sysRole);

    /**
     * Describe: 根据 id 获取角色信息
     */
    SysRole getById(String id);

    /**
     * Describe: 根据 id 修改用户数据
     */
    boolean update(SysRole sysRole);

    /**
     * Describe: 获取角色权限
     */
    List<SysPower> getRolePower(String roleId);

    /**
     * Describe: 保存角色权限
     */
    Boolean saveRolePower(String roleId, List<String> powerIds);

    /**
     * Describe: 根据 id 删除角色数据
     */
    Boolean remove(String id);

    /**
     * Describe: 根据 id 删除角色数据
     */
    boolean batchRemove(String[] ids);

}

package com.weiyu.chaitoufeng.service.system;

import com.github.pagehelper.PageInfo;
import com.weiyu.chaitoufeng.domain.request.PageDomain;
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
     * Param: queryRoleParam
     * Return: 操作结果
     */
    List<SysRole> list(SysRole queryRoleParam);

    /**
     * Describe: 分页查询角色数据
     * Param: queryRoleParam
     * Param: pageDomain
     * Return: 操作结果
     */
    PageInfo<SysRole> page(SysRole param, PageDomain pageDomain);

    /**
     * Describe: 保存角色数据
     * Param: SysRole
     * Return: 操作结果
     */
    boolean save(SysRole sysRole);

    /**
     * Describe: 根据 id 获取角色信息
     * Param: id
     * Return: 操作结果
     */
    SysRole getById(String id);

    /**
     * Describe: 根据 id 修改用户数据
     * Param: ids
     * Return: 操作结果
     */
    boolean update(SysRole sysRole);

    /**
     * Describe: 获取角色权限
     * Param: roleId
     * Return: 操作结果
     */
    List<SysPower> getRolePower(String roleId);

    /**
     * Describe: 保存角色权限
     * Param: roleId , powerIds
     * Return: 操作结果
     */
    Boolean saveRolePower(String roleId, List<String> powerIds);

    /**
     * Describe: 根据 id 删除角色数据
     * Param: id
     * Return: 操作结果
     */
    Boolean remove(String id);

    /**
     * Describe: 根据 id 删除角色数据
     * Param: ids
     * Return: 操作结果
     */
    boolean batchRemove(String[] ids);

}

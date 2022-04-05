package com.weiyu.chaitoufeng.mapper.system;

import com.weiyu.chaitoufeng.domain.system.SysRole;
import com.weiyu.chaitoufeng.domain.system.SysUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Description: 系统角色接口
 * Since: 2022-03-13 23:59
 * Author: wish_dq
 */
@Mapper
public interface SysRoleMapper {
    /**
     * Describe: 根据 username 查询用户权限
     */
    List<SysUser> selectByUsername(@Param("username") String username);

    /**
     * Describe: 查询角色列表
     */
    List<SysRole> selectList(SysRole param);

    /**
     * Describe: 添加角色数据
     */
    Integer insert(SysRole sysRole);

    /**
     * Describe: 根据 Id 查询角色
     */
    SysRole selectById(@Param("id") String id);

    /**
     * Describe: 根据 roleName 查询角色
     */
    SysRole selectByRoleCode(@Param("roleCode") String roleCode);

    /**
     * Describe: 根据 Id 修改角色
     */
    Integer updateById(SysRole sysRole);

    /**
     * Describe: 根据 Id 删除用户
     */
    Integer deleteById(String id);

    /**
     * Describe: 根据 Id 批量删除
     */
    Integer deleteByIds(String[] ids);

}

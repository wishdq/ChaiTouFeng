package com.weiyu.chaitoufeng.mapper.system;

import com.weiyu.chaitoufeng.domain.system.SysUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Description: 用户接口层
 * Since: 2022-03-13 11:18
 * Author: wish_dq
 */
@Mapper
public interface SysUserMapper {

    /**
     * Describe: 根据 username 查询用户
     */
    SysUser selectByUsername(@Param("username") String username);

    /**
     * Describe: 根据 Id 查询用户
     */
    SysUser selectById(@Param("id") String id);

    /**
     * Describe: 根据 SysUser 条件查询用户
     */
    List<SysUser> selectList(SysUser param);

    /**
     * Describe: 根据 SysUser 条件查询用户数量
     */
    Integer count(SysUser sysUser);

    /**
     * Describe: 添加用户数据
     */
    Integer insert(SysUser sysUser);

    /**
     * Describe: 根据 Id 修改用户
     */
    Integer updateById(SysUser sysUser);

    /**
     * Describe: 根据 Id 删除用户
     */
    Integer deleteById(String id);


    /**
     * Describe: 根据 Id 批量删除
     */
    Integer deleteByIds(String[] ids);

    /**
     * Describe: 重置部门
     */
    Integer resetDeptByDeptId(String deptId);

    /**
     * Describe: 批量重置部门
     */
    Integer resetDeptByDeptIds(String[] deptIds);
}

package com.weiyu.chaitoufeng.mapper.system;

import com.weiyu.chaitoufeng.domain.system.SysUserRole;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Description: 用户角色接口
 * Since: 2022-03-14 0:01
 * Author: wish_dq
 */
@Mapper
public interface SysUserRoleMapper {

    int batchInsert(List<SysUserRole> sysUserRoles);

    int deleteByUserId(String userId);

    int deleteByUserIds(String[] userIds);

    int deleteByRoleId(String roleId);

    int deleteByRoleIds(String[] roleIds);

    List<SysUserRole> selectByUserId(String userId);
}


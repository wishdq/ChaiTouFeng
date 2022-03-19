package com.weiyu.chaitoufeng.mapper;

import com.weiyu.chaitoufeng.domain.system.SysRolePower;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Description: 角色权限接口
 * Since: 2022-03-18 20:56
 * Author: wish_dq
 */
@Mapper
public interface SysRolePowerMapper {

    List<SysRolePower> selectByRoleId(String roleId);

    int batchInsert(List<SysRolePower> sysRolePowers);

    int deleteByRoleId(String roleId);

    int deleteByRoleIds(String[] roleIds);

    int deleteByPowerId(String powerId);

    int deleteByPowerIds(String[] powerIds);
}

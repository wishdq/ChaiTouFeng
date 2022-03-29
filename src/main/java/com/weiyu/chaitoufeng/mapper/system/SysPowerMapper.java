package com.weiyu.chaitoufeng.mapper.system;

import com.weiyu.chaitoufeng.domain.system.SysMenu;
import com.weiyu.chaitoufeng.domain.system.SysPower;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Description: 系统权限接口
 * Since: 2022-03-13 23:45
 * Author: wish_dq
 */
@Mapper
public interface SysPowerMapper {

    /**
     * Describe: 根据 SysPower 条件查询权限
     */
    List<SysPower> selectList(SysPower sysPower);

    /**
     * Describe: 根据 SysPower 条件查询权限
     */
    List<SysPower> selectListByParentId(String parentId);

    /**
     * Describe: 保存 SysPower 权限数据
     */
    Integer insert(SysPower sysPower);

    /**
     * Describe: 根据 Id 查询权限
     */
    SysPower selectById(@Param("id") String id);

    /**
     * Describe: 根据 username 查询用户权限
     */
    List<SysPower> selectByUsername(String username);

    /**
     * Describe: 根据 username 查询用户菜单
     */
    List<SysMenu> selectMenuByUsername(String username);


    /**
     * Describe: 修改权限信息
     */
    int updateById(SysPower sysPower);

    /**
     * Describe: 删除权限信息
     */
    int deleteById(String id);
}


package com.weiyu.chaitoufeng.mapper.system;

import com.weiyu.chaitoufeng.domain.system.SysConfig;
import com.weiyu.chaitoufeng.domain.system.SysConfigGroup;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Description: 系统配置接口
 * Since: 2022-03-21 0:50
 * Author: wish_dq
 */
@Mapper
public interface SysConfigMapper {

    /**
     * Describe: 查询系统配置信息
     */
    List<SysConfig> selectList(SysConfig param);


    /**
     * Describe: 添加系统配置
     */
    Integer insert(SysConfig sysConfig);

    /**
     * Describe: 根据 Id 查询系统配置
     */
    SysConfig selectById(@Param("id") String id);

    /**
     * Describe: 根据 Code 查询系统配置
     */
    SysConfig selectByCode(@Param("code") String code);

    /**
     * Describe: 根据 Id 修改系统配置
     */
    Integer updateById(SysConfig sysConfig);

    /**
     * Describe: 根据 Id 删除系统配置
     */
    Integer deleteById(String id);

    /**
     * Describe: 根据 Id 删除系统配置
     */
    Integer deleteByIds(String[] id);


    List<SysConfigGroup> groupTree();

    List<SysConfigGroup> groupList(SysConfigGroup configGroup);


    Integer updateGroupById(SysConfigGroup configGroup);

    Integer deleteGroupById(String id);

    Integer insertGroup(SysConfigGroup configGroup);
}

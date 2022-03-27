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
     * Param: SysConfig
     * Return: 执行结果
     */
    List<SysConfig> selectList(SysConfig param);


    /**
     * Describe: 添加系统配置
     * Param: SysConfig
     * Return: 执行结果
     */
    Integer insert(SysConfig sysConfig);

    /**
     * Describe: 根据 Id 查询系统配置
     * Param: id
     * Return: SysConfig
     */
    SysConfig selectById(@Param("id") String id);

    /**
     * Describe: 根据 Code 查询系统配置
     * Param: code
     * Return: SysConfig
     */
    SysConfig selectByCode(@Param("code") String code);

    /**
     * Describe: 根据 Id 修改系统配置
     * Param: SysConfig
     * Return: Boolean
     */
    Integer updateById(SysConfig sysConfig);

    /**
     * Describe: 根据 Id 删除系统配置
     * Param: id
     * Return: SysConfig
     */
    Integer deleteById(String id);

    /**
     * Describe: 根据 Id 删除系统配置
     * Param: id
     * Return: SysConfig
     */
    Integer deleteByIds(String[] id);


    List<SysConfigGroup> groupTree();

    List<SysConfigGroup> groupList(SysConfigGroup configGroup);

    //SysConfigGroup getGroupById();

    Integer updateGroupById(SysConfigGroup configGroup);

    Integer deleteGroupById(String id);

    Integer insertGroup(SysConfigGroup configGroup);


}

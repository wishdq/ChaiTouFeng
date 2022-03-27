package com.weiyu.chaitoufeng.mapper.system;

import com.weiyu.chaitoufeng.domain.system.SysDept;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Description: 部门接口
 * Since: 2022-03-18 21:41
 * Author: wish_dq
 */
@Mapper
public interface SysDeptMapper {

    /**
     * Describe: 查询部门列表
     * Param: SysDept
     * Return: List<SysDept>
     */
    List<SysDept> selectList(SysDept param);

    /**
     * Describe: 添加部门数据
     * Param: SysDept
     * Return: 执行结果
     */
    Integer insert(SysDept sysDept);

    /**
     * Describe: 根据 Id 查询部门
     * Param: id
     * Return: SysDept
     */
    SysDept selectById(@Param("id") String id);

    /**
     * Describe: 根据 Id 修改部门
     * Param: SysDept
     * Return: Integer
     */
    Integer updateById(SysDept sysDept);

    /**
     * Describe: 根据 Id 删除部门
     * Param: id
     * Return: Integer
     */
    Integer deleteById(String id);

    /**
     * Describe: 根据 Id 批量删除
     * Param: ids
     * Return: Integer
     */
    Integer deleteByIds(String[] ids);

    /**
     * Describe: 根据 parentId 查询部门
     * Param: parentId
     * Return: SysDept
     */
    List<SysDept> selectListByParentId(String parentId);

}


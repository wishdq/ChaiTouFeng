package com.weiyu.chaitoufeng.mapper;

import com.weiyu.chaitoufeng.domain.system.SysFile;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Description: 系统文件服务接口
 * Since: 2022-03-18 18:55
 * Author: wish_dq
 */
@Mapper
public interface SysFileMapper {

    /**
     * Describe: 插入文件信息
     * Param: File
     * Return: int
     */
    int insert(SysFile file);

    /**
     * Describe: 查询文件列表
     * Param: null
     * Return: List<File>
     */
    List<SysFile> selectList(SysFile sysFile);

    /**
     * Describe: 根据 Id 查询文件信息
     * Param: id
     * Return: File
     */
    SysFile selectById(@Param("id") String id);

    /**
     * Describe: 根据 Id 删除文件信息
     * Param: id
     * Return: int
     */
    int deleteById(@Param("id") String id);

}


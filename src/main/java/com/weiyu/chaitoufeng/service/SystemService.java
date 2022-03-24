package com.weiyu.chaitoufeng.service;

import com.weiyu.chaitoufeng.domain.base.SysBaseDict;
import com.weiyu.chaitoufeng.domain.base.SysBaseLog;
import com.weiyu.chaitoufeng.domain.base.SysBaseRole;
import com.weiyu.chaitoufeng.domain.base.SysBaseUser;

import java.util.List;

/**
 * Description: 系统基础 API
 * Since: 2022-03-21 0:40
 * Author: wish_dq
 */
public interface SystemService {

    /**
     * 根据用户账号查询用户信息
     *
     * @param username
     * @return
     */
    SysBaseUser getUserByName(String username);


    /**
     * 根据用户id查询用户信息
     *
     * @param id
     * @return
     */
    SysBaseUser getUserById(String id);

    /**
     * 通过用户账号查询角色集合
     *
     * @param username
     * @return
     */
    List<SysBaseRole> getRolesByUsername(String username);

    /**
     * 根据字典code获取可用的字典列表数据
     *
     * @param typeCode
     * @return
     */
    List<SysBaseDict> selectDictByCode(String typeCode);

    /**
     * 查询表字典通过查询指定table的 text code key 获取字典值
     *
     * @param table 表名
     * @param text  label
     * @param code  value
     * @return
     */
    List<SysBaseDict> queryTableDictItemsByCode(String table, String text, String code);


    /**
     * 查询表字典 通过查询指定table的 text code 获取字典（指定查询条件）
     *
     * @param table 表名
     * @param text  label
     * @param code  value
     * @param filterSql 条件
     */
    List<SysBaseDict> queryTableDictItemsByCodeAndFilter(String table, String text, String code, String filterSql);


    /**
     * 查询表字典 通过查询指定table的 text code key 获取字典值，包含value
     *
     * @param table    表名
     * @param text     label
     * @param code     value
     * @param keyArray values
     * @return
     */
    List<SysBaseDict> queryTableDictByKeys(String table, String text, String code, String[] keyArray);

    /**
     * 查询系统配置
     *
     * @param code
     */
    String getConfig(String code);

    /**
     * 存储日志
     *
     * @param log 日志对象
     */
    Boolean saveLog(SysBaseLog log);
}

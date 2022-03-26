package com.weiyu.chaitoufeng.service;

import com.weiyu.chaitoufeng.common.logging.LoggingType;
import com.weiyu.chaitoufeng.domain.system.SysLog;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Description: 日 志 服 务 接 口
 * Since: 2022-03-18 19:50
 * Author: wish_dq
 */
public interface ISysLogService {

    /**
     * Describe: 执 行 插 入 操 作
     * Param: logging
     * Return: boolean
     */
    boolean save(SysLog sysLog);

    /**
     * Aop存储日志
     *
     * @param log 日志对象
     */
    Boolean aopSaveLog(SysLog log);

    /**
     * Describe: 执 行 查 询 操 作
     * Param: loggingType
     * Return: 日志列表
     */
    List<SysLog> data(LoggingType loggingType, LocalDateTime startTime, LocalDateTime endTime);

    /**
     * Describe: 根 据 id 查 询 日 志
     * Param: id
     * Return: Logging
     */
    SysLog getById(String id);

    /**
     * Describe: 根据 operateName:操作人员名称 查询日志
     * Param: operateName
     * Return: 日志列表
     */
    List<SysLog> selectTopLoginLog(String operateName);

}


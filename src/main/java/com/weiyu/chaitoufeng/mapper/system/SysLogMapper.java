package com.weiyu.chaitoufeng.mapper.system;

import com.weiyu.chaitoufeng.common.logging.LoggingType;
import com.weiyu.chaitoufeng.domain.system.SysLog;
import org.apache.ibatis.annotations.Mapper;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Description: 日 志 接 口
 * Since: 2022-03-18 20:03
 * Author: wish_dq
 */
@Mapper
public interface SysLogMapper {

    /**
     * Describe: 插入日志信息
     */
    int insert(SysLog sysLog);

    /**
     * Describe: 查询日志信息
     */
    List<SysLog> selectList(LoggingType loggingType, LocalDateTime startTime, LocalDateTime endTime);

    /**
     * Describe: 根据 id 查询日志信息
     */
    SysLog getById(String id);

    /**
     * Describe: 根据 operateName 查询日志
     */
    List<SysLog> selectTopLoginLog(String operateName);

}


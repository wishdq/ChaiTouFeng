package com.weiyu.chaitoufeng.service.system;

import com.baomidou.mybatisplus.extension.service.IService;
import com.weiyu.chaitoufeng.common.logging.LoggingType;
import com.weiyu.chaitoufeng.domain.system.SysLog;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Description: 日 志 服 务 接 口
 * Since: 2022-03-18 19:50
 * Author: wish_dq
 */
public interface ISysLogService extends IService<SysLog> {

    /**
     * Describe: 执 行 插 入 操 作
     */
    boolean save(SysLog sysLog);

    /**
     * 按时间查看访问人数
     */
    int count(String createTime);

    /**
     * Describe: 执 行 查 询 操 作
     */
    List<SysLog> data(LoggingType loggingType, LocalDateTime startTime, LocalDateTime endTime);

    /**
     * Describe: 根 据 id 查 询 日 志
     */
    SysLog getById(String id);

    /**
     * Describe: 根据 operateName:操作人员名称 查询日志
     */
    List<SysLog> selectTopLoginLog(String operateName);

}


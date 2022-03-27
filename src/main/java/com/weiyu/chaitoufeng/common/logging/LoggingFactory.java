package com.weiyu.chaitoufeng.common.logging;

import com.weiyu.chaitoufeng.service.system.ISysLogService;
import com.weiyu.chaitoufeng.domain.system.SysLog;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * Description: 日 志 异 步 工 厂
 * Since: 2022-03-27 17:13
 * Author: wish_dq
 */
@Component
public class LoggingFactory {

    /**
     * 引 入 日 志 服 务
     */
    @Resource
    private ISysLogService logService;

    /**
     * 执 行 日 志 入 库 操 作
     */
    @Async
    public void record(SysLog sysLog) {
        logService.save(sysLog);
    }
}
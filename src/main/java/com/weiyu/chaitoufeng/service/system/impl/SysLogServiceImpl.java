package com.weiyu.chaitoufeng.service.system.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.weiyu.chaitoufeng.common.logging.LoggingType;
import com.weiyu.chaitoufeng.common.tools.SecurityUtil;
import com.weiyu.chaitoufeng.common.tools.ServletUtil;
import com.weiyu.chaitoufeng.service.system.ISysLogService;
import com.weiyu.chaitoufeng.domain.system.SysLog;
import com.weiyu.chaitoufeng.domain.system.SysUser;
import com.weiyu.chaitoufeng.mapper.system.SysLogMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Description: 日 志 服 务 接 口 实 现
 * Since: 2022-03-18 20:02
 * Author: wish_dq
 */
@Service
public class SysLogServiceImpl extends ServiceImpl<SysLogMapper,SysLog> implements ISysLogService {

    @Resource
    private SysLogMapper sysLogMapper;

    @Override
    public boolean save(SysLog sysLog) {
        sysLog.setOperateAddress(ServletUtil.getRemoteHost());
        sysLog.setMethod(ServletUtil.getRequestURI());
        sysLog.setCreateTime(LocalDateTime.now());
        sysLog.setRequestMethod(RequestMethod.valueOf(ServletUtil.getMethod()));
        sysLog.setOperateUrl(ServletUtil.getRequestURI());
        sysLog.setBrowser(ServletUtil.getBrowser());
        sysLog.setRequestBody(ServletUtil.getQueryParam());
        sysLog.setSystemOs(ServletUtil.getSystem());
        SysUser currentUser = (SysUser) SecurityUtil.currentUser();
        sysLog.setOperateName(null != currentUser ? currentUser.getUsername() : "未登录用户");
        int result = sysLogMapper.insert(sysLog);
        return result > 0;
    }

    /**
     * 按时间查看访问人数
     */
    @Override
    public int count(String createTime) {
        return sysLogMapper.getCount(createTime);
    }

    @Override
    public List<SysLog> data(LoggingType loggingType, LocalDateTime startTime, LocalDateTime endTime) {
        return sysLogMapper.selectList(loggingType, startTime, endTime);
    }

    @Override
    public SysLog getById(String id) {
        return sysLogMapper.getById(id);
    }

    @Override
    public List<SysLog> selectTopLoginLog(String operateName) {
        return sysLogMapper.selectTopLoginLog(operateName);
    }

}


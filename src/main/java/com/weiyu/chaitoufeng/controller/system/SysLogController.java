package com.weiyu.chaitoufeng.controller.system;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.weiyu.chaitoufeng.common.constant.ControllerConstant;
import com.weiyu.chaitoufeng.common.logging.LoggingType;
import com.weiyu.chaitoufeng.controller.base.BaseController;
import com.weiyu.chaitoufeng.domain.build.PageDomain;
import com.weiyu.chaitoufeng.domain.build.ResultTable;
import com.weiyu.chaitoufeng.domain.system.SysLog;
import com.weiyu.chaitoufeng.service.system.ISysLogService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.time.LocalDateTime;

/**
 * Description: 日志控制器
 * Since: 2022-03-18 20:17
 * Author: wish_dq
 */
@RestController
@RequestMapping(ControllerConstant.API_SYSTEM_PREFIX + "log")
public class SysLogController extends BaseController {

    @Resource
    private ISysLogService sysLogService;

    /**
     * Describe: 行为日志视图
     */
    @GetMapping("main")
    @PreAuthorize("hasPermission('/system/log/main','sys:log:main')")
    public ModelAndView main() {
        return jumpPage("admin/system/log/main");
    }

    /**
     * Describe: 操作日志数据
     */
    @GetMapping("operateLog")
    @PreAuthorize("hasPermission('/system/log/operateLog','sys:log:operateLog')")
    public ResultTable operateLog(PageDomain pageDomain, LocalDateTime startTime, LocalDateTime endTime) {
        PageHelper.startPage(pageDomain.getPage(), pageDomain.getLimit());
        PageInfo<SysLog> pageInfo = new PageInfo<>(sysLogService.data(LoggingType.OPERATE, startTime, endTime));
        return pageTable(pageInfo.getList(), pageInfo.getTotal());
    }

    /**
     * Describe: 登录日志数据
     */
    @GetMapping("loginLog")
    @PreAuthorize("hasPermission('/system/log/loginLog','sys:log:loginLog')")
    public ResultTable loginLog(PageDomain pageDomain, LocalDateTime startTime, LocalDateTime endTime) {
        PageHelper.startPage(pageDomain.getPage(), pageDomain.getLimit());
        PageInfo<SysLog> pageInfo = new PageInfo<>(sysLogService.data(LoggingType.LOGIN, startTime, endTime));
        return pageTable(pageInfo.getList(), pageInfo.getTotal());
    }

    /**
     * Describe: 日志详情
     */
    @GetMapping("/info")
    @PreAuthorize("hasPermission('/system/log/info','sys:log:info')")
    public ModelAndView details() {
        return jumpPage("system/log/info");
    }

}
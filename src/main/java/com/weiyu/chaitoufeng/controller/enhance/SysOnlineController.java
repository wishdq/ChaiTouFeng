package com.weiyu.chaitoufeng.controller.enhance;

import com.weiyu.chaitoufeng.common.constant.ControllerConstant;
import com.weiyu.chaitoufeng.common.result.Result;
import com.weiyu.chaitoufeng.common.session.HttpSessionContext;
import com.weiyu.chaitoufeng.common.session.HttpSessionContextHolder;
import com.weiyu.chaitoufeng.controller.base.BaseController;
import com.weiyu.chaitoufeng.domain.build.ResultTable;
import com.weiyu.chaitoufeng.domain.system.SysOnlineUser;
import com.weiyu.chaitoufeng.domain.system.SysUser;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.session.SessionInformation;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * Description: 在线用户控制器
 * Since: 2022-03-17 23:21
 * Author: wish_dq
 */
@RestController
@RequestMapping(ControllerConstant.API_SYSTEM_PREFIX + "online")
public class SysOnlineController extends BaseController {

    @Resource
    private SessionRegistry sessionRegistry;

    /**
     * Describe: 在线用户列表
     * Param: username
     * Return: ModelAndView
     */
    @GetMapping("data")
    @PreAuthorize("hasPermission('/system/online/data','sys:online:data')")
    public ResultTable data(String username) {
        List<Object> allPrincipalsUser = sessionRegistry.getAllPrincipals();
        List<SysOnlineUser> onlineUser = new ArrayList<>();
        for (Object obj : allPrincipalsUser) {
            SysOnlineUser sysOnlineUser = new SysOnlineUser();
            SysUser objs = (SysUser) obj;
            sysOnlineUser.setUserId(objs.getUserId());
            sysOnlineUser.setUsername(objs.getUsername());
            sysOnlineUser.setRealName(objs.getRealName());
            sysOnlineUser.setLastTime(objs.getLastTime());
            System.out.println(objs.getLastTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
            sysOnlineUser.setOnlineTime(Duration.between(objs.getLastTime(), LocalDateTime.now()).toMinutes() + "分钟");
            onlineUser.add(sysOnlineUser);
        }
        // TODO 使用 Stream 进行内存数据过滤
        return dataTable(onlineUser);
    }

    /**
     * Describe: 获取在线用户列表视图
     * Return: ModelAndView
     */
    @GetMapping("main")
    @PreAuthorize("hasPermission('/system/online/main','sys:online:main')")
    public ModelAndView main() {
        return jumpPage("system/user/online");
    }


    /**
     * Describe: 踢出用户（下线）
     * Param: onlineId
     * Return: ModelAndView
     */
    @DeleteMapping("/remove/{onlineId}")
    @PreAuthorize("hasPermission('/system/online/remove','sys:online:remove')")
    public Result remove(@PathVariable String onlineId) {
        // 从sessionRegistry中获取所有的用户信息
        List<Object> principals = sessionRegistry.getAllPrincipals();
        for (Object principal : principals) {
            SysUser userDetails = (SysUser) principal;
            String userId = userDetails.getUserId();
            if (onlineId.equals(userId)) {
                // 不允许操作admin用户下线
                if ("admin".equals(userDetails.getUsername())) {
                    return failure("不允许操作超级管理员[admin]下线");
                }
                for (SessionInformation sessionInformation : sessionRegistry.getAllSessions(userDetails, false)) {
                    sessionInformation.expireNow();
                    // 从sessionRegistry中清除session信息
                    sessionRegistry.removeSessionInformation(sessionInformation.getSessionId());
                    HttpSessionContext sessionContext = HttpSessionContextHolder.currentSessionContext();
                    // 销毁session
                    sessionContext.getSession(sessionInformation.getSessionId()).invalidate();
                }
                return success(String.format("用户[%s]已下线", userDetails.getRealName()));
            }
        }
        return failure();
    }
}

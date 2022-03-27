package com.weiyu.chaitoufeng.controller;


import com.weiyu.chaitoufeng.common.logging.BusinessType;
import com.weiyu.chaitoufeng.controller.base.BaseController;
import com.weiyu.chaitoufeng.secure.session.SecureSessionService;
import com.weiyu.chaitoufeng.common.logging.Logging;
import com.weiyu.chaitoufeng.common.tools.SecurityUtil;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * Description: 入口逻辑
 * Since: 2022-03-12 10:20
 * Author: wish_dq
 */
@Slf4j
@Controller
public class EntranceController extends BaseController {

    @Resource
    private SessionRegistry sessionRegistry;

    /**
     * Describe: 获取登录视图
     */
    @GetMapping("login")
    public String login(HttpServletRequest request) {
        if (SecurityUtil.isAuthentication()) {
            SecureSessionService.expiredSession(request, sessionRegistry);
            // 用户已登录重定向指定界面
            return "redirect:/admin";
        }
        return "login";
    }

    /**
     * Describe: 获取admin视图
     */
    @GetMapping("admin")
    @Logging(title = "主页", describe = "返回 Admin 主页视图", type = BusinessType.ADD)
    public ModelAndView admin() {
        return jumpPage("admin");
    }

    /**
     * Describe: 获取控制台主页视图 console
     */
    @GetMapping("console")
    public ModelAndView home() {
        return jumpPage("console/console");
    }

    /**
     * Describe:无权限页面 返回403页面
     */
    @GetMapping("error/403")
    public ModelAndView noPermission() {
        return jumpPage("error/403");
    }

    /**
     * Describe:找不带页面 返回404页面
     */
    @GetMapping("error/404")
    public ModelAndView notFound() {
        return jumpPage("error/404");
    }

    /**
     * Describe:异常处理页 返回500界面
     */
    @GetMapping("error/500")
    public ModelAndView onException() {
        return jumpPage("error/500");
    }

}
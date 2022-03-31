package com.weiyu.chaitoufeng.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.weiyu.chaitoufeng.common.logging.BusinessType;
import com.weiyu.chaitoufeng.common.result.Result;
import com.weiyu.chaitoufeng.common.tools.SequenceUtil;
import com.weiyu.chaitoufeng.controller.base.BaseController;
import com.weiyu.chaitoufeng.domain.home.HomeUser;
import com.weiyu.chaitoufeng.secure.session.SecureSessionService;
import com.weiyu.chaitoufeng.common.logging.Logging;
import com.weiyu.chaitoufeng.common.tools.SecurityUtil;

import com.weiyu.chaitoufeng.service.site.SiteUserService;
import com.wf.captcha.utils.CaptchaUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

/**
 * Description: 入口逻辑
 * Since: 2022-03-12 10:20
 * Author: wish_dq
 */
@Slf4j
@Controller
public class EntranceController extends BaseController {

    @Resource
    SiteUserService userService;

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
            return "admin";
        }
        return "login";
    }


    @PostMapping("register")
    @ResponseBody
    public Result register(@RequestParam("username") String username,
                           @RequestParam("password") String password,
                           @RequestParam("captcha") String captcha,
                           HttpServletRequest request) {
        if (!CaptchaUtil.ver(captcha,request)) {
            return Result.failure("验证码错误");
        }

        QueryWrapper<HomeUser> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username",username);
        if (userService.list(queryWrapper).size() == 1){
            return Result.failure("用户名已存在");
        }

        HomeUser homeUser = new HomeUser();
        homeUser.setUserId(SequenceUtil.makeStringId());
        homeUser.setUsername(username);
        homeUser.setPassword(password);
        homeUser.setCreateTime(LocalDateTime.now());
        userService.save(homeUser);

        return Result.success("注册成功");
    }

    @GetMapping("register")
    public String registerToIndex(HttpServletRequest request) {
        return "test";
    }

    //获取index视图
    @GetMapping(value = {"index","/"})
    public ModelAndView index() {
        return jumpPage("index");
    }

    //获取admin视图
    @GetMapping("admin")
    @Logging(title = "主页", describe = "返回 Admin 主页视图", type = BusinessType.ADD)
    public ModelAndView admin() {
        return jumpPage("admin");
    }

    //获取控制台主页视图 console
    @GetMapping("console")
    public ModelAndView home() {
        return jumpPage("admin/console/console");
    }

    //无权限页面 返回403页面
    @GetMapping("error/403")
    public ModelAndView noPermission() {
        return jumpPage("error/403");
    }

    //找不带页面 返回404页面
    @GetMapping("error/404")
    public ModelAndView notFound() {
        return jumpPage("error/404");
    }

    //异常处理页 返回500界面
    @GetMapping("error/500")
    public ModelAndView onException() {
        return jumpPage("error/500");
    }

}
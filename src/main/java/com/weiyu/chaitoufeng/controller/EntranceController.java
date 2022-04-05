package com.weiyu.chaitoufeng.controller;


import com.weiyu.chaitoufeng.common.logging.BusinessType;
import com.weiyu.chaitoufeng.common.logging.Logging;
import com.weiyu.chaitoufeng.common.result.Result;
import com.weiyu.chaitoufeng.common.tools.SecurityUtil;
import com.weiyu.chaitoufeng.common.tools.SequenceUtil;
import com.weiyu.chaitoufeng.controller.base.BaseController;
import com.weiyu.chaitoufeng.domain.system.SysUser;
import com.weiyu.chaitoufeng.secure.session.SecureSessionService;
import com.weiyu.chaitoufeng.service.system.ISysRoleService;
import com.weiyu.chaitoufeng.service.system.ISysUserService;
import com.wf.captcha.utils.CaptchaUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
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
    ISysUserService userService;

    @Resource
    ISysRoleService roleService;

    @Resource
    private SessionRegistry sessionRegistry;

    @Resource
    PasswordEncoder passwordEncoder;


    @GetMapping("/")
    public String toIndex(){
        return "redirect:/index";
    }
    @GetMapping("index")
    public String index(Model model) {
        SysUser sysUser = (SysUser) SecurityUtil.currentUser();
        if (sysUser != null && sysUser.getIsAdmin()){
            return "redirect:admin";
        }
        model.addAttribute("active","index");
        return "home/index";
    }
    /**
     * Describe: 获取登录视图
     */
    @GetMapping("login")
    public String login(HttpServletRequest request) {
        if (SecurityUtil.isAuthentication()) {
            SecureSessionService.expiredSession(request, sessionRegistry);

            // 用户已登录重定向指定界面
            SysUser sysUser = (SysUser) SecurityUtil.currentUser();
            if (sysUser != null && sysUser.getIsAdmin()){
                return "redirect:admin";
            }
            return "redirect:index";
        }
        return "login";
    }

    //获取admin视图
    @GetMapping("admin")
    @Logging(title = "主页", describe = "返回 Admin 主页视图", type = BusinessType.ADD)
    public String admin() {
        SysUser sysUser = (SysUser) SecurityUtil.currentUser();
        if (sysUser != null && sysUser.getIsAdmin()){
            return "admin";
        }
        return "redirect:index";
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

        if (userService.isRegister(username)){
            return Result.failure("用户名已存在");
        }

        //保存用户
        SysUser user = new SysUser(
                SequenceUtil.makeStringId(),
                username,
                username,
                passwordEncoder.encode(password),
                "1", "1");
        user.setCreateTime(LocalDateTime.now());
        boolean userSave = userService.save(user);

        //保存注册用户的，用户角色
        boolean userRoleSave = userService.saveRegisterUserRole(user.getUserId(),
                roleService.getRoleIdByRoleCode("home"));

        return Result.decide(userSave&&userRoleSave,"注册成功","注册失败");
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
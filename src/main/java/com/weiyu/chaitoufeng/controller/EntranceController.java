package com.weiyu.chaitoufeng.controller;


import cn.hutool.core.date.DateField;
import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.weiyu.chaitoufeng.common.logging.BusinessType;
import com.weiyu.chaitoufeng.common.logging.Logging;
import com.weiyu.chaitoufeng.common.result.Result;
import com.weiyu.chaitoufeng.common.tools.SecurityUtil;
import com.weiyu.chaitoufeng.common.tools.SequenceUtil;
import com.weiyu.chaitoufeng.controller.base.BaseController;
import com.weiyu.chaitoufeng.domain.home.HomeCollect;
import com.weiyu.chaitoufeng.domain.home.HomeLove;
import com.weiyu.chaitoufeng.domain.home.HomeReview;
import com.weiyu.chaitoufeng.domain.system.SysUser;
import com.weiyu.chaitoufeng.secure.session.SecureSessionService;
import com.weiyu.chaitoufeng.service.home.HomeCollectService;
import com.weiyu.chaitoufeng.service.home.HomeLoveService;
import com.weiyu.chaitoufeng.service.home.HomeReviewService;
import com.weiyu.chaitoufeng.service.system.ISysLogService;
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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.List;

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
    ISysLogService sysLogService;

    @Resource
    ISysRoleService roleService;

    @Resource
    private SessionRegistry sessionRegistry;

    @Resource
    PasswordEncoder passwordEncoder;

    @Resource
    HomeCollectService collectService;

    @Resource
    HomeLoveService loveService;

    @Resource
    HomeReviewService reviewService;


    @GetMapping("/")
    public String toIndex() {
        return "redirect:/index";
    }

    @GetMapping("index")
    public String index(Model model) {
        SysUser sysUser = (SysUser) SecurityUtil.currentUser();
        if (sysUser != null && sysUser.getIsAdmin()) {
            return "redirect:admin";
        }
        if (SecurityUtil.isAuthentication()) {
            QueryWrapper<HomeCollect> collectQueryWrapper = new QueryWrapper<>();
            collectQueryWrapper.eq("user_id", ((SysUser) SecurityUtil.currentUser()).getUserId());
            int collectNum = (int) collectService.count(collectQueryWrapper);

            QueryWrapper<HomeLove> loveQueryWrapper = new QueryWrapper<>();
            loveQueryWrapper.eq("user_id", ((SysUser) SecurityUtil.currentUser()).getUserId());
            int loveNum = (int) loveService.count(loveQueryWrapper);

            QueryWrapper<HomeReview> reviewQueryWrapper = new QueryWrapper<>();
            reviewQueryWrapper.eq("review_user_id", ((SysUser) SecurityUtil.currentUser()).getUserId());
            int reviewNum = (int) reviewService.count(reviewQueryWrapper);

            model.addAttribute("collectNum", collectNum);
            model.addAttribute("loveNum", loveNum);
            model.addAttribute("reviewNum", reviewNum);
        }
        model.addAttribute("active", "index");
        return "index";
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
            if (sysUser != null && sysUser.getIsAdmin()) {
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
        if (sysUser != null && sysUser.getIsAdmin()) {
            return "admin";
        }
        return "redirect:index";
    }

    @PostMapping("register")
    @ResponseBody
    public Result register(@RequestParam("username") String username, @RequestParam("password") String password, @RequestParam("captcha") String captcha, HttpServletRequest request) {
        if (!CaptchaUtil.ver(captcha, request)) {
            return Result.failure("验证码错误");
        }

        if (userService.isRegister(username)) {
            return Result.failure("用户名已存在");
        }

        //保存用户
        SysUser user = new SysUser(SequenceUtil.makeStringId(), username, username, passwordEncoder.encode(password), "1", "1");
        user.setCreateTime(LocalDateTime.now());
        boolean userSave = userService.save(user);

        //保存注册用户的，用户角色
        boolean userRoleSave = userService.saveRegisterUserRole(user.getUserId(), roleService.getRoleIdByRoleCode("home"));

        return Result.decide(userSave && userRoleSave, "注册成功", "注册失败");
    }


    //获取控制台主页视图 console
    @GetMapping("console")
    public ModelAndView home(Model model) throws ParseException {
        SysUser sysUser = (SysUser) SecurityUtil.currentUser();

        int thisDay = sysLogService.count(DateUtil.yesterday().toString());
        int thisWeekend = sysLogService.count(DateUtil.lastWeek().toString());
        int thisMouth = sysLogService.count(DateUtil.lastMonth().toString());
        int thisSeason = sysLogService.count(DateUtil.offset(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(DateUtil.now()), DateField.MONTH, -4).toString());

        QueryWrapper<HomeReview> reviewQueryWrapper = new QueryWrapper<>();
        reviewQueryWrapper.orderByDesc("create_time");
        List<HomeReview> reviews = reviewService.getActiveList();

        model.addAttribute("logs", sysLogService.selectTopLoginLog(sysUser.getUsername()));
        model.addAttribute("thisDay", thisDay);
        model.addAttribute("thisWeekend", thisWeekend);
        model.addAttribute("thisMouth", thisMouth);
        model.addAttribute("thisSeason", thisSeason);
        model.addAttribute("reviews", reviews);
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
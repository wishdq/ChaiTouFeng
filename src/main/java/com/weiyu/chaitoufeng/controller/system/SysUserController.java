package com.weiyu.chaitoufeng.controller.system;

import com.github.pagehelper.PageInfo;
import com.weiyu.chaitoufeng.common.constant.ControllerConstant;
import com.weiyu.chaitoufeng.common.logging.BusinessType;
import com.weiyu.chaitoufeng.common.logging.Logging;
import com.weiyu.chaitoufeng.common.result.Result;
import com.weiyu.chaitoufeng.common.tools.SecurityUtil;
import com.weiyu.chaitoufeng.common.tools.SequenceUtil;
import com.weiyu.chaitoufeng.controller.base.BaseController;
import com.weiyu.chaitoufeng.domain.build.EditPassword;
import com.weiyu.chaitoufeng.domain.build.PageDomain;
import com.weiyu.chaitoufeng.domain.build.ResultTable;
import com.weiyu.chaitoufeng.domain.system.SysMenu;
import com.weiyu.chaitoufeng.domain.system.SysRole;
import com.weiyu.chaitoufeng.domain.system.SysUser;
import com.weiyu.chaitoufeng.service.system.ISysLogService;
import com.weiyu.chaitoufeng.service.system.ISysRoleService;
import com.weiyu.chaitoufeng.service.system.ISysUserService;
import org.apache.logging.log4j.util.Strings;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

/**
 * Description: 用户控制器
 * Since: 2022-03-13 11:02
 * Author: wish_dq
 */
@RestController
@RequestMapping(ControllerConstant.API_SYSTEM_PREFIX + "user")
public class SysUserController extends BaseController {

    //基础路径
    private static final String MODULE_PATH = "admin/system/user/";

    //用户模块服务
    @Resource
    private ISysUserService sysUserService;

    //角色模块服务
    @Resource
    private ISysRoleService sysRoleService;

    //日志模块服务
    @Resource
    private ISysLogService sysLogService;

    /**
     * Describe: 获取用户列表视图
     */
    @GetMapping("main")
    @PreAuthorize("hasPermission('/system/user/main','sys:user:main')")
    public ModelAndView main() {
        return jumpPage(MODULE_PATH + "main");
    }

    /**
     * Describe: 获取用户列表数据
     */
    @GetMapping("data")
    @PreAuthorize("hasPermission('/system/user/data','sys:user:data')")
    @Logging(title = "查询用户", describe = "查询用户", type = BusinessType.QUERY)
    public ResultTable data(PageDomain pageDomain, SysUser param) {
        PageInfo<SysUser> pageInfo = sysUserService.page(param, pageDomain);
        return pageTable(pageInfo.getList(), pageInfo.getTotal());
    }

    /**
     * Describe: 用户新增视图
     */
    @GetMapping("add")
    @PreAuthorize("hasPermission('/system/user/add','sys:user:add')")
    public ModelAndView add(Model model) {
        List<SysRole> sysRoles = sysRoleService.list(null);
        model.addAttribute("sysRoles",sysRoles);
        return jumpPage(MODULE_PATH + "add");
    }

    /**
     * Describe: 用户新增接口
     */
    @PostMapping("save")
    @PreAuthorize("hasPermission('/system/user/add','sys:user:add')")
    @Logging(title = "新增用户", describe = "新增用户", type = BusinessType.ADD)
    public Result save(@RequestBody SysUser sysUser) {
        sysUser.setLogin("0");
        sysUser.setEnable("1");
        sysUser.setStatus("1");
        sysUser.setUserId(SequenceUtil.makeStringId());
        sysUser.setPassword(new BCryptPasswordEncoder().encode(sysUser.getPassword()));
        sysUser.setCreateTime(LocalDateTime.now());
        Boolean resultUser = sysUserService.save(sysUser);
        Boolean resultUserRole = sysUserService.saveUserRole(sysUser.getUserId(), Arrays.asList(sysUser.getRoleIds().split(",")));
        return decide(resultUser && resultUserRole);
    }

    /**
     * Describe: 用户修改视图
     */
    @GetMapping("edit")
    @PreAuthorize("hasPermission('/system/user/edit','sys:user:edit')")
    public ModelAndView edit(Model model, String userId) {
        model.addAttribute("sysRoles", sysUserService.getUserRole(userId));
        model.addAttribute("sysUser", sysUserService.getById(userId));
        return jumpPage(MODULE_PATH + "edit");
    }

    /**
     * Describe: 用户密码修改视图
     */
    @GetMapping("editPassword")
    @PreAuthorize("hasPermission('/system/user/editPassword','sys:user:editPassword')")
    public ModelAndView editPasswordView(Model model, String userId) {
        model.addAttribute("userId",userId);
        return jumpPage(MODULE_PATH + "password");
    }

    /**
     * Describe: 用户密码修改接口
     */
    @PutMapping("editPassword")
    public Result editPassword(@RequestBody EditPassword editPassword) {
        String userId = editPassword.getUserId();
        String newPassword = editPassword.getNewPassword();
        String confirmPassword = editPassword.getConfirmPassword();
        if (Strings.isBlank(confirmPassword) || Strings.isBlank(newPassword)) {
            return failure("输入不能为空");
        }
        if (!newPassword.equals(confirmPassword)) {
            return failure("两次密码输入不一致");
        }
        SysUser sysUser = new SysUser();
        sysUser.setUserId(userId);
        sysUser.setPassword(new BCryptPasswordEncoder().encode(newPassword));
        boolean result = sysUserService.update(sysUser);
        return decide(result, "修改成功", "修改失败");
    }

    /**
     * Describe: 用户修改接口
     */
    @PutMapping("update")
    @PreAuthorize("hasPermission('/system/user/edit','sys:user:edit')")
    @Logging(title = "修改用户", describe = "修改用户", type = BusinessType.EDIT)
    public Result update(@RequestBody SysUser sysUser) {
        sysUser.setUpdateTime(LocalDateTime.now());
        sysUserService.saveUserRole(sysUser.getUserId(), Arrays.asList(sysUser.getRoleIds().split(",")));
        boolean result = sysUserService.update(sysUser);
        return decide(result);
    }

    /**
     * Describe: 头像修改接口
     */
    @PutMapping("updateAvatar")
    @Logging(title = "修改头像", describe = "修改头像", type = BusinessType.EDIT)
    public Result updateAvatar(@RequestBody SysUser sysUser) {
        String userId = ((SysUser) SecurityUtil.currentUser()).getUserId();
        sysUser.setUserId(userId);
        boolean result = sysUserService.update(sysUser);
        return decide(result);
    }

    /**
     * Describe: 用户批量删除接口
     */
    @DeleteMapping("batchRemove/{ids}")
    @PreAuthorize("hasPermission('/system/user/remove','sys:user:remove')")
    @Logging(title = "删除用户", describe = "删除用户", type = BusinessType.REMOVE)
    public Result batchRemove(@PathVariable String ids) {
        boolean result = sysUserService.batchRemove(ids.split(","));
        return decide(result);
    }

    /**
     * Describe: 用户删除接口
     */
    @Transactional(rollbackFor = Exception.class)
    @DeleteMapping("remove/{id}")
    @PreAuthorize("hasPermission('/system/user/remove','sys:user:remove')")
    @Logging(title = "删除用户", describe = "删除用户", type = BusinessType.REMOVE)
    public Result remove(@PathVariable String id) {
        boolean result = sysUserService.remove(id);
        return decide(result);
    }

    /**
     * Describe: 根据 username 获取菜单数据
     */
    @GetMapping("menu")
    public List<SysMenu> getUserMenu() {
        SysUser sysUser = (SysUser) SecurityUtil.currentUser();
        List<SysMenu> menus = sysUserService.getUserMenu(sysUser.getUsername());
        return sysUserService.toUserMenu(menus, "0");
    }

    /**
     * Describe: 根据 userId 开启用户
     */
    @PutMapping("enable")
    public Result enable(@RequestBody SysUser sysUser) {
        sysUser.setEnable("1");
        boolean result = sysUserService.update(sysUser);
        return decide(result);
    }

    /**
     * Describe: 根据 userId 禁用用户
     */
    @PutMapping("disable")
    public Result disable(@RequestBody SysUser sysUser) {
        sysUser.setEnable("0");
        boolean result = sysUserService.update(sysUser);
        return decide(result);
    }

    /**
     * Describe: 个人资料
     */
    @GetMapping("center")
    public ModelAndView center(Model model) {
        SysUser sysUser = (SysUser) SecurityUtil.currentUser();
        model.addAttribute("userInfo", sysUserService.getById(sysUser.getUserId()));
        //model.addAttribute("logs", sysLogService.selectTopLoginLog(sysUser.getUsername()));
        return jumpPage(MODULE_PATH + "center");
    }

    /**
     * Describe: 用户修改接口
     */
    @PutMapping("updateInfo")
    public Result updateInfo(@RequestBody SysUser sysUser) {
        boolean result = sysUserService.update(sysUser);
        return decide(result);
    }


    /**
     * Describe: 更换头像
     */
    @GetMapping("profile/{id}")
    public ModelAndView profile(Model model, @PathVariable("id") String userId) {
        model.addAttribute("userId", userId);
        return jumpPage(MODULE_PATH + "profile");
    }
}

package com.weiyu.chaitoufeng.controller.site;

import com.github.pagehelper.PageInfo;
import com.weiyu.chaitoufeng.common.constant.ControllerConstant;
import com.weiyu.chaitoufeng.common.result.Result;
import com.weiyu.chaitoufeng.common.tools.SequenceUtil;
import com.weiyu.chaitoufeng.controller.base.BaseController;
import com.weiyu.chaitoufeng.domain.build.PageDomain;
import com.weiyu.chaitoufeng.domain.build.ResultTable;
import com.weiyu.chaitoufeng.domain.system.SysUser;
import com.weiyu.chaitoufeng.service.system.ISysRoleService;
import com.weiyu.chaitoufeng.service.system.ISysUserService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.Arrays;

/**
 * Description:
 * Since: 2022-03-31 13:34
 * Author: wish_dq
 */
@RestController
@RequestMapping(ControllerConstant.API_SITE_PREFIX + "user")
public class SiteUserController extends BaseController {

    private final String MODULE_PATH = "admin/site/user/";

    @Resource
    ISysUserService siteUserService;

    @Resource
    PasswordEncoder passwordEncoder;

    @Resource
    ISysRoleService roleService;

    /**
     * 前端用户视图
     */
    @GetMapping("main")
    @PreAuthorize("hasPermission('/site/user/main','site:user:main')")
    public ModelAndView main() {
        return jumpPage(MODULE_PATH + "main");
    }

    @GetMapping("add")
    @PreAuthorize("hasPermission('/site/user/add','site:user:add')")
    public ModelAndView add() {
        return jumpPage(MODULE_PATH + "add");
    }

    @GetMapping("edit")
    @PreAuthorize("hasPermission('/site/user/edit','site:user:edit')")
    public ModelAndView edit(Model model, String userId) {
        SysUser user = siteUserService.getById(userId);
        model.addAttribute("user", user);
        return jumpPage(MODULE_PATH + "edit");
    }

    /**
     * 前端数据部分
     */

    @GetMapping("data")
    @PreAuthorize("hasPermission('/site/user/data','site:user:data')")
    public ResultTable data(PageDomain pageDomain, SysUser param) {
        PageInfo<SysUser> pageInfo = siteUserService.getHomePage(pageDomain, param);
        return pageTable(pageInfo.getList(), pageInfo.getTotal());
    }


    @PostMapping("save")
    @PreAuthorize("hasPermission('/site/user/add','site:user:add')")
    public Result save(@RequestBody SysUser user) {
        user.setUserId(SequenceUtil.makeStringId());
        if ("".equals(user.getRealName())){
            user.setRealName(user.getUsername());
        }
        user.setEnable("1");
        user.setCreateTime(LocalDateTime.now());
        boolean userResult = siteUserService.save(user);
        boolean userRoleResult = siteUserService.saveRegisterUserRole(user.getUserId(),
                roleService.getRoleIdByRoleCode("home"));
        return Result.decide(userResult&&userRoleResult,"添加用户成功","添加用户失败");
    }

    @PutMapping("isEnable/{status}")
    public Result enable(@PathVariable String status, @RequestBody SysUser homeUser) {
        if ("enable".equals(status)) {
            homeUser.setEnable("1");
        } else if ("disable".equals(status)) {
            homeUser.setEnable("0");
        } else {
            return Result.failure("输入状态错误");
        }
        boolean result = siteUserService.updateById(homeUser);
        return decide(result);
    }

    @PutMapping("update")
    @PreAuthorize("hasPermission('/site/user/edit','site:user:edit')")
    public Result update(@RequestBody SysUser user) {
        if (!"".equals(user.getPassword())) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        }else {
            user.setPassword(null);
        }
        boolean result = siteUserService.updateById(user);
        return decide(result);
    }

    @DeleteMapping("remove/{id}")
    @PreAuthorize("hasPermission('/site/user/remove','site:user:remove')")
    public Result remove(@PathVariable("id") String id) {
        Boolean result = siteUserService.removeById(id);
        return decide(result);
    }

    @DeleteMapping("batchRemove/{ids}")
    @PreAuthorize("hasPermission('/site/user/remove','site:user:remove')")
    public Result batchRemove(@PathVariable String ids) {
        boolean result = siteUserService.removeBatchByIds(Arrays.asList(ids.split(",")));
        return decide(result);
    }
}

package com.weiyu.chaitoufeng.controller.system;

import com.github.pagehelper.PageInfo;
import com.weiyu.chaitoufeng.common.constant.ControllerConstant;
import com.weiyu.chaitoufeng.common.result.Result;
import com.weiyu.chaitoufeng.common.tools.SequenceUtil;
import com.weiyu.chaitoufeng.controller.base.BaseController;
import com.weiyu.chaitoufeng.domain.build.PageDomain;
import com.weiyu.chaitoufeng.domain.build.ResultTable;
import com.weiyu.chaitoufeng.domain.build.ResultTree;
import com.weiyu.chaitoufeng.domain.system.SysRole;
import com.weiyu.chaitoufeng.service.system.ISysRoleService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.Arrays;

/**
 * Description: 角 色 控 制 器
 * Since: 2022-03-18 21:56
 * Author: wish_dq
 */
@RestController
@RequestMapping(ControllerConstant.API_SYSTEM_PREFIX + "role")
public class SysRoleController extends BaseController {

    //基础路径
    private static final String MODULE_PATH = "admin/system/role/";

    //角色模块服务
    @Resource
    private ISysRoleService sysRoleService;

    /**
     * Describe: 获取角色列表视图
     */
    @GetMapping("main")
    @PreAuthorize("hasPermission('/system/role/main','sys:role:main')")
    public ModelAndView main() {
        return jumpPage(MODULE_PATH + "main");
    }

    /**
     * Describe: 获取角色列表数据
     */
    @GetMapping("data")
    @PreAuthorize("hasPermission('/system/role/data','sys:role:data')")
    public ResultTable data(PageDomain pageDomain, SysRole param) {
        PageInfo<SysRole> pageInfo = sysRoleService.page(param, pageDomain);
        return pageTable(pageInfo.getList(), pageInfo.getTotal());
    }

    /**
     * Describe: 获取角色新增视图
     */
    @GetMapping("add")
    @PreAuthorize("hasPermission('/system/role/add','sys:role:add')")
    public ModelAndView add() {
        return jumpPage(MODULE_PATH + "add");
    }

    /**
     * Describe: 保存角色信息
     */
    @PostMapping("save")
    @PreAuthorize("hasPermission('/system/role/add','sys:role:add')")
    public Result save(@RequestBody SysRole sysRole) {
        sysRole.setRoleId(SequenceUtil.makeStringId());
        boolean result = sysRoleService.save(sysRole);
        return decide(result);
    }

    /**
     * Describe: 获取角色修改视图
     */
    @GetMapping("edit")
    @PreAuthorize("hasPermission('/system/role/edit','sys:role:edit')")
    public ModelAndView edit(ModelAndView modelAndView, String roleId) {
        modelAndView.addObject("sysRole", sysRoleService.getById(roleId));
        modelAndView.setViewName(MODULE_PATH + "edit");
        return modelAndView;
    }

    /**
     * Describe: 修改角色信息
     */
    @PutMapping("update")
    @PreAuthorize("hasPermission('/system/role/edit','sys:role:edit')")
    public Result update(@RequestBody SysRole sysRole) {
        boolean result = sysRoleService.update(sysRole);
        return decide(result);
    }

    /**
     * Describe: 获取角色授权视图
     */
    @GetMapping("power")
    @PreAuthorize("hasPermission('/system/role/power','sys:role:power')")
    public ModelAndView power(Model model, String roleId) {
        model.addAttribute("roleId", roleId);
        return jumpPage(MODULE_PATH + "power");
    }

    /**
     * Describe: 保存角色权限
     */
    @PutMapping("saveRolePower")
    @PreAuthorize("hasPermission('/system/role/power','sys:role:power')")
    public Result saveRolePower(String roleId, String powerIds) {
        boolean result = sysRoleService.saveRolePower(roleId, Arrays.asList(powerIds.split(",")));
        return decide(result);
    }

    /**
     * Describe: 获取角色权限
     */
    @GetMapping("getRolePower")
    @PreAuthorize("hasPermission('/system/role/power','sys:role:power')")
    public ResultTree getRolePower(String roleId) {
        return dataTree(sysRoleService.getRolePower(roleId));
    }

    /**
     * Describe: 用户删除接口
     */
    @DeleteMapping("remove/{id}")
    @PreAuthorize("hasPermission('/system/role/remove','sys:role:remove')")
    public Result remove(@PathVariable String id) {
        boolean result = sysRoleService.remove(id);
        return decide(result);
    }

    /**
     * Describe: 用户批量删除接口
     */
    @DeleteMapping("batchRemove/{ids}")
    @PreAuthorize("hasPermission('/system/role/remove','sys:role:remove')")
    public Result batchRemove(@PathVariable String ids) {
        boolean result = sysRoleService.batchRemove(ids.split(","));
        return decide(result);
    }

    /**
     * Describe: 根据 Id 启用角色
     */
    @PutMapping("enable")
    public Result enable(@RequestBody SysRole sysRole) {
        sysRole.setEnable("0");
        boolean result = sysRoleService.update(sysRole);
        return decide(result);
    }

    /**
     * Describe: 根据 Id 禁用角色
     */
    @PutMapping("disable")
    public Result disable(@RequestBody SysRole sysRole) {
        sysRole.setEnable("1");
        boolean result = sysRoleService.update(sysRole);
        return decide(result);
    }

}


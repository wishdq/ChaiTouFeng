package com.weiyu.chaitoufeng.controller.system;

import com.weiyu.chaitoufeng.common.constant.ControllerConstant;
import com.weiyu.chaitoufeng.common.result.Result;
import com.weiyu.chaitoufeng.common.tools.SequenceUtil;
import com.weiyu.chaitoufeng.controller.base.BaseController;
import com.weiyu.chaitoufeng.domain.build.ResultTable;
import com.weiyu.chaitoufeng.domain.build.ResultTree;
import com.weiyu.chaitoufeng.domain.system.SysDept;
import com.weiyu.chaitoufeng.service.system.ISysDeptService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Description: 部门管理
 * Since: 2022-03-18 21:16
 * Author: wish_dq
 */
@RestController
@RequestMapping(ControllerConstant.API_SYSTEM_PREFIX + "dept")
public class SysDeptController extends BaseController {

    //基础路径
    private static final String MODULE_PATH = "admin/system/dept/";

    //部门模块服务
    @Resource
    private ISysDeptService sysDeptService;

    /**
     * Describe: 获取部门列表视图
     */
    @GetMapping("main")
    @PreAuthorize("hasPermission('/system/dept/main','sys:dept:main')")
    public ModelAndView main() {
        return jumpPage(MODULE_PATH + "main");
    }

    /**
     * Describe: 获取父级部门选择数据
     */
    @GetMapping("selectParent")
    public ResultTree selectParent(SysDept sysDept) {
        List<SysDept> list = sysDeptService.list(sysDept);
        SysDept baseDept = new SysDept();
        baseDept.setDeptName("顶级部门");
        baseDept.setDeptId("0");
        baseDept.setParentId("-1");
        list.add(baseDept);
        return dataTree(list);
    }
    /**
     * Describe: 获取部门列表数据
     */
    @GetMapping("data")
    @PreAuthorize("hasPermission('/system/dept/data','sys:dept:data')")
    public ResultTable data(SysDept param) {
        List<SysDept> data = sysDeptService.list(param);
        return dataTable(data);
    }

    /**
     * Describe: 获取部门树状数据结构
     */
    @GetMapping("tree")
    public ResultTree tree(SysDept param) {
        List<SysDept> data = sysDeptService.list(param);
        return dataTree(data);
    }

    /**
     * Describe: 获取部门新增视图
     */
    @GetMapping("add")
    @PreAuthorize("hasPermission('/system/dept/add','sys:dept:add')")
    public ModelAndView add() {
        return jumpPage(MODULE_PATH + "add");
    }

    /**
     * Describe: 保存部门信息
     */
    @PostMapping("save")
    @PreAuthorize("hasPermission('/system/dept/add','sys:dept:add')")
    public Result save(@RequestBody SysDept sysDept) {
        sysDept.setDeptId(SequenceUtil.makeStringId());
        sysDept.setUpdateTime(LocalDateTime.now());
        boolean result = sysDeptService.save(sysDept);
        return decide(result);
    }

    /**
     * Describe: 获取部门修改视图
     */
    @GetMapping("edit")
    @PreAuthorize("hasPermission('/system/dept/edit','sys:dept:edit')")
    public ModelAndView edit(ModelAndView modelAndView, String deptId) {
        modelAndView.addObject("sysDept", sysDeptService.getById(deptId));
        modelAndView.setViewName(MODULE_PATH + "edit");
        return modelAndView;
    }

    /**
     * Describe: 修改部门信息
     */
    @PutMapping("update")
    @PreAuthorize("hasPermission('/system/dept/edit','sys:dept:edit')")
    public Result update(@RequestBody SysDept sysDept) {
        sysDept.setUpdateTime(LocalDateTime.now());
        boolean result = sysDeptService.update(sysDept);
        return decide(result);
    }

    /**
     * Describe: 部门删除接口
     */
    @DeleteMapping("remove/{id}")
    @PreAuthorize("hasPermission('/system/dept/remove','sys:dept:remove')")
    public Result remove(@PathVariable String id) {
        if (sysDeptService.selectByParentId(id).size() > 0) {
            return failure("请先删除下级部门");
        }
        boolean result = sysDeptService.remove(id);
        return decide(result);
    }

    /**
     * Describe: 部门批量删除接口
     */
    @DeleteMapping("batchRemove/{ids}")
    @PreAuthorize("hasPermission('/system/dept/remove','sys:dept:remove')")
    public Result batchRemove(@PathVariable String ids) {
        boolean result = sysDeptService.batchRemove(ids.split(","));
        return decide(result);
    }

    /**
     * Describe: 根据 Id 启用部门
     */
    @PutMapping("enable")
    @PreAuthorize("hasPermission('/system/dept/edit','sys:dept:edit')")
    public Result enable(@RequestBody SysDept sysDept) {
        sysDept.setStatus("0");
        boolean result = sysDeptService.update(sysDept);
        return decide(result);
    }

    /**
     * Describe: 根据 Id 禁用部门
     */
    @PutMapping("disable")
    @PreAuthorize("hasPermission('/system/dept/edit','sys:dept:edit')")
    public Result disable(@RequestBody SysDept sysDept) {
        sysDept.setStatus("1");
        boolean result = sysDeptService.update(sysDept);
        return decide(result);
    }
}

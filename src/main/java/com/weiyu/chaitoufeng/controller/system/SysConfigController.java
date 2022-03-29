package com.weiyu.chaitoufeng.controller.system;

import com.github.pagehelper.PageInfo;
import com.weiyu.chaitoufeng.common.constant.ControllerConstant;
import com.weiyu.chaitoufeng.common.result.Result;
import com.weiyu.chaitoufeng.common.tools.SequenceUtil;
import com.weiyu.chaitoufeng.controller.base.BaseController;
import com.weiyu.chaitoufeng.domain.build.PageDomain;
import com.weiyu.chaitoufeng.domain.build.ResultTable;
import com.weiyu.chaitoufeng.domain.build.ResultTree;
import com.weiyu.chaitoufeng.domain.system.SysConfig;
import com.weiyu.chaitoufeng.domain.system.SysConfigGroup;
import com.weiyu.chaitoufeng.service.system.ISysConfigService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.List;

/**
 * Description: 系 统 配 置 控 制 器
 * Since: 2022-03-23 20:31
 * Author: wish_dq
 */
@RestController
@RequestMapping(ControllerConstant.API_SYSTEM_PREFIX + "config")
public class SysConfigController extends BaseController {

    private final String MODULE_PATH = "system/config/";

    @Resource
    private ISysConfigService sysConfigService;

    /**
     * Describe: 数据字典列表视图
     */
    @GetMapping("main")
    @PreAuthorize("hasPermission('/system/config/main','sys:config:main')")
    public ModelAndView main() {
        return jumpPage(MODULE_PATH + "main");
    }

    /**
     * Describe: 数据字典列表数据
     */
    @GetMapping("data")
    @PreAuthorize("hasPermission('/system/config/data','sys:config:data')")
    public ResultTable data(PageDomain pageDomain, SysConfig param) {
        PageInfo<SysConfig> pageInfo = sysConfigService.page(pageDomain,param);
        return pageTable(pageInfo.getList(), pageInfo.getTotal());
    }

    /**
     * Describe: 数据字典类型新增视图
     */
    @GetMapping("add")
    @PreAuthorize("hasPermission('/system/config/add','sys:config:add')")
    public ModelAndView add(Model model) {
        List<SysConfigGroup> configGroups = sysConfigService.groupData();
        model.addAttribute("configGroups",configGroups);
        return jumpPage(MODULE_PATH + "add");
    }

    /**
     * Describe: 新增字典类型接口
     */
    @PostMapping("save")
    @PreAuthorize("hasPermission('/system/config/add','sys:config:add')")
    public Result save(@RequestBody SysConfig sysConfig) {
        sysConfig.setConfigId(SequenceUtil.makeStringId());
        boolean result = sysConfigService.save(sysConfig);
        return decide(result);
    }

    /**
     * Describe: 数据字典类型修改视图
     */
    @GetMapping("edit")
    @PreAuthorize("hasPermission('/system/config/edit','sys:config:edit')")
    public ModelAndView edit(Model model, String configId) {
        model.addAttribute("sysConfig", sysConfigService.getById(configId));
        List<SysConfigGroup> configGroups = sysConfigService.groupData();
        model.addAttribute("configGroups",configGroups);
        return jumpPage(MODULE_PATH + "edit");
    }

    /**
     * Describe: 数据字典类型修改视图
     */
    @PutMapping("update")
    @PreAuthorize("hasPermission('/system/config/edit','sys:config:edit')")
    public Result update(@RequestBody SysConfig sysConfig) {
        boolean result = sysConfigService.updateById(sysConfig);
        return decide(result);
    }

    /**
     * Describe: 根据 configId 开启系统配置
     */
    @PutMapping("status/{isEnable}")
    public Result enable(@PathVariable String isEnable, @RequestBody SysConfig sysConfig) {
        if("enable".equals(isEnable)) {
            sysConfig.setEnable("1");
        }else if ("disable".equals(isEnable)){
            sysConfig.setEnable("0");
        }
        boolean result = sysConfigService.updateById(sysConfig);
        return decide(result);
    }

    /**
     * Describe: 数据字典删除
     */
    @DeleteMapping("remove/{id}")
    @PreAuthorize("hasPermission('/system/config/remove','sys:config:remove')")
    public Result remove(@PathVariable("id") String id) {
        Boolean result = sysConfigService.remove(id);
        return decide(result);
    }

    /**
     * Describe: 系统配置批量删除接口
     */
    @DeleteMapping("batchRemove/{ids}")
    @PreAuthorize("hasPermission('/system/config/remove','sys:config:remove')")
    public Result batchRemove(@PathVariable String ids) {
        boolean result = sysConfigService.batchRemove(ids.split(","));
        return decide(result);
    }


    /**
     * Describe: 获取配置组树状数据结构
     */
    @GetMapping("tree")
    public ResultTree tree() {
        List<SysConfigGroup> data = sysConfigService.tree();
        return dataTree(data);
    }

    @GetMapping("group")
    @PreAuthorize("hasPermission('/system/config/group','sys:config:group')")
    public ModelAndView group(){
        return jumpPage(MODULE_PATH + "group");
    }

    @GetMapping("group/data")
    @PreAuthorize("hasPermission('/system/config/group/data','sys:config:group:data')")
    public ResultTable groupDate(PageDomain pageDomain){
        List<SysConfigGroup> list = sysConfigService.groupData();
        PageInfo<SysConfigGroup> pageInfo = new PageInfo<>(list);
        return pageTable(pageInfo.getList(),pageInfo.getTotal());
    }

    @PutMapping("group/{isEnable}")
    public Result groupEnable(@PathVariable("isEnable") String isEnable,@RequestBody SysConfigGroup configGroup){
        if("enable".equals(isEnable)) {
            configGroup.setEnable("1");
        }else if ("disable".equals(isEnable)){
            configGroup.setEnable("0");
        }
        Boolean result = sysConfigService.updateGroup(configGroup);
        return decide(result);
    }

    @GetMapping("group/add")
    @PreAuthorize("hasPermission('/system/config/group/add','sys:config:group:add')")
    public ModelAndView groupAdd(){
        return jumpPage(MODULE_PATH + "groupAdd");
    }

    @PostMapping("group/save")
    public Result addConfigGroupSave(@RequestBody SysConfigGroup configGroup){
        configGroup.setConfigGroupId(SequenceUtil.makeStringId());
        configGroup.setGroupParentId("1");
        boolean result = sysConfigService.addGroup(configGroup);
        return decide(result);
    }

    @PostMapping("group/update")
    public Result groupUpdate(@RequestBody SysConfigGroup configGroup){
        Boolean result = sysConfigService.updateGroup(configGroup);
        return decide(result);
    }

    @DeleteMapping("group/remove/{id}")
    @PreAuthorize("hasPermission('/system/config/group/remove','sys:config:group:remove')")
    public Result groupDelete(@PathVariable String id){
        SysConfig sysConfig = new SysConfig();
        sysConfig.setConfigGroupId(id);
        if (sysConfigService.list(sysConfig).size() > 0) {
            return failure("请先删除配置组下设置");
        }
        Boolean result = sysConfigService.removeGroup(id);
        return decide(result);
    }

}


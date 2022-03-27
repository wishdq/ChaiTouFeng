package com.weiyu.chaitoufeng.controller.system;

import cn.hutool.core.map.MapUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.weiyu.chaitoufeng.common.constant.ConfigurationConstant;
import com.weiyu.chaitoufeng.common.constant.ControllerConstant;
import com.weiyu.chaitoufeng.common.result.Result;
import com.weiyu.chaitoufeng.common.tools.SequenceUtil;
import com.weiyu.chaitoufeng.controller.base.BaseController;
import com.weiyu.chaitoufeng.domain.SetupEvent;
import com.weiyu.chaitoufeng.domain.request.PageDomain;
import com.weiyu.chaitoufeng.domain.response.ResultTable;
import com.weiyu.chaitoufeng.domain.response.ResultTree;
import com.weiyu.chaitoufeng.domain.system.SysConfig;
import com.weiyu.chaitoufeng.domain.system.SysConfigGroup;
import com.weiyu.chaitoufeng.domain.system.SysDept;
import com.weiyu.chaitoufeng.domain.system.SysUser;
import com.weiyu.chaitoufeng.service.ISysConfigService;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.annotations.Param;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

/**
 * Description: 系 统 配 置 控 制 器
 * Since: 2022-03-23 20:31
 * Author: wish_dq
 */
@RestController
@RequestMapping(ControllerConstant.API_SYSTEM_PREFIX + "config")
public class SysConfigController extends BaseController implements ApplicationEventPublisherAware {

    private final String MODULE_PATH = "system/config/";

    @Resource
    private ISysConfigService sysConfigService;


    //@Resource
    //private SysConfig sysConfig ;

    private ApplicationEventPublisher applicationEventPublisher;


    /**
     * Describe: 数据字典列表视图
     * Param: ModelAndView
     * Return: ModelAndView
     */
    @GetMapping("main")
    @PreAuthorize("hasPermission('/system/config/main','sys:config:main')")
    public ModelAndView main() {
        return jumpPage(MODULE_PATH + "main");
    }
    /**
     * Describe: 数据字典列表数据
     * Param: sysConfig
     * Return: ResultTable
     */
    @GetMapping("data")
    @PreAuthorize("hasPermission('/system/config/data','sys:config:data')")
    public ResultTable data(PageDomain pageDomain, SysConfig param) {
        PageInfo<SysConfig> pageInfo = sysConfigService.page(pageDomain,param);
        return pageTable(pageInfo.getList(), pageInfo.getTotal());
    }



    /**
     * Describe: 数据字典类型新增视图
     * Param: sysConfig
     * Return: ModelAndView
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
     * Param: sysConfig
     * Return: ResultBean
     */
    @PostMapping("save")
    @PreAuthorize("hasPermission('/system/config/add','sys:config:add')")
    public Result save(@RequestBody SysConfig sysConfig) {
        sysConfig.setConfigId(SequenceUtil.makeStringId());
        //sysConfig.setConfigType("custom");
        boolean result = sysConfigService.save(sysConfig);
        return decide(result);
    }

    /**
     * Describe: 数据字典类型修改视图
     * Param: sysConfig
     * Return: ModelAndView
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
     * Param: sysConfig
     * Return: ModelAndView
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
    @ApiOperation(value = "开启用户登录")
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
     * Param: sysConfig
     * Return: ModelAndView
     */
    @DeleteMapping("remove/{id}")
    @PreAuthorize("hasPermission('/system/config/remove','sys:config:remove')")
    public Result remove(@PathVariable("id") String id) {
        Boolean result = sysConfigService.remove(id);
        return decide(result);
    }

    /**
     * Describe: 系统配置批量删除接口
     * Param: ids
     * Return: Result
     */
    @DeleteMapping("batchRemove/{ids}")
    @ApiOperation(value = "批量删除系统配置数据")
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

    //@GetMapping("group/edit")
    //public ModelAndView groupEdit(@RequestParam("configGroupId") String configGroupId,Model model){
    //    model.addAttribute("configGroup",sysConfigService.getGroupById(configGroupId));
    //    return jumpPage(MODULE_PATH+"groupEdit");
    //}

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
    //@GetMapping("main")
    //@PreAuthorize("hasPermission('/system/config/main','sys:config:main')")
    //public ModelAndView main(Model model) {
    //    SysConfig mailFromConfig = sysConfigService.getByCode(ConfigurationConstant.MAIN_FROM);
    //    SysConfig mailUserConfig = sysConfigService.getByCode(ConfigurationConstant.MAIN_USER);
    //    SysConfig mailPassConfig = sysConfigService.getByCode(ConfigurationConstant.MAIN_PASS);
    //    SysConfig mailHostConfig = sysConfigService.getByCode(ConfigurationConstant.MAIN_HOST);
    //    SysConfig mailPortConfig = sysConfigService.getByCode(ConfigurationConstant.MAIN_PORT);
    //    sysConfig.setMailFrom(mailFromConfig == null ? "" : mailFromConfig.getConfigValue());
    //    sysConfig.setMailUser(mailUserConfig == null ? "" : mailUserConfig.getConfigValue());
    //    sysConfig.setMailPass(mailPassConfig == null ? "" : mailPassConfig.getConfigValue());
    //    sysConfig.setMailHost(mailHostConfig == null ? "" : mailHostConfig.getConfigValue());
    //    sysConfig.setMailPort(mailPortConfig == null ? "" : mailPortConfig.getConfigValue());
    //
    //    SysConfig uploadKindConfig = sysConfigService.getByCode(ConfigurationConstant.UPLOAD_KIND);
    //    SysConfig uploadPathConfig = sysConfigService.getByCode(ConfigurationConstant.UPLOAD_PATH);
    //    sysConfig.setUploadKind(uploadKindConfig == null ? "" : uploadKindConfig.getConfigValue());
    //    sysConfig.setUploadPath(uploadPathConfig == null ? "" : uploadPathConfig.getConfigValue());
    //
    //    model.addAttribute("setup", sysConfig);
    //    return jumpPage(MODULE_PATH + "main");
    //}

    //@Transactional(rollbackFor = Exception.class)
    //@PutMapping("save")
    //@PreAuthorize("hasPermission('/system/config/add','sys:config:add')")
    //public Result save(@RequestBody SysConfig sysConfig) {
    //
    //    String from = sysConfig.getMailFrom();
    //    String user = sysConfig.getMailUser();
    //    String pass = sysConfig.getMailPass();
    //    String port = sysConfig.getMailPort();
    //    String host = sysConfig.getMailHost();
    //
    //    String uploadKind = sysConfig.getUploadKind();
    //    String uploadPath = sysConfig.getUploadPath();
    //
    //    updateSetup("邮箱来源", ConfigurationConstant.MAIN_FROM, from);
    //    updateSetup("邮箱用户", ConfigurationConstant.MAIN_USER, user);
    //    updateSetup("邮箱密码", ConfigurationConstant.MAIN_PASS, pass);
    //    updateSetup("邮箱端口", ConfigurationConstant.MAIN_PORT, port);
    //    updateSetup("邮箱主机", ConfigurationConstant.MAIN_HOST, host);
    //
    //    updateSetup("上传方式", ConfigurationConstant.UPLOAD_KIND, uploadKind);
    //    updateSetup("上传路径", ConfigurationConstant.UPLOAD_PATH, uploadPath);
    //
    //    HashMap<String, String> map = MapUtil.newHashMap(5);
    //    map.put(ConfigurationConstant.MAIN_FROM, from);
    //    map.put(ConfigurationConstant.MAIN_USER, user);
    //    map.put(ConfigurationConstant.MAIN_PASS, pass);
    //    map.put(ConfigurationConstant.MAIN_PORT, port);
    //    map.put(ConfigurationConstant.MAIN_HOST, host);
    //    SetupEvent setupEvent = new SetupEvent(this, map);
    //    applicationEventPublisher.publishEvent(setupEvent);
    //    return success("保存成功");
    //}

    private void updateSetup(String name, String code, String value) {
        SysConfig config = sysConfigService.getByCode(code);
        if (config != null) {
            config.setConfigValue(value);
            sysConfigService.updateById(config);
        } else {
            config = new SysConfig();
            config.setConfigId(SequenceUtil.makeStringId());
            config.setConfigName(name);
            config.setConfigCode(code);
            config.setConfigType("system");
            config.setConfigValue(value);
            sysConfigService.save(config);
        }
    }

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }

}


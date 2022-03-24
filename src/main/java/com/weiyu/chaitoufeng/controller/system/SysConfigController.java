package com.weiyu.chaitoufeng.controller.system;

import cn.hutool.core.map.MapUtil;
import com.weiyu.chaitoufeng.common.constant.ConfigurationConstant;
import com.weiyu.chaitoufeng.common.constant.ControllerConstant;
import com.weiyu.chaitoufeng.common.result.Result;
import com.weiyu.chaitoufeng.common.tools.SequenceUtil;
import com.weiyu.chaitoufeng.controller.base.BaseController;
import com.weiyu.chaitoufeng.domain.SetupEvent;
import com.weiyu.chaitoufeng.domain.system.SysConfig;
import com.weiyu.chaitoufeng.service.ISysConfigService;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.HashMap;

/**
 * Description: 系 统 配 置 控 制 器
 * Since: 2022-03-23 20:31
 * Author: wish_dq
 */
@RestController
@RequestMapping(ControllerConstant.API_SYSTEM_PREFIX + "config")
public class SysConfigController extends BaseController implements ApplicationEventPublisherAware {

    // 基础路径
    private final String MODULE_PATH = "system/config/";

    @Resource
    private ISysConfigService sysConfigService;

    @Resource
    private SysConfig sysConfig ;

    private ApplicationEventPublisher applicationEventPublisher;


    @GetMapping("main")
    @PreAuthorize("hasPermission('/system/config/main','sys:config:main')")
    public ModelAndView main(Model model) {
        SysConfig mailFromConfig = sysConfigService.getByCode(ConfigurationConstant.MAIN_FROM);
        SysConfig mailUserConfig = sysConfigService.getByCode(ConfigurationConstant.MAIN_USER);
        SysConfig mailPassConfig = sysConfigService.getByCode(ConfigurationConstant.MAIN_PASS);
        SysConfig mailHostConfig = sysConfigService.getByCode(ConfigurationConstant.MAIN_HOST);
        SysConfig mailPortConfig = sysConfigService.getByCode(ConfigurationConstant.MAIN_PORT);
        sysConfig.setMailFrom(mailFromConfig == null ? "" : mailFromConfig.getConfigValue());
        sysConfig.setMailUser(mailUserConfig == null ? "" : mailUserConfig.getConfigValue());
        sysConfig.setMailPass(mailPassConfig == null ? "" : mailPassConfig.getConfigValue());
        sysConfig.setMailHost(mailHostConfig == null ? "" : mailHostConfig.getConfigValue());
        sysConfig.setMailPort(mailPortConfig == null ? "" : mailPortConfig.getConfigValue());

        SysConfig uploadKindConfig = sysConfigService.getByCode(ConfigurationConstant.UPLOAD_KIND);
        SysConfig uploadPathConfig = sysConfigService.getByCode(ConfigurationConstant.UPLOAD_PATH);
        sysConfig.setUploadKind(uploadKindConfig == null ? "" : uploadKindConfig.getConfigValue());
        sysConfig.setUploadPath(uploadPathConfig == null ? "" : uploadPathConfig.getConfigValue());

        model.addAttribute("setup", sysConfig);
        return jumpPage(MODULE_PATH + "main");
    }

    @Transactional(rollbackFor = Exception.class)
    @PutMapping("save")
    @PreAuthorize("hasPermission('/system/config/add','sys:config:add')")
    public Result save(@RequestBody SysConfig sysConfig) {

        String from = sysConfig.getMailFrom();
        String user = sysConfig.getMailUser();
        String pass = sysConfig.getMailPass();
        String port = sysConfig.getMailPort();
        String host = sysConfig.getMailHost();

        String uploadKind = sysConfig.getUploadKind();
        String uploadPath = sysConfig.getUploadPath();

        updateSetup("邮箱来源", ConfigurationConstant.MAIN_FROM, from);
        updateSetup("邮箱用户", ConfigurationConstant.MAIN_USER, user);
        updateSetup("邮箱密码", ConfigurationConstant.MAIN_PASS, pass);
        updateSetup("邮箱端口", ConfigurationConstant.MAIN_PORT, port);
        updateSetup("邮箱主机", ConfigurationConstant.MAIN_HOST, host);

        updateSetup("上传方式", ConfigurationConstant.UPLOAD_KIND, uploadKind);
        updateSetup("上传路径", ConfigurationConstant.UPLOAD_PATH, uploadPath);

        HashMap<String, String> map = MapUtil.newHashMap(5);
        map.put(ConfigurationConstant.MAIN_FROM, from);
        map.put(ConfigurationConstant.MAIN_USER, user);
        map.put(ConfigurationConstant.MAIN_PASS, pass);
        map.put(ConfigurationConstant.MAIN_PORT, port);
        map.put(ConfigurationConstant.MAIN_HOST, host);
        SetupEvent setupEvent = new SetupEvent(this, map);
        applicationEventPublisher.publishEvent(setupEvent);
        return success("保存成功");
    }

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


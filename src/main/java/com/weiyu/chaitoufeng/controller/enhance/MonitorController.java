package com.weiyu.chaitoufeng.controller.enhance;

import com.weiyu.chaitoufeng.common.constant.ControllerConstant;
import com.weiyu.chaitoufeng.common.server.CpuInfo;
import com.weiyu.chaitoufeng.common.tools.SystemUtil;
import com.weiyu.chaitoufeng.controller.base.BaseController;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * Description: 服务器控制器
 * Since: 2022-03-17 22:25
 * Author: wish_dq
 */
@RestController
@RequestMapping(ControllerConstant.API_SYSTEM_PREFIX + "monitor")
public class MonitorController extends BaseController {

    @GetMapping("main")
    @PreAuthorize("hasPermission('/system/monitor/main','sys:monitor:main')")
    public ModelAndView main(Model model) {
        CpuInfo cpu = SystemUtil.getCpu();
        model.addAttribute("cpu", cpu);
        return jumpPage("system/monitor/main");
    }
}
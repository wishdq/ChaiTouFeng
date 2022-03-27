package com.weiyu.chaitoufeng.controller;

import com.weiyu.chaitoufeng.common.repeatsubmit.NoRepeatSubmit;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Description:
 * Since: 2022-03-20 11:17
 * Author: wish_dq
 */
@Controller
public class TestController {
    @RequestMapping("/test")
    @NoRepeatSubmit
    public String test(Model model) {
        model.addAttribute("info","wertyguy");
        return "test";
    }

}
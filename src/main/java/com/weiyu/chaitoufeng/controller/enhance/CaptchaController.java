package com.weiyu.chaitoufeng.controller.enhance;

import com.weiyu.chaitoufeng.common.result.Result;
import com.weiyu.chaitoufeng.controller.base.BaseController;
import com.wf.captcha.utils.CaptchaUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Description: 验证码控制器
 * Since: 2022-03-13 15:28
 * Author: wish_dq
 */
@RestController
@RequestMapping("system/captcha")
public class CaptchaController extends BaseController {

    // 验证码生成
    @RequestMapping("generate")
    public void generate(HttpServletRequest request, HttpServletResponse response) throws Exception {
        CaptchaUtil.out(4,request, response);
    }

    // 异步验证
    @RequestMapping("verify")
    public Result verify(HttpServletRequest request, String captcha) {
        if (CaptchaUtil.ver(captcha, request)) {
            return success("验证成功");
        }
        return failure("验证失败");
    }
}


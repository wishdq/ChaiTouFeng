package com.weiyu.chaitoufeng.controller.site;

import com.github.pagehelper.PageInfo;
import com.weiyu.chaitoufeng.common.constant.ControllerConstant;
import com.weiyu.chaitoufeng.common.result.Result;
import com.weiyu.chaitoufeng.controller.base.BaseController;
import com.weiyu.chaitoufeng.domain.build.PageDomain;
import com.weiyu.chaitoufeng.domain.build.ResultTable;
import com.weiyu.chaitoufeng.domain.home.HomeRectify;
import com.weiyu.chaitoufeng.service.home.HomeRectifyService;
import com.weiyu.chaitoufeng.service.poetry.PoemService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.Arrays;

/**
 * Description:
 * Since: 2022-05-04 17:58
 * Author: wish_dq
 */
@RestController
@RequestMapping(ControllerConstant.API_SITE_PREFIX + "rectify")
public class SiteRectifyController extends BaseController {

    private final String MODULE_PATH = "admin/site/rectify/";

    @Resource
    HomeRectifyService homeRectifyService;

    @Resource
    PoemService poemService;
    

    @GetMapping("main")
    @PreAuthorize("hasPermission('/site/rectify/main','site:rectify:main')")
    public ModelAndView main() {
        return jumpPage(MODULE_PATH + "main");
    }

    @GetMapping("data")
    @PreAuthorize("hasPermission('/site/rectify/data','site:rectify:data')")
    public ResultTable data(PageDomain pageDomain, HomeRectify param) {
        PageInfo<HomeRectify> pageInfo = homeRectifyService.getPage(pageDomain,param);
        return pageTable(pageInfo.getList(), pageInfo.getTotal());
    }

    @GetMapping("view")
    public ModelAndView view(String poemId, Model model){
        model.addAttribute("poem",poemService.getById(poemId));
        return jumpPage(MODULE_PATH + "view");
    }

    @PutMapping("sure/{rectifyId}")
    public Result sure(@PathVariable String rectifyId){

        return decide(homeRectifyService.sureUpdate(rectifyId));
    }

    @DeleteMapping("remove/{id}")
    @PreAuthorize("hasPermission('/site/rectify/remove','site:rectify:remove')")
    public Result remove(@PathVariable("id") String id) {
        Boolean result = homeRectifyService.removeById(id);
        return decide(result);
    }

    @DeleteMapping("batchRemove/{ids}")
    @PreAuthorize("hasPermission('/site/rectify/remove','site:rectify:remove')")
    public Result batchRemove(@PathVariable String ids) {
        boolean result = homeRectifyService.removeBatchByIds(Arrays.asList(ids.split(",")));
        return decide(result);
    }
}

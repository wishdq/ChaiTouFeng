package com.weiyu.chaitoufeng.controller.site;

import com.github.pagehelper.PageInfo;
import com.weiyu.chaitoufeng.common.constant.ControllerConstant;
import com.weiyu.chaitoufeng.common.result.Result;
import com.weiyu.chaitoufeng.controller.base.BaseController;
import com.weiyu.chaitoufeng.domain.build.PageDomain;
import com.weiyu.chaitoufeng.domain.build.ResultTable;
import com.weiyu.chaitoufeng.domain.home.HomeReview;
import com.weiyu.chaitoufeng.service.site.HomeReviewService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.Arrays;

/**
 * Description:
 * Since: 2022-03-31 15:35
 * Author: wish_dq
 */

@RestController
@RequestMapping(ControllerConstant.API_SITE_PREFIX + "review")
public class HomeReviewController extends BaseController {

    private final String MODULE_PATH = "admin/site/review/";

    @Resource
    HomeReviewService homeReviewService;


    @GetMapping("main")
    @PreAuthorize("hasPermission('/site/review/main','site:review:main')")
    public ModelAndView main() {
        return jumpPage(MODULE_PATH + "main");
    }

    @GetMapping("data")
    @PreAuthorize("hasPermission('/site/review/data','site:review:data')")
    public ResultTable data(PageDomain pageDomain, HomeReview param) {
        PageInfo<HomeReview> pageInfo = homeReviewService.getPage(pageDomain,param);
        return pageTable(pageInfo.getList(), pageInfo.getTotal());
    }


    @PutMapping("isEnable/{status}")
    public Result enable(@PathVariable String status, @RequestBody HomeReview homeReview) {
        if("enable".equals(status)) {
            homeReview.setEnable("1");
        }else if ("disable".equals(status)){
            homeReview.setEnable("0");
        }else {
            return  Result.failure("输入状态错误");
        }
        boolean result = homeReviewService.updateById(homeReview);
        return decide(result);
    }


    @DeleteMapping("remove/{id}")
    @PreAuthorize("hasPermission('/site/review/remove','site:review:remove')")
    public Result remove(@PathVariable("id") String id) {
        Boolean result = homeReviewService.removeById(id);
        return decide(result);
    }

    @DeleteMapping("batchRemove/{ids}")
    @PreAuthorize("hasPermission('/site/review/remove','site:review:remove')")
    public Result batchRemove(@PathVariable String ids) {
        boolean result = homeReviewService.removeBatchByIds(Arrays.asList(ids.split(",")));
        return decide(result);
    }
}

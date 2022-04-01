package com.weiyu.chaitoufeng.controller.home;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageInfo;
import com.weiyu.chaitoufeng.controller.base.BaseController;
import com.weiyu.chaitoufeng.domain.build.PageDomain;
import com.weiyu.chaitoufeng.domain.build.ResultTable;
import com.weiyu.chaitoufeng.domain.poetry.Poem;
import com.weiyu.chaitoufeng.service.poetry.PoemService;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;

/**
 * Description:
 * Since: 2022-04-01 17:31
 * Author: wish_dq
 */
@RestController
public class HomeController extends BaseController {

    private final String MODULE_PATH = "home/";

    //@Resource
    //SiteUserService siteUserService;

    @Resource
    PoemService poemService;

    //获取index视图
    @GetMapping(value = {"index","/"})
    public ModelAndView index(Model model) {
        QueryWrapper<Poem> queryWrapper = new QueryWrapper<>();
        queryWrapper.isNotNull("poem_id");
        Long poemNum = poemService.count(queryWrapper);
        model.addAttribute("poemNum",poemNum);
        return jumpPage(MODULE_PATH+"index");
    }

    @GetMapping(MODULE_PATH+"data")
    public ResultTable homeData(PageDomain pageDomain) {
        //PageDomain pageDomain = new PageDomain();
        //pageDomain.setPage(1);
        //pageDomain.setLimit(10);
        //System.out.println("23333333333");
        //List<Poem> poems = poemService.page(null,pageDomain);
        PageInfo<Poem> pageInfo = poemService.page(null, pageDomain);
        return pageTable(pageInfo.getList(), pageInfo.getTotal());
    }

    //@GetMapping(MODULE_PATH+"dataCount")
    //public Long dataCount(){
    //    QueryWrapper<Poem> queryWrapper = new QueryWrapper<>();
    //    queryWrapper.isNotNull("poem_id");
    //    Long count = poemService.count(queryWrapper);
    //    return count;
    //}

}

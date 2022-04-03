package com.weiyu.chaitoufeng.controller.home;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageInfo;
import com.weiyu.chaitoufeng.common.result.Result;
import com.weiyu.chaitoufeng.controller.base.BaseController;
import com.weiyu.chaitoufeng.domain.build.PageDomain;
import com.weiyu.chaitoufeng.domain.build.ResultTable;
import com.weiyu.chaitoufeng.domain.poetry.Poem;
import com.weiyu.chaitoufeng.domain.poetry.PoemDynasty;
import com.weiyu.chaitoufeng.domain.poetry.PoemQuote;
import com.weiyu.chaitoufeng.service.poetry.PoemDynastyService;
import com.weiyu.chaitoufeng.service.poetry.PoemQuoteService;
import com.weiyu.chaitoufeng.service.poetry.PoemService;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * Description:
 * Since: 2022-04-01 17:31
 * Author: wish_dq
 */
@RestController
public class HomeController extends BaseController {

    private final String MODULE_PATH = "home/";

    private static final Random random = new Random();
    
    @Resource
    PoemService poemService;

    @Resource
    PoemDynastyService dynastyService;

    @Resource
    PoemQuoteService quoteService;

    /**
     * 视图部分
     */
    @GetMapping(value = {"index","/"})
    public ModelAndView index(Model model) {
        QueryWrapper<Poem> queryWrapper = new QueryWrapper<>();
        queryWrapper.isNotNull("poem_id");
        Long poemNum = poemService.count(queryWrapper);
        model.addAttribute("poemNum",poemNum);
        return jumpPage(MODULE_PATH+"index");
    }

    @GetMapping("poem")
    public ModelAndView poem(Model model){
        QueryWrapper<Poem> queryWrapper = new QueryWrapper<>();
        queryWrapper.isNotNull("poem_id");
        Long poemNum = poemService.count(queryWrapper);

        model.addAttribute("poemNum",poemNum);
        return jumpPage(MODULE_PATH+"poem");
    }

    @GetMapping("sentence")
    public ModelAndView sentence(Model model){
        QueryWrapper<PoemQuote> queryWrapper = new QueryWrapper<>();
        queryWrapper.isNotNull("quote_id");
        Long sentenceNum = quoteService.count(queryWrapper);

        model.addAttribute("sentenceNum",sentenceNum);
        return jumpPage(MODULE_PATH+"sentence");
    }

    @GetMapping("author")
    public ModelAndView author(){
        return jumpPage(MODULE_PATH+"author");
    }

    @GetMapping("dynasty")
    public ModelAndView dynasty(){
        return jumpPage(MODULE_PATH+"dynasty");
    }

    @GetMapping("about")
    public ModelAndView aboutMe(){
        return jumpPage(MODULE_PATH+"about");
    }


    /**
     * 逻辑处理
     */
    @GetMapping(MODULE_PATH+"index")
    public ResultTable homoIndex(){
        List<Poem> poems = poemService.randoms(random.nextInt((int) (poemService.count()/6)));
        return ResultTable.dataTable(poems);
    }

    @GetMapping(MODULE_PATH+"data")
    public ResultTable homeData(PageDomain pageDomain) {
        PageInfo<Poem> pageInfo = poemService.page(null, pageDomain);
        return pageTable(pageInfo.getList(), pageInfo.getTotal());
    }

    @PutMapping(MODULE_PATH+"poem/update")
    public Result poemUpdate(@RequestBody Poem poem){
        boolean result = poemService.updateById(poem);
        return Result.decide(result);
    }


    @GetMapping(MODULE_PATH+"sentence")
    public ResultTable sentenceData(PageDomain pageDomain) {
        PageInfo<PoemQuote> pageInfo = quoteService.getPage(pageDomain,null);
        return pageTable(pageInfo.getList(), pageInfo.getTotal());
    }


    @GetMapping(MODULE_PATH+"dynasty/data")
    public ResultTable dynastyData(){
        List<PoemDynasty> dynasties = dynastyService.list();
        return ResultTable.dataTable(dynasties);
    }


}

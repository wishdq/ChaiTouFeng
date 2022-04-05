package com.weiyu.chaitoufeng.controller.home;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageInfo;
import com.weiyu.chaitoufeng.common.result.Result;
import com.weiyu.chaitoufeng.common.tools.SequenceUtil;
import com.weiyu.chaitoufeng.controller.base.BaseController;
import com.weiyu.chaitoufeng.domain.build.PageDomain;
import com.weiyu.chaitoufeng.domain.build.ResultTable;
import com.weiyu.chaitoufeng.domain.home.HomeReview;
import com.weiyu.chaitoufeng.domain.poetry.Poem;
import com.weiyu.chaitoufeng.domain.poetry.PoemDynasty;
import com.weiyu.chaitoufeng.domain.poetry.PoemQuote;
import com.weiyu.chaitoufeng.service.poetry.PoemDynastyService;
import com.weiyu.chaitoufeng.service.poetry.PoemQuoteService;
import com.weiyu.chaitoufeng.service.poetry.PoemService;
import com.weiyu.chaitoufeng.service.site.HomeReviewService;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.time.LocalDateTime;
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

    @Resource
    HomeReviewService reviewService;

    /**
     * 视图部分
     */

    @GetMapping("poem")
    public ModelAndView poem(Model model){
        QueryWrapper<Poem> queryWrapper = new QueryWrapper<>();
        queryWrapper.isNotNull("poem_id");
        Long poemNum = poemService.count(queryWrapper);

        model.addAttribute("poemNum",poemNum);
        model.addAttribute("active","poem");
        return jumpPage(MODULE_PATH+"poem");
    }

    @GetMapping("poem/{poemId}")
    public ModelAndView poemItem(@PathVariable String poemId,Model model) {
        Poem poem = poemService.getById(poemId);
        QueryWrapper<HomeReview> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("review_location_id",poemId);

        int reviewNum = (int) reviewService.count(queryWrapper);
        model.addAttribute("poemItem",poem);
        model.addAttribute("reviewNum",reviewNum);
        model.addAttribute("active","poem");

        return jumpPage(MODULE_PATH+"poemItem");
    }

    @GetMapping("sentence")
    public ModelAndView sentence(Model model){
        QueryWrapper<PoemQuote> queryWrapper = new QueryWrapper<>();
        queryWrapper.isNotNull("quote_id");
        Long sentenceNum = quoteService.count(queryWrapper);

        model.addAttribute("sentenceNum",sentenceNum);
        model.addAttribute("active","sentence");
        return jumpPage(MODULE_PATH+"sentence");
    }

    @GetMapping("author")
    public ModelAndView author(Model model){
        model.addAttribute("active","author");
        return jumpPage(MODULE_PATH+"author");
    }

    @GetMapping("dynasty")
    public ModelAndView dynasty(Model model){
        model.addAttribute("active","dynasty");
        return jumpPage(MODULE_PATH+"dynasty");
    }

    @GetMapping("about")
    public ModelAndView aboutMe(Model model){
        model.addAttribute("active","about");
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

    @GetMapping(MODULE_PATH+"poem")
    public ResultTable homeData(PageDomain pageDomain) {
        PageInfo<Poem> pageInfo = poemService.page(null, pageDomain);
        return pageTable(pageInfo.getList(), pageInfo.getTotal());
    }

    @PutMapping(MODULE_PATH+"poem/update")
    public Result poemUpdate(@RequestBody Poem poem){
        boolean result = poemService.updateById(poem);
        return Result.decide(result);
    }

    /**
     * 获取评论列表
     */
    @GetMapping(MODULE_PATH+"poem/review/{poemId}")
    public ResultTable poemReview(PageDomain pageDomain, @PathVariable String poemId){
        HomeReview homeReview = new HomeReview();
        homeReview.setReviewLocationId(poemId);
        PageInfo<HomeReview> pageInfo = reviewService.getHomePage(pageDomain,homeReview);
        return pageTable(pageInfo.getList(), pageInfo.getTotal());
    }

    /**
     * 添加评论
     */
    @PutMapping(MODULE_PATH+"poem/review/add")
    public Result addPoemReview(@RequestBody HomeReview review){
        review.setReviewId(SequenceUtil.makeStringId());
        review.setEnable("1");
        review.setCreateTime(LocalDateTime.now());
        boolean result = reviewService.save(review);
        return Result.decide(result);
    }
    /**
     * 更新评论
     */
    @PutMapping(MODULE_PATH+"poem/review/update")
    public Result updatePoemReview(@RequestBody HomeReview review){
        System.out.println("=================");
        System.out.println(review);
        boolean result = reviewService.updateById(review);
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

package com.weiyu.chaitoufeng.controller.home;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageInfo;
import com.weiyu.chaitoufeng.common.result.Result;
import com.weiyu.chaitoufeng.common.tools.SecurityUtil;
import com.weiyu.chaitoufeng.common.tools.SequenceUtil;
import com.weiyu.chaitoufeng.controller.base.BaseController;
import com.weiyu.chaitoufeng.domain.build.PageDomain;
import com.weiyu.chaitoufeng.domain.build.ResultTable;
import com.weiyu.chaitoufeng.domain.home.HomeCollect;
import com.weiyu.chaitoufeng.domain.home.HomeLove;
import com.weiyu.chaitoufeng.domain.home.HomeReview;
import com.weiyu.chaitoufeng.domain.poetry.Poem;
import com.weiyu.chaitoufeng.domain.poetry.PoemAuthor;
import com.weiyu.chaitoufeng.domain.poetry.PoemDynasty;
import com.weiyu.chaitoufeng.domain.poetry.PoemQuote;
import com.weiyu.chaitoufeng.domain.system.SysUser;
import com.weiyu.chaitoufeng.service.home.HomeCollectService;
import com.weiyu.chaitoufeng.service.home.HomeLoveService;
import com.weiyu.chaitoufeng.service.poetry.PoemAuthorService;
import com.weiyu.chaitoufeng.service.poetry.PoemDynastyService;
import com.weiyu.chaitoufeng.service.poetry.PoemQuoteService;
import com.weiyu.chaitoufeng.service.poetry.PoemService;
import com.weiyu.chaitoufeng.service.home.HomeReviewService;
import com.weiyu.chaitoufeng.service.system.ISysConfigService;
import com.weiyu.chaitoufeng.service.system.ISysUserService;
import lombok.extern.java.Log;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
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
@Slf4j
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
    PoemAuthorService authorService;

    @Resource
    HomeReviewService reviewService;

    @Resource
    HomeCollectService collectService;

    @Resource
    HomeLoveService loveService;

    @Resource
    PasswordEncoder passwordEncoder;

    @Resource
    ISysUserService userService;

    @Resource
    ISysConfigService configService;

    /**
     * 视图部分
     */

    @GetMapping("poem")
    public ModelAndView poem(Model model) {
        int poemNum = Integer.parseInt(configService.getValueByCode("poem_num"));

        model.addAttribute("poemNum", poemNum);
        model.addAttribute("active", "poem");
        if (SecurityUtil.isAuthentication()) {
            QueryWrapper<HomeCollect> collectQueryWrapper = new QueryWrapper<>();
            collectQueryWrapper.eq("user_id", ((SysUser) SecurityUtil.currentUser()).getUserId());
            int collectNum = (int) collectService.count(collectQueryWrapper);

            QueryWrapper<HomeLove> loveQueryWrapper = new QueryWrapper<>();
            loveQueryWrapper.eq("user_id", ((SysUser) SecurityUtil.currentUser()).getUserId());
            int loveNum = (int) loveService.count(loveQueryWrapper);

            QueryWrapper<HomeReview> reviewQueryWrapper = new QueryWrapper<>();
            reviewQueryWrapper.eq("review_user_id", ((SysUser) SecurityUtil.currentUser()).getUserId());
            int reviewNum = (int) reviewService.count(reviewQueryWrapper);

            model.addAttribute("collectNum", collectNum);
            model.addAttribute("loveNum", loveNum);
            model.addAttribute("reviewNum", reviewNum);
        }
        return jumpPage(MODULE_PATH + "poem");
    }

    @GetMapping("poem/{poemId}")
    public ModelAndView poemItem(@PathVariable String poemId, Model model) {
        Poem poem = poemService.getById(poemId);
        QueryWrapper<HomeReview> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("review_location_id", poemId);

        int reviewNum = (int) reviewService.count(queryWrapper);
        model.addAttribute("poemItem", poem);
        model.addAttribute("reviewNum", reviewNum);
        model.addAttribute("active", "poem");

        return jumpPage(MODULE_PATH + "poemItem");
    }

    @GetMapping("sentence")
    public ModelAndView sentence(Model model) {
        QueryWrapper<PoemQuote> queryWrapper = new QueryWrapper<>();
        queryWrapper.isNotNull("quote_id");
        Long sentenceNum = quoteService.count(queryWrapper);

        model.addAttribute("sentenceNum", sentenceNum);
        model.addAttribute("active", "sentence");
        return jumpPage(MODULE_PATH + "sentence");
    }

    @GetMapping("author")
    public ModelAndView author(Model model) {
        int authorNum = (int) authorService.count();
        model.addAttribute("active", "author");
        model.addAttribute("authorNum", authorNum);
        return jumpPage(MODULE_PATH + "author");
    }

    @GetMapping("dynasty")
    public ModelAndView dynasty(Model model) {
        model.addAttribute("active", "dynasty");
        return jumpPage(MODULE_PATH + "dynasty");
    }

    @GetMapping("profile")
    public ModelAndView profile(Model model) {
        if (!SecurityUtil.isAuthentication()) {
            return jumpPage("login");
        }

        QueryWrapper<HomeCollect> collectQueryWrapper = new QueryWrapper<>();
        collectQueryWrapper.eq("user_id", ((SysUser) SecurityUtil.currentUser()).getUserId());
        int collectNum = (int) collectService.count(collectQueryWrapper);

        QueryWrapper<HomeLove> loveQueryWrapper = new QueryWrapper<>();
        loveQueryWrapper.eq("user_id", ((SysUser) SecurityUtil.currentUser()).getUserId());
        int loveNum = (int) loveService.count(loveQueryWrapper);

        QueryWrapper<HomeReview> reviewQueryWrapper = new QueryWrapper<>();
        reviewQueryWrapper.eq("review_user_id", ((SysUser) SecurityUtil.currentUser()).getUserId());
        int reviewNum = (int) reviewService.count(reviewQueryWrapper);

        SysUser curUser = userService.getById(((SysUser) SecurityUtil.currentUser()).getUserId());
        model.addAttribute("curUser", curUser);
        model.addAttribute("collectNum", collectNum);
        model.addAttribute("loveNum", loveNum);
        model.addAttribute("reviewNum", reviewNum);
        return jumpPage(MODULE_PATH + "profile");
    }

    @GetMapping("about")
    public ModelAndView search(Model model) {
        model.addAttribute("active", "about");
        return jumpPage(MODULE_PATH + "about");
    }

    @GetMapping("search")
    public ModelAndView aboutMe(String key, Model model) {
        QueryWrapper<Poem> poemQueryWrapper = new QueryWrapper<>();
        poemQueryWrapper.like("content", key);
        int searchNum = (int) poemService.count(poemQueryWrapper);
        model.addAttribute("searchNum", searchNum);
        model.addAttribute("key", key);
        return jumpPage(MODULE_PATH + "search");
    }


    /**
     * 逻辑处理
     */
    @GetMapping(MODULE_PATH + "index")
    public ResultTable homoIndex() {
        int poemNum = Integer.parseInt(configService.getValueByCode("poem_num"));
        List<Poem> poems = poemService.randoms(random.nextInt(poemNum / 6));
        return ResultTable.dataTable(poems);
    }

    @GetMapping(MODULE_PATH + "poem")
    public ResultTable homeData(PageDomain pageDomain) {
        List<Poem> poems = poemService.topZhanZan(pageDomain);
        return ResultTable.dataTable(poems);
    }

    @PutMapping(MODULE_PATH + "poem/update/{option}/{userId}")
    public Result poemUpdate(@RequestBody Poem poem,
                             @PathVariable String option,
                             @PathVariable String userId) {
        switch (option) {
            case "shouCuang":
                HomeCollect collect = new HomeCollect(SequenceUtil.makeStringId(), userId, poem.getPoemId());
                if (collectService.isHadCollect(collect)) {
                    return Result.failure("你已加入收藏");
                }
                collectService.save(collect);
                break;
            case "xiHuan":
                HomeLove love = new HomeLove(SequenceUtil.makeStringId(), userId, poem.getPoemId());
                if (loveService.isHadLove(love)) {
                    return Result.failure("你已加入喜欢");
                }
                loveService.save(love);
                break;
        }
        boolean result = poemService.updateById(poem);
        return Result.decide(result);
    }

    /**
     * 获取评论列表
     */
    @GetMapping(MODULE_PATH + "poem/review/{poemId}")
    public ResultTable poemReview(PageDomain pageDomain, @PathVariable String poemId) {
        HomeReview homeReview = new HomeReview();
        homeReview.setReviewLocationId(poemId);
        PageInfo<HomeReview> pageInfo = reviewService.getHomePage(pageDomain, homeReview);
        return pageTable(pageInfo.getList(), pageInfo.getTotal());
    }

    /**
     * 添加评论
     */
    @PutMapping(MODULE_PATH + "poem/review/add")
    public Result addPoemReview(@RequestBody HomeReview review) {
        review.setReviewId(SequenceUtil.makeStringId());
        review.setEnable("1");
        review.setCreateTime(LocalDateTime.now());
        boolean result = reviewService.save(review);
        return Result.decide(result);
    }

    /**
     * 更新评论
     */
    @PutMapping(MODULE_PATH + "poem/review/update")
    public Result updatePoemReview(@RequestBody HomeReview review) {
        boolean result = reviewService.updateById(review);
        return Result.decide(result);
    }

    @GetMapping(MODULE_PATH + "sentence")
    public ResultTable sentenceData(PageDomain pageDomain) {
        PageInfo<PoemQuote> pageInfo = quoteService.getPage(pageDomain, null);
        return pageTable(pageInfo.getList(), pageInfo.getTotal());
    }


    @GetMapping(MODULE_PATH + "dynasty/data")
    public ResultTable dynastyData() {
        List<PoemDynasty> dynasties = dynastyService.list();
        return ResultTable.dataTable(dynasties);
    }


    @PostMapping(MODULE_PATH + "profile/save")
    public Result profileUpdate(@RequestBody SysUser user) {
        if ("".equals(user.getPassword())) {
            user.setPassword(null);
        } else {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        }
        boolean result = userService.updateById(user);
        return Result.decide(result);
    }

    /**
     * 收藏列表
     */
    @GetMapping(MODULE_PATH + "collect/data/{userId}")
    public ResultTable collectData(PageDomain pageDomain, @PathVariable String userId) {
        if (!SecurityUtil.isAuthentication()) {
            return ResultTable.dataTable("请登录");
        }
        PageInfo<HomeCollect> pageInfo = collectService.getPage(pageDomain, userId);
        return pageTable(pageInfo.getList(), pageInfo.getTotal());
    }

    /**
     * 喜欢列表
     */
    @GetMapping(MODULE_PATH + "love/data/{userId}")
    public ResultTable loveData(PageDomain pageDomain, @PathVariable String userId) {
        if (!SecurityUtil.isAuthentication()) {
            return ResultTable.dataTable("请登录");
        }
        PageInfo<HomeLove> pageInfo = loveService.getPage(pageDomain, userId);
        return pageTable(pageInfo.getList(), pageInfo.getTotal());
    }

    /**
     * 个人评论列表
     */
    @GetMapping(MODULE_PATH + "review/data/{userId}")
    public ResultTable reviewData(PageDomain pageDomain, @PathVariable String userId) {
        if (!SecurityUtil.isAuthentication()) {
            return ResultTable.dataTable("请登录");
        }
        PageInfo<HomeReview> pageInfo = reviewService.getProfilePage(pageDomain, userId);
        return pageTable(pageInfo.getList(), pageInfo.getTotal());
    }

    /**
     * 个人中心操作
     */
    @PutMapping(MODULE_PATH + "profile/option/{code}")
    public Result profileOption(@RequestBody String id, @PathVariable String code) {
        if (!SecurityUtil.isAuthentication()) {
            return Result.failure("请登录");
        }
        switch (code) {
            case "del-collect":
                collectService.removeById(id);
                break;
            case "del-love":
                loveService.removeById(id);
                break;
            case "del-review":
                reviewService.removeById(id);
                break;
            default:
                return Result.failure();
        }
        return Result.success();
    }

    @GetMapping(MODULE_PATH + "search/data/{key}")
    public ResultTable searchData(PageDomain pageDomain, @PathVariable String key) {
        if ("".equals(key)) {
            return ResultTable.dataTable("关键字不为空");
        }
        Poem poem = new Poem();
        poem.setContent(key);
        PageInfo<Poem> pageInfo = poemService.page(poem, pageDomain);
        return pageTable(pageInfo.getList(), pageInfo.getTotal());
    }


    @GetMapping(MODULE_PATH + "author/data/{dynasty}")
    public ResultTable authorData(PageDomain pageDomain, @PathVariable String dynasty) {
        if (pageDomain.getPage() == null || pageDomain.getLimit() == null) {
            return ResultTable.dataTable("访问参数错误");
        }
        PoemAuthor author = new PoemAuthor();
        if ("不限".equals(dynasty)) {
            author.setDynasty(null);
        } else {
            author.setDynasty(dynasty);
        }
        PageInfo<PoemAuthor> pageInfo = authorService.getPage(pageDomain, author);
        return pageTable(pageInfo.getList(), pageInfo.getTotal());
    }
}

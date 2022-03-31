package com.weiyu.chaitoufeng.controller.poetry;

import com.github.pagehelper.PageInfo;
import com.weiyu.chaitoufeng.common.constant.ControllerConstant;
import com.weiyu.chaitoufeng.common.result.Result;
import com.weiyu.chaitoufeng.common.tools.SequenceUtil;
import com.weiyu.chaitoufeng.controller.base.BaseController;
import com.weiyu.chaitoufeng.domain.build.PageDomain;
import com.weiyu.chaitoufeng.domain.build.ResultTable;
import com.weiyu.chaitoufeng.domain.poetry.PoemQuote;
import com.weiyu.chaitoufeng.service.poetry.PoemQuoteService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.Arrays;

/**
 * Description:
 * Since: 2022-03-28 11:04
 * Author: wish_dq
 */
@RestController
@RequestMapping(ControllerConstant.API_POETRY_PREFIX + "quote")
public class PoemQuoteController extends BaseController {

    private final String MODULE_PATH = "admin/poetry/quote/";

    @Resource
    PoemQuoteService quoteService;


    @GetMapping("main")
    @PreAuthorize("hasPermission('/poetry/quote/main','poetry:quote:main')")
    public ModelAndView main() {
        return jumpPage(MODULE_PATH + "main");
    }

    @GetMapping("data")
    @PreAuthorize("hasPermission('/poetry/quote/data','poetry:quote:data')")
    public ResultTable data(PageDomain pageDomain, PoemQuote param) {
        PageInfo<PoemQuote> pageInfo = quoteService.getPage(pageDomain,param);
        return pageTable(pageInfo.getList(), pageInfo.getTotal());
    }


    @GetMapping("add")
    @PreAuthorize("hasPermission('/poetry/quote/add','poetry:quote:add')")
    public ModelAndView add() {
        return jumpPage(MODULE_PATH + "add");
    }


    @PostMapping("save")
    @PreAuthorize("hasPermission('/poetry/quote/add','poetry:quote:add')")
    public Result save(@RequestBody PoemQuote quote) {
        quote.setQuoteId(SequenceUtil.makeStringId());
        quote.setUpdateTime(LocalDateTime.now());
        boolean result = quoteService.save(quote);
        return decide(result);
    }

    @GetMapping("edit")
    @PreAuthorize("hasPermission('/poetry/quote/edit','poetry:quote:edit')")
    public ModelAndView edit(Model model, String quoteId) {
        PoemQuote quote = quoteService.getById(quoteId);
        model.addAttribute("quote",quote);
        return jumpPage(MODULE_PATH + "edit");
    }


    @PutMapping("update")
    @PreAuthorize("hasPermission('/poetry/quote/edit','poetry:quote:edit')")
    public Result update(@RequestBody PoemQuote quote) {
        quote.setUpdateTime(LocalDateTime.now());
        boolean result = quoteService.updateById(quote);
        return decide(result);
    }

    @DeleteMapping("remove/{id}")
    @PreAuthorize("hasPermission('/poetry/quote/remove','poetry:quote:remove')")
    public Result remove(@PathVariable("id") String id) {
        Boolean result = quoteService.removeById(id);
        return decide(result);
    }

    @DeleteMapping("batchRemove/{ids}")
    @PreAuthorize("hasPermission('/poetry/quote/remove','poetry:quote:remove')")
    public Result batchRemove(@PathVariable String ids) {
        boolean result = quoteService.removeBatchByIds(Arrays.asList(ids.split(",")));
        return decide(result);
    }
    
}

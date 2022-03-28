package com.weiyu.chaitoufeng.controller.poetry;

import com.weiyu.chaitoufeng.common.constant.ControllerConstant;
import com.weiyu.chaitoufeng.common.result.Result;
import com.weiyu.chaitoufeng.common.tools.SequenceUtil;
import com.weiyu.chaitoufeng.controller.base.BaseController;
import com.weiyu.chaitoufeng.domain.poetry.PoemDynasty;
import com.weiyu.chaitoufeng.domain.request.PageDomain;
import com.weiyu.chaitoufeng.domain.response.ResultTable;
import com.weiyu.chaitoufeng.service.poetry.PoemDynastyService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Description:
 * Since: 2022-03-27 23:39
 * Author: wish_dq
 */

@RestController
@RequestMapping(ControllerConstant.API_POETRY_PREFIX + "dynasty")
public class PoemDynastyController extends BaseController {

    private final String MODULE_PATH = "poetry/dynasty/";

    @Resource
    PoemDynastyService dynastyService;

    @GetMapping("main")
    @PreAuthorize("hasPermission('/poetry/dynasty/main','poetry:dynasty:main')")
    public ModelAndView main() {
        return jumpPage(MODULE_PATH + "main");
    }

    @GetMapping("data")
    @PreAuthorize("hasPermission('/poetry/dynasty/data','poetry:dynasty:data')")
    public ResultTable data(PageDomain pageDomain, PoemDynasty param) {
        List<PoemDynasty> dynasties = dynastyService.list();
        ResultTable resultTable = new ResultTable();
        resultTable.setCode(0);
        resultTable.setCount((long) dynasties.size());
        resultTable.setData(dynasties);
        return resultTable;
    }


    @GetMapping("add")
    @PreAuthorize("hasPermission('/poetry/dynasty/add','poetry:dynasty:add')")
    public ModelAndView add() {
        return jumpPage(MODULE_PATH + "add");
    }


    @PostMapping("save")
    @PreAuthorize("hasPermission('/poetry/dynasty/add','poetry:dynasty:add')")
    public Result save(@RequestBody PoemDynasty dynasty) {
        dynasty.setDynastyId(SequenceUtil.makeStringId());
        dynasty.setUpdatedTime(LocalDateTime.now());
        boolean result = dynastyService.save(dynasty);
        return decide(result);
    }

    @GetMapping("edit")
    @PreAuthorize("hasPermission('/poetry/dynasty/edit','poetry:dynasty:edit')")
    public ModelAndView edit(Model model, String dynastyId) {
        PoemDynasty dynasty = dynastyService.getById(dynastyId);
        model.addAttribute("dynasty",dynasty);
        return jumpPage(MODULE_PATH + "edit");
    }


    @PutMapping("update")
    @PreAuthorize("hasPermission('/poetry/dynasty/edit','poetry:dynasty:edit')")
    public Result update(@RequestBody PoemDynasty dynasty) {
        boolean result = dynastyService.updateById(dynasty);
        return decide(result);
    }

    @DeleteMapping("remove/{id}")
    @PreAuthorize("hasPermission('/poetry/dynasty/remove','poetry:dynasty:remove')")
    public Result remove(@PathVariable("id") String id) {
        Boolean result = dynastyService.removeById(id);
        return decide(result);
    }

}

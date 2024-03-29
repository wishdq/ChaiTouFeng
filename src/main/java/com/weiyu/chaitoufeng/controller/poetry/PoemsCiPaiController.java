package com.weiyu.chaitoufeng.controller.poetry;

import com.github.pagehelper.PageInfo;
import com.weiyu.chaitoufeng.common.constant.ControllerConstant;
import com.weiyu.chaitoufeng.common.result.Result;
import com.weiyu.chaitoufeng.common.tools.SequenceUtil;
import com.weiyu.chaitoufeng.controller.base.BaseController;
import com.weiyu.chaitoufeng.domain.build.PageDomain;
import com.weiyu.chaitoufeng.domain.build.ResultTable;
import com.weiyu.chaitoufeng.domain.poetry.PoemCiPai;
import com.weiyu.chaitoufeng.service.poetry.PoemsCiPaiService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.time.LocalDateTime;

/**
 * Description:
 * Since: 2022-03-27 17:56
 * Author: wish_dq
 */
@RestController
@RequestMapping(ControllerConstant.API_POETRY_PREFIX + "ciPai")
public class PoemsCiPaiController extends BaseController {

    private final String MODULE_PATH = "admin/poetry/ciPai/";

    @Resource
    PoemsCiPaiService ciPaiService;

    @GetMapping("main")
    @PreAuthorize("hasPermission('/poetry/ciPai/main','poetry:ciPai:main')")
    public ModelAndView main() {
        return jumpPage(MODULE_PATH + "main");
    }

    @GetMapping("data")
    @PreAuthorize("hasPermission('/poetry/ciPai/data','poetry:ciPai:data')")
    public ResultTable data(PageDomain pageDomain, PoemCiPai param) {
        PageInfo<PoemCiPai> pageInfo = ciPaiService.page(param, pageDomain);
        return pageTable(pageInfo.getList(), pageInfo.getTotal());
    }


    @GetMapping("add")
    @PreAuthorize("hasPermission('/poetry/ciPai/add','poetry:ciPai:add')")
    public ModelAndView add() {
        return jumpPage(MODULE_PATH + "add");
    }


    @PostMapping("save")
    @PreAuthorize("hasPermission('/poetry/ciPai/add','poetry:ciPai:add')")
    public Result save(@RequestBody PoemCiPai ciPai) {
        ciPai.setCiPaiId(SequenceUtil.makeStringId());
        ciPai.setUpdateTime(LocalDateTime.now());
        boolean result = ciPaiService.save(ciPai);
        return decide(result);
    }

    @GetMapping("edit")
    @PreAuthorize("hasPermission('/poetry/ciPai/edit','poetry:ciPai:edit')")
    public ModelAndView edit(Model model, String ciPaiId) {
        PoemCiPai ciPai = ciPaiService.getById(ciPaiId);
        model.addAttribute("ciPai",ciPai);
        return jumpPage(MODULE_PATH + "edit");
    }


    @PutMapping("update")
    @PreAuthorize("hasPermission('/poetry/ciPai/edit','poetry:ciPai:edit')")
    public Result update(@RequestBody PoemCiPai ciPai) {
        ciPai.setUpdateTime(LocalDateTime.now());
        boolean result = ciPaiService.updateById(ciPai);
        return decide(result);
    }

    @DeleteMapping("remove/{id}")
    @PreAuthorize("hasPermission('/poetry/ciPai/remove','poetry:ciPai:remove')")
    public Result remove(@PathVariable("id") String id) {
        Boolean result = ciPaiService.removeById(id);
        return decide(result);
    }

    @DeleteMapping("batchRemove/{ids}")
    @PreAuthorize("hasPermission('/poetry/ciPai/remove','poetry:ciPai:remove')")
    public Result batchRemove(@PathVariable String ids) {
        boolean result = ciPaiService.batchRemove(ids.split(","));
        return decide(result);
    }
}

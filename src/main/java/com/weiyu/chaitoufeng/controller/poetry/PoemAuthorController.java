package com.weiyu.chaitoufeng.controller.poetry;

import com.github.pagehelper.PageInfo;
import com.weiyu.chaitoufeng.common.constant.ControllerConstant;
import com.weiyu.chaitoufeng.common.result.Result;
import com.weiyu.chaitoufeng.common.tools.SequenceUtil;
import com.weiyu.chaitoufeng.controller.base.BaseController;
import com.weiyu.chaitoufeng.domain.poetry.PoemAuthor;
import com.weiyu.chaitoufeng.domain.request.PageDomain;
import com.weiyu.chaitoufeng.domain.response.ResultTable;
import com.weiyu.chaitoufeng.service.poetry.PoemAuthorService;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.Arrays;

/**
 * Description:
 * Since: 2022-03-28 16:07
 * Author: wish_dq
 */

@RestController
@RequestMapping(ControllerConstant.API_POETRY_PREFIX + "author")
public class PoemAuthorController extends BaseController {

    private final String MODULE_PATH = "poetry/author/";

    @Resource
    PoemAuthorService authorService;


    @GetMapping("main")
    @PreAuthorize("hasPermission('/poetry/author/main','poetry:author:main')")
    public ModelAndView main() {
        return jumpPage(MODULE_PATH + "main");
    }

    @GetMapping("data")
    @PreAuthorize("hasPermission('/poetry/author/data','poetry:author:data')")
    public ResultTable data(PageDomain pageDomain, PoemAuthor param) {
        PageInfo<PoemAuthor> pageInfo = authorService.getPage(pageDomain,param);
        return pageTable(pageInfo.getList(), pageInfo.getTotal());
    }


    @GetMapping("add")
    @PreAuthorize("hasPermission('/poetry/author/add','poetry:author:add')")
    public ModelAndView add() {
        return jumpPage(MODULE_PATH + "add");
    }


    @PostMapping("save")
    @PreAuthorize("hasPermission('/poetry/author/add','poetry:author:add')")
    public Result save(@RequestBody PoemAuthor author) {
        author.setAuthorId(SequenceUtil.makeStringId());
        author.setUpdateTime(LocalDateTime.now());
        boolean result = authorService.save(author);
        return decide(result);
    }

    @GetMapping("edit")
    @PreAuthorize("hasPermission('/poetry/author/edit','poetry:author:edit')")
    public ModelAndView edit(Model model, String authorId) {
        PoemAuthor author = authorService.getById(authorId);
        model.addAttribute("author",author);
        return jumpPage(MODULE_PATH + "edit");
    }


    @PutMapping("update")
    @PreAuthorize("hasPermission('/poetry/author/edit','poetry:author:edit')")
    public Result update(@RequestBody PoemAuthor author) {
        author.setUpdateTime(LocalDateTime.now());
        boolean result = authorService.updateById(author);
        return decide(result);
    }

    @DeleteMapping("remove/{id}")
    @PreAuthorize("hasPermission('/poetry/author/remove','poetry:author:remove')")
    public Result remove(@PathVariable("id") String id) {
        Boolean result = authorService.removeById(id);
        return decide(result);
    }

    @DeleteMapping("batchRemove/{ids}")
    @ApiOperation(value = "批量删除系统配置数据")
    @PreAuthorize("hasPermission('/poetry/author/remove','poetry:author:remove')")
    public Result batchRemove(@PathVariable String ids) {
        boolean result = authorService.removeBatchByIds(Arrays.asList(ids.split(",")));
        return decide(result);
    }

}

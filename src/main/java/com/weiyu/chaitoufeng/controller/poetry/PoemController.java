package com.weiyu.chaitoufeng.controller.poetry;

import com.github.pagehelper.PageInfo;
import com.weiyu.chaitoufeng.common.constant.ControllerConstant;
import com.weiyu.chaitoufeng.common.result.Result;
import com.weiyu.chaitoufeng.common.tools.SequenceUtil;
import com.weiyu.chaitoufeng.controller.base.BaseController;
import com.weiyu.chaitoufeng.domain.poetry.Poem;
import com.weiyu.chaitoufeng.domain.build.PageDomain;
import com.weiyu.chaitoufeng.domain.build.ResultTable;
import com.weiyu.chaitoufeng.service.poetry.PoemService;
import com.weiyu.chaitoufeng.service.system.ISysConfigService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Arrays;

/**
 * Description:
 * Since: 2022-03-28 22:51
 * Author: wish_dq
 */
@RestController
@RequestMapping(ControllerConstant.API_POETRY_PREFIX + "poem")
public class PoemController extends BaseController {

    private final String MODULE_PATH = "admin/poetry/poem/";

    @Resource
    PoemService poemService;

    @Resource
    ISysConfigService configService;

    @GetMapping("main")
    @PreAuthorize("hasPermission('/poetry/poem/main','poetry:poem:main')")
    public ModelAndView main() {
        return jumpPage(MODULE_PATH + "main");
    }

    @GetMapping("data")
    @PreAuthorize("hasPermission('/poetry/poem/data','poetry:poem:data')")
    public ResultTable data(PageDomain pageDomain, Poem param,
                            HttpServletRequest request,
                            ServletResponse response) throws ServletException, IOException {
        if (pageDomain.getPage() != null && pageDomain.getLimit() != null){
            PageInfo<Poem> pageInfo = poemService.page(param, pageDomain);
            return pageTable(pageInfo.getList(), pageInfo.getTotal());
        }
        request.getRequestDispatcher("/error/403").forward(request,response);
        return null;
    }


    @GetMapping("add")
    @PreAuthorize("hasPermission('/poetry/poem/add','poetry:poem:add')")
    public ModelAndView add() {
        return jumpPage(MODULE_PATH + "add");
    }


    @PostMapping("save")
    @PreAuthorize("hasPermission('/poetry/poem/add','poetry:poem:add')")
    public Result save(@RequestBody Poem poem) {
        poem.setPoemId(SequenceUtil.makeStringId());
        poem.setUpdateTime(LocalDateTime.now());
        boolean result = poemService.save(poem);
        String poem_num = String.valueOf(Integer.parseInt(configService.getValueByCode("poem_num"))+1);
        boolean resultConfig = configService.setValueByCode("poem_num",poem_num);
        return decide(result&&resultConfig);
    }

    @GetMapping("edit")
    @PreAuthorize("hasPermission('/poetry/poem/edit','poetry:poem:edit')")
    public ModelAndView edit(Model model, String poemId) {
        Poem poem = poemService.getById(poemId);
        model.addAttribute("poem",poem);
        return jumpPage(MODULE_PATH + "edit");
    }


    @PutMapping("update")
    @PreAuthorize("hasPermission('/poetry/poem/edit','poetry:poem:edit')")
    public Result update(@RequestBody Poem poem) {
        boolean result = poemService.updateById(poem);
        poem.setUpdateTime(LocalDateTime.now());
        return decide(result);
    }

    @DeleteMapping("remove/{id}")
    @PreAuthorize("hasPermission('/poetry/poem/remove','poetry:poem:remove')")
    public Result remove(@PathVariable("id") String id) {
        Boolean result = poemService.removeById(id);
        String poem_num = String.valueOf(Integer.parseInt(configService.getValueByCode("poem_num"))-1);
        boolean resultConfig = configService.setValueByCode("poem_num",poem_num);
        return decide(result && resultConfig);
    }

    @DeleteMapping("batchRemove/{ids}")
    @PreAuthorize("hasPermission('/poetry/poem/remove','poetry:poem:remove')")
    public Result batchRemove(@PathVariable String ids) {
        boolean result = poemService.removeByIds(Arrays.asList(ids.split(",")));
        String poem_num = String.valueOf(Integer.parseInt(configService.getValueByCode("poem_num"))-Arrays.asList(ids.split(",")).size());
        boolean resultConfig = configService.setValueByCode("poem_num",poem_num);
        return decide(result && resultConfig);
    }
}

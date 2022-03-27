package com.weiyu.chaitoufeng.controller.system;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.weiyu.chaitoufeng.common.constant.ControllerConstant;
import com.weiyu.chaitoufeng.common.result.Result;
import com.weiyu.chaitoufeng.config.property.SysFileTemplateProperty;
import com.weiyu.chaitoufeng.controller.base.BaseController;
import com.weiyu.chaitoufeng.domain.request.PageDomain;
import com.weiyu.chaitoufeng.service.system.ISysFileService;
import com.weiyu.chaitoufeng.common.constant.CommonConstant;
import com.weiyu.chaitoufeng.domain.response.ResultTable;
import com.weiyu.chaitoufeng.domain.system.SysFile;
import io.swagger.annotations.Api;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.Map;


/**
 * Description: 系统文件控制器
 * Since: 2022-03-18 18:43
 * Author: wish_dq
 */
@RestController
@Api(tags = {"资源文件"})
@RequestMapping(ControllerConstant.API_SYSTEM_PREFIX + "file")
public class SysFileController extends BaseController {

    /**
     * 系 统 文 件
     */
    private final String MODULE_PATH = "system/file/";

    /**
     * 配置文件
     */
    @Resource
    private SysFileTemplateProperty uploadProperty;

    /**
     * 移 除 服 务
     */
    @Autowired
    private Map<String, ISysFileService> fileServiceMap;

    /**
     * 根据配置文件选择实现类
     *
     * @return
     */
    private ISysFileService getFileService() {
        ISysFileService fileService = null;
        if (uploadProperty.isFtpUse()) {
            fileService = this.fileServiceMap.get("SysFileFTPServiceImpl");
        } else {
            fileService = this.fileServiceMap.get("SysFileServiceImpl");
        }
        return fileService;
    }

    /**
     * Describe: 文件管理页面
     * Param: null
     * Return: ModelAndView
     */
    @GetMapping("main")
    @PreAuthorize("hasPermission('/system/file/main','sys:file:main')")
    public ModelAndView main() {
        return jumpPage(MODULE_PATH + "main");
    }

    /**
     * Describe: 文件资源数据
     * Param: PageDomain
     * Return: 文件资源列表
     */
    @GetMapping("data")
    @PreAuthorize("hasPermission('/system/file/data','sys:file:data')")
    public ResultTable data(PageDomain pageDomain, SysFile sysFile) {
        PageHelper.startPage(pageDomain.getPage(), pageDomain.getLimit());
        PageInfo<SysFile> pageInfo = new PageInfo<>(getFileService().data(sysFile));
        return pageTable(pageInfo.getList(), pageInfo.getTotal());
        //PageInfo<SysRole> pageInfo = fileServiceMap.page(sysFile, pageDomain);
        //return pageTable(pageInfo.getList(), pageInfo.getTotal());
    }

    /**
     * Describe: 文件上传视图
     * Param: null
     * Return: 执行结果
     */
    @GetMapping("add")
    @PreAuthorize("hasPermission('/system/file/add','sys:file:add')")
    public ModelAndView add() {
        return jumpPage(MODULE_PATH + "add");
    }

    /**
     * Describe: 文件上传接口
     * Param: SysUser
     * Return: Result
     */
    @PostMapping("upload")
    public Result upload(@RequestParam("file") MultipartFile file) {
        String result = getFileService().upload(file);
        if (Strings.isNotBlank(result)) {
            return Result.success(0, "上传成功", result);
        } else {
            return Result.failure("上传失败");
        }
    }

    /**
     * Describe: 文件获取接口
     * Param: id
     * Return: 文件流
     */
    @GetMapping("download/{id}")
    public void download(@PathVariable("id") String id) {
        getFileService().download(id);
    }

    /**
     * Describe: 文件删除接口
     * Param: id
     * Return: 文件流
     */
    @DeleteMapping("remove/{id}")
    @PreAuthorize("hasPermission('/system/file/remove','sys:file:remove')")
    public Result remove(@PathVariable("id") String id) {
        boolean result = getFileService().remove(id);
        return Result.decide(result, "删除成功", "删除失败");
    }

    /**
     * Describe: 文件删除接口
     * Param: id
     * Return: 文件流
     */
    @Transactional(rollbackFor = Exception.class)
    @DeleteMapping("batchRemove/{ids}")
    @PreAuthorize("hasPermission('/system/file/remove','sys:file:remove')")
    public Result batchRemove(@PathVariable("ids") String ids) {
        for (String id : ids.split(CommonConstant.COMMA)) {
            getFileService().remove(id);
        }
        return Result.success("删除成功");
    }
}


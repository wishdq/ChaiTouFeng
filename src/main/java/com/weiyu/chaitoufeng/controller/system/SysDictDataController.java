package com.weiyu.chaitoufeng.controller.system;

import com.weiyu.chaitoufeng.common.constant.ControllerConstant;
import com.weiyu.chaitoufeng.common.result.Result;
import com.weiyu.chaitoufeng.controller.base.BaseController;
import com.weiyu.chaitoufeng.domain.system.SysDictData;
import com.weiyu.chaitoufeng.service.system.ISysDictDataService;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * Description:
 * Since: 2022-03-27 21:37
 * Author: wish_dq
 */
@RestController
@Api(tags = {"字典类型"})
@RequestMapping(ControllerConstant.API_SYSTEM_PREFIX + "dictData")
public class SysDictDataController extends BaseController {

    @Resource
    private ISysDictDataService sysDictDataService;
    /**
     * Describe: 根据字典code获取数据字典列表数据
     * Param: typeCode
     * Return: Result
     */
    @GetMapping("selectByCode")
    public Result selectByCode(String typeCode) {
        List<SysDictData> list = sysDictDataService.selectByCode(typeCode);
        return success(list);
    }
}

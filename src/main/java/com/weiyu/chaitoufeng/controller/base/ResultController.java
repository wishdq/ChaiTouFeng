package com.weiyu.chaitoufeng.controller.base;

import com.weiyu.chaitoufeng.common.result.Result;
import com.weiyu.chaitoufeng.domain.response.ResultTable;
import com.weiyu.chaitoufeng.domain.response.ResultTree;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

/**
 * Description: 统一响应 Controller
 * Since: 2022-03-18 19:06
 * Author: wish_dq
 */
public class ResultController {

    /**
     * Describe: 返回 Tree 数据
     * Return Tree数据
     */
    protected static ResultTree dataTree(Object data) {
        ResultTree tree = new ResultTree();
        tree.setData(data);
        return tree;
    }

    /**
     * Describe: 返回数据表格数据 分页
     * Return 表格分页数据
     */
    public static ResultTable pageTable(Object data, long count) {
        return ResultTable.pageTable(count, data);
    }

    /**
     * Describe: 返回数据表格数据
     * Return 表格分页数据
     */
    protected static ResultTable dataTable(Object data) {
        return ResultTable.dataTable(data);
    }

    /**
     * Describe: 返回树状表格数据 分页
     * Return 表格分页数据
     */
    protected static ResultTable treeTable(Object data) {
        return ResultTable.dataTable(data);
    }

    /**
     * 成功操作
     */
    public Result success() {
        return Result.success();
    }

    public Result success(String msg) {
        return Result.success(msg);
    }

    public Result success(Object data) {
        return Result.success(data);
    }

    public Result success(String msg, Object data) {
        return Result.success(msg, data);
    }

    public Result success(int code, String message, Object data) {
        return Result.success(code, message, data);
    }

    /**
     * 失败操作
     */
    public Result failure() {
        return Result.failure();
    }

    public Result failure(String msg) {
        return Result.failure(msg);
    }

    public Result failure(String msg, Object data) {
        return Result.failure(msg, data);
    }

    public Result failure(int code, String msg, Object data) {
        return Result.failure(code, msg, data);
    }

    /**
     * 选择返回
     */
    public Result decide(Boolean b) {
        return Result.decide(b);
    }

    public Result decide(Boolean b, String success, String failure) {
        return Result.decide(b, success, failure);
    }

    public Result decide(int result) {
        if (result > 0) {
            return Result.decide(true);
        } else {
            return Result.decide(false);
        }
    }

    public Result decide(int result, String success, String failure) {
        if (result > 0) {
            return Result.decide(true, success, failure);
        } else {
            return Result.decide(false, success, failure);
        }
    }

    /**
     * 页面跳转
     */
    public ModelAndView jumpPage(String path) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName(path);
        return modelAndView;
    }

    /**
     * 带参数的页面跳转
     */
    public ModelAndView jumpPage(String path, Map<String, ?> params) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName(path);
        modelAndView.addAllObjects(params);
        return modelAndView;
    }
    ////
    //public ModelAndView addData(Map<String,?> paras){
    //    ModelAndView modelAndView = new ModelAndView();
    //    modelAndView.addAllObjects(paras);
    //    return modelAndView;
    //}

}

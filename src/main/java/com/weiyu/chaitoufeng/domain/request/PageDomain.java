package com.weiyu.chaitoufeng.domain.request;

import lombok.Data;

/**
 * Description: 分 页 参 数 封 装
 * Since: 2022-03-12 23:13
 * Author: wish_dq
 */
@Data
public class PageDomain {

    /**
     * 当前页
     */
    private Integer page;

    /**
     * 每页数量
     */
    private Integer limit;

    /**
     * 获取开始的数据行
     */
    public Integer start() {
        return (this.page - 1) * this.limit;
    }

    /**
     * 获取结束的数据行
     */
    public Integer end() {
        return this.page * this.limit;
    }

}

package com.weiyu.chaitoufeng.domain.base;

import com.weiyu.chaitoufeng.domain.BaseDomain;
import lombok.Data;

/**
 * Description: 权限领域模型
 * Since: 2022-03-14 0:34
 * Author: wish_dq
 */
@Data
public class SysBasePower extends BaseDomain {

    /**
     * 编号
     */
    private String powerId;

    /**
     * 权限名称
     */
    private String powerName;

    /**
     * 类型
     */
    private String powerType;

    /**
     * 标识
     */
    private String powerCode;

    /**
     * 路径
     */
    private String powerUrl;

    /**
     * 打开方式
     */
    private String openType;

    /**
     * 父级编号
     */
    private String parentId;

    /**
     * 图标
     */
    private String icon;

    /**
     * 排序
     */
    private Integer sort;

    /**
     * 开启
     */
    private Boolean enable;

    /**
     * 计算列 提供给前端组件
     */
    private String checkArr = "0";

}


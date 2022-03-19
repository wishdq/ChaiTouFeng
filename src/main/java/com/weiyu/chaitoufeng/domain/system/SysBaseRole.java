package com.weiyu.chaitoufeng.domain.system;

import com.weiyu.chaitoufeng.domain.BaseDomain;
import lombok.Data;

/**
 * Description: 角色领域模型
 * Since: 2022-03-14 0:37
 * Author: wish_dq
 */
@Data
public class SysBaseRole extends BaseDomain {

    /**
     * 编号
     */
    private String roleId;

    /**
     * 角色名称
     */
    private String roleName;

    /**
     * 角色值
     */
    private String roleCode;

    /**
     * 状态
     */
    private String enable;

    /**
     * 描述
     */
    private String details;

    /**
     * 排序
     */
    private Integer sort;

    /**
     * 提供前端 显示
     */
    private boolean checked = false;
}


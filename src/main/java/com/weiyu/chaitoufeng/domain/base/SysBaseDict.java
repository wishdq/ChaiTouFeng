package com.weiyu.chaitoufeng.domain.base;

import com.weiyu.chaitoufeng.domain.BaseDomain;
import lombok.Data;

/**
 * Description: 字典值领域模型
 * Since: 2022-03-21 0:43
 * Author: wish_dq
 */
@Data
public class SysBaseDict extends BaseDomain {

    /**
     * id 编号
     */
    private String dataId;

    /**
     * 字典显示
     */
    private String dataLabel;

    /**
     * 字典值
     */
    private String dataValue;

    /**
     * 字典类型
     */
    private String typeCode;

    /**
     * 是否为默认
     */
    private String isDefault;

    /**
     * 是否启用
     */
    private String enable;

}

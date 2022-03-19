package com.weiyu.chaitoufeng.domain.system;

import com.weiyu.chaitoufeng.domain.BaseDomain;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.ibatis.type.Alias;

/**
 * Description: 角色领域模型
 * Since: 2022-03-13 23:12
 * Author: wish_dq
 */
@Data
// lombok:在子类方法中实现父类的属性比较
@EqualsAndHashCode(callSuper = true)
@Alias("SysRole")
public class SysRole extends BaseDomain {

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

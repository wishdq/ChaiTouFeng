package com.weiyu.chaitoufeng.domain.system;

import lombok.Data;
import org.apache.ibatis.type.Alias;

/**
 * Description: 角色权限映射关系
 * Since: 2022-03-13 23:13
 * Author: wish_dq
 */
@Data
@Alias("SysRolePower")
public class SysRolePower {

    /**
     * 映射编号
     */
    private String id;

    /**
     * 角色编号
     */
    private String roleId;

    /**
     * 权限编号
     */
    private String powerId;

}

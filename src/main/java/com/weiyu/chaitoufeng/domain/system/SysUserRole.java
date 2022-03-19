package com.weiyu.chaitoufeng.domain.system;

import lombok.Data;
import org.apache.ibatis.type.Alias;

/**
 * Description: 用户角色映射关系
 * Since: 2022-03-13 23:11
 * Author: wish_dq
 */
@Data
@Alias("SysUserRole")
public class SysUserRole {

    /**
     * 映射标识
     */
    private String id;

    /**
     * 用户编号
     */
    private String userId;

    /**
     * 角色编号
     */
    private String roleId;

}

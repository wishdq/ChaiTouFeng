package com.weiyu.chaitoufeng.domain.system;

import com.weiyu.chaitoufeng.domain.BaseDomain;
import lombok.Data;
import org.apache.ibatis.type.Alias;

/**
 * Description: 字典值领域模型
 * Since: 2022-03-18 21:35
 * Author: wish_dq
 */
@Data
@Alias("SysDept")
public class SysDept extends BaseDomain {

    /**
     * 部门编号
     */
    private String deptId;

    /**
     * 部门名称
     */
    private String deptName;

    /**
     * 部门地址
     */
    private String address;

    /**
     * 父级编号
     */
    private String parentId;

    /**
     * 负责人
     */
    private String leader;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 状态
     */
    private String status;

    /**
     * 排序
     */
    private Integer sort;

}


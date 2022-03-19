package com.weiyu.chaitoufeng.domain;

import lombok.Data;

/**
 * Description: 用户修改密码接口参数实体
 * Since: 2022-03-18 20:51
 * Author: wish_dq
 */
@Data
public class EditPassword {

    /**
     * 旧密码
     */
    private String oldPassword;

    /**
     * 新密码
     */
    private String newPassword;

    /**
     * 确认密码
     */
    private String confirmPassword;

}

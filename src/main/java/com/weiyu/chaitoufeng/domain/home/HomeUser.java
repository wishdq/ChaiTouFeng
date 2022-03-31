package com.weiyu.chaitoufeng.domain.home;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;
import lombok.Data;
import org.apache.ibatis.type.Alias;

/**
 * 前台用户
 * @TableName home_user
 */
@TableName(value ="home_user")
@Data
@Alias("HomeUser")
public class HomeUser implements Serializable {
    /**
     * 用户主键
     */
    @TableId
    private String userId;

    /**
     * 用户名
     */
    private String username;

    /**
     * 别名
     */
    private String profileName;

    /**
     * 密码
     */
    private String password;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 是否可用
     */
    private String enable;

    /**
     * 性别
     */
    private String sex;

    /**
     * 备注
     */
    private String remark;

    /**
     * 注册时间
     */
    private LocalDateTime createTime;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}
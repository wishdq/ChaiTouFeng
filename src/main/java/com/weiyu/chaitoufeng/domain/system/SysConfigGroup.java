package com.weiyu.chaitoufeng.domain.system;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 系统配置组
 */
@TableName(value ="sys_config_group")
@Data
public class SysConfigGroup implements Serializable {
    /**
     * 配置组id
     */
    @TableId
    private String configGroupId;

    /**
     * 父级id
     */
    private String groupParentId;

    /**
     * 配置组名称
     */
    private String name;

    /**
     * 状态
     */
    private String status;

    /**
     * 排序
     */
    private Integer sort;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}
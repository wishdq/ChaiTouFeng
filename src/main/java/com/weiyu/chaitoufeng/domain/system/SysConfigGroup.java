package com.weiyu.chaitoufeng.domain.system;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;
import org.apache.ibatis.type.Alias;

/**
 * 系统配置组
 */
@TableName(value ="sys_config_group")
@Data
@Alias("SysConfigGroup")
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
     * 是否启用
     */
    private String enable;

    /**
     * 排序
     */
    private Integer sort;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}
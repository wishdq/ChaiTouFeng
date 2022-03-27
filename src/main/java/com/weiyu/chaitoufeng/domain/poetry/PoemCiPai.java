package com.weiyu.chaitoufeng.domain.poetry;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import org.apache.ibatis.type.Alias;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Description: 词牌信息
 * Since: 2022-03-27 15:10
 * Author: wish_dq
 */
@Data
@Alias("PoemCiPai")
public class PoemCiPai implements Serializable {
    /**
     * 词牌ID
     */
    @TableId
    private String ciPaiId;

    /**
     * 词牌名
     */
    private String name;

    /**
     * 词牌描述信息
     */
    private String description;

    /**
     * 最后一次更新
     */
    @TableField( fill = FieldFill.INSERT)
    private LocalDateTime updateTime;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}
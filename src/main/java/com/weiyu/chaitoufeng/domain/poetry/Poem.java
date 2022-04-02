package com.weiyu.chaitoufeng.domain.poetry;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;

import lombok.Data;
import org.apache.ibatis.type.Alias;

/**
 * 古诗文集
 * @TableName poem
 */

@Data
@Alias("Poem")
@TableName(value ="poem")
public class Poem implements Serializable {
    /**
     * 
     */
    @TableId
    private String poemId;

    /**
     * 标题
     */
    private String title;

    /**
     * 朝代
     */
    private String dynasty;

    /**
     * 作者
     */
    private String author;

    /**
     * 内容
     */
    private String content;

    /**
     * 类型
     */
    private String type;

    /**
     * 标签
     */
    private String tags;

    /**
     * 注解
     */
    private String zhujie;

    /**
     * 
     */
    private String shangxi;

    /**
     * 
     */
    private String yiwen;

    /**
     * 收藏数
     */
    private Integer shouCuang;

    /**
     * 喜欢数
     */
    private Integer xiHuan;

    /**
     * 点赞数
     */
    private Integer dianZan;

    private LocalDateTime updateTime;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}
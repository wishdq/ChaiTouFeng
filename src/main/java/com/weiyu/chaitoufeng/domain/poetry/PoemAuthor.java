package com.weiyu.chaitoufeng.domain.poetry;

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
 * 诗集作者表
 * @TableName poem_author
 */

@Data
@Alias("PoemAuthor")
@TableName(value ="poem_author")
public class PoemAuthor implements Serializable {
    /**
     * 作者id
     */
    @TableId("author_id")
    private Object authorId;

    /**
     * 作者名字
     */
    @TableField("name")
    private String name;

    /**
     * 作者名字首字母拼音
     */
    @TableField("name_py")
    private String namePy;

    /**
     * 作者朝代
     */
    @TableField("dynasty")
    private String dynasty;

    /**
     * 简介
     */
    @TableField("brief_intro")
    private String briefIntro;


    /**
     * 
     */
    @TableField("else_intro")
    private String elseIntro;

    /**
     * 出生日期
     */
    @TableField("birth_year")
    private String birthYear;

    /**
     * 死亡日期
     */
    @TableField("dead_year")
    private String deadYear;

    /**
     * 更新时间
     */
    @TableField("update_time")
    private LocalDateTime updateTime;

    /**
     * 
     */
    @TableField("baidu_wiki")
    private String baiduWiki;

    /**
     * 全部作品数
     */
    @TableField("works_count")
    private Integer worksCount;


    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}
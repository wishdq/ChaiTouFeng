package com.weiyu.chaitoufeng.domain.poetry;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.apache.ibatis.type.Alias;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * 诗集引用名句表
 * @TableName poem_quote
 */
@TableName(value ="poem_quote")
@Data
//@Alias("PoemQuote")
public class PoemQuote implements Serializable {
    /**
     * 名句id
     */
    @TableId(value = "quote_id")
    private String quoteId;

    /**
     * 句子
     */
    @TableField(value = "sentence")
    private String sentence;

    /**
     * 名句对应的诗词id
     */
    @TableField(value = "dynasty")
    private String dynasty;

    /**
     * 作者
     */
    @TableField(value = "author")
    private String author;

    /**
     * 标题
     */
    @TableField(value = "title")
    private String title;

    /**
     * 体裁
     */
    @TableField("kind")
    private String kind;

    /**
     * 最后一次更新时间
     */
    @TableField(value = "update_time",fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}
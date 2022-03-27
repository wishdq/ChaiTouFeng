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
 * 
 * @TableName poem_dynasty
 */
@TableName(value ="poem_dynasty")
@Data
@Alias("PoemDynasty")
public class PoemDynasty implements Serializable {


    private String dynastyId;

    /**
     * 统称
     */
    private String parentName;

    /**
     * 朝代名字
     */
    private String name;

    /**
     * 朝代拼音
     */
    private String pinyin;

    /**
     * 
     */
    private String startEnd;

    /**
     * 首都
     */
    private String capital;

    /**
     * 当今位置
     */
    private String nowLocation;

    /**
     * 民族
     */
    private String nation;

    /**
     * 朝代创立者
     */
    private String stateFounder;

    /**
     * 更新时间
     */
    private LocalDateTime updatedTime;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}
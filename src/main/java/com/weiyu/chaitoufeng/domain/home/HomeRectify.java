package com.weiyu.chaitoufeng.domain.home;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;
import org.apache.ibatis.type.Alias;

/**
 * 
 * @TableName poem_rectify
 */
@TableName(value ="poem_rectify")
@Data
@Alias("HomeRectify")
public class HomeRectify implements Serializable {

    @TableId
    private String rectifyId;

    private String poemId;

    private String kind;

    private String status;

    private String content;

    private String contentBefore;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}
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
 * 
 * @TableName home_review
 */
@TableName(value ="home_review")
@Data
@Alias("HomeReview")
public class HomeReview implements Serializable {
    /**
     * 评论ID
     */
    @TableId
    private String reviewId;

    /**
     * 评论内容
     */
    private String content;

    private String reviewUserName;

    private String reviewUserProfileName;


    private String enable;

    /**
     * 评论位置ID
     */
    private String reviewLocationId;

    /**
     * 评论用户ID
     */
    private String reviewUserId;

    /**
     * 评论时间
     */
    private LocalDateTime createTime;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}
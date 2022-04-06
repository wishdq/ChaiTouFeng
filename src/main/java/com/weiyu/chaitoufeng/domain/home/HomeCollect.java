package com.weiyu.chaitoufeng.domain.home;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.type.Alias;

/**
 * 
 * @TableName home_collect
 */
@TableName(value ="home_collect")
@Data
@Alias("HomeCollect")
@NoArgsConstructor
public class HomeCollect implements Serializable {
    /**
     * 
     */
    @TableId
    private String collectId;

    /**
     * 
     */
    private String userId;

    /**
     * 
     */
    private String poemId;

    /**
     * 计算列
     */
    @TableField(exist = false)
    private String title;

    @TableField(exist = false)
    private String dynasty;

    @TableField(exist = false)
    private String author;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    public HomeCollect(String collectId,String userId,String poemId){
        this.collectId = collectId;
        this.userId = userId;
        this.poemId = poemId;
    }
}
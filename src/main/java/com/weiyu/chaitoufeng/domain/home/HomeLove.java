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
 * @TableName home_love
 */
@TableName(value ="home_love")
@Data
@Alias("HomeLove")
@NoArgsConstructor
public class HomeLove implements Serializable {
    /**
     * 
     */
    @TableId
    private String loveId;

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

    public HomeLove(String loveId,String userId,String poemId){
        this.loveId = loveId;
        this.userId = userId;
        this.poemId = poemId;
    }
}
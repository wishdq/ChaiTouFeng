package com.weiyu.chaitoufeng.mapper.site;

import com.weiyu.chaitoufeng.domain.home.HomeReview;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.weiyu.chaitoufeng.domain.home.HomeUser;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
* @author wish_dq
* @description 针对表【home_review】的数据库操作Mapper
* @createDate 2022-03-31 15:24:59
* @Entity com.weiyu.chaitoufeng.domain.home.HomeReview
*/
@Mapper
public interface HomeReviewMapper extends BaseMapper<HomeReview> {

    /**
     * 管理评论页
     */
    List<HomeReview> selectPageList(HomeReview homeReview);

    /**
     * 前台评论页
     */
    List<HomeReview> selectHomePageList(HomeReview homeReview);
}





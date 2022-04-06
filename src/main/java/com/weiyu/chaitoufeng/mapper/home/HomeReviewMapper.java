package com.weiyu.chaitoufeng.mapper.home;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.weiyu.chaitoufeng.domain.home.HomeReview;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* @author wish_dq
* @description 针对表【home_review】的数据库操作Mapper
* @createDate 2022-03-31 15:24:59
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

    /**
     * 根据用户userId，查看评论列表
     */
    List<HomeReview> selectList(@Param("userId") String userId);
}





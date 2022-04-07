package com.weiyu.chaitoufeng.service.home;

import com.baomidou.mybatisplus.extension.service.IService;
import com.github.pagehelper.PageInfo;
import com.weiyu.chaitoufeng.domain.build.PageDomain;
import com.weiyu.chaitoufeng.domain.home.HomeReview;

import java.util.List;

/**
* @author wish_dq
* @description 针对表【home_review】的数据库操作Service
* @createDate 2022-03-31 15:24:59
*/
public interface HomeReviewService extends IService<HomeReview> {

    /**
     * 管理评论页
     */
    PageInfo<HomeReview> getPage(PageDomain pageDomain, HomeReview homeReview);

    /**
     * 前台评论页
     */
    PageInfo<HomeReview> getHomePage(PageDomain pageDomain, HomeReview homeReview);


    /**
     * 个人评论列表
     */
    PageInfo<HomeReview> getProfilePage(PageDomain pageDomain, String userId);

    /**
     * 控制台活动列表
     */
    List<HomeReview> getActiveList();
}

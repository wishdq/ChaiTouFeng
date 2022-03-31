package com.weiyu.chaitoufeng.service.site;

import com.github.pagehelper.PageInfo;
import com.weiyu.chaitoufeng.domain.build.PageDomain;
import com.weiyu.chaitoufeng.domain.home.HomeReview;
import com.baomidou.mybatisplus.extension.service.IService;
import com.weiyu.chaitoufeng.domain.home.HomeUser;

/**
* @author wish_dq
* @description 针对表【home_review】的数据库操作Service
* @createDate 2022-03-31 15:24:59
*/
public interface HomeReviewService extends IService<HomeReview> {

    PageInfo<HomeReview> getPage(PageDomain pageDomain, HomeReview homeReview);
}

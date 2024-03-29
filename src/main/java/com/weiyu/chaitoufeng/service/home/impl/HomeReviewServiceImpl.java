package com.weiyu.chaitoufeng.service.home.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.weiyu.chaitoufeng.domain.build.PageDomain;
import com.weiyu.chaitoufeng.domain.home.HomeLove;
import com.weiyu.chaitoufeng.domain.home.HomeReview;
import com.weiyu.chaitoufeng.mapper.home.HomeReviewMapper;
import com.weiyu.chaitoufeng.service.home.HomeReviewService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.List;

/**
* @author wish_dq
* @description 针对表【home_review】的数据库操作Service实现
* @createDate 2022-03-31 15:24:59
*/
@Service
public class HomeReviewServiceImpl extends ServiceImpl<HomeReviewMapper, HomeReview>
    implements HomeReviewService{

    @Resource
    HomeReviewMapper homeReviewMapper;

    @Override
    public boolean save(HomeReview entity) {
        return super.save(entity);
    }

    /**
     * 管理评论页
     */
    @Override
    public PageInfo<HomeReview> getPage(PageDomain pageDomain, HomeReview homeReview) {
        PageHelper.startPage(pageDomain.getPage(), pageDomain.getLimit());
        List<HomeReview> homeReviews = homeReviewMapper.selectPageList(homeReview);
        return new PageInfo<>(homeReviews);
    }

    /**
     * 前台评论页
     */
    @Override
    public PageInfo<HomeReview> getHomePage(PageDomain pageDomain, HomeReview homeReview) {
        PageHelper.startPage(pageDomain.getPage(), pageDomain.getLimit());
        List<HomeReview> homeReviews = homeReviewMapper.selectHomePageList(homeReview);
        return new PageInfo<>(homeReviews);
    }

    @Override
    public List<HomeReview> getActiveList() {
        return homeReviewMapper.selectActiveList();
    }

    @Override
    public PageInfo<HomeReview> getProfilePage(PageDomain pageDomain, String userId) {
        PageHelper.startPage(pageDomain.getPage(), pageDomain.getLimit());
        List<HomeReview> reviews = homeReviewMapper.selectList(userId);
        return new PageInfo<>(reviews);
    }

    @Override
    public boolean removeById(HomeReview entity) {
        return super.removeById(entity);
    }

    @Override
    public boolean removeBatchByIds(Collection<?> list) {
        return super.removeBatchByIds(list);
    }

    @Override
    public boolean updateById(HomeReview entity) {
        return super.updateById(entity);
    }
}





package com.weiyu.chaitoufeng.service.site.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.weiyu.chaitoufeng.domain.build.PageDomain;
import com.weiyu.chaitoufeng.domain.home.HomeReview;
import com.weiyu.chaitoufeng.domain.home.HomeUser;
import com.weiyu.chaitoufeng.mapper.site.HomeUserMapper;
import com.weiyu.chaitoufeng.service.site.HomeReviewService;
import com.weiyu.chaitoufeng.mapper.site.HomeReviewMapper;
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

    @Override
    public PageInfo<HomeReview> getPage(PageDomain pageDomain, HomeReview homeReview) {
        PageHelper.startPage(pageDomain.getPage(), pageDomain.getLimit());
        List<HomeReview> homeReviews = homeReviewMapper.selectPageList(homeReview);
        return new PageInfo<>(homeReviews);
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





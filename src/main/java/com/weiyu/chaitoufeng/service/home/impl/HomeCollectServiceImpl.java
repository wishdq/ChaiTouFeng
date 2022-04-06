package com.weiyu.chaitoufeng.service.home.impl;


import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.weiyu.chaitoufeng.domain.build.PageDomain;
import com.weiyu.chaitoufeng.domain.home.HomeCollect;
import com.weiyu.chaitoufeng.domain.poetry.PoemQuote;
import com.weiyu.chaitoufeng.mapper.home.HomeCollectMapper;
import com.weiyu.chaitoufeng.service.home.HomeCollectService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
* @author wish_dq
* @description 针对表【home_collect】的数据库操作Service实现
* @createDate 2022-04-06 14:48:02
*/
@Service
public class HomeCollectServiceImpl extends ServiceImpl<HomeCollectMapper, HomeCollect>
    implements HomeCollectService {

    @Resource
    HomeCollectMapper collectMapper;

    @Override
    public PageInfo<HomeCollect> getPage(PageDomain pageDomain, String userId) {
        PageHelper.startPage(pageDomain.getPage(), pageDomain.getLimit());
        List<HomeCollect> collects = collectMapper.selectList(userId);
        return new PageInfo<>(collects);
    }

    @Override
    public boolean save(HomeCollect entity) {
        return super.save(entity);
    }

    @Override
    public boolean isHadCollect(HomeCollect collect) {
        return collectMapper.hadCollectCount(collect) > 0;
    }

    @Override
    public boolean removeById(HomeCollect entity) {
        return super.removeById(entity);
    }

    @Override
    public long count(Wrapper<HomeCollect> queryWrapper) {
        return super.count(queryWrapper);
    }

    @Override
    public List<HomeCollect> list(Wrapper<HomeCollect> queryWrapper) {
        return super.list(queryWrapper);
    }
}





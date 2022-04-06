package com.weiyu.chaitoufeng.service.home.impl;


import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.weiyu.chaitoufeng.domain.build.PageDomain;
import com.weiyu.chaitoufeng.domain.home.HomeCollect;
import com.weiyu.chaitoufeng.domain.home.HomeLove;
import com.weiyu.chaitoufeng.service.home.HomeLoveService;
import com.weiyu.chaitoufeng.mapper.home.HomeLoveMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author wish_dq
 * @description 针对表【home_love】的数据库操作Service实现
 * @createDate 2022-04-06 14:48:02
 */
@Service
public class HomeLoveServiceImpl extends ServiceImpl<HomeLoveMapper, HomeLove> implements HomeLoveService {

    @Resource
    HomeLoveMapper loveMapper;

    @Override
    public PageInfo<HomeLove> getPage(PageDomain pageDomain, String userId) {
        PageHelper.startPage(pageDomain.getPage(), pageDomain.getLimit());
        List<HomeLove> loves = loveMapper.selectList(userId);
        return new PageInfo<>(loves);
    }

    @Override
    public boolean save(HomeLove entity) {
        return super.save(entity);
    }

    @Override
    public boolean isHadLove(HomeLove love) {
        return loveMapper.hadLoveCount(love)>0;
    }

    @Override
    public boolean removeById(HomeLove entity) {
        return super.removeById(entity);
    }

    @Override
    public long count(Wrapper<HomeLove> queryWrapper) {
        return super.count(queryWrapper);
    }

    @Override
    public List<HomeLove> list(Wrapper<HomeLove> queryWrapper) {
        return super.list(queryWrapper);
    }
}





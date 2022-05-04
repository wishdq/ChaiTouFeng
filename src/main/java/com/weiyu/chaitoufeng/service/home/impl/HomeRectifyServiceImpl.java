package com.weiyu.chaitoufeng.service.home.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.weiyu.chaitoufeng.domain.build.PageDomain;
import com.weiyu.chaitoufeng.domain.home.HomeRectify;
import com.weiyu.chaitoufeng.domain.poetry.Poem;
import com.weiyu.chaitoufeng.service.home.HomeRectifyService;
import com.weiyu.chaitoufeng.mapper.home.HomeRectifyMapper;
import com.weiyu.chaitoufeng.service.poetry.PoemService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
* @author wish_dq
* @description 针对表【poem_rectify】的数据库操作Service实现
* @createDate 2022-05-04 17:21:01
*/
@Service
public class HomeRectifyServiceImpl extends ServiceImpl<HomeRectifyMapper, HomeRectify>
    implements HomeRectifyService {

    @Resource
    HomeRectifyMapper homeRectifyMapper;

    @Resource
    PoemService poemService;

    @Override
    public PageInfo<HomeRectify> getPage(PageDomain pageDomain, HomeRectify homeRectify) {
        PageHelper.startPage(pageDomain.getPage(), pageDomain.getLimit());
        List<HomeRectify> homeRectifies = homeRectifyMapper.selectPageList(homeRectify);
        return new PageInfo<>(homeRectifies);
    }

    @Override
    public boolean sureUpdate(String rectifyId) {
        HomeRectify homeRectify = homeRectifyMapper.selectById(rectifyId);
        Poem poem = new Poem();
        poem.setPoemId(homeRectify.getPoemId());
        switch (homeRectify.getKind()){
            case "title":poem.setTitle(homeRectify.getContent());break;
            case "author":poem.setAuthor(homeRectify.getContent());break;
            case "dynasty":poem.setDynasty(homeRectify.getContent());break;
            case "content":poem.setContent(homeRectify.getContent());break;
        }
        HomeRectify rectify = new HomeRectify();
        rectify.setRectifyId(rectifyId);
        rectify.setStatus("1");
        homeRectifyMapper.updateById(rectify);
        return poemService.updateById(poem);
    }
}





package com.weiyu.chaitoufeng.service.poetry.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.weiyu.chaitoufeng.domain.poetry.Poem;
import com.weiyu.chaitoufeng.domain.build.PageDomain;
import com.weiyu.chaitoufeng.service.poetry.PoemService;
import com.weiyu.chaitoufeng.mapper.poetry.PoemMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
* @author wish_dq
* @description 针对表【poem(古诗文集)】的数据库操作Service实现
* @createDate 2022-03-28 22:34:32
*/
@Service
public class PoemServiceImpl extends ServiceImpl<PoemMapper, Poem>
    implements PoemService{

    @Resource
    PoemMapper poemMapper;

    @Override
    public List<Poem> randoms(Integer objects){
        return poemMapper.random(objects);
    }

    @Override
    public PageInfo<Poem> page(Poem poem, PageDomain pageDomain) {
        PageHelper.startPage(pageDomain.getPage(), pageDomain.getLimit());
        List<Poem> poems = poemMapper.selectList(poem);
        return new PageInfo<>(poems);
    }

    @Override
    public List<Poem> topZhanZan(PageDomain pageDomain) {
        return poemMapper.topList(pageDomain.start(),pageDomain.getLimit());
    }

    @Override
    public boolean save(Poem entity) {
        return super.save(entity);
    }

    @Override
    public boolean removeById(Poem entity) {
        return super.removeById(entity);
    }

    @Override
    public boolean removeBatchByIds(Collection<?> list) {
        return super.removeBatchByIds(list);
    }

    @Override
    public boolean updateById(Poem entity) {
        return super.updateById(entity);
    }
}





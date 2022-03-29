package com.weiyu.chaitoufeng.service.poetry.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.weiyu.chaitoufeng.domain.poetry.PoemAuthor;
import com.weiyu.chaitoufeng.domain.build.PageDomain;
import com.weiyu.chaitoufeng.service.poetry.PoemAuthorService;
import com.weiyu.chaitoufeng.mapper.poetry.PoemAuthorMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.List;

/**
 * 针对表【poem_author(诗集作者表)】的数据库操作Service实现
 */
@Service
public class PoemAuthorServiceImpl extends ServiceImpl<PoemAuthorMapper, PoemAuthor>
    implements PoemAuthorService{

    @Resource
    PoemAuthorMapper authorMapper;

    @Override
    public boolean save(PoemAuthor entity) {
        return super.save(entity);
    }

    @Override
    public PageInfo<PoemAuthor> getPage(PageDomain pageDomain, PoemAuthor author) {
        PageHelper.startPage(pageDomain.getPage(), pageDomain.getLimit());
        List<PoemAuthor> authors = authorMapper.selectList(author);
        return new PageInfo<>(authors);
    }


    @Override
    public boolean removeById(PoemAuthor entity) {
        return super.removeById(entity);
    }

    @Override
    public boolean removeByIds(Collection<?> list) {
        return super.removeByIds(list);
    }

    @Override
    public boolean updateById(PoemAuthor author) {
        int result = authorMapper.updateById(author);
        return result > 0;
    }
}





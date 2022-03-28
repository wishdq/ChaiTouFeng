package com.weiyu.chaitoufeng.service.poetry.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.weiyu.chaitoufeng.domain.poetry.PoemQuote;
import com.weiyu.chaitoufeng.domain.request.PageDomain;
import com.weiyu.chaitoufeng.mapper.poetry.PoemQuoteMapper;
import com.weiyu.chaitoufeng.service.poetry.PoemQuoteService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.List;

/**
* @author wish_dq
* @description 针对表【poem_quote(诗集引用名句表)】的数据库操作Service实现
* @createDate 2022-03-28 10:36:31
*/

@Service
public class PoemQuoteServiceImpl extends ServiceImpl<PoemQuoteMapper, PoemQuote> implements PoemQuoteService{

    @Resource
    PoemQuoteMapper quoteMapper;

    public PoemQuoteServiceImpl() {
        super();
    }

    @Override
    public PageInfo<PoemQuote> getPage(PageDomain pageDomain, PoemQuote quote) {
        PageHelper.startPage(pageDomain.getPage(), pageDomain.getLimit());
        List<PoemQuote> quotes = quoteMapper.selectList(quote);
        return new PageInfo<>(quotes);
    }

    @Override
    public boolean save(PoemQuote entity) {
        return super.save(entity);
    }

    @Override
    public boolean removeById(PoemQuote entity) {
        return super.removeById(entity);
    }

    @Override
    public boolean removeBatchByIds(Collection<?> list) {
        return super.removeBatchByIds(list);
    }

    @Override
    public boolean updateById(PoemQuote entity) {
        return super.updateById(entity);
    }
//@Override
    //public boolean updateById(PoemQuote entity) {
    //    return super.updateById(entity);
    //}
}

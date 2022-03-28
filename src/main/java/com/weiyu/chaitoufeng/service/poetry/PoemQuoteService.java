package com.weiyu.chaitoufeng.service.poetry;

import com.baomidou.mybatisplus.extension.service.IService;
import com.github.pagehelper.PageInfo;
import com.weiyu.chaitoufeng.domain.poetry.PoemCiPai;
import com.weiyu.chaitoufeng.domain.poetry.PoemQuote;
import com.weiyu.chaitoufeng.domain.request.PageDomain;

import java.util.List;

/**
* @author wish_dq
* @description 针对表【poem_quote(诗集引用名句表)】的数据库操作Service
* @createDate 2022-03-28 10:36:31
*/
public interface PoemQuoteService extends IService<PoemQuote> {


    PageInfo<PoemQuote> getPage(PageDomain pageDomain,PoemQuote quote);

}

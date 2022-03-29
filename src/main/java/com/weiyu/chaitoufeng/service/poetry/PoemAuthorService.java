package com.weiyu.chaitoufeng.service.poetry;

import com.github.pagehelper.PageInfo;
import com.weiyu.chaitoufeng.domain.poetry.PoemAuthor;
import com.baomidou.mybatisplus.extension.service.IService;
import com.weiyu.chaitoufeng.domain.build.PageDomain;

/**
* @author wish_dq
* @description 针对表【poem_author(诗集作者表)】的数据库操作Service
* @createDate 2022-03-28 15:50:21
*/
public interface PoemAuthorService extends IService<PoemAuthor> {

    PageInfo<PoemAuthor> getPage(PageDomain pageDomain, PoemAuthor author);


    boolean updateById(PoemAuthor author);



}

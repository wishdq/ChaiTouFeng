package com.weiyu.chaitoufeng.service.poetry;

import com.github.pagehelper.PageInfo;
import com.weiyu.chaitoufeng.domain.poetry.Poem;
import com.baomidou.mybatisplus.extension.service.IService;
import com.weiyu.chaitoufeng.domain.build.PageDomain;

/**
* @author wish_dq
* @description 针对表【poem(古诗文集)】的数据库操作Service
* @createDate 2022-03-28 22:34:32
*/
public interface PoemService extends IService<Poem> {

    PageInfo<Poem> page(Poem poem, PageDomain pageDomain);

}

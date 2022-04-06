package com.weiyu.chaitoufeng.service.home;


import com.baomidou.mybatisplus.extension.service.IService;
import com.github.pagehelper.PageInfo;
import com.weiyu.chaitoufeng.domain.build.PageDomain;
import com.weiyu.chaitoufeng.domain.home.HomeCollect;
import com.weiyu.chaitoufeng.domain.poetry.PoemQuote;

/**
 * @author wish_dq
 * @description 针对表【home_collect】的数据库操作Service
 * @createDate 2022-04-06 14:48:02
 */
public interface HomeCollectService extends IService<HomeCollect> {

    boolean isHadCollect(HomeCollect collect);

    PageInfo<HomeCollect> getPage(PageDomain pageDomain, String userId);

}

package com.weiyu.chaitoufeng.service.home;


import com.baomidou.mybatisplus.extension.service.IService;
import com.github.pagehelper.PageInfo;
import com.weiyu.chaitoufeng.domain.build.PageDomain;
import com.weiyu.chaitoufeng.domain.home.HomeCollect;
import com.weiyu.chaitoufeng.domain.home.HomeLove;

/**
* @author wish_dq
* @description 针对表【home_love】的数据库操作Service
* @createDate 2022-04-06 14:48:02
*/
public interface HomeLoveService extends IService<HomeLove> {


    boolean isHadLove(HomeLove love);

    PageInfo<HomeLove> getPage(PageDomain pageDomain, String userId);
}

package com.weiyu.chaitoufeng.service.home;

import com.github.pagehelper.PageInfo;
import com.weiyu.chaitoufeng.domain.build.PageDomain;
import com.weiyu.chaitoufeng.domain.home.HomeRectify;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author wish_dq
* @description 针对表【poem_rectify】的数据库操作Service
* @createDate 2022-05-04 17:21:01
*/
public interface HomeRectifyService extends IService<HomeRectify> {

    PageInfo<HomeRectify> getPage(PageDomain pageDomain, HomeRectify homeRectify);

    boolean sureUpdate(String rectifyId);
}

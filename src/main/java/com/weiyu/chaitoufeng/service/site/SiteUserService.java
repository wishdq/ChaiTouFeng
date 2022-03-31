package com.weiyu.chaitoufeng.service.site;

import com.github.pagehelper.PageInfo;
import com.weiyu.chaitoufeng.domain.build.PageDomain;
import com.weiyu.chaitoufeng.domain.home.HomeUser;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author wish_dq
* @description 针对表【home_user(前台用户)】的数据库操作Service
* @createDate 2022-03-31 13:10:01
*/
public interface SiteUserService extends IService<HomeUser> {

    PageInfo<HomeUser> getPage(PageDomain pageDomain, HomeUser homeUser);

}

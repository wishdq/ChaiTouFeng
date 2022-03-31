package com.weiyu.chaitoufeng.service.site.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.weiyu.chaitoufeng.domain.build.PageDomain;
import com.weiyu.chaitoufeng.domain.home.HomeUser;
import com.weiyu.chaitoufeng.service.site.SiteUserService;
import com.weiyu.chaitoufeng.mapper.site.HomeUserMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.List;

/**
* @author wish_dq
* @description 针对表【home_user(前台用户)】的数据库操作Service实现
* @createDate 2022-03-31 13:10:01
*/
@Service
public class SiteUserServiceImpl extends ServiceImpl<HomeUserMapper, HomeUser>
    implements SiteUserService {

    @Resource
    HomeUserMapper homeUserMapper;

    @Override
    public boolean save(HomeUser entity) {
        return super.save(entity);
    }



    @Override
    public PageInfo<HomeUser> getPage(PageDomain pageDomain, HomeUser homeUser) {
        PageHelper.startPage(pageDomain.getPage(), pageDomain.getLimit());
        List<HomeUser> homeUsers = homeUserMapper.selectPageList(homeUser);
        return new PageInfo<>(homeUsers);
    }


    @Override
    public boolean removeById(HomeUser entity) {
        return super.removeById(entity);
    }

    @Override
    public boolean removeBatchByIds(Collection<?> list) {
        return super.removeBatchByIds(list);
    }

    @Override
    public boolean updateById(HomeUser entity) {
        return super.updateById(entity);
    }
}





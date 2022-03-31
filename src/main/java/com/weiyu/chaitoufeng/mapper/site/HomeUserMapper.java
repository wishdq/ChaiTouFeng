package com.weiyu.chaitoufeng.mapper.site;

import com.weiyu.chaitoufeng.domain.home.HomeUser;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
* @author wish_dq
* @description 针对表【home_user(前台用户)】的数据库操作Mapper
* @createDate 2022-03-31 13:10:01
* @Entity com.weiyu.chaitoufeng.domain.home.HomeUser
*/
@Mapper
public interface HomeUserMapper extends BaseMapper<HomeUser> {

    List<HomeUser> selectPageList(HomeUser homeUser);

}





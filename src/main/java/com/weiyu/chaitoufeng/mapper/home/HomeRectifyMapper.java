package com.weiyu.chaitoufeng.mapper.home;

import com.weiyu.chaitoufeng.domain.home.HomeRectify;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
* @author wish_dq
* @description 针对表【poem_rectify】的数据库操作Mapper
* @createDate 2022-05-04 17:21:01
* @Entity com.weiyu.chaitoufeng.domain.home.PoemRectify
*/
@Mapper
public interface HomeRectifyMapper extends BaseMapper<HomeRectify> {

    List<HomeRectify> selectPageList(HomeRectify homeRectify);
}





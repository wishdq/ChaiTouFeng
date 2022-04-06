package com.weiyu.chaitoufeng.mapper.home;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.weiyu.chaitoufeng.domain.home.HomeCollect;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* @author wish_dq
* @description 针对表【home_collect】的数据库操作Mapper
* @createDate 2022-04-06 14:48:02
*/
@Mapper
public interface HomeCollectMapper extends BaseMapper<HomeCollect> {

    int hadCollectCount(HomeCollect collect);

    /**
     * 根据用户userId，查看收藏列表
     */
    List<HomeCollect> selectList(@Param("userId") String userId);
}





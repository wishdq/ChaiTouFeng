package com.weiyu.chaitoufeng.mapper.home;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.weiyu.chaitoufeng.domain.home.HomeLove;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* @author wish_dq
* @description 针对表【home_love】的数据库操作Mapper
* @createDate 2022-04-06 14:48:02
*/
public interface HomeLoveMapper extends BaseMapper<HomeLove> {

    int hadLoveCount(HomeLove love);

    /**
     * 根据用户userId，查看喜欢列表
     */
    List<HomeLove> selectList(@Param("userId") String userId);
}





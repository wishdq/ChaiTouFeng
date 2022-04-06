package com.weiyu.chaitoufeng.mapper.poetry;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.weiyu.chaitoufeng.domain.poetry.PoemAuthor;
import org.apache.ibatis.annotations.Mapper;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
* @author wish_dq
* @description 针对表【poem_author(诗集作者表)】的数据库操作Mapper
* @createDate 2022-03-28 15:50:21
*/
@Mapper
public interface PoemAuthorMapper extends BaseMapper<PoemAuthor> {

    @Override
    int insert(PoemAuthor entity);

    List<PoemAuthor> selectList(PoemAuthor author);

    @Override
    int deleteById(Serializable id);

    @Override
    int deleteBatchIds(Collection<?> idList);

    @Override
    int updateById(PoemAuthor entity);
}





package com.weiyu.chaitoufeng.mapper.poetry;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.weiyu.chaitoufeng.domain.poetry.PoemQuote;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
* @author wish_dq
* @description 针对表【poem_quote(诗集引用名句表)】的数据库操作Mapper
* @createDate 2022-03-28 10:36:31
* @Entity com.weiyu.chaitoufeng.domain.poetry.PoemQuote
*/
@Mapper
public interface PoemQuoteMapper extends BaseMapper<PoemQuote> {

    List<PoemQuote> selectList(PoemQuote quote);

    @Override
    int insert(PoemQuote entity);


    int updateById(PoemQuote entity);


    //@Delete("delete  from poem_quote where quote_id = #{quoteId}")
    //int deleteById(String id);

    @Override
    int deleteById(Serializable id);

    @Override
    int deleteBatchIds(Collection<?> idList);


}

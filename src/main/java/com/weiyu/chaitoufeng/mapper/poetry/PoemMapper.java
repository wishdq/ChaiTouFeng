package com.weiyu.chaitoufeng.mapper.poetry;

import com.weiyu.chaitoufeng.domain.poetry.Poem;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.weiyu.chaitoufeng.domain.poetry.PoemCiPai;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
* @author wish_dq
* @description 针对表【poem(古诗文集)】的数据库操作Mapper
* @createDate 2022-03-28 22:34:32
* @Entity com.weiyu.chaitoufeng.domain.poetry.Poem
*/
@Mapper
public interface PoemMapper extends BaseMapper<Poem> {

    List<Poem> selectList(Poem poem);


    List<Poem> random(Integer objects);
}





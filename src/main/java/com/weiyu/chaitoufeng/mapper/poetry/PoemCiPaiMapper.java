package com.weiyu.chaitoufeng.mapper.poetry;

import com.weiyu.chaitoufeng.domain.poetry.PoemCiPai;

import java.util.List;


/**
 * Description: 针对表【poems_cipai(词牌信息)】的数据库操作Mapper
 * Since: 2022-03-27 17:38
 * Author: wish_dq
 */
public interface PoemCiPaiMapper {

    List<PoemCiPai> selectList(PoemCiPai ciPai);

    PoemCiPai getById(String ciPaiId);

    Boolean insert(PoemCiPai ciPai);

    Boolean deleteById(String ciPaiId);

    Boolean deleteByIds(String[] ciPaiIds);

    Boolean updateById(PoemCiPai ciPaiId);
}
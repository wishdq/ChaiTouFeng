package com.weiyu.chaitoufeng.mapper.poetry;

import com.weiyu.chaitoufeng.domain.poetry.PoemDynasty;

import java.util.List;

/**
 * Description:
 * Since: 2022-03-27 22:57
 * Author: wish_dq
 */
public interface PoemDynastyMapper {

    List<PoemDynasty> selectList();

    PoemDynasty getById(String dynastyId);

    Boolean insert(PoemDynasty dynasty);

    Boolean updateById(PoemDynasty dynasty);

    Boolean deleteById(String dynastyId);
}

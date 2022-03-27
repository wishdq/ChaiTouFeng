package com.weiyu.chaitoufeng.service.poetry;

import com.weiyu.chaitoufeng.domain.poetry.PoemDynasty;

import java.util.List;

/**
 * Description:
 * Since: 2022-03-27 22:57
 * Author: wish_dq
 */
public interface PoemDynastyService {

    List<PoemDynasty> list();

    PoemDynasty getById(String dynastyId);

    Boolean save(PoemDynasty dynasty);

    Boolean updateById(PoemDynasty dynasty);

    Boolean removeById(String dynastyId);
}

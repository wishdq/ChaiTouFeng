package com.weiyu.chaitoufeng.service.poetry;

import com.github.pagehelper.PageInfo;
import com.weiyu.chaitoufeng.domain.poetry.PoemCiPai;
import com.weiyu.chaitoufeng.domain.request.PageDomain;

import java.util.List;

/**
 * Description:
 * Since: 2022-03-27 17:38
 * Author: wish_dq
 */
public interface PoemsCiPaiService{

    List<PoemCiPai> list(PoemCiPai ciPai);

    PageInfo<PoemCiPai> page(PoemCiPai ciPai, PageDomain pageDomain);

    PoemCiPai getById(String ciPaiId);

    boolean save(PoemCiPai entity);

    boolean removeById(String ciPaiId);

    boolean batchRemove(String[] ciPaiIds) ;


    boolean updateById(PoemCiPai ciPaiId);
}

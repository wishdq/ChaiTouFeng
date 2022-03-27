package com.weiyu.chaitoufeng.service.poetry;

import com.baomidou.mybatisplus.extension.service.IService;
import com.github.pagehelper.PageInfo;
import com.weiyu.chaitoufeng.domain.poetry.PoemsCiPai;
import com.weiyu.chaitoufeng.domain.request.PageDomain;
import com.weiyu.chaitoufeng.domain.system.SysUser;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * Description:
 * Since: 2022-03-27 17:38
 * Author: wish_dq
 */
public interface PoemsCiPaiService{

    List<PoemsCiPai> list(PoemsCiPai ciPai);

    PageInfo<PoemsCiPai> page(PoemsCiPai ciPai, PageDomain pageDomain);

    PoemsCiPai getById(String ciPaiId);

    boolean save(PoemsCiPai entity);

    boolean removeById(String ciPaiId);

    boolean batchRemove(String[] ciPaiIds) ;


    boolean updateById(PoemsCiPai ciPaiId);
}

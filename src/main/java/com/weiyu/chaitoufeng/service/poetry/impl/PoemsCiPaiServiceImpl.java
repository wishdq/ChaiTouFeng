package com.weiyu.chaitoufeng.service.poetry.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.weiyu.chaitoufeng.domain.poetry.PoemCiPai;
import com.weiyu.chaitoufeng.domain.request.PageDomain;
import com.weiyu.chaitoufeng.mapper.poetry.PoemCiPaiMapper;
import com.weiyu.chaitoufeng.service.poetry.PoemsCiPaiService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Description:
 * Since: 2022-03-27 17:40
 * Author: wish_dq
 */
@Service
public class PoemsCiPaiServiceImpl implements PoemsCiPaiService{

    @Resource
    PoemCiPaiMapper ciPaiMapper;

    @Override
    public List<PoemCiPai> list(PoemCiPai ciPai) {
        return ciPaiMapper.selectList(ciPai);
    }

    @Override
    public PageInfo<PoemCiPai> page(PoemCiPai ciPai, PageDomain pageDomain) {
        PageHelper.startPage(pageDomain.getPage(), pageDomain.getLimit());
        List<PoemCiPai> ciPais = ciPaiMapper.selectList(ciPai);
        return new PageInfo<>(ciPais);
    }

    @Override
    public PoemCiPai getById(String ciPaiId) {
        return ciPaiMapper.getById(ciPaiId);
    }

    @Override
    public boolean save(PoemCiPai entity) {
        return ciPaiMapper.insert(entity);
    }

    @Override
    public boolean removeById(String id) {
        return ciPaiMapper.deleteById(id);
    }

    @Override
    public boolean batchRemove(String[] ciPaiIds) {
        return ciPaiMapper.deleteByIds(ciPaiIds);
    }

    @Override
    public boolean updateById(PoemCiPai ciPaiId) {
        return ciPaiMapper.updateById(ciPaiId);
    }
}
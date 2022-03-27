package com.weiyu.chaitoufeng.service.poetry.impl;

import com.weiyu.chaitoufeng.domain.poetry.PoemDynasty;
import com.weiyu.chaitoufeng.mapper.poetry.PoemDynastyMapper;
import com.weiyu.chaitoufeng.service.poetry.PoemDynastyService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Description:
 * Since: 2022-03-27 22:58
 * Author: wish_dq
 */
@Service
public class PoemDynastyServiceImpl implements PoemDynastyService {

    @Resource
    PoemDynastyMapper dynastyMapper;

    @Override
    public List<PoemDynasty> list() {
        return dynastyMapper.selectList();
    }

    @Override
    public PoemDynasty getById(String dynastyId) {
        return dynastyMapper.getById(dynastyId);
    }

    @Override
    public Boolean save(PoemDynasty dynasty) {
        return dynastyMapper.insert(dynasty);
    }

    @Override
    public Boolean updateById(PoemDynasty dynasty) {
        return dynastyMapper.updateById(dynasty);
    }

    @Override
    public Boolean removeById(String dynastyId) {
        return dynastyMapper.deleteById(dynastyId);
    }
}

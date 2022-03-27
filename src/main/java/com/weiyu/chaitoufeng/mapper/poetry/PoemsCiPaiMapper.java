package com.weiyu.chaitoufeng.mapper.poetry;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.weiyu.chaitoufeng.domain.poetry.PoemsCiPai;

import java.util.List;


/**
 * Description: 针对表【poems_cipai(词牌信息)】的数据库操作Mapper
 * Since: 2022-03-27 17:38
 * Author: wish_dq
 */
public interface PoemsCiPaiMapper {

    List<PoemsCiPai> selectList(PoemsCiPai ciPai);

    PoemsCiPai getById(String ciPaiId);

    Boolean insert(PoemsCiPai ciPai);

    Boolean deleteById(String ciPaiId);

    Boolean deleteByIds(String[] ciPaiIds);

    Boolean updateById(PoemsCiPai ciPaiId);
}
package com.weiyu.chaitoufeng.service.system;

import com.weiyu.chaitoufeng.domain.system.SysDictData;

import java.util.List;

/**
 * Description:
 * Since: 2022-03-27 21:39
 * Author: wish_dq
 */
public interface ISysDictDataService {
    List<SysDictData> selectByCode(String typeCode);
}

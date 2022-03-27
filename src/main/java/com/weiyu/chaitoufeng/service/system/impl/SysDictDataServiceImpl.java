package com.weiyu.chaitoufeng.service.system.impl;

import cn.hutool.extra.spring.SpringUtil;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.weiyu.chaitoufeng.domain.system.SysDictData;
import com.weiyu.chaitoufeng.mapper.system.SysDictDataMapper;
import com.weiyu.chaitoufeng.service.system.ISysDictDataService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * Description:
 * Since: 2022-03-27 21:40
 * Author: wish_dq
 */
@Service
public class SysDictDataServiceImpl implements ISysDictDataService {

    public static LoadingCache<String, List<SysDictData>> loadingCacheSysDictData = CacheBuilder.newBuilder().maximumSize(100).expireAfterWrite(600, TimeUnit.SECONDS).build(new CacheLoader<String, List<SysDictData>>() {
        @Override
        public List<SysDictData> load(String typeCode) {
            SysDictDataMapper tempSysDictDataMapper = SpringUtil.getBean("sysDictDataMapper", SysDictDataMapper.class);
            return tempSysDictDataMapper.selectByCode(typeCode);
        }
    });
    @Override
    public List<SysDictData> selectByCode(String typeCode) {
        try {
            return loadingCacheSysDictData.get(typeCode);
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();

    }
}

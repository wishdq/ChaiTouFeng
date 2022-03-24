package com.weiyu.chaitoufeng.service.impl;
//
//
//import cn.hutool.extra.spring.SpringUtil;
//import com.github.pagehelper.PageHelper;
//import com.github.pagehelper.PageInfo;
//import com.google.common.cache.CacheBuilder;
//import com.google.common.cache.CacheLoader;
//import com.google.common.cache.LoadingCache;
//import com.weiyu.chaitoufeng.domain.request.PageDomain;
//import com.weiyu.chaitoufeng.domain.system.SysDictData;
//import com.weiyu.chaitoufeng.mapper.SysDictDataMapper;
//import com.weiyu.chaitoufeng.service.ISysDictDataService;
//import org.springframework.stereotype.Service;
//
//import javax.annotation.Resource;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.concurrent.ExecutionException;
//import java.util.concurrent.TimeUnit;
//
/**
 * Description: 字典值服务实现类
 * Since: 2022-03-21 0:22
 * Author: wish_dq
 */

import cn.hutool.extra.spring.SpringUtil;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.weiyu.chaitoufeng.domain.system.SysDictData;
import com.weiyu.chaitoufeng.mapper.SysDictDataMapper;
import com.weiyu.chaitoufeng.service.ISysDictDataService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

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
//
//    public static LoadingCache<String, List<SysDictData>> loadingCacheSysDictData = CacheBuilder.newBuilder().maximumSize(100).expireAfterWrite(600, TimeUnit.SECONDS).build(new CacheLoader<String, List<SysDictData>>() {
//        @Override
//        public List<SysDictData> load(String typeCode) {
//            SysDictDataMapper tempSysDictDataMapper = SpringUtil.getBean("sysDictDataMapper", SysDictDataMapper.class);
//            return tempSysDictDataMapper.selectByCode(typeCode);
//        }
//    });
//    @Resource
//    private SysDictDataMapper sysDictDataMapper;
//
//    @Override
//    public List<SysDictData> list(SysDictData sysDictData) {
//        return sysDictDataMapper.selectList(sysDictData);
//    }
//
//    @Override
//    public List<SysDictData> selectByCode(String typeCode) {
//        try {
//            return loadingCacheSysDictData.get(typeCode);
//        } catch (ExecutionException e) {
//            e.printStackTrace();
//        }
//        return new ArrayList<>();
//
//    }
//
//    @Override
//    public void refreshCacheTypeCode(String typeCode) {
//        try {
//            loadingCacheSysDictData.refresh(typeCode);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    @Override
//    public PageInfo<SysDictData> page(SysDictData sysDictData, PageDomain pageDomain) {
//        PageHelper.startPage(pageDomain.getPage(), pageDomain.getLimit());
//        List<SysDictData> list = sysDictDataMapper.selectList(sysDictData);
//        return new PageInfo<>(list);
//    }
//
//    @Override
//    public Boolean save(SysDictData sysDictData) {
//        Integer result = sysDictDataMapper.insert(sysDictData);
//        if (result > 0) {
//            refreshCacheTypeCode(sysDictData.getTypeCode());
//            return true;
//        } else {
//            return false;
//        }
//    }
//
//    @Override
//    public SysDictData getById(String id) {
//        return sysDictDataMapper.selectById(id);
//    }
//
//    @Override
//    public Boolean updateById(SysDictData sysDictData) {
//        int result = sysDictDataMapper.updateById(sysDictData);
//        if (result > 0) {
//            refreshCacheTypeCode(sysDictData.getTypeCode());
//            return true;
//        } else {
//            return false;
//        }
//    }
//
//    @Override
//    public Boolean remove(String id) {
//        SysDictData sysDictData = sysDictDataMapper.selectById(id);
//        if (sysDictData != null) {
//            sysDictDataMapper.deleteById(id);
//            refreshCacheTypeCode(sysDictData.getTypeCode());
//        }
//        return true;
//    }
//}

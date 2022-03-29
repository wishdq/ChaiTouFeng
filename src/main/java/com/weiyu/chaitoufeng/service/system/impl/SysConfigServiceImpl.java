package com.weiyu.chaitoufeng.service.system.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.weiyu.chaitoufeng.service.system.ISysConfigService;
import com.weiyu.chaitoufeng.domain.system.SysConfigGroup;
import com.weiyu.chaitoufeng.domain.build.PageDomain;
import com.weiyu.chaitoufeng.domain.system.SysConfig;
import com.weiyu.chaitoufeng.mapper.system.SysConfigMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Description: 系统配置服务接口实现
 * Since: 2022-03-23 20:51
 * Author: wish_dq
 */
@Service
public class SysConfigServiceImpl implements ISysConfigService {

    /**
     * 系统配置数据库操作接口
     */
    @Resource
    private SysConfigMapper sysConfigMapper;

    /**
     * Describe: 根据条件查询系统配置列表数据
     */
    @Override
    public List<SysConfig> list(SysConfig param) {
        return sysConfigMapper.selectList(param);
    }



    /**
     * Describe: 根据条件查询系统配置列表数据 分页
     */
    @Override
    public PageInfo<SysConfig> page(PageDomain pageDomain,SysConfig param) {
        PageHelper.startPage(pageDomain.getPage(), pageDomain.getLimit());
        List<SysConfig> list = sysConfigMapper.selectList(param);
        return new PageInfo<>(list);
    }

    /**
     * Describe: 保存系统配置数据
     */
    @Override
    public Boolean save(SysConfig sysConfig) {
        Integer result = sysConfigMapper.insert(sysConfig);
        return result > 0;
    }

    /**
     * Describe: 根据 ID 查询系统配置
     */
    @Override
    public SysConfig getById(String id) {
        return sysConfigMapper.selectById(id);
    }

    /**
     * Describe: 根据 Code 查询系统配置
     */
    @Override
    public SysConfig getByCode(String code) {
        return sysConfigMapper.selectByCode(code);
    }

    /**
     * Describe: 根据 ID 修改系统配置
     */
    @Override
    public Boolean updateById(SysConfig sysConfig) {
        int result = sysConfigMapper.updateById(sysConfig);
        return result > 0;
    }

    /**
     * Describe: 根据 ID 删除系统配置
     */
    @Override
    public Boolean remove(String id) {
        Integer result = sysConfigMapper.deleteById(id);
        return result > 0;
    }

    /**
     * Describe: 根据 ID 批量删除系统配置
     */
    @Override
    public Boolean batchRemove(String[] ids) {
        Integer result = sysConfigMapper.deleteByIds(ids);
        return result > 0;
    }

    /**
     * 配置组
     */

    @Override
    public List<SysConfigGroup> tree() {
        return sysConfigMapper.groupTree();
    }

    @Override
    public List<SysConfigGroup> groupData() {
        return sysConfigMapper.groupList(null);
    }


    @Override
    public Boolean addGroup(SysConfigGroup configGroup) {
        Integer result = sysConfigMapper.insertGroup(configGroup);
        return result > 0;
    }

    @Override
    public Boolean updateGroup(SysConfigGroup configGroup) {
        int result = sysConfigMapper.updateGroupById(configGroup);
        return result > 0;
    }

    @Override
    public Boolean removeGroup(String id) {
        Integer result = sysConfigMapper.deleteGroupById(id);
        return result > 0;
    }
}

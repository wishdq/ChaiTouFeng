package com.weiyu.chaitoufeng.service.system;

import com.github.pagehelper.PageInfo;
import com.weiyu.chaitoufeng.domain.system.SysConfigGroup;
import com.weiyu.chaitoufeng.domain.build.PageDomain;
import com.weiyu.chaitoufeng.domain.system.SysConfig;

import java.util.List;

/**
 * Description: 系统配置服务接口
 * Since: 2022-03-23 20:41
 * Author: wish_dq
 */
public interface ISysConfigService {

    /**
     * Describe: 根据条件查询系统配置列表数据
     */
    List<SysConfig> list(SysConfig param);


    /**
     * Describe: 根据条件查询系统配置列表数据 分页
     */
    PageInfo<SysConfig> page(PageDomain pageDomain,SysConfig param);

    /**
     * Describe: 根据 Id 查询系统配置
     */
    SysConfig getById(String id);

    /**
     * Describe: 根据 code 查询系统配置
     */
    SysConfig getByCode(String code);

    /**
     * Describe: 插入 SysConfig 数据
     */
    Boolean save(SysConfig sysConfig);

    /**
     * Describe: 修改 SysConfig 数据
     */
    Boolean updateById(SysConfig sysConfig);

    /**
     * Describe: 删除 SysConfig 数据
     */
    Boolean remove(String id);

    /**
     * Describe: 批量 SysConfig 数据
     */
    Boolean batchRemove(String[] ids);

    /**
     * 获取配置树
     */
    List<SysConfigGroup> tree();

    List<SysConfigGroup> groupData();

    Boolean addGroup(SysConfigGroup configGroup);

    Boolean updateGroup(SysConfigGroup configGroup);

    Boolean removeGroup(String id);


}

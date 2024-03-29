package com.weiyu.chaitoufeng.service.system;

import com.weiyu.chaitoufeng.domain.system.SysPower;

import java.util.List;

/**
 * Description: 权限服务接口类
 * Since: 2022-03-18 22:05
 * Author: wish_dq
 */
public interface ISysPowerService {

    /**
     * Describe: 根据条件查询权限列表数据
     */
    List<SysPower> list(SysPower sysPower);

    /**
     * Describe: 保存权限数据
     */
    boolean save(SysPower sysPower);

    /**
     * Describe: 根据 id 获取权限数据
     */
    SysPower getById(String id);

    /**
     * Describe: 修改权限数据
     */
    boolean update(SysPower sysPower);

    /**
     * Describe: 根据 id 删除权限
     */
    boolean remove(String id);

    /**
     * Describe: 根据 parentId 查询权限
     */
    List<SysPower> selectByParentId(String parentId);
}


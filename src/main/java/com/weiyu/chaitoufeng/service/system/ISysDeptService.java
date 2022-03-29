package com.weiyu.chaitoufeng.service.system;

import com.github.pagehelper.PageInfo;
import com.weiyu.chaitoufeng.domain.system.SysDept;
import com.weiyu.chaitoufeng.domain.build.PageDomain;

import java.util.List;

/**
 * Description: 部门服务接口类
 * Since: 2022-03-18 21:38
 * Author: wish_dq
 */
public interface ISysDeptService {

    /**
     * Describe: 查询部门数据
     */
    List<SysDept> list(SysDept param);

    /**
     * Describe: 分页查询部门数据
     */
    PageInfo<SysDept> page(SysDept param, PageDomain pageDomain);

    /**
     * Describe: 保存部门数据
     */
    boolean save(SysDept sysDept);

    /**
     * Describe: 根据 id 获取部门信息
     */
    SysDept getById(String id);

    /**
     * Describe: 根据 id 修改用户数据
     */
    boolean update(SysDept sysDept);

    /**
     * Describe: 根据 id 删除部门数据
     */
    Boolean remove(String id);

    /**
     * Describe: 根据 id 删除部门数据
     */
    boolean batchRemove(String[] ids);

    /**
     * Describe: 根据 parentId 查询部门数据
     */
    List<SysDept> selectByParentId(String tenantId);
}

package com.weiyu.chaitoufeng.service.impl;

import com.weiyu.chaitoufeng.domain.system.SysPower;
import com.weiyu.chaitoufeng.mapper.SysPowerMapper;
import com.weiyu.chaitoufeng.mapper.SysRolePowerMapper;
import com.weiyu.chaitoufeng.service.ISysPowerService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Description: 权限服务实现类
 * Since: 2022-03-18 22:06
 * Author: wish_dq
 */
@Service
public class SysPowerServiceImpl implements ISysPowerService {

    /**
     * 引入服务
     */
    @Resource
    private SysPowerMapper sysPowerMapper;

    /**
     * 引入角色权限服务
     */
    @Resource
    private SysRolePowerMapper sysRolePowerMapper;

    /**
     * Describe: 查询权限列表
     * Param: SysPower
     * Return: 执行结果
     */
    @Override
    public List<SysPower> list(SysPower sysPower) {
        return sysPowerMapper.selectList(sysPower);
    }

    /**
     * Describe: 保存权限
     * Param: SysPower
     * Return: 执行结果
     */
    @Override
    public boolean save(SysPower sysPower) {
        int result = sysPowerMapper.insert(sysPower);
        return result > 0;
    }

    /**
     * Describe: 根据 ID 查询权限
     * Param: id
     * Return: 返回前线信息
     */
    @Override
    public SysPower getById(String id) {
        return sysPowerMapper.selectById(id);
    }

    /**
     * Describe: 修改权限数据
     * Param: SysPower
     * Return: 执行结果
     */
    @Override
    public boolean update(SysPower sysPower) {
        int result = sysPowerMapper.updateById(sysPower);
        return result > 0;
    }

    /**
     * Describe: 根据 ID 删除权限信息
     * Param: id
     * Return: 执行结果
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean remove(String id) {
        sysPowerMapper.deleteById(id);
        sysRolePowerMapper.deleteByPowerId(id);
        return true;
    }

    /**
     * Describe: 根据 parentId 查询权限
     * Param: parentId
     * Return: 操作结果
     */
    @Override
    public List<SysPower> selectByParentId(String parentId) {
        return sysPowerMapper.selectListByParentId(parentId);
    }
}

//package com.weiyu.chaitoufeng.service.system.impl;
//
//import com.github.pagehelper.PageHelper;
//import com.github.pagehelper.PageInfo;
//import com.weiyu.chaitoufeng.domain.system.SysNotice;
//import com.weiyu.chaitoufeng.domain.build.PageDomain;
//import com.weiyu.chaitoufeng.mapper.system.SysNoticeMapper;
//import com.weiyu.chaitoufeng.service.system.ISysNoticeService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.time.LocalDateTime;
//import java.util.List;
//
///**
// * Description: noticeService业务层处理
// * Since: 2022-03-20 21:39
// * Author: wish_dq
// */
//@Service
//public class SysNoticeServiceImpl implements ISysNoticeService {
//    @Autowired
//    private SysNoticeMapper sysNoticeMapper;
//
//    /**
//     * 查询notice
//     */
//    @Override
//    public SysNotice selectSysNoticeById(String id) {
//        return sysNoticeMapper.selectSysNoticeById(id);
//    }
//
//    /**
//     * 查询notice列表
//     */
//    @Override
//    public List<SysNotice> selectSysNoticeList(SysNotice sysNotice) {
//        return sysNoticeMapper.selectSysNoticeList(sysNotice);
//    }
//
//    /**
//     * 查询notice 分页集合
//     */
//    @Override
//    public PageInfo<SysNotice> selectSysNoticePage(SysNotice sysNotice, PageDomain pageDomain) {
//        PageHelper.startPage(pageDomain.getPage(), pageDomain.getLimit());
//        List<SysNotice> data = sysNoticeMapper.selectSysNoticeList(sysNotice);
//        return new PageInfo<>(data);
//    }
//
//    /**
//     * 新增notice
//     */
//
//    @Override
//    public int insertSysNotice(SysNotice sysNotice) {
//        sysNotice.setCreateTime(LocalDateTime.now());
//        return sysNoticeMapper.insertSysNotice(sysNotice);
//    }
//
//    /**
//     * 修改notice
//     */
//    @Override
//    public int updateSysNotice(SysNotice sysNotice) {
//        return sysNoticeMapper.updateSysNotice(sysNotice);
//    }
//
//    /**
//     * 删除notice对象
//     */
//    @Override
//    public int deleteSysNoticeByIds(String[] ids) {
//        return sysNoticeMapper.deleteSysNoticeByIds(ids);
//    }
//
//    /**
//     * 删除notice信息
//     */
//    @Override
//    public int deleteSysNoticeById(String id) {
//        return sysNoticeMapper.deleteSysNoticeById(id);
//    }
//}

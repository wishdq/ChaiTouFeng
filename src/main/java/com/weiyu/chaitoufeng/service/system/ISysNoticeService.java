package com.weiyu.chaitoufeng.service.system;

import com.github.pagehelper.PageInfo;
import com.weiyu.chaitoufeng.domain.system.SysNotice;
import com.weiyu.chaitoufeng.domain.build.PageDomain;

import java.util.List;

/**
 * Description: noticeService接口
 * Since: 2022-03-20 21:38
 * Author: wish_dq
 */
public interface ISysNoticeService {
    /**
     * 查询notice
     */
    SysNotice selectSysNoticeById(String id);


    /**
     * 查询notice
     */
    PageInfo<SysNotice> selectSysNoticePage(SysNotice sysNotice, PageDomain pageDomain);

    /**
     * 查询notice列表
     */
    List<SysNotice> selectSysNoticeList(SysNotice sysNotice);

    /**
     * 新增notice
     */
    int insertSysNotice(SysNotice sysNotice);

    /**
     * 修改notice
     */
    int updateSysNotice(SysNotice sysNotice);

    /**
     * 批量删除notice
     */
    int deleteSysNoticeByIds(String[] ids);

    /**
     * 删除notice信息
     */
    int deleteSysNoticeById(String id);

}

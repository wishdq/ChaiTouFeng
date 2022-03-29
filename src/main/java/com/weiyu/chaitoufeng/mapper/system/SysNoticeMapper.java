package com.weiyu.chaitoufeng.mapper.system;

import com.weiyu.chaitoufeng.domain.system.SysNotice;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Description: noticeMapper接口
 * Since: 2022-03-20 21:42
 * Author: wish_dq
 */
@Mapper
public interface SysNoticeMapper {
    /**
     * 查询notice
     */
    SysNotice selectSysNoticeById(String id);

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
     * 删除notice
     */
    int deleteSysNoticeById(String id);

    /**
     * 批量删除notice
     */
    int deleteSysNoticeByIds(String[] ids);

}

package com.weiyu.chaitoufeng.mapper;

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
     *
     * @param id noticeID
     * @return notice
     */
    public SysNotice selectSysNoticeById(String id);

    /**
     * 查询notice列表
     *
     * @param sysNotice notice
     * @return notice集合
     */
    List<SysNotice> selectSysNoticeList(SysNotice sysNotice);

    /**
     * 新增notice
     *
     * @param sysNotice notice
     * @return 结果
     */
    int insertSysNotice(SysNotice sysNotice);

    /**
     * 修改notice
     *
     * @param sysNotice notice
     * @return 结果
     */
    int updateSysNotice(SysNotice sysNotice);

    /**
     * 删除notice
     *
     * @param id noticeID
     * @return 结果
     */
    int deleteSysNoticeById(String id);

    /**
     * 批量删除notice
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    int deleteSysNoticeByIds(String[] ids);

}

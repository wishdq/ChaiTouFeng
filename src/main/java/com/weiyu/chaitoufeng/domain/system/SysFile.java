package com.weiyu.chaitoufeng.domain.system;

import com.weiyu.chaitoufeng.domain.BaseDomain;
import lombok.Data;

import java.time.LocalDate;

/**
 * Description: 文 件 接 口 实 体
 * Since: 2022-03-18 18:51
 * Author: wish_dq
 */
@Data
public class SysFile extends BaseDomain {

    /**
     * 文件编号
     */
    private String id;

    /**
     * 文件名称
     */
    private String fileName;

    /**
     * 文件路径
     */
    private String filePath;

    /**
     * 文件类型
     */
    private String fileType;

    /**
     * 文件大小
     */
    private String fileSize;

    /**
     * 文件描述
     */
    private String fileDesc;

    /**
     * 所属日期
     */
    private LocalDate targetDate;

}


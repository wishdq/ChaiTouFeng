package com.weiyu.chaitoufeng.common.server;

import lombok.Data;

/**
 * Description: 系统磁盘信息类
 * Since: 2022-03-17 22:30
 * Author: wish_dq
 */
@Data
public class SysFileInfo {

    /**
     * 盘符路径
     */
    private String dirName;

    /**
     * 盘符类型
     */
    private String sysTypeName;

    /**
     * 文件类型
     */
    private String typeName;

    /**
     * 总大小
     */
    private String total;

    /**
     * 剩余大小
     */
    private String free;

    /**
     * 已经使用量
     */
    private String used;

    /**
     * 资源的使用率
     */
    private double usage;
}

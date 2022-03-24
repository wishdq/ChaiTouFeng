package com.weiyu.chaitoufeng.config.property;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.io.File;

/**
 * Description: 系 统文 件 上 传 配 置 类
 * Since: 2022-03-18 18:45
 * Author: wish_dq
 */
@Data
@Component
@ConfigurationProperties("chaitoufeng.upload")
public class SysFileTemplateProperty {

    // windows 系统文件上传路径
    private String windowsPath;

    // linux 系统文件上传路径
    private String linuxPath;

    // 是否启用ftp服务器
    private boolean ftpUse;

    // ftp服务器url
    private String hostname;

    // ftp服务器端口号
    private int port;

    // ftp服务器用户名
    private String username;

    // ftp服务器密码
    private String password;

    // upload path 根据系统环境获取上传路径
    public String getUploadPath() {
        return '\\' == File.separatorChar ? this.windowsPath : this.linuxPath;
    }

}

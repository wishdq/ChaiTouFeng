package com.weiyu.chaitoufeng.service.system;

import com.weiyu.chaitoufeng.domain.system.SysFile;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * Description: 系统文件服务接口
 * Since: 2022-03-18 18:49
 * Author: wish_dq
 */
public interface ISysFileService {

    /**
     * 文 件 上 传 服 务
     */
    String upload(MultipartFile file);

    /**
     * 文 件 下 载 服 务
     */
    void download(String id);

    /**
     * 文 件 列 表
     */
    List<SysFile> data(SysFile sysFile);

    /**
     * 删 除 文 件
     */
    boolean remove(String id);

    /**
     * 文 件 夹 列 表
     */
    List<String> fileDirs();

}
package com.weiyu.chaitoufeng.domain.system;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.weiyu.chaitoufeng.common.logging.BusinessType;
import com.weiyu.chaitoufeng.common.logging.LoggingType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.type.Alias;
import org.springframework.web.bind.annotation.RequestMethod;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * Description: 日 志 实 体 类
 * Since: 2022-03-18 19:51
 * Author: wish_dq
 */
@Data
@Alias("SysLog")
@TableName("sys_logging")
@NoArgsConstructor
@AllArgsConstructor
public class SysLog {

    //编号
    private String id;

    //标题
    private String title;

    //描述
    private String description;

    //业务类型
    private BusinessType businessType;

    //请求方式
    private RequestMethod requestMethod;

    //请求的方法
    private String method;

    //请求的连接
    private String operateUrl;

    //用户 IP 地址
    private String operateAddress;

    //请 求 参 数
    private String requestParam;

    //获 取 请 求 体
    private String requestBody;

    //接 口 响 应 数 据
    private String responseBody;

    //接 口 执 行 状 态
    private boolean success;

    //日 志 类 型
    private LoggingType loggingType;

    //异 常 信 息
    private String errorMsg;

    //使用浏览器
    private String browser;

    //操作系统
    private String systemOs;

    //操 作 时 间
    private LocalDateTime createTime;

    //操 作 人 员 名 称
    private String operateName;

    //扩 展 信 息
    private Map<String, String> map = new HashMap<>();
}

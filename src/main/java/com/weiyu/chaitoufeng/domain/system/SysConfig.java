package com.weiyu.chaitoufeng.domain.system;

import com.weiyu.chaitoufeng.domain.BaseDomain;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.apache.ibatis.type.Alias;
import org.springframework.stereotype.Component;


/**
 * Description: 系 统 配 置
 * Since: 2022-03-21 0:51
 * Author: wish_dq
 */

@Data
@Alias("SysConfig")
public class SysConfig extends BaseDomain {

    //配置标识
    private String configId;

    //配置组Id
    private String configGroupId;

    //配置名称
    private String configName;

    // 配置类型
    private String configType;

    //配置标识
    private String configCode;

    //配置值
    private String configValue;

    // 是否可用
    private String enable;

    /**
     * 邮箱配置
     */
    //private String mailHost;
    //private String mailPort;
    //private String mailFrom;
    //private String mailUser;
    //private String mailPass;
    //private String uploadKind;
    //private String uploadPath;
}


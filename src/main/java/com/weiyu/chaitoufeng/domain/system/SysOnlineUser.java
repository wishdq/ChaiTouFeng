package com.weiyu.chaitoufeng.domain.system;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * Description: 在线用户实体类
 * Since: 2022-03-17 23:25
 * Author: wish_dq
 */
@Data
public class SysOnlineUser {

    private String userId;

    private String username;

    private String realName;

    private LocalDateTime lastTime;

    private String onlineTime;

}
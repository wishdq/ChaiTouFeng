package com.weiyu.chaitoufeng.domain.system;

import com.weiyu.chaitoufeng.domain.BaseDomain;
import lombok.Data;

/**
 * Description: 消息通知对象 sys_notice
 * Since: 2022-03-20 21:40
 * Author: wish_dq
 */
@Data
public class SysNotice extends BaseDomain {
    /** 编号 */
    private String id;

    /** 标题 */
    private String title;

    /** 内容 */
    private String content;

    /** 发送人 */
    private String sender;

    /** 发送人 */
    private String senderName;

    /** 接收者 */
    private String accept;

    /** 接收人 */
    private String acceptName;

    /** 类型 */
    private String type;

}

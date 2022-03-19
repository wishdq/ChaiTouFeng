package com.weiyu.chaitoufeng.common.result;

import lombok.Getter;

/**
 * Description: 统 一 返 回：状态码 + 消息
 * Since: 2022-03-10 14:00
 * Author: wish_dq
 */
public enum ResultCode {

    SUCCESS(200, "操作成功"),

    FAILURE(500, "操作失败");

    @Getter
    private final int code;

    @Getter
    private final String message;

    // 构 造 方 法
    ResultCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

}

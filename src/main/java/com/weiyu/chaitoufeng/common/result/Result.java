package com.weiyu.chaitoufeng.common.result;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * Description: Ajax 返 回 JSON 结 果 封 装 数 据
 * @Accessors(chain = true)生成setter方法返回this（也就是返回的是对象）
 *      例子： new Result().code(31).msg("hello");//返回对象
 * Since: 2022-03-10 13:56
 * Author: wish_dq
 */
@Data
@Accessors(chain = true)
public class Result<T> implements Serializable {

    // 是否成功
    private boolean success;
    // 状态码
    private int code;
    // 返回消息
    private String msg;
    // 返回数据
    private T data;

    // 成 功 操 作
    public static <T> Result<T> success() {
        return success(null);
    }

    // 成 功 操 作  ===>  数据
    public static <T> Result<T> success(T data) {
        return success(ResultCode.SUCCESS.getMessage(), data);
    }

    // 成 功 操 作  ===>  消息
    public static <T> Result<T> success(String message) {
        return success(message, null);
    }

    // 成 功 操 作  ===>  消息 + 数据
    public static <T> Result<T> success(String message, T data) {
        return success(ResultCode.SUCCESS.getCode(), message, data);
    }

    // 成 功 操 作  ===>  自 定 义:  状态码 + 消息
    public static <T> Result<T> success(int code, String message) {
        return success(code, message, null);
    }

    // 成 功 操 作  ===>  自 定 义:  状态码 + 消息 + 数据
    public static <T> Result<T> success(int code, String message, T data) {
        Result<T> result = new Result<T>();
        result.setCode(code);
        result.setMsg(message);
        result.setSuccess(true);
        result.setData(data);
        return result;
    }

    // 失 败 操 作  ===>  默认消息
    public static <T> Result<T> failure() {
        return failure(ResultCode.SUCCESS.getMessage());
    }

    // 失 败 操 作  ===>  自 定 义： 消息
    public static <T> Result<T> failure(String message) {
        return failure(message, null);
    }

    // 失 败 操 作  ===>  自 定 义： 消息 + 数据
    public static <T> Result<T> failure(String message, T data) {
        return failure(ResultCode.FAILURE.getCode(), message, data);
    }

    // 失 败 操 作  ===>  自 定 义： 状态码 + 消息
    public static <T> Result<T> failure(int code, String message) {
        return failure(ResultCode.FAILURE.getCode(), message, null);
    }

    // 失 败 操 作  ===>  自 定 义： 状态码 + 消息 + 数据
    public static <T> Result<T> failure(int code, String message, T data) {
        Result<T> result = new Result<T>();
        result.setCode(code);
        result.setMsg(message);
        result.setSuccess(false);
        result.setData(data);
        return result;
    }

    // 返 回 操 作 ===> 默认返回消息
    public static <T> Result<T> decide(boolean b) {
        return decide(b, ResultCode.SUCCESS.getMessage(), ResultCode.FAILURE.getMessage());
    }

    // 返 回 操 作 ===> 自 定 义 消 息
    public static <T> Result<T> decide(boolean b, String success, String failure) {
        if (b) {
            return success(success);
        } else {
            return failure(failure);
        }
    }

}

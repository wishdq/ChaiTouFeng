package com.weiyu.chaitoufeng.common.repeatsubmit;

import java.lang.annotation.*;

/**
 * Description: 表单重复提交注解
 * Since: 2022-03-19 23:43
 * Author: wish_dq
 */


@Target(ElementType.METHOD) // 作用到方法上
@Retention(RetentionPolicy.RUNTIME) // 运行时有效
public @interface NoRepeatSubmit {
    ///**
    // * 锁过期的时间
    // * */
    //int seconds() default 5;
    ///**
    // * 锁的位置
    // * */
    //String location() default "NoRepeatSubmit";
    ///**
    // * 要扫描的参数位置
    // * */
    //int argIndex() default 0;
    ///**
    // * 参数名称
    // * */
    //String name() default "";

}
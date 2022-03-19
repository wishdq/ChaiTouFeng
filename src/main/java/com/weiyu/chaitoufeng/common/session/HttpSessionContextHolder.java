package com.weiyu.chaitoufeng.common.session;



/**
 * Description: 获取 HttpSessionContext 对象实例持有者
 * Since: 2022-03-13 9:42
 * Author: wish_dq
 */
public class HttpSessionContextHolder {

    public static HttpSessionContext currentSessionContext() {
        return HttpSessionContext.getInstance();
    }

}
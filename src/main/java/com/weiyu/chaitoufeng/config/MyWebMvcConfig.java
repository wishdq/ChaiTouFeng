package com.weiyu.chaitoufeng.config;

import com.weiyu.chaitoufeng.config.property.SecurityProperty;
import com.weiyu.chaitoufeng.domain.interceptor.RepeatSubmitInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;

/**
 * Description:  拦截配置：
 *  *              1.编写一个拦截器实现HandlerInterceptor接口
 *  *              2.拦截器注册到容器中（实现WebMvcConfigurer的addInterceptors）
 *  *              3.指定拦截规则【如果是拦截所有，静态资源也会被拦截】
 * Since: 2022-03-20 9:51
 * Author: wish_dq
 */
//@Configuration
public class MyWebMvcConfig implements WebMvcConfigurer {

    @Resource
    RepeatSubmitInterceptor repeatSubmit;

    @Resource
    SecurityProperty securityProperty;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(repeatSubmit)
                //拦截路径
                .addPathPatterns("/**")
                // 放行路径
                .excludePathPatterns(securityProperty.getOpenApi());
    }
}

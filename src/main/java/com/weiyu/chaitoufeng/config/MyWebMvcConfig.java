//package com.weiyu.chaitoufeng.config;
//
//import org.springframework.context.annotation.Configuration;
//import org.springframework.stereotype.Component;
//import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//
///**
// * Description:  拦截配置：
// *  *              1.编写一个拦截器实现HandlerInterceptor接口
// *  *              2.拦截器注册到容器中（实现WebMvcConfigurer的addInterceptors）
// *  *              3.指定拦截规则【如果是拦截所有，静态资源也会被拦截】
// * Since: 2022-03-20 9:51
// * Author: wish_dq
// */
//@Component
//public class MyWebMvcConfig implements WebMvcConfigurer {
//
//    /**
//     * 配置静态资源映射
//     */
//    @Override
//    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
//
//    }
//}

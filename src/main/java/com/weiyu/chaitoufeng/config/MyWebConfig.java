package com.weiyu.chaitoufeng.config;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.unit.DataSize;

import javax.servlet.MultipartConfigElement;

/**
 * Description: 网站配置类
 * Since: 2022-03-25 10:28
 * Author: wish_dq
 */
@Configuration
public class MyWebConfig {

    /**
     * Mybatis-plus 分页
     * @return
     */
    //@Bean
    //public MybatisPlusInterceptor mybatisPlusInterceptor() {
    //    MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
    //    interceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.MYSQL));
    //    return interceptor;
    //}

    /**
     * 文件上传配置
     */
    //@Bean
    //public MultipartConfigElement multipartConfigElement() {
    //    MultipartConfigFactory factory = new MultipartConfigFactory();
    //    // 单个数据大小
    //    factory.setMaxFileSize(DataSize.parse("30MB")); // KB,MB
    //    //总上传数据大小
    //    factory.setMaxRequestSize(DataSize.parse("100MB"));
    //    return factory.createMultipartConfig();
    //}

}

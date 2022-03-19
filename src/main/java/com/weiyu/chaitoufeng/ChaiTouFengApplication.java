package com.weiyu.chaitoufeng;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;


@MapperScan("com.weiyu.chaitoufeng.mapper")
//(exclude = {DataSourceAutoConfiguration.class, org.activiti.spring.boot.SecurityAutoConfiguration.class, SecurityAutoConfiguration.class})
@SpringBootApplication(
        scanBasePackages = "com.weiyu.chaitoufeng",
        exclude = {
                //DataSourceAutoConfiguration.class,
                //org.activiti.spring.boot.SecurityAutoConfiguration.class,
                //SecurityAutoConfiguration.class
        })
public class ChaiTouFengApplication {

    public static void main(String[] args) {
        SpringApplication.run(ChaiTouFengApplication.class, args);
    }

}

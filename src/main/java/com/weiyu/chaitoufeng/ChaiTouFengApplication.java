package com.weiyu.chaitoufeng;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@MapperScan("com.weiyu.chaitoufeng.mapper")
@SpringBootApplication(
        scanBasePackages = "com.weiyu.chaitoufeng")
public class ChaiTouFengApplication {

    public static void main(String[] args) {
        SpringApplication.run(ChaiTouFengApplication.class, args);
    }

}

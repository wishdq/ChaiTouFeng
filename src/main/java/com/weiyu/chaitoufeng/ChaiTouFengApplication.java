package com.weiyu.chaitoufeng;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

/**
 * @ServletComponentScan 解决@WebListener不起作用问题
 */

@ServletComponentScan
@MapperScan("com.weiyu.chaitoufeng.mapper")
@SpringBootApplication(scanBasePackages = "com.weiyu.chaitoufeng")
public class ChaiTouFengApplication {

    public static void main(String[] args) {
        SpringApplication.run(ChaiTouFengApplication.class, args);
    }

}

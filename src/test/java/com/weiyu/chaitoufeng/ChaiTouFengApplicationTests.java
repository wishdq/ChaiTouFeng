package com.weiyu.chaitoufeng;

import com.weiyu.chaitoufeng.common.tools.FileUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import javax.servlet.MultipartConfigElement;
import java.time.Duration;

import static io.lettuce.core.GeoArgs.Unit.m;

@SpringBootTest
class ChaiTouFengApplicationTests {

    @Resource
    MultipartConfigElement multipartConfigElement;

    @Test
    void contextLoads() {
        System.out.println(multipartConfigElement.getMaxFileSize());
        System.out.println(multipartConfigElement.getMaxRequestSize());
    }

}

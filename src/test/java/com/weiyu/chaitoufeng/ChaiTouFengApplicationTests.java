package com.weiyu.chaitoufeng;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import javax.servlet.MultipartConfigElement;

@SpringBootTest
class ChaiTouFengApplicationTests {

    @Resource
    MultipartConfigElement multipartConfigElement;

    //@Resource
    //SystemUsageUtil systemUsageUtil;

    @Test
    void contextLoads() {
        //System.out.println(multipartConfigElement.getMaxFileSize());
        //System.out.println(multipartConfigElement.getMaxRequestSize());
        //System.out.println(SystemUsageUtil.getMemoryUsage());
        //CpuInfo cpuInfo = new CpuInfo();
        //System.out.println(cpuInfo);
    }

}

package com.weiyu.chaitoufeng;

import com.weiyu.chaitoufeng.common.tools.FileUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.Duration;

import static io.lettuce.core.GeoArgs.Unit.m;

@SpringBootTest
class ChaiTouFengApplicationTests {


    @Test
    void contextLoads() {
        String size = FileUtil.getPrintSize(3911680);
        System.out.println(size);
        //System.out.println(27.5 <1024);
    }

}

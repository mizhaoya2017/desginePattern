package com.mzy.shejimoshi.SpringAOP;

import com.mzy.shejimoshi.ShejimoshiApplication;
import com.mzy.shejimoshi.SpringAOP.service.AopTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {ShejimoshiApplication.class})// 指定启动类
public class Client {
    @Autowired
    private AopTest t;

    @Test
    public void test1() {
        t.test();
    }
}

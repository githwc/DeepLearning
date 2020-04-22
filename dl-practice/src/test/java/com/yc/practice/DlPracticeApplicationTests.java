package com.yc.practice;

import lombok.extern.slf4j.Slf4j;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
public class DlPracticeApplicationTests {


    @Before
    public void before(){
        log.info("当前类======ApplicationTest.before()开始了");
    }

    @After
    public void after(){
        log.info("当前类======ApplicationTest.after()结束了");
    }

    @Test
    public void dayCompareStr(){

    }


}

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
    public void aa(){
        System.out.println("方法执行前");
        System.out.println("方法执行前");
        System.out.println("方法执行前");
        System.out.println("方法执行前");
    }

    @After
    public void bb(){
        System.out.println("方法执行后");
        System.out.println("方法执行后");
        System.out.println("方法执行后");
        System.out.println("方法执行后");
    }

    @Test
    public void dayCompareStr(){

    }


}

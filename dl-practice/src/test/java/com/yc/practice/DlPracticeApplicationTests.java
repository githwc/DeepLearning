package com.yc.practice;

import lombok.extern.slf4j.Slf4j;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
        List<String> list = Arrays.asList("kobe","Lamban","Dave","Lina","Lihong");

        List<String> outList = list.stream().
                filter(s-> s.startsWith("L"))
                .map(String::toUpperCase)
                .distinct()
                .sorted()
                .collect(Collectors.toList());

        System.out.println(outList);

        String [] arr = {"kobe","Lamban","Dave","Lina","Lihong"};
        String[] outArr = Stream.of(arr).filter(s-> s.startsWith("L"))
                .map(String::toUpperCase)
                .toArray(String[]::new);
        System.out.println(Arrays.toString(outArr));
    }


}

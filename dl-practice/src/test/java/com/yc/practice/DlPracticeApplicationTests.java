package com.yc.practice;

import lombok.extern.slf4j.Slf4j;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.Period;

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
        String beginDate = "2020-03-01";
        String endDate = "2021-02-28";
        Period period = Period.between(LocalDate.parse(beginDate), LocalDate.parse(endDate));
        StringBuffer sb = new StringBuffer();
        sb.append(period.getYears()).append(",")
                .append(period.getMonths()).append(",")
                .append(period.getDays());
        System.out.println(sb);

    }

}

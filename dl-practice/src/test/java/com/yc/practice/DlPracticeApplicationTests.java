package com.yc.practice;

import cn.hutool.core.date.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Date;

import static cn.hutool.core.date.DateUnit.HOUR;
import static cn.hutool.core.date.DateUnit.MINUTE;

@SpringBootTest
@Slf4j
public class DlPracticeApplicationTests {


    @Before
    public void before(){
        log.info("当前类======ApplicationTest.before()开始了");
    }

    @After
    public void after(){
        System.out.println();
        log.info("当前类======ApplicationTest.after()结束了");
    }

    @Test
    public void dayCompareStr(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String begin = "2020-03-08 12:23:33";
        String end = "2020-03-08 14:28:36";
        try{
            // String  dd = getDatePoor(sdf.parse(end),sdf.parse(begin));
            LocalDateTime a = LocalDateTime.now();
            LocalDateTime b = LocalDateTime.now().plusMinutes(49).plusSeconds(23);
            String dd = getDatePoor2(b,a);
            System.out.println(dd);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public static String getDatePoor(Date endDate, Date nowDate) {
        long nd = 1000 * 24 * 60 * 60;
        long nh = 1000 * 60 * 60;
        long nm = 1000 * 60;
        long ns = 1000;
        // 获得两个时间的毫秒时间差异
        long diff = endDate.getTime() - nowDate.getTime();
        // 计算差多少天
        long day = diff / nd;
        // 计算差多少小时
        long hour = diff % nd / nh;
        // 计算差多少分钟
        long min = diff % nd % nh / nm;
        // 计算差多少秒//输出结果
        long sec = diff % nd % nh % nm / ns;
        return day + "天" + hour + "小时" + min + "分钟"+sec +"秒";
    }



    public static String getDatePoor2(LocalDateTime endDate, LocalDateTime nowDate) {
        long nd = 1000 * 24 * 60 * 60;
        long nh = 1000 * 60 * 60;
        long nm = 1000 * 60;
        long ns = 1000;
        // 获得两个时间的毫秒时间差异
        Duration duration = Duration.between(nowDate,endDate);
        // long diff = endDate.getTime() - nowDate.getTime();
        long diff = duration.toMillis();
        // 计算差多少天
        long day = diff / nd;
        // 计算差多少小时
        long hour = diff % nd / nh;
        // 计算差多少分钟
        long min = diff % nd % nh / nm;
        // 计算差多少秒//输出结果
        long sec = diff % nd % nh % nm / ns;
        // return day + "天" + hour + "小时" + min + "分钟"+sec +"秒";
        if(hour == 0 ){
            return min + "分钟"+sec +"秒";
        }else if(hour > 0){
            return hour + "小时" + min + "分钟"+sec +"秒";
        }else {
            return day + "天" + hour + "小时" + min + "分钟"+sec +"秒";
        }
    }

}

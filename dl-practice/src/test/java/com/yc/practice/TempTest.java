package com.yc.practice;

import cn.hutool.core.util.RandomUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 功能描述：
 * <p>版权所有：</p>
 * 未经本人许可，不得以任何方式复制或使用本程序任何部分
 *
 * @Company: 紫色年华
 * @Author: xieyc
 * @Datetime: 2020-05-26
 * @Version: 1.0.0
 */
@SpringBootTest
@Slf4j
public class TempTest {

    @Test
    public void tests(){
        System.out.println(System.currentTimeMillis());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm");
        long lt = new Long(System.currentTimeMillis());
        Date date = new Date(lt);
        System.out.println(simpleDateFormat.format(date));
        // Calendar calendar = Calendar.getInstance();
        // calendar.setTime(new Date());
        // calendar.add(Calendar.DAY_OF_YEAR,-1);
        // String dd = simpleDateFormat.format(calendar.getTime());
        // System.out.println(dd);
        // System.out.println(IdUtil.simpleUUID());
        // System.out.println(RandomUtil.getRandom());
    }

    @Test
    public void test1(){
        // BigDecimal a = null;
        // Integer faultRate = 6;
        // a = BigDecimal.valueOf(faultRate.doubleValue()/3);
        // BigDecimal b =a.setScale(2, RoundingMode.HALF_UP);//保留两位小数
        // System.out.println("结果是"+b);

        BigDecimal cc = BigDecimal.valueOf(3);
        BigDecimal dd = BigDecimal.valueOf(9);
        NumberFormat percent = NumberFormat.getPercentInstance();
        percent.setMaximumFractionDigits(2);
        BigDecimal ddd = dd.divide(cc,2,RoundingMode.HALF_UP);
        System.out.println(ddd);
        System.out.println(percent.format(ddd.doubleValue()));
    }
}

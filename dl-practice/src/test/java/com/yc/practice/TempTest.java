package com.yc.practice;

import io.swagger.models.auth.In;
import jdk.nashorn.internal.runtime.linker.LinkerCallSite;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.*;

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
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        long lt = new Long("1591254000000");
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

    @Test
    public void test2(){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date date = new Date(new Long("1591214400000"));
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.HOUR_OF_DAY,-8);
        String startTime = simpleDateFormat.format(calendar.getTime());
        System.out.println(startTime);
        // System.out.println(startTime.substring(startTime.length()-5));
        //
        // date = new Date(new Long("1591254000000"));
        // String initEndTime = simpleDateFormat.format(date);
        // System.out.println(initEndTime.substring(initEndTime.length()-5));
        // List<String> list = new ArrayList<>();
        // for (int i = 4; i < 15; i++) {
        //     System.out.println(i);
        //     list.add(i+"");
        // }
        // System.out.println(list);

        // long lt = new Long("");
        // Date date = new Date(lt);
        // System.out.println(simpleDateFormat.format(date));
    }

    public static void main(String[] args) {
        // 循环次数
        int Ahoursmin = 20;
        String a = "20:00";
        int b = Integer.parseInt(a.split(":")[0]) * 3600;
        int c = Integer.parseInt(a.split(":")[1]) * 60;
        // 开始时间
        int startTime = b + c;
        // 间隔时间
        int limit = 30*60;

        int initForntTime = startTime - limit;
        int initEndTime = startTime;
        List<String> list = new ArrayList<>();
        for (int i = 0; i < Ahoursmin; i++) {
            Map map = new HashMap();
            // 开始时间加limit分钟
            initForntTime = initForntTime+limit;
            // 结束时间加limit分钟
            initEndTime = initEndTime+limit;
            String frontTime = "";
            String endTime = "" ;
            // 起
            if(initForntTime != 0){
                // 换算成小时
                int AHourst = (Integer.valueOf(initForntTime) / 3600);
                // 换算成分钟
                int Aminute = (Integer.valueOf(initForntTime) % 3600 / 60);
                //把生成的时间转化成字符串
                String hourst = String.valueOf(AHourst);
                String minute = String.valueOf(Aminute);
                if(hourst.length() == 1){
                    hourst = "0"+hourst;
                }
                if(hourst.equals("24")){
                    hourst = "00";
                }
                if(Integer.parseInt(hourst)>24){
                    hourst = "0"+ (Integer.parseInt(hourst) - 24);
                }
                if(minute.length() == 1){
                    minute = "0"+minute;
                }
                frontTime = hourst + ":" + minute;
            }
            // 至
            if(initEndTime != 0){
                //换算成小时
                int AHourst = (Integer.valueOf(initEndTime) / 3600);
                //换算成分钟
                int Aminute = (Integer.valueOf(initEndTime) % 3600 / 60);
                //把生成的时间转化成字符串
                String hourst = String.valueOf(AHourst);
                String minute = String.valueOf(Aminute);
                if(hourst.length() == 1){
                    hourst = "0"+hourst;
                }
                if(hourst.equals("24")){
                    hourst = "00";
                }
                if(Integer.parseInt(hourst)>24){
                    hourst = "0"+ (Integer.parseInt(hourst) - 24);
                }
                if(minute.length() == 1){
                    minute = "0"+minute;
                }
                endTime = hourst + ":" + minute;
            }
            map.put("section", frontTime+"~"+endTime);
            list.add(frontTime+"~"+endTime);
            System.out.println(map);
        }
        System.out.println(list);
    }

    @Test
    public void tes(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.HOUR_OF_DAY,-8);
        Date d = calendar.getTime();
        System.out.println(d);
    }
}

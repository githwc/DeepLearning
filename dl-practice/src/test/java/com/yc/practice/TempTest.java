package com.yc.practice;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.yc.common.constant.CommonEnum;
import com.yc.core.category.entity.ProductCategory;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.SQLOutput;
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
@RunWith(SpringRunner.class)
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

    @Test
    public void ads(){
        // String ss = "8IYMZIJPK\u001B02\u001B0.10\u001B01\u001B05\u001B02\u001B02\u001B\u001B" +
        //         "\u001B12020022900087894149\u001B\u001B12020022900087894149\u001B2020-02-29\u001B14:32:28\u001B06\u001B10286632\u001B1\u001B222\u001B444\u001B111\u001B333\u001B0\u001B0.00\u001B0.00\u001B0.00\u001B\u001B\u001B\u001B01\u001B0020000000\u001B2020-03-20\u001B10000000000000145513\u001B02\u001B3\u001B\u001B\u001B12020022900087894148\u001B02\u001B0.00\u001B0.00\u001B00\u001B00";
        // String[] arr = ss.split(String.valueOf((char)27));
        // for (int i = 0; i < arr.length; i++) {
        //     System.out.println(arr[i]);
        // }

        String dd = "37\u001B\u001B\u001B\u001B\u001B\u001B\u001B\u001B\u001B\u001B\u001B\u001B\u001B\u001B\u001B\u001B\u001B\u001B\u001B\u001B\u001B\u001B\u001B\u001B\u001B\u001B\u001B\u001B\u001B\u001B\u001B\u001B\u001B\u001B\u001B\u001B\u001B\u001B\u001B\u001B\u001B\n" +
                "8IYMZIJPK\u001B02\u001B0.10\u001B01\u001B05\u001B02\u001B02\u001B\u001B\u001B12020022900087894149\u001B\u001B12020022900087894149\u001B2020-02-29\u001B14:32:28\u001B06\u001B10286632\u001B1\u001B222\u001B444\u001B111\u001B333\u001B0\u001B0.00\u001B0.00\u001B0.00\u001B\u001B\u001B\u001B01\u001B0020000000\u001B2020-03-20\u001B10000000000000145513\u001B02\u001B3\u001B\u001B\u001B12020022900087894148\u001B02\u001B0.00\u001B0.00\u001B00\u001B00\n" +
                "8BOVwjYxuH\u001B02\u001B0.20\u001B01\u001B05\u001B02\u001B02\u001B\u001B\u001B12020022900087894150\u001B\u001B12020022900087894150\u001B2020-02-29\u001B14:32:28\u001B06\u001B10286632\u001B1\u001B222\u001B444\u001B111\u001B333\u001B0\u001B0.00\u001B0.00\u001B0.00\u001B\u001B\u001B\u001B01\u001B0020000000\u001B2020-03-20\u001B10000000000000145513\u001B02\u001B3\u001B\u001B\u001B12020022900087894148\u001B02\u001B0.00\u001B0.00\u001B00\u001B00\n" +
                "ElF1M3shH\u001B02\u001B0.10\u001B01\u001B05\u001B02\u001B02\u001B\u001B\u001B12020022900087894156\u001B\u001B12020022900087894156\u001B2020-02-29\u001B14:45:48\u001B06\u001B10286632\u001B1\u001B222\u001B444\u001B111\u001B333\u001B0\u001B0.00\u001B0.00\u001B0.00\u001B\u001B\u001B\u001B01\u001B0020000000\u001B2020-03-20\u001B10000000000000145513\u001B02\u001B3\u001B\u001B\u001B12020022900087894155\u001B02\u001B0.00\u001B0.00\u001B00\u001B00\n" +
                "77nJx9gLeU\u001B02\u001B0.20\u001B01\u001B05\u001B02\u001B02\u001B\u001B\u001B12020022900087894157\u001B\u001B12020022900087894157\u001B2020-02-29\u001B14:45:48\u001B06\u001B10286632\u001B1\u001B222\u001B444\u001B111\u001B333\u001B0\u001B0.00\u001B0.00\u001B0.00\u001B\u001B\u001B\u001B01\u001B0020000000\u001B2020-03-20\u001B10000000000000145513\u001B02\u001B3\u001B\u001B\u001B12020022900087894155\u001B02\u001B0.00\u001B0.00\u001B00\u001B00\n" +
                "SADpPbw7W\u001B02\u001B0.10\u001B01\u001B05\u001B02\u001B02\u001B\u001B\u001B12020022900087896000\u001B\u001B12020022900087896000\u001B2020-02-29\u001B16:18:54\u001B06\u001B10286632\u001B1\u001B222\u001B444\u001B111\u001B333\u001B0\u001B0.00\u001B0.00\u001B0.00\u001B\u001B\u001B\u001B01\u001B0020000000\u001B2020-03-20\u001B10000000000000145513\u001B02\u001B3\u001B\u001B\u001B12020022900087895999\u001B02\u001B0.00\u001B0.00\u001B00\u001B00\n" +
                "s7HBDdzWyz\u001B02\u001B0.20\u001B01\u001B05\u001B02\u001B02\u001B\u001B\u001B12020022900087896001\u001B\u001B12020022900087896001\u001B2020-02-29\u001B16:18:54\u001B06\u001B10286632\u001B1\u001B222\u001B444\u001B111\u001B333\u001B0\u001B0.00\u001B0.00\u001B0.00\u001B\u001B\u001B\u001B01\u001B0020000000\u001B2020-03-20\u001B10000000000000145513\u001B02\u001B3\u001B\u001B\u001B12020022900087895999\u001B02\u001B0.00\u001B0.00\u001B00\u001B00\n" +
                "20200102T100100SVKJ9\u001B02\u001B0.30\u001B01\u001B05\u001B02\u001B02\u001B\u001B\u001B1JFT2020022900002803713\u001B\u001B12020022900087894148\u001B2020-02-29\u001B14:32:28\u001B06\u001B10286632\u001B1\u001B\u001B\u001B\u001B\u001B0\u001B\u001B\u001B\u001B\u001B\u001B\u001B01\u001B0020000000\u001B2020-03-20\u001B10000000000000145513\u001B02\u001B3\u001B\u001B\u001B\u001B01\u001B0.00\u001B0.00\u001B\u001B00\n" +
                "20200102T100100Q5BXK\u001B02\u001B0.30\u001B01\u001B05\u001B02\u001B02\u001B\u001B\u001B1JFT2020022900002803718\u001B\u001B12020022900087894155\u001B2020-02-29\u001B14:45:48\u001B06\u001B10286632\u001B1\u001B\u001B\u001B\u001B\u001B0\u001B\u001B\u001B\u001B\u001B\u001B\u001B01\u001B0020000000\u001B2020-03-20\u001B10000000000000145513\u001B02\u001B3\u001B\u001B\u001B\u001B01\u001B0.00\u001B0.00\u001B\u001B00\n" +
                "000E0000001343816876\u001B02\u001B0.01\u001B01\u001B05\u001B02\u001B02\u001B\u001B\u001B1JFT2020022900002803855\u001B\u001B12020022900087894922\u001B2020-02-29\u001B15:09:41\u001B01\u001B10281031\u001B1\u001B222\u001B444\u001B111\u001B333\u001B0\u001B0.00\u001B0.00\u001B0.00\u001B\u001B\u001B\u001B01\u001B0020000000\u001B2020-03-20\u001B10000000000000145513\u001B01\u001B3\u001B\u001B\u001B\u001B\u001B0.00\u001B0.00\u001B00\u001B00\n" +
                "000E0000001311480001\u001B02\u001B0.01\u001B01\u001B05\u001B02\u001B02\u001B\u001B\u001B1JFT2020022900002803869\u001B\u001B12020022900087894941\u001B2020-02-29\u001B15:14:30\u001B01\u001B10281031\u001B1\u001B222\u001B444\u001B111\u001B333\u001B0\u001B0.00\u001B0.00\u001B0.00\u001B\u001B\u001B\u001B01\u001B0020000000\u001B2020-03-20\u001B10000000000000145513\u001B01\u001B3\u001B\u001B\u001B\u001B\u001B0.00\u001B0.00\u001B00\u001B00\n" +
                "20200102T100100ufKGt\u001B02\u001B0.30\u001B01\u001B05\u001B02\u001B02\u001B\u001B\u001B1JFT2020022900002804123\u001B\u001B12020022900087895999\u001B2020-02-29\u001B16:18:54\u001B06\u001B10286632\u001B1\u001B\u001B\u001B\u001B\u001B0\u001B\u001B\u001B\u001B\u001B\u001B\u001B01\u001B0020000000\u001B2020-03-20\u001B10000000000000145513\u001B02\u001B3\u001B\u001B\u001B\u001B01\u001B0.00\u001B0.00\u001B\u001B00\n" +
                "20200102T100100uovdF\u001B02\u001B0.30\u001B01\u001B05\u001B02\u001B02\u001B\u001B\u001B1JFT2020022900002804232\u001B\u001B12020022900087896168\u001B2020-02-29\u001B17:17:52\u001B06\u001B10286632\u001B1\u001B\u001B\u001B\u001B\u001B0\u001B\u001B\u001B\u001B\u001B\u001B\u001B01\u001B0020000000\u001B2020-03-20\u001B10000000000000145513\u001B02\u001B3\u001B\u001B\u001B\u001B01\u001B0.00\u001B0.00\u001B\u001B00\n" +
                "20200102T100100mI1WX\u001B02\u001B0.30\u001B01\u001B05\u001B02\u001B02\u001B\u001B\u001B1JFT2020022900002804538\u001B\u001B12020022900087896513\u001B2020-02-29\u001B14:31:58\u001B06\u001B10286632\u001B1\u001B\u001B\u001B\u001B\u001B0\u001B\u001B\u001B\u001B\u001B\u001B\u001B01\u001B0020000000\u001B2020-03-20\u001B10000000000000145513\u001B02\u001B3\u001B\u001B\u001B\u001B01\u001B0.00\u001B0.00\u001B\u001B00\n" +
                "xemm121700111\u001B02\u001B100.00\u001B01\u001B05\u001B02\u001B02\u001B\u001B\u001B1JFT2020022900002804540\u001B\u001B12020022900087896517\u001B2020-02-29\u001B14:33:34\u001B06\u001B10281030\u001B1\u001B222\u001B444\u001B111\u001B333\u001B0\u001B0.11\u001B0.00\u001B0.11\u001B\u001B\u001B\u001B01\u001B0020000000\u001B2020-03-20\u001B10000000000000145513\u001B01\u001B3\u001B\u001B\u001B\u001B\u001B0.00\u001B0.00\u001B00\u001B00\n" +
                "20200102T100100dBEZx\u001B02\u001B0.30\u001B01\u001B05\u001B02\u001B02\u001B\u001B\u001B1JFT2020022900002804549\u001B\u001B12020022900087896530\u001B2020-02-29\u001B14:39:11\u001B06\u001B10286632\u001B1\u001B\u001B\u001B\u001B\u001B0\u001B\u001B\u001B\u001B\u001B\u001B\u001B01\u001B0020000000\u001B2020-03-20\u001B10000000000000145513\u001B02\u001B3\u001B\u001B\u001B\u001B01\u001B0.00\u001B0.00\u001B\u001B00\n" +
                "20200102T100100pAlTE\u001B02\u001B0.30\u001B01\u001B05\u001B02\u001B02\u001B\u001B\u001B1JFT2020022900002804553\u001B\u001B12020022900087896536\u001B2020-02-29\u001B14:39:53\u001B06\u001B10286632\u001B1\u001B\u001B\u001B\u001B\u001B0\u001B\u001B\u001B\u001B\u001B\u001B\u001B01\u001B0020000000\u001B2020-03-20\u001B10000000000000145513\u001B02\u001B3\u001B\u001B\u001B\u001B01\u001B0.00\u001B0.00\u001B\u001B00\n" +
                "20200102T100100SUQx6\u001B02\u001B0.10\u001B01\u001B05\u001B02\u001B02\u001B\u001B\u001B1JFT2020022900002804554\u001B\u001B12020022900087896539\u001B2020-02-29\u001B14:40:12\u001B06\u001B10281030\u001B1\u001B222\u001B444\u001B111\u001B333\u001B0\u001B0.00\u001B0.00\u001B0.00\u001B\u001B\u001B\u001B01\u001B0020000000\u001B2020-03-20\u001B10000000000000145513\u001B01\u001B3\u001B\u001B\u001B\u001B\u001B0.00\u001B0.00\u001B00\u001B00\n" +
                "20200102T100100bMcaj\u001B02\u001B0.10\u001B01\u001B05\u001B02\u001B02\u001B\u001B\u001B1JFT2020022900002804555\u001B\u001B12020022900087896540\u001B2020-02-29\u001B14:42:28\u001B06\u001B10281030\u001B1\u001B222\u001B444\u001B111\u001B333\u001B0\u001B0.00\u001B0.00\u001B0.00\u001B\u001B\u001B\u001B01\u001B0020000000\u001B2020-03-20\u001B10000000000000145513\u001B01\u001B3\u001B\u001B\u001B\u001B\u001B0.00\u001B0.00\u001B00\u001B00\n" +
                "20200102T100100aFB9f\u001B02\u001B0.10\u001B01\u001B05\u001B02\u001B02\u001B\u001B\u001B1JFT2020022900002804566\u001B\u001B12020022900087896551\u001B2020-02-29\u001B15:37:52\u001B06\u001B10281030\u001B1\u001B222\u001B444\u001B111\u001B333\u001B0\u001B0.00\u001B0.00\u001B0.00\u001B\u001B\u001B\u001B01\u001B0020000000\u001B2020-03-20\u001B10000000000000145513\u001B01\u001B3\u001B\u001B\u001B\u001B\u001B0.00\u001B0.00\u001B00\u001B00\n" +
                "000E0000000827604791\u001B02\u001B0.01\u001B01\u001B05\u001B02\u001B02\u001B\u001B\u001B1JFT2020022900002804568\u001B\u001B12020022900087896553\u001B2020-02-29\u001B15:44:26\u001B01\u001B10281031\u001B1\u001B222\u001B444\u001B111\u001B333\u001B0\u001B0.00\u001B0.00\u001B0.00\u001B\u001B\u001B\u001B01\u001B0020000000\u001B2020-03-20\u001B10000000000000145513\u001B01\u001B3\u001B\u001B\u001B\u001B\u001B0.00\u001B0.00\u001B00\u001B00\n" +
                "20200102T100100sZyDb\u001B02\u001B0.10\u001B01\u001B05\u001B02\u001B02\u001B\u001B\u001B1JFT2020022900002804578\u001B\u001B12020022900087896572\u001B2020-02-29\u001B15:56:26\u001B06\u001B10281030\u001B1\u001B222\u001B444\u001B111\u001B333\u001B0\u001B0.00\u001B0.00\u001B0.00\u001B\u001B\u001B\u001B01\u001B0020000000\u001B2020-03-20\u001B10000000000000145513\u001B01\u001B3\u001B\u001B\u001B\u001B\u001B0.00\u001B0.00\u001B00\u001B00\n" +
                "000E0000000596110323\u001B02\u001B0.01\u001B01\u001B05\u001B02\u001B02\u001B\u001B\u001B1JFT2020022900002804579\u001B\u001B12020022900087896571\u001B2020-02-29\u001B15:57:05\u001B01\u001B10281031\u001B1\u001B222\u001B444\u001B111\u001B333\u001B0\u001B0.00\u001B0.00\u001B0.00\u001B\u001B\u001B\u001B01\u001B0020000000\u001B2020-03-20\u001B10000000000000145513\u001B01\u001B3\u001B\u001B\u001B\u001B\u001B0.00\u001B0.00\u001B00\u001B00\n" +
                "000E0000000535674473\u001B02\u001B0.01\u001B01\u001B05\u001B02\u001B02\u001B\u001B\u001B1JFT2020022900002804582\u001B\u001B12020022900087896574\u001B2020-02-29\u001B15:58:14\u001B01\u001B10281031\u001B1\u001B222\u001B444\u001B111\u001B333\u001B0\u001B0.00\u001B0.00\u001B0.00\u001B\u001B\u001B\u001B01\u001B0020000000\u001B2020-03-20\u001B10000000000000145513\u001B01\u001B3\u001B\u001B\u001B\u001B\u001B0.00\u001B0.00\u001B00\u001B00\n" +
                "20200102T100100KhSk9\u001B02\u001B0.10\u001B01\u001B05\u001B02\u001B02\u001B\u001B\u001B1JFT2020022900002804651\u001B\u001B12020022900087896863\u001B2020-02-29\u001B16:26:42\u001B06\u001B10281030\u001B1\u001B222\u001B444\u001B111\u001B333\u001B0\u001B0.00\u001B0.00\u001B0.00\u001B\u001B\u001B\u001B01\u001B0020000000\u001B2020-03-20\u001B10000000000000145513\u001B01\u001B3\u001B\u001B\u001B\u001B\u001B0.00\u001B0.00\u001B00\u001B00\n" +
                "20200102T100100F6GZQ\u001B02\u001B0.30\u001B01\u001B05\u001B02\u001B02\u001B\u001B\u001B1JFT2020022900002804687\u001B\u001B12020022900087896913\u001B2020-02-29\u001B16:55:07\u001B06\u001B10286632\u001B1\u001B\u001B\u001B\u001B\u001B0\u001B\u001B\u001B\u001B\u001B\u001B\u001B01\u001B0020000000\u001B2020-03-20\u001B10000000000000145513\u001B02\u001B3\u001B\u001B\u001B\u001B01\u001B0.00\u001B0.00\u001B\u001B00\n" +
                "20200102T100100YmdYM\u001B02\u001B0.30\u001B01\u001B05\u001B02\u001B02\u001B\u001B\u001B1JFT2020022900002804690\u001B\u001B12020022900087896918\u001B2020-02-29\u001B16:55:32\u001B06\u001B10286632\u001B1\u001B\u001B\u001B\u001B\u001B0\u001B\u001B\u001B\u001B\u001B\u001B\u001B01\u001B0020000000\u001B2020-03-20\u001B10000000000000145513\u001B02\u001B3\u001B\u001B\u001B\u001B01\u001B0.00\u001B0.00\u001B\u001B00\n" +
                "20200102T100100B1O4X\u001B02\u001B0.10\u001B01\u001B05\u001B02\u001B02\u001B\u001B\u001B1JFT2020022900002804795\u001B\u001B12020022900087897446\u001B2020-02-29\u001B17:44:04\u001B06\u001B10281030\u001B1\u001B222\u001B444\u001B111\u001B333\u001B0\u001B0.00\u001B0.00\u001B0.00\u001B\u001B\u001B\u001B01\u001B0020000000\u001B2020-03-20\u001B10000000000000145513\u001B01\u001B3\u001B\u001B\u001B\u001B\u001B0.00\u001B0.00\u001B00\u001B00\n" +
                "20200102T100100wppSZ\u001B02\u001B0.10\u001B01\u001B05\u001B02\u001B02\u001B\u001B\u001B1JFT2020022900002804796\u001B\u001B12020022900087897447\u001B2020-02-29\u001B17:49:48\u001B06\u001B10281030\u001B1\u001B222\u001B444\u001B111\u001B333\u001B0\u001B0.00\u001B0.00\u001B0.00\u001B\u001B\u001B\u001B01\u001B0020000000\u001B2020-03-20\u001B10000000000000145513\u001B01\u001B3\u001B\u001B\u001B\u001B\u001B0.00\u001B0.00\u001B00\u001B00\n" +
                "20200102T100100IjcqL\u001B02\u001B0.10\u001B01\u001B05\u001B02\u001B02\u001B\u001B\u001B1JFT2020022900002804797\u001B\u001B12020022900087897451\u001B2020-02-29\u001B17:52:34\u001B06\u001B10281030\u001B1\u001B222\u001B444\u001B111\u001B333\u001B0\u001B0.00\u001B0.00\u001B0.00\u001B\u001B\u001B\u001B01\u001B0020000000\u001B2020-03-20\u001B10000000000000145513\u001B01\u001B3\u001B\u001B\u001B\u001B\u001B0.00\u001B0.00\u001B00\u001B00\n" +
                "20200102T100100IZyru\u001B02\u001B0.10\u001B01\u001B05\u001B02\u001B02\u001B\u001B\u001B1JFT2020022900002804798\u001B\u001B12020022900087897452\u001B2020-02-29\u001B18:04:01\u001B06\u001B10281030\u001B1\u001B222\u001B444\u001B111\u001B333\u001B0\u001B0.00\u001B0.00\u001B0.00\u001B\u001B\u001B\u001B01\u001B0020000000\u001B2020-03-20\u001B10000000000000145513\u001B01\u001B3\u001B\u001B\u001B\u001B\u001B0.00\u001B0.00\u001B00\u001B00\n" +
                "20200102T100100D56U1\u001B02\u001B0.10\u001B01\u001B05\u001B02\u001B02\u001B\u001B\u001B1JFT2020022900002805171\u001B\u001B12020022900087898275\u001B2020-02-29\u001B11:52:47\u001B06\u001B10281030\u001B1\u001B222\u001B444\u001B111\u001B333\u001B0\u001B0.00\u001B0.00\u001B0.00\u001B\u001B\u001B\u001B01\u001B0020000000\u001B2020-03-20\u001B10000000000000145513\u001B01\u001B3\u001B\u001B\u001B\u001B\u001B0.00\u001B0.00\u001B00\u001B00\n" +
                "20200102T100100Sczsw\u001B02\u001B0.10\u001B01\u001B05\u001B02\u001B02\u001B\u001B\u001B1JFT2020022900002805172\u001B\u001B12020022900087898276\u001B2020-02-29\u001B11:52:47\u001B06\u001B10281030\u001B1\u001B222\u001B444\u001B111\u001B333\u001B0\u001B0.00\u001B0.00\u001B0.00\u001B\u001B\u001B\u001B01\u001B0020000000\u001B2020-03-20\u001B10000000000000145513\u001B01\u001B3\u001B\u001B\u001B\u001B\u001B0.00\u001B0.00\u001B00\u001B00\n" +
                "20200102T100100mr7hb\u001B02\u001B0.10\u001B01\u001B05\u001B02\u001B02\u001B\u001B\u001B1JFT2020022900002805174\u001B\u001B12020022900087898279\u001B2020-02-29\u001B11:56:25\u001B06\u001B10281030\u001B1\u001B222\u001B444\u001B111\u001B333\u001B0\u001B0.00\u001B0.00\u001B0.00\u001B\u001B\u001B\u001B01\u001B0020000000\u001B2020-03-20\u001B10000000000000145513\u001B01\u001B3\u001B\u001B\u001B\u001B\u001B0.00\u001B0.00\u001B00\u001B00\n" +
                "20200102T100100Uprwh\u001B02\u001B0.10\u001B01\u001B05\u001B02\u001B02\u001B\u001B\u001B1JFT2020022900002805175\u001B\u001B12020022900087898278\u001B2020-02-29\u001B11:56:25\u001B06\u001B10281030\u001B1\u001B222\u001B444\u001B111\u001B333\u001B0\u001B0.00\u001B0.00\u001B0.00\u001B\u001B\u001B\u001B01\u001B0020000000\u001B2020-03-20\u001B10000000000000145513\u001B01\u001B3\u001B\u001B\u001B\u001B\u001B0.00\u001B0.00\u001B00\u001B00\n" +
                "20200102T100100fCsx7\u001B02\u001B0.10\u001B01\u001B05\u001B02\u001B02\u001B\u001B\u001B1JFT2020022900002805178\u001B\u001B12020022900087898284\u001B2020-02-29\u001B11:59:28\u001B06\u001B10281030\u001B1\u001B222\u001B444\u001B111\u001B333\u001B0\u001B0.00\u001B0.00\u001B0.00\u001B\u001B\u001B\u001B01\u001B0020000000\u001B2020-03-20\u001B10000000000000145513\u001B01\u001B3\u001B\u001B\u001B\u001B\u001B0.00\u001B0.00\u001B00\u001B00\n" +
                "000E0000001182000605\u001B02\u001B0.01\u001B01\u001B05\u001B02\u001B02\u001B\u001B\u001B1JFT2020022900002805215\u001B\u001B12020022900087898315\u001B2020-02-29\u001B13:53:12\u001B01\u001B10281031\u001B1\u001B222\u001B444\u001B111\u001B333\u001B0\u001B0.00\u001B0.00\u001B0.00\u001B\u001B\u001B\u001B01\u001B0020000000\u001B2020-03-20\u001B10000000000000145513\u001B01\u001B3\u001B\u001B\u001B\u001B\u001B0.00\u001B0.00\u001B00\u001B00\n" +
                "000E0000000196702754\u001B02\u001B0.01\u001B01\u001B05\u001B02\u001B02\u001B\u001B\u001B1JFT2020022900002805274\u001B\u001B12020022900087898385\u001B2020-02-29\u001B14:29:46\u001B01\u001B10281031\u001B1\u001B222\u001B444\u001B111\u001B333\u001B0\u001B0.00\u001B0.00\u001B0.00\u001B\u001B\u001B\u001B01\u001B0020000000\u001B2020-03-20\u001B10000000000000145513\u001B01\u001B3\u001B\u001B\u001B\u001B\u001B0.00\u001B0.00\u001B00\u001B00";
        String[] arra = dd.split(String.valueOf((char)10));
        List<Map<Integer,String>> list = new ArrayList<>();
        for (int i = 1; i < arra.length; i++) {
            String[] columns = arra[i].split(String.valueOf((char)27));
            Map<Integer,String> mapItem = new HashMap<>();
            for (int j = 0; j < columns.length; j++) {
                mapItem.put(j,columns[j]);
            }
            list.add(mapItem);
        }
        System.out.println(list);
    }

    enum TransCode implements CommonEnum {
        /**
         * 交易代码
         */
        TRANS_01("01", "退款"),
        TRANS_02("02", "支付"),
        TRANS_03("04", "充值"),
        TRANS_04("12", "预付卡充值退款"),
        ;
        private String code;
        private String name;

        TransCode(String code, String name) {
            this.code = code;
            this.name = name;
        }
        public String getCode() {
            return code;
        }
        public String getName() {
            return name;
        }
        public static String getEnumByCode(String code) {
            for (TransCode bt : values()) {
                if (bt.code.equals(code)) {
                    return bt.getName();
                }
            }
            return "";
        }
    }


    @Test
    public void goodClass0(){
        String result = "[{\n" +
                "\t\t\"channelList\": [1, 6],\n" +
                "\t\t\"hasChildren\": true,\n" +
                "\t\t\"id\": 14988,\n" +
                "\t\t\"level\": 1,\n" +
                "\t\t\"name\": \"其他商品\",\n" +
                "\t\t\"parentId\": 0,\n" +
                "\t\t\"priority\": 16,\n" +
                "\t\t\"ruleId\": \"1016\",\n" +
                "\t\t\"type\": 1,\n" +
                "\t\t\"child\": [{\n" +
                "\t\t\t\"channelList\": [1, 6],\n" +
                "\t\t\t\"hasChildren\": true,\n" +
                "\t\t\t\"id\": 15069,\n" +
                "\t\t\t\"level\": 2,\n" +
                "\t\t\t\"name\": \"众筹\",\n" +
                "\t\t\t\"parentId\": 14988,\n" +
                "\t\t\t\"priority\": 131,\n" +
                "\t\t\t\"ruleId\": \"10160004\",\n" +
                "\t\t\t\"type\": 1\n" +
                "\t\t}, {\n" +
                "\t\t\t\"channelList\": [1, 6],\n" +
                "\t\t\t\"hasChildren\": false,\n" +
                "\t\t\t\"id\": 15086,\n" +
                "\t\t\t\"level\": 2,\n" +
                "\t\t\t\"name\": \"公益\",\n" +
                "\t\t\t\"parentId\": 14988,\n" +
                "\t\t\t\"priority\": 132,\n" +
                "\t\t\t\"ruleId\": \"10160005\",\n" +
                "\t\t\t\"type\": 1\n" +
                "\t\t}, {\n" +
                "\t\t\t\"channelList\": [1, 6],\n" +
                "\t\t\t\"hasChildren\": true,\n" +
                "\t\t\t\"id\": 15010,\n" +
                "\t\t\t\"level\": 2,\n" +
                "\t\t\t\"name\": \"其他\",\n" +
                "\t\t\t\"parentId\": 14988,\n" +
                "\t\t\t\"priority\": 129,\n" +
                "\t\t\t\"ruleId\": \"10160002\",\n" +
                "\t\t\t\"type\": 1\n" +
                "\t\t}, {\n" +
                "\t\t\t\"channelList\": [1, 6],\n" +
                "\t\t\t\"hasChildren\": true,\n" +
                "\t\t\t\"id\": 15019,\n" +
                "\t\t\t\"level\": 2,\n" +
                "\t\t\t\"name\": \"商务/设计服务\",\n" +
                "\t\t\t\"parentId\": 14988,\n" +
                "\t\t\t\"priority\": 130,\n" +
                "\t\t\t\"ruleId\": \"10160003\",\n" +
                "\t\t\t\"type\": 1\n" +
                "\t\t}, {\n" +
                "\t\t\t\"channelList\": [1, 6],\n" +
                "\t\t\t\"hasChildren\": false,\n" +
                "\t\t\t\"id\": 15087,\n" +
                "\t\t\t\"level\": 2,\n" +
                "\t\t\t\"name\": \"慈善\",\n" +
                "\t\t\t\"parentId\": 14988,\n" +
                "\t\t\t\"priority\": 133,\n" +
                "\t\t\t\"ruleId\": \"10160006\",\n" +
                "\t\t\t\"type\": 1\n" +
                "\t\t}, {\n" +
                "\t\t\t\"channelList\": [1, 6],\n" +
                "\t\t\t\"hasChildren\": true,\n" +
                "\t\t\t\"id\": 14989,\n" +
                "\t\t\t\"level\": 2,\n" +
                "\t\t\t\"name\": \"拍卖会专用\",\n" +
                "\t\t\t\"parentId\": 14988,\n" +
                "\t\t\t\"priority\": 128,\n" +
                "\t\t\t\"ruleId\": \"10160001\",\n" +
                "\t\t\t\"type\": 1\n" +
                "\t\t}]\n" +
                "\t},{\n" +
                "\t\t\"channelList\": [1, 6],\n" +
                "\t\t\"hasChildren\": true,\n" +
                "\t\t\"id\": 6044,\n" +
                "\t\t\"level\": 1,\n" +
                "\t\t\"name\": \"医疗保健\",\n" +
                "\t\t\"parentId\": 0,\n" +
                "\t\t\"priority\": 7,\n" +
                "\t\t\"ruleId\": \"1007\",\n" +
                "\t\t\"type\": 1,\n" +
                "\t\t\"child\": [{\n" +
                "\t\t\t\"channelList\": [1, 6],\n" +
                "\t\t\t\"hasChildren\": true,\n" +
                "\t\t\t\"id\": 6045,\n" +
                "\t\t\t\"level\": 2,\n" +
                "\t\t\t\"name\": \"保健用品\",\n" +
                "\t\t\t\"parentId\": 6044,\n" +
                "\t\t\t\"priority\": 55,\n" +
                "\t\t\t\"ruleId\": \"10070001\",\n" +
                "\t\t\t\"type\": 1\n" +
                "\t\t}, {\n" +
                "\t\t\t\"channelList\": [1, 6],\n" +
                "\t\t\t\"hasChildren\": true,\n" +
                "\t\t\t\"id\": 6098,\n" +
                "\t\t\t\"level\": 2,\n" +
                "\t\t\t\"name\": \"康复辅助\",\n" +
                "\t\t\t\"parentId\": 6044,\n" +
                "\t\t\t\"priority\": 56,\n" +
                "\t\t\t\"ruleId\": \"10070002\",\n" +
                "\t\t\t\"type\": 1\n" +
                "\t\t}, {\n" +
                "\t\t\t\"channelList\": [1, 6],\n" +
                "\t\t\t\"hasChildren\": true,\n" +
                "\t\t\t\"id\": 6119,\n" +
                "\t\t\t\"level\": 2,\n" +
                "\t\t\t\"name\": \"急救护理\",\n" +
                "\t\t\t\"parentId\": 6044,\n" +
                "\t\t\t\"priority\": 57,\n" +
                "\t\t\t\"ruleId\": \"10070003\",\n" +
                "\t\t\t\"type\": 1\n" +
                "\t\t}, {\n" +
                "\t\t\t\"channelList\": [1, 6],\n" +
                "\t\t\t\"hasChildren\": true,\n" +
                "\t\t\t\"id\": 6163,\n" +
                "\t\t\t\"level\": 2,\n" +
                "\t\t\t\"name\": \"计生避孕\",\n" +
                "\t\t\t\"parentId\": 6044,\n" +
                "\t\t\t\"priority\": 59,\n" +
                "\t\t\t\"ruleId\": \"10070005\",\n" +
                "\t\t\t\"type\": 1\n" +
                "\t\t}, {\n" +
                "\t\t\t\"channelList\": [1, 6],\n" +
                "\t\t\t\"hasChildren\": true,\n" +
                "\t\t\t\"id\": 6140,\n" +
                "\t\t\t\"level\": 2,\n" +
                "\t\t\t\"name\": \"诊察用具\",\n" +
                "\t\t\t\"parentId\": 6044,\n" +
                "\t\t\t\"priority\": 58,\n" +
                "\t\t\t\"ruleId\": \"10070004\",\n" +
                "\t\t\t\"type\": 1\n" +
                "\t\t}]\n" +
                "\t},{\n" +
                "\t\t\"channelList\": [1, 6],\n" +
                "\t\t\"hasChildren\": true,\n" +
                "\t\t\"id\": 12794,\n" +
                "\t\t\"level\": 1,\n" +
                "\t\t\"name\": \"原料设备\",\n" +
                "\t\t\"parentId\": 0,\n" +
                "\t\t\"priority\": 13,\n" +
                "\t\t\"ruleId\": \"1013\",\n" +
                "\t\t\"type\": 1,\n" +
                "\t\t\"child\": [{\n" +
                "\t\t\t\"channelList\": [1, 6],\n" +
                "\t\t\t\"hasChildren\": true,\n" +
                "\t\t\t\"id\": 13341,\n" +
                "\t\t\t\"level\": 2,\n" +
                "\t\t\t\"name\": \"农机/农具/农膜\",\n" +
                "\t\t\t\"parentId\": 12794,\n" +
                "\t\t\t\"priority\": 109,\n" +
                "\t\t\t\"ruleId\": \"10130007\",\n" +
                "\t\t\t\"type\": 1\n" +
                "\t\t}, {\n" +
                "\t\t\t\"channelList\": [1, 6],\n" +
                "\t\t\t\"hasChildren\": true,\n" +
                "\t\t\t\"id\": 13382,\n" +
                "\t\t\t\"level\": 2,\n" +
                "\t\t\t\"name\": \"农用物资\",\n" +
                "\t\t\t\"parentId\": 12794,\n" +
                "\t\t\t\"priority\": 110,\n" +
                "\t\t\t\"ruleId\": \"10130008\",\n" +
                "\t\t\t\"type\": 1\n" +
                "\t\t}, {\n" +
                "\t\t\t\"channelList\": [1, 6],\n" +
                "\t\t\t\"hasChildren\": true,\n" +
                "\t\t\t\"id\": 12849,\n" +
                "\t\t\t\"level\": 2,\n" +
                "\t\t\t\"name\": \"包装\",\n" +
                "\t\t\t\"parentId\": 12794,\n" +
                "\t\t\t\"priority\": 104,\n" +
                "\t\t\t\"ruleId\": \"10130002\",\n" +
                "\t\t\t\"type\": 1\n" +
                "\t\t}, {\n" +
                "\t\t\t\"channelList\": [1, 6],\n" +
                "\t\t\t\"hasChildren\": true,\n" +
                "\t\t\t\"id\": 13418,\n" +
                "\t\t\t\"level\": 2,\n" +
                "\t\t\t\"name\": \"商业设备\",\n" +
                "\t\t\t\"parentId\": 12794,\n" +
                "\t\t\t\"priority\": 111,\n" +
                "\t\t\t\"ruleId\": \"10130009\",\n" +
                "\t\t\t\"type\": 1\n" +
                "\t\t}, {\n" +
                "\t\t\t\"channelList\": [1, 6],\n" +
                "\t\t\t\"hasChildren\": true,\n" +
                "\t\t\t\"id\": 12795,\n" +
                "\t\t\t\"level\": 2,\n" +
                "\t\t\t\"name\": \"搬运/仓储/物流设备\",\n" +
                "\t\t\t\"parentId\": 12794,\n" +
                "\t\t\t\"priority\": 103,\n" +
                "\t\t\t\"ruleId\": \"10130001\",\n" +
                "\t\t\t\"type\": 1\n" +
                "\t\t}, {\n" +
                "\t\t\t\"channelList\": [1, 6],\n" +
                "\t\t\t\"hasChildren\": true,\n" +
                "\t\t\t\"id\": 13186,\n" +
                "\t\t\t\"level\": 2,\n" +
                "\t\t\t\"name\": \"机械设备\",\n" +
                "\t\t\t\"parentId\": 12794,\n" +
                "\t\t\t\"priority\": 107,\n" +
                "\t\t\t\"ruleId\": \"10130005\",\n" +
                "\t\t\t\"type\": 1\n" +
                "\t\t}, {\n" +
                "\t\t\t\"channelList\": [1, 6],\n" +
                "\t\t\t\"hasChildren\": true,\n" +
                "\t\t\t\"id\": 13037,\n" +
                "\t\t\t\"level\": 2,\n" +
                "\t\t\t\"name\": \"标准件/零部件/工业耗材\",\n" +
                "\t\t\t\"parentId\": 12794,\n" +
                "\t\t\t\"priority\": 105,\n" +
                "\t\t\t\"ruleId\": \"10130003\",\n" +
                "\t\t\t\"type\": 1\n" +
                "\t\t}, {\n" +
                "\t\t\t\"channelList\": [1, 6],\n" +
                "\t\t\t\"hasChildren\": true,\n" +
                "\t\t\t\"id\": 13698,\n" +
                "\t\t\t\"level\": 2,\n" +
                "\t\t\t\"name\": \"橡塑材料及制品\",\n" +
                "\t\t\t\"parentId\": 12794,\n" +
                "\t\t\t\"priority\": 113,\n" +
                "\t\t\t\"ruleId\": \"10130011\",\n" +
                "\t\t\t\"type\": 1\n" +
                "\t\t}, {\n" +
                "\t\t\t\"channelList\": [1, 6],\n" +
                "\t\t\t\"hasChildren\": true,\n" +
                "\t\t\t\"id\": 13590,\n" +
                "\t\t\t\"level\": 2,\n" +
                "\t\t\t\"name\": \"润滑/胶粘/试剂/实验室耗材\",\n" +
                "\t\t\t\"parentId\": 12794,\n" +
                "\t\t\t\"priority\": 112,\n" +
                "\t\t\t\"ruleId\": \"10130010\",\n" +
                "\t\t\t\"type\": 1\n" +
                "\t\t}, {\n" +
                "\t\t\t\"channelList\": [1, 6],\n" +
                "\t\t\t\"hasChildren\": true,\n" +
                "\t\t\t\"id\": 13754,\n" +
                "\t\t\t\"level\": 2,\n" +
                "\t\t\t\"name\": \"畜牧/养殖物资和设备\",\n" +
                "\t\t\t\"parentId\": 12794,\n" +
                "\t\t\t\"priority\": 114,\n" +
                "\t\t\t\"ruleId\": \"10130012\",\n" +
                "\t\t\t\"type\": 1\n" +
                "\t\t}, {\n" +
                "\t\t\t\"channelList\": [1, 6],\n" +
                "\t\t\t\"hasChildren\": true,\n" +
                "\t\t\t\"id\": 13146,\n" +
                "\t\t\t\"level\": 2,\n" +
                "\t\t\t\"name\": \"纺织面料/辅料/配套\",\n" +
                "\t\t\t\"parentId\": 12794,\n" +
                "\t\t\t\"priority\": 106,\n" +
                "\t\t\t\"ruleId\": \"10130004\",\n" +
                "\t\t\t\"type\": 1\n" +
                "\t\t}, {\n" +
                "\t\t\t\"channelList\": [1, 6],\n" +
                "\t\t\t\"hasChildren\": true,\n" +
                "\t\t\t\"id\": 13311,\n" +
                "\t\t\t\"level\": 2,\n" +
                "\t\t\t\"name\": \"金属材料及制品\",\n" +
                "\t\t\t\"parentId\": 12794,\n" +
                "\t\t\t\"priority\": 108,\n" +
                "\t\t\t\"ruleId\": \"10130006\",\n" +
                "\t\t\t\"type\": 1\n" +
                "\t\t}]\n" +
                "\t}, {\n" +
                "\t\t\"channelList\": [1, 6],\n" +
                "\t\t\"hasChildren\": true,\n" +
                "\t\t\"id\": 7819,\n" +
                "\t\t\"level\": 1,\n" +
                "\t\t\"name\": \"家居建材\",\n" +
                "\t\t\"parentId\": 0,\n" +
                "\t\t\"priority\": 10,\n" +
                "\t\t\"ruleId\": \"1010\",\n" +
                "\t\t\"type\": 1,\n" +
                "\t\t\"child\": [{\n" +
                "\t\t\t\"channelList\": [1, 6],\n" +
                "\t\t\t\"hasChildren\": true,\n" +
                "\t\t\t\"id\": 9143,\n" +
                "\t\t\t\"level\": 2,\n" +
                "\t\t\t\"name\": \"五金/工具/劳保用品\",\n" +
                "\t\t\t\"parentId\": 7819,\n" +
                "\t\t\t\"priority\": 81,\n" +
                "\t\t\t\"ruleId\": \"10100011\",\n" +
                "\t\t\t\"type\": 1\n" +
                "\t\t}, {\n" +
                "\t\t\t\"channelList\": [1, 6],\n" +
                "\t\t\t\"hasChildren\": true,\n" +
                "\t\t\t\"id\": 9864,\n" +
                "\t\t\t\"level\": 2,\n" +
                "\t\t\t\"name\": \"住宅家具\",\n" +
                "\t\t\t\"parentId\": 7819,\n" +
                "\t\t\t\"priority\": 82,\n" +
                "\t\t\t\"ruleId\": \"10100012\",\n" +
                "\t\t\t\"type\": 1\n" +
                "\t\t}, {\n" +
                "\t\t\t\"channelList\": [1, 6],\n" +
                "\t\t\t\"hasChildren\": true,\n" +
                "\t\t\t\"id\": 8710,\n" +
                "\t\t\t\"level\": 2,\n" +
                "\t\t\t\"name\": \"全屋定制\",\n" +
                "\t\t\t\"parentId\": 7819,\n" +
                "\t\t\t\"priority\": 78,\n" +
                "\t\t\t\"ruleId\": \"10100008\",\n" +
                "\t\t\t\"type\": 1\n" +
                "\t\t}, {\n" +
                "\t\t\t\"channelList\": [1, 6],\n" +
                "\t\t\t\"hasChildren\": true,\n" +
                "\t\t\t\"id\": 8839,\n" +
                "\t\t\t\"level\": 2,\n" +
                "\t\t\t\"name\": \"商业/办公家具/商用设施\",\n" +
                "\t\t\t\"parentId\": 7819,\n" +
                "\t\t\t\"priority\": 79,\n" +
                "\t\t\t\"ruleId\": \"10100009\",\n" +
                "\t\t\t\"type\": 1\n" +
                "\t\t}, {\n" +
                "\t\t\t\"channelList\": [1, 6],\n" +
                "\t\t\t\"hasChildren\": true,\n" +
                "\t\t\t\"id\": 8016,\n" +
                "\t\t\t\"level\": 2,\n" +
                "\t\t\t\"name\": \"基础建材\",\n" +
                "\t\t\t\"parentId\": 7819,\n" +
                "\t\t\t\"priority\": 73,\n" +
                "\t\t\t\"ruleId\": \"10100003\",\n" +
                "\t\t\t\"type\": 1\n" +
                "\t\t}, {\n" +
                "\t\t\t\"channelList\": [1, 6],\n" +
                "\t\t\t\"hasChildren\": true,\n" +
                "\t\t\t\"id\": 8263,\n" +
                "\t\t\t\"level\": 2,\n" +
                "\t\t\t\"name\": \"家居饰品\",\n" +
                "\t\t\t\"parentId\": 7819,\n" +
                "\t\t\t\"priority\": 74,\n" +
                "\t\t\t\"ruleId\": \"10100004\",\n" +
                "\t\t\t\"type\": 1\n" +
                "\t\t}, {\n" +
                "\t\t\t\"channelList\": [1, 6],\n" +
                "\t\t\t\"hasChildren\": true,\n" +
                "\t\t\t\"id\": 8363,\n" +
                "\t\t\t\"level\": 2,\n" +
                "\t\t\t\"name\": \"家装主材\",\n" +
                "\t\t\t\"parentId\": 7819,\n" +
                "\t\t\t\"priority\": 75,\n" +
                "\t\t\t\"ruleId\": \"10100005\",\n" +
                "\t\t\t\"type\": 1\n" +
                "\t\t}, {\n" +
                "\t\t\t\"channelList\": [1, 6],\n" +
                "\t\t\t\"hasChildren\": true,\n" +
                "\t\t\t\"id\": 8538,\n" +
                "\t\t\t\"level\": 2,\n" +
                "\t\t\t\"name\": \"家装灯饰光源\",\n" +
                "\t\t\t\"parentId\": 7819,\n" +
                "\t\t\t\"priority\": 76,\n" +
                "\t\t\t\"ruleId\": \"10100006\",\n" +
                "\t\t\t\"type\": 1\n" +
                "\t\t}, {\n" +
                "\t\t\t\"channelList\": [1, 6],\n" +
                "\t\t\t\"hasChildren\": true,\n" +
                "\t\t\t\"id\": 8577,\n" +
                "\t\t\t\"level\": 2,\n" +
                "\t\t\t\"name\": \"居家布艺\",\n" +
                "\t\t\t\"parentId\": 7819,\n" +
                "\t\t\t\"priority\": 77,\n" +
                "\t\t\t\"ruleId\": \"10100007\",\n" +
                "\t\t\t\"type\": 1\n" +
                "\t\t}, {\n" +
                "\t\t\t\"channelList\": [1, 6],\n" +
                "\t\t\t\"hasChildren\": true,\n" +
                "\t\t\t\"id\": 7820,\n" +
                "\t\t\t\"level\": 2,\n" +
                "\t\t\t\"name\": \"床上用品\",\n" +
                "\t\t\t\"parentId\": 7819,\n" +
                "\t\t\t\"priority\": 71,\n" +
                "\t\t\t\"ruleId\": \"10100001\",\n" +
                "\t\t\t\"type\": 1\n" +
                "\t\t}, {\n" +
                "\t\t\t\"channelList\": [1, 6],\n" +
                "\t\t\t\"hasChildren\": true,\n" +
                "\t\t\t\"id\": 9038,\n" +
                "\t\t\t\"level\": 2,\n" +
                "\t\t\t\"name\": \"特色手工艺\",\n" +
                "\t\t\t\"parentId\": 7819,\n" +
                "\t\t\t\"priority\": 80,\n" +
                "\t\t\t\"ruleId\": \"10100010\",\n" +
                "\t\t\t\"type\": 1\n" +
                "\t\t}, {\n" +
                "\t\t\t\"channelList\": [1, 6],\n" +
                "\t\t\t\"hasChildren\": true,\n" +
                "\t\t\t\"id\": 7859,\n" +
                "\t\t\t\"level\": 2,\n" +
                "\t\t\t\"name\": \"电子/电工\",\n" +
                "\t\t\t\"parentId\": 7819,\n" +
                "\t\t\t\"priority\": 72,\n" +
                "\t\t\t\"ruleId\": \"10100002\",\n" +
                "\t\t\t\"type\": 1\n" +
                "\t\t}, {\n" +
                "\t\t\t\"channelList\": [1, 6],\n" +
                "\t\t\t\"hasChildren\": true,\n" +
                "\t\t\t\"id\": 10123,\n" +
                "\t\t\t\"level\": 2,\n" +
                "\t\t\t\"name\": \"装修设计/施工/监理\",\n" +
                "\t\t\t\"parentId\": 7819,\n" +
                "\t\t\t\"priority\": 83,\n" +
                "\t\t\t\"ruleId\": \"10100013\",\n" +
                "\t\t\t\"type\": 1\n" +
                "\t\t}]\n" +
                "\t}, {\n" +
                "\t\t\"channelList\": [1, 6],\n" +
                "\t\t\"hasChildren\": true,\n" +
                "\t\t\"id\": 6167,\n" +
                "\t\t\"level\": 1,\n" +
                "\t\t\"name\": \"家用电器\",\n" +
                "\t\t\"parentId\": 0,\n" +
                "\t\t\"priority\": 8,\n" +
                "\t\t\"ruleId\": \"1008\",\n" +
                "\t\t\"type\": 1,\n" +
                "\t\t\"child\": [{\n" +
                "\t\t\t\"channelList\": [1, 6],\n" +
                "\t\t\t\"hasChildren\": true,\n" +
                "\t\t\t\"id\": 6342,\n" +
                "\t\t\t\"level\": 2,\n" +
                "\t\t\t\"name\": \"个人护理\",\n" +
                "\t\t\t\"parentId\": 6167,\n" +
                "\t\t\t\"priority\": 62,\n" +
                "\t\t\t\"ruleId\": \"10080003\",\n" +
                "\t\t\t\"type\": 1\n" +
                "\t\t}, {\n" +
                "\t\t\t\"channelList\": [1, 6],\n" +
                "\t\t\t\"hasChildren\": true,\n" +
                "\t\t\t\"id\": 6168,\n" +
                "\t\t\t\"level\": 2,\n" +
                "\t\t\t\"name\": \"厨房电器\",\n" +
                "\t\t\t\"parentId\": 6167,\n" +
                "\t\t\t\"priority\": 60,\n" +
                "\t\t\t\"ruleId\": \"10080001\",\n" +
                "\t\t\t\"type\": 1\n" +
                "\t\t}, {\n" +
                "\t\t\t\"channelList\": [1, 6],\n" +
                "\t\t\t\"hasChildren\": true,\n" +
                "\t\t\t\"id\": 6278,\n" +
                "\t\t\t\"level\": 2,\n" +
                "\t\t\t\"name\": \"大家电\",\n" +
                "\t\t\t\"parentId\": 6167,\n" +
                "\t\t\t\"priority\": 61,\n" +
                "\t\t\t\"ruleId\": \"10080002\",\n" +
                "\t\t\t\"type\": 1\n" +
                "\t\t}, {\n" +
                "\t\t\t\"channelList\": [1, 6],\n" +
                "\t\t\t\"hasChildren\": true,\n" +
                "\t\t\t\"id\": 6428,\n" +
                "\t\t\t\"level\": 2,\n" +
                "\t\t\t\"name\": \"影音电器\",\n" +
                "\t\t\t\"parentId\": 6167,\n" +
                "\t\t\t\"priority\": 64,\n" +
                "\t\t\t\"ruleId\": \"10080005\",\n" +
                "\t\t\t\"type\": 1\n" +
                "\t\t}, {\n" +
                "\t\t\t\"channelList\": [1, 6],\n" +
                "\t\t\t\"hasChildren\": true,\n" +
                "\t\t\t\"id\": 6379,\n" +
                "\t\t\t\"level\": 2,\n" +
                "\t\t\t\"name\": \"生活电器\",\n" +
                "\t\t\t\"parentId\": 6167,\n" +
                "\t\t\t\"priority\": 63,\n" +
                "\t\t\t\"ruleId\": \"10080004\",\n" +
                "\t\t\t\"type\": 1\n" +
                "\t\t}]\n" +
                "\t}, {\n" +
                "\t\t\"channelList\": [1, 6],\n" +
                "\t\t\"hasChildren\": true,\n" +
                "\t\t\"id\": 3992,\n" +
                "\t\t\"level\": 1,\n" +
                "\t\t\"name\": \"数码办公\",\n" +
                "\t\t\"parentId\": 0,\n" +
                "\t\t\"priority\": 5,\n" +
                "\t\t\"ruleId\": \"1005\",\n" +
                "\t\t\"type\": 1,\n" +
                "\t\t\"child\": [{\n" +
                "\t\t\t\"channelList\": [1, 6],\n" +
                "\t\t\t\"hasChildren\": true,\n" +
                "\t\t\t\"id\": 3993,\n" +
                "\t\t\t\"level\": 2,\n" +
                "\t\t\t\"name\": \"3C/数码/配件\",\n" +
                "\t\t\t\"parentId\": 3992,\n" +
                "\t\t\t\"priority\": 34,\n" +
                "\t\t\t\"ruleId\": \"10050001\",\n" +
                "\t\t\t\"type\": 1\n" +
                "\t\t}, {\n" +
                "\t\t\t\"channelList\": [1, 6],\n" +
                "\t\t\t\"hasChildren\": true,\n" +
                "\t\t\t\"id\": 4856,\n" +
                "\t\t\t\"level\": 2,\n" +
                "\t\t\t\"name\": \"二手数码\",\n" +
                "\t\t\t\"parentId\": 3992,\n" +
                "\t\t\t\"priority\": 41,\n" +
                "\t\t\t\"ruleId\": \"10050008\",\n" +
                "\t\t\t\"type\": 1\n" +
                "\t\t}, {\n" +
                "\t\t\t\"channelList\": [1, 6],\n" +
                "\t\t\t\"hasChildren\": true,\n" +
                "\t\t\t\"id\": 4293,\n" +
                "\t\t\t\"level\": 2,\n" +
                "\t\t\t\"name\": \"办公设备/耗材/相关服务\",\n" +
                "\t\t\t\"parentId\": 3992,\n" +
                "\t\t\t\"priority\": 37,\n" +
                "\t\t\t\"ruleId\": \"10050004\",\n" +
                "\t\t\t\"type\": 1\n" +
                "\t\t}, {\n" +
                "\t\t\t\"channelList\": [1, 6],\n" +
                "\t\t\t\"hasChildren\": true,\n" +
                "\t\t\t\"id\": 4863,\n" +
                "\t\t\t\"level\": 2,\n" +
                "\t\t\t\"name\": \"品牌台机/品牌一体机/服务器\",\n" +
                "\t\t\t\"parentId\": 3992,\n" +
                "\t\t\t\"priority\": 42,\n" +
                "\t\t\t\"ruleId\": \"10050009\",\n" +
                "\t\t\t\"type\": 1\n" +
                "\t\t}, {\n" +
                "\t\t\t\"channelList\": [1, 6],\n" +
                "\t\t\t\"hasChildren\": false,\n" +
                "\t\t\t\"id\": 4869,\n" +
                "\t\t\t\"level\": 2,\n" +
                "\t\t\t\"name\": \"平板电脑/MID\",\n" +
                "\t\t\t\"parentId\": 3992,\n" +
                "\t\t\t\"priority\": 43,\n" +
                "\t\t\t\"ruleId\": \"10050010\",\n" +
                "\t\t\t\"type\": 1\n" +
                "\t\t}, {\n" +
                "\t\t\t\"channelList\": [1, 6],\n" +
                "\t\t\t\"hasChildren\": false,\n" +
                "\t\t\t\"id\": 4878,\n" +
                "\t\t\t\"level\": 2,\n" +
                "\t\t\t\"name\": \"手机\",\n" +
                "\t\t\t\"parentId\": 3992,\n" +
                "\t\t\t\"priority\": 45,\n" +
                "\t\t\t\"ruleId\": \"10050012\",\n" +
                "\t\t\t\"type\": 1\n" +
                "\t\t}, {\n" +
                "\t\t\t\"channelList\": [1, 6],\n" +
                "\t\t\t\"hasChildren\": true,\n" +
                "\t\t\t\"id\": 4879,\n" +
                "\t\t\t\"level\": 2,\n" +
                "\t\t\t\"name\": \"数码相机/单反相机/摄像机\",\n" +
                "\t\t\t\"parentId\": 3992,\n" +
                "\t\t\t\"priority\": 46,\n" +
                "\t\t\t\"ruleId\": \"10050013\",\n" +
                "\t\t\t\"type\": 1\n" +
                "\t\t}, {\n" +
                "\t\t\t\"channelList\": [1, 6],\n" +
                "\t\t\t\"hasChildren\": true,\n" +
                "\t\t\t\"id\": 4998,\n" +
                "\t\t\t\"level\": 2,\n" +
                "\t\t\t\"name\": \"文具电教/文化用品/商务用品\",\n" +
                "\t\t\t\"parentId\": 3992,\n" +
                "\t\t\t\"priority\": 48,\n" +
                "\t\t\t\"ruleId\": \"10050015\",\n" +
                "\t\t\t\"type\": 1\n" +
                "\t\t}, {\n" +
                "\t\t\t\"channelList\": [1, 6],\n" +
                "\t\t\t\"hasChildren\": true,\n" +
                "\t\t\t\"id\": 4537,\n" +
                "\t\t\t\"level\": 2,\n" +
                "\t\t\t\"name\": \"智能设备\",\n" +
                "\t\t\t\"parentId\": 3992,\n" +
                "\t\t\t\"priority\": 38,\n" +
                "\t\t\t\"ruleId\": \"10050005\",\n" +
                "\t\t\t\"type\": 1\n" +
                "\t\t}, {\n" +
                "\t\t\t\"channelList\": [1, 6],\n" +
                "\t\t\t\"hasChildren\": true,\n" +
                "\t\t\t\"id\": 4662,\n" +
                "\t\t\t\"level\": 2,\n" +
                "\t\t\t\"name\": \"电子元器件/配件\",\n" +
                "\t\t\t\"parentId\": 3992,\n" +
                "\t\t\t\"priority\": 40,\n" +
                "\t\t\t\"ruleId\": \"10050007\",\n" +
                "\t\t\t\"type\": 1\n" +
                "\t\t}, {\n" +
                "\t\t\t\"channelList\": [1, 6],\n" +
                "\t\t\t\"hasChildren\": true,\n" +
                "\t\t\t\"id\": 4568,\n" +
                "\t\t\t\"level\": 2,\n" +
                "\t\t\t\"name\": \"电玩及配件/游戏机攻略\",\n" +
                "\t\t\t\"parentId\": 3992,\n" +
                "\t\t\t\"priority\": 39,\n" +
                "\t\t\t\"ruleId\": \"10050006\",\n" +
                "\t\t\t\"type\": 1\n" +
                "\t\t}, {\n" +
                "\t\t\t\"channelList\": [1, 6],\n" +
                "\t\t\t\"hasChildren\": true,\n" +
                "\t\t\t\"id\": 4241,\n" +
                "\t\t\t\"level\": 2,\n" +
                "\t\t\t\"name\": \"电脑硬件/显示器/电脑周边\",\n" +
                "\t\t\t\"parentId\": 3992,\n" +
                "\t\t\t\"priority\": 36,\n" +
                "\t\t\t\"ruleId\": \"10050003\",\n" +
                "\t\t\t\"type\": 1\n" +
                "\t\t}, {\n" +
                "\t\t\t\"channelList\": [1, 6],\n" +
                "\t\t\t\"hasChildren\": false,\n" +
                "\t\t\t\"id\": 4240,\n" +
                "\t\t\t\"level\": 2,\n" +
                "\t\t\t\"name\": \"笔记本电脑\",\n" +
                "\t\t\t\"parentId\": 3992,\n" +
                "\t\t\t\"priority\": 35,\n" +
                "\t\t\t\"ruleId\": \"10050002\",\n" +
                "\t\t\t\"type\": 1\n" +
                "\t\t}, {\n" +
                "\t\t\t\"channelList\": [1, 6],\n" +
                "\t\t\t\"hasChildren\": true,\n" +
                "\t\t\t\"id\": 4896,\n" +
                "\t\t\t\"level\": 2,\n" +
                "\t\t\t\"name\": \"网络设备/网络相关\",\n" +
                "\t\t\t\"parentId\": 3992,\n" +
                "\t\t\t\"priority\": 47,\n" +
                "\t\t\t\"ruleId\": \"10050014\",\n" +
                "\t\t\t\"type\": 1\n" +
                "\t\t}, {\n" +
                "\t\t\t\"channelList\": [1, 6],\n" +
                "\t\t\t\"hasChildren\": true,\n" +
                "\t\t\t\"id\": 4870,\n" +
                "\t\t\t\"level\": 2,\n" +
                "\t\t\t\"name\": \"闪存卡/U盘/存储/移动硬盘\",\n" +
                "\t\t\t\"parentId\": 3992,\n" +
                "\t\t\t\"priority\": 44,\n" +
                "\t\t\t\"ruleId\": \"10050011\",\n" +
                "\t\t\t\"type\": 1\n" +
                "\t\t}]\n" +
                "\t}, {\n" +
                "\t\t\"channelList\": [1, 6],\n" +
                "\t\t\"hasChildren\": true,\n" +
                "\t\t\"id\": 10155,\n" +
                "\t\t\"level\": 1,\n" +
                "\t\t\"name\": \"文化玩乐\",\n" +
                "\t\t\"parentId\": 0,\n" +
                "\t\t\"priority\": 11,\n" +
                "\t\t\"ruleId\": \"1011\",\n" +
                "\t\t\"type\": 1,\n" +
                "\t\t\"child\": [{\n" +
                "\t\t\t\"channelList\": [1, 6],\n" +
                "\t\t\t\"hasChildren\": true,\n" +
                "\t\t\t\"id\": 11011,\n" +
                "\t\t\t\"level\": 2,\n" +
                "\t\t\t\"name\": \"乐器/吉他/钢琴/配件\",\n" +
                "\t\t\t\"parentId\": 10155,\n" +
                "\t\t\t\"priority\": 89,\n" +
                "\t\t\t\"ruleId\": \"10110006\",\n" +
                "\t\t\t\"type\": 1\n" +
                "\t\t}, {\n" +
                "\t\t\t\"channelList\": [1, 6],\n" +
                "\t\t\t\"hasChildren\": true,\n" +
                "\t\t\t\"id\": 11326,\n" +
                "\t\t\t\"level\": 2,\n" +
                "\t\t\t\"name\": \"书籍/杂志/报纸\",\n" +
                "\t\t\t\"parentId\": 10155,\n" +
                "\t\t\t\"priority\": 91,\n" +
                "\t\t\t\"ruleId\": \"10110008\",\n" +
                "\t\t\t\"type\": 1\n" +
                "\t\t}, {\n" +
                "\t\t\t\"channelList\": [1, 6],\n" +
                "\t\t\t\"hasChildren\": true,\n" +
                "\t\t\t\"id\": 10450,\n" +
                "\t\t\t\"level\": 2,\n" +
                "\t\t\t\"name\": \"古董/邮币/字画/收藏\",\n" +
                "\t\t\t\"parentId\": 10155,\n" +
                "\t\t\t\"priority\": 87,\n" +
                "\t\t\t\"ruleId\": \"10110004\",\n" +
                "\t\t\t\"type\": 1\n" +
                "\t\t}, {\n" +
                "\t\t\t\"channelList\": [1, 6],\n" +
                "\t\t\t\"hasChildren\": true,\n" +
                "\t\t\t\"id\": 10957,\n" +
                "\t\t\t\"level\": 2,\n" +
                "\t\t\t\"name\": \"宗教用品\",\n" +
                "\t\t\t\"parentId\": 10155,\n" +
                "\t\t\t\"priority\": 88,\n" +
                "\t\t\t\"ruleId\": \"10110005\",\n" +
                "\t\t\t\"type\": 1\n" +
                "\t\t}, {\n" +
                "\t\t\t\"channelList\": [1, 6],\n" +
                "\t\t\t\"hasChildren\": true,\n" +
                "\t\t\t\"id\": 10156,\n" +
                "\t\t\t\"level\": 2,\n" +
                "\t\t\t\"name\": \"宠物/宠物食品及用品\",\n" +
                "\t\t\t\"parentId\": 10155,\n" +
                "\t\t\t\"priority\": 84,\n" +
                "\t\t\t\"ruleId\": \"10110001\",\n" +
                "\t\t\t\"type\": 1\n" +
                "\t\t}, {\n" +
                "\t\t\t\"channelList\": [1, 6],\n" +
                "\t\t\t\"hasChildren\": true,\n" +
                "\t\t\t\"id\": 10375,\n" +
                "\t\t\t\"level\": 2,\n" +
                "\t\t\t\"name\": \"度假线路/签证送关/旅游服务\",\n" +
                "\t\t\t\"parentId\": 10155,\n" +
                "\t\t\t\"priority\": 85,\n" +
                "\t\t\t\"ruleId\": \"10110002\",\n" +
                "\t\t\t\"type\": 1\n" +
                "\t\t}, {\n" +
                "\t\t\t\"channelList\": [1, 6],\n" +
                "\t\t\t\"hasChildren\": true,\n" +
                "\t\t\t\"id\": 11231,\n" +
                "\t\t\t\"level\": 2,\n" +
                "\t\t\t\"name\": \"模玩/动漫/周边/cos/桌游\",\n" +
                "\t\t\t\"parentId\": 10155,\n" +
                "\t\t\t\"priority\": 90,\n" +
                "\t\t\t\"ruleId\": \"10110007\",\n" +
                "\t\t\t\"type\": 1\n" +
                "\t\t}, {\n" +
                "\t\t\t\"channelList\": [1, 6],\n" +
                "\t\t\t\"hasChildren\": true,\n" +
                "\t\t\t\"id\": 11875,\n" +
                "\t\t\t\"level\": 2,\n" +
                "\t\t\t\"name\": \"特价酒店/特色客栈/公寓旅馆\",\n" +
                "\t\t\t\"parentId\": 10155,\n" +
                "\t\t\t\"priority\": 92,\n" +
                "\t\t\t\"ruleId\": \"10110009\",\n" +
                "\t\t\t\"type\": 1\n" +
                "\t\t}, {\n" +
                "\t\t\t\"channelList\": [1, 6],\n" +
                "\t\t\t\"hasChildren\": true,\n" +
                "\t\t\t\"id\": 10420,\n" +
                "\t\t\t\"level\": 2,\n" +
                "\t\t\t\"name\": \"电影/演出/体育赛事\",\n" +
                "\t\t\t\"parentId\": 10155,\n" +
                "\t\t\t\"priority\": 86,\n" +
                "\t\t\t\"ruleId\": \"10110003\",\n" +
                "\t\t\t\"type\": 1\n" +
                "\t\t}, {\n" +
                "\t\t\t\"channelList\": [1, 6],\n" +
                "\t\t\t\"hasChildren\": true,\n" +
                "\t\t\t\"id\": 11887,\n" +
                "\t\t\t\"level\": 2,\n" +
                "\t\t\t\"name\": \"音乐/影视/明星/音像\",\n" +
                "\t\t\t\"parentId\": 10155,\n" +
                "\t\t\t\"priority\": 93,\n" +
                "\t\t\t\"ruleId\": \"10110010\",\n" +
                "\t\t\t\"type\": 1\n" +
                "\t\t}]\n" +
                "\t}, {\n" +
                "\t\t\"channelList\": [1, 6],\n" +
                "\t\t\"hasChildren\": true,\n" +
                "\t\t\"id\": 1686,\n" +
                "\t\t\"level\": 1,\n" +
                "\t\t\"name\": \"日用百货\",\n" +
                "\t\t\"parentId\": 0,\n" +
                "\t\t\"priority\": 1,\n" +
                "\t\t\"ruleId\": \"1001\",\n" +
                "\t\t\"type\": 1,\n" +
                "\t\t\"child\": [{\n" +
                "\t\t\t\"channelList\": [1, 6],\n" +
                "\t\t\t\"hasChildren\": true,\n" +
                "\t\t\t\"id\": 1835,\n" +
                "\t\t\t\"level\": 2,\n" +
                "\t\t\t\"name\": \"厨房/烹饪用具\",\n" +
                "\t\t\t\"parentId\": 1686,\n" +
                "\t\t\t\"priority\": 3,\n" +
                "\t\t\t\"ruleId\": \"10010003\",\n" +
                "\t\t\t\"type\": 1\n" +
                "\t\t}, {\n" +
                "\t\t\t\"channelList\": [1, 6],\n" +
                "\t\t\t\"hasChildren\": true,\n" +
                "\t\t\t\"id\": 1958,\n" +
                "\t\t\t\"level\": 2,\n" +
                "\t\t\t\"name\": \"家庭/个人清洁工具\",\n" +
                "\t\t\t\"parentId\": 1686,\n" +
                "\t\t\t\"priority\": 4,\n" +
                "\t\t\t\"ruleId\": \"10010004\",\n" +
                "\t\t\t\"type\": 1\n" +
                "\t\t}, {\n" +
                "\t\t\t\"channelList\": [1, 6],\n" +
                "\t\t\t\"hasChildren\": true,\n" +
                "\t\t\t\"id\": 2147,\n" +
                "\t\t\t\"level\": 2,\n" +
                "\t\t\t\"name\": \"居家日用品\",\n" +
                "\t\t\t\"parentId\": 1686,\n" +
                "\t\t\t\"priority\": 6,\n" +
                "\t\t\t\"ruleId\": \"10010006\",\n" +
                "\t\t\t\"type\": 1\n" +
                "\t\t}, {\n" +
                "\t\t\t\"channelList\": [1, 6],\n" +
                "\t\t\t\"hasChildren\": true,\n" +
                "\t\t\t\"id\": 1687,\n" +
                "\t\t\t\"level\": 2,\n" +
                "\t\t\t\"name\": \"成人用品/情趣用品\",\n" +
                "\t\t\t\"parentId\": 1686,\n" +
                "\t\t\t\"priority\": 1,\n" +
                "\t\t\t\"ruleId\": \"10010001\",\n" +
                "\t\t\t\"type\": 1\n" +
                "\t\t}, {\n" +
                "\t\t\t\"channelList\": [1, 6],\n" +
                "\t\t\t\"hasChildren\": true,\n" +
                "\t\t\t\"id\": 2272,\n" +
                "\t\t\t\"level\": 2,\n" +
                "\t\t\t\"name\": \"收纳整理\",\n" +
                "\t\t\t\"parentId\": 1686,\n" +
                "\t\t\t\"priority\": 7,\n" +
                "\t\t\t\"ruleId\": \"10010007\",\n" +
                "\t\t\t\"type\": 1\n" +
                "\t\t}, {\n" +
                "\t\t\t\"channelList\": [1, 6],\n" +
                "\t\t\t\"hasChildren\": true,\n" +
                "\t\t\t\"id\": 2389,\n" +
                "\t\t\t\"level\": 2,\n" +
                "\t\t\t\"name\": \"洗护清洁剂/卫生巾/纸品/香薰\",\n" +
                "\t\t\t\"parentId\": 1686,\n" +
                "\t\t\t\"priority\": 8,\n" +
                "\t\t\t\"ruleId\": \"10010008\",\n" +
                "\t\t\t\"type\": 1\n" +
                "\t\t}, {\n" +
                "\t\t\t\"channelList\": [1, 6],\n" +
                "\t\t\t\"hasChildren\": true,\n" +
                "\t\t\t\"id\": 2055,\n" +
                "\t\t\t\"level\": 2,\n" +
                "\t\t\t\"name\": \"节庆用品/礼品\",\n" +
                "\t\t\t\"parentId\": 1686,\n" +
                "\t\t\t\"priority\": 5,\n" +
                "\t\t\t\"ruleId\": \"10010005\",\n" +
                "\t\t\t\"type\": 1\n" +
                "\t\t}, {\n" +
                "\t\t\t\"channelList\": [1, 6],\n" +
                "\t\t\t\"hasChildren\": true,\n" +
                "\t\t\t\"id\": 1709,\n" +
                "\t\t\t\"level\": 2,\n" +
                "\t\t\t\"name\": \"餐饮用具\",\n" +
                "\t\t\t\"parentId\": 1686,\n" +
                "\t\t\t\"priority\": 2,\n" +
                "\t\t\t\"ruleId\": \"10010002\",\n" +
                "\t\t\t\"type\": 1\n" +
                "\t\t}]\n" +
                "\t}, {\n" +
                "\t\t\"channelList\": [1, 6],\n" +
                "\t\t\"hasChildren\": true,\n" +
                "\t\t\"id\": 3804,\n" +
                "\t\t\"level\": 1,\n" +
                "\t\t\"name\": \"服装鞋包\",\n" +
                "\t\t\"parentId\": 0,\n" +
                "\t\t\"priority\": 4,\n" +
                "\t\t\"ruleId\": \"1004\",\n" +
                "\t\t\"type\": 1,\n" +
                "\t\t\"child\": [{\n" +
                "\t\t\t\"channelList\": [1, 6],\n" +
                "\t\t\t\"hasChildren\": true,\n" +
                "\t\t\t\"id\": 3971,\n" +
                "\t\t\t\"level\": 2,\n" +
                "\t\t\t\"name\": \"唐装/民族服装/舞台服装\",\n" +
                "\t\t\t\"parentId\": 3804,\n" +
                "\t\t\t\"priority\": 32,\n" +
                "\t\t\t\"ruleId\": \"10040008\",\n" +
                "\t\t\t\"type\": 1\n" +
                "\t\t}, {\n" +
                "\t\t\t\"channelList\": [1, 6],\n" +
                "\t\t\t\"hasChildren\": true,\n" +
                "\t\t\t\"id\": 3924,\n" +
                "\t\t\t\"level\": 2,\n" +
                "\t\t\t\"name\": \"女装/女士精品\",\n" +
                "\t\t\t\"parentId\": 3804,\n" +
                "\t\t\t\"priority\": 30,\n" +
                "\t\t\t\"ruleId\": \"10040006\",\n" +
                "\t\t\t\"type\": 1\n" +
                "\t\t}, {\n" +
                "\t\t\t\"channelList\": [1, 6],\n" +
                "\t\t\t\"hasChildren\": true,\n" +
                "\t\t\t\"id\": 3908,\n" +
                "\t\t\t\"level\": 2,\n" +
                "\t\t\t\"name\": \"女鞋\",\n" +
                "\t\t\t\"parentId\": 3804,\n" +
                "\t\t\t\"priority\": 29,\n" +
                "\t\t\t\"ruleId\": \"10040005\",\n" +
                "\t\t\t\"type\": 1\n" +
                "\t\t}, {\n" +
                "\t\t\t\"channelList\": [1, 6],\n" +
                "\t\t\t\"hasChildren\": true,\n" +
                "\t\t\t\"id\": 3805,\n" +
                "\t\t\t\"level\": 2,\n" +
                "\t\t\t\"name\": \"服装配件/皮带/帽子/围巾/口罩\",\n" +
                "\t\t\t\"parentId\": 3804,\n" +
                "\t\t\t\"priority\": 25,\n" +
                "\t\t\t\"ruleId\": \"10040001\",\n" +
                "\t\t\t\"type\": 1\n" +
                "\t\t}, {\n" +
                "\t\t\t\"channelList\": [1, 6],\n" +
                "\t\t\t\"hasChildren\": true,\n" +
                "\t\t\t\"id\": 3875,\n" +
                "\t\t\t\"level\": 2,\n" +
                "\t\t\t\"name\": \"男女内衣/居家服/保暖塑身衣\",\n" +
                "\t\t\t\"parentId\": 3804,\n" +
                "\t\t\t\"priority\": 28,\n" +
                "\t\t\t\"ruleId\": \"10040004\",\n" +
                "\t\t\t\"type\": 1\n" +
                "\t\t}, {\n" +
                "\t\t\t\"channelList\": [1, 6],\n" +
                "\t\t\t\"hasChildren\": true,\n" +
                "\t\t\t\"id\": 3846,\n" +
                "\t\t\t\"level\": 2,\n" +
                "\t\t\t\"name\": \"男装\",\n" +
                "\t\t\t\"parentId\": 3804,\n" +
                "\t\t\t\"priority\": 27,\n" +
                "\t\t\t\"ruleId\": \"10040003\",\n" +
                "\t\t\t\"type\": 1\n" +
                "\t\t}, {\n" +
                "\t\t\t\"channelList\": [1, 6],\n" +
                "\t\t\t\"hasChildren\": true,\n" +
                "\t\t\t\"id\": 3830,\n" +
                "\t\t\t\"level\": 2,\n" +
                "\t\t\t\"name\": \"男鞋\",\n" +
                "\t\t\t\"parentId\": 3804,\n" +
                "\t\t\t\"priority\": 26,\n" +
                "\t\t\t\"ruleId\": \"10040002\",\n" +
                "\t\t\t\"type\": 1\n" +
                "\t\t}, {\n" +
                "\t\t\t\"channelList\": [1, 6],\n" +
                "\t\t\t\"hasChildren\": true,\n" +
                "\t\t\t\"id\": 3977,\n" +
                "\t\t\t\"level\": 2,\n" +
                "\t\t\t\"name\": \"箱包/男女皮具\",\n" +
                "\t\t\t\"parentId\": 3804,\n" +
                "\t\t\t\"priority\": 33,\n" +
                "\t\t\t\"ruleId\": \"10040009\",\n" +
                "\t\t\t\"type\": 1\n" +
                "\t\t}, {\n" +
                "\t\t\t\"channelList\": [1, 6],\n" +
                "\t\t\t\"hasChildren\": true,\n" +
                "\t\t\t\"id\": 3962,\n" +
                "\t\t\t\"level\": 2,\n" +
                "\t\t\t\"name\": \"职业制服/校服/套装\",\n" +
                "\t\t\t\"parentId\": 3804,\n" +
                "\t\t\t\"priority\": 31,\n" +
                "\t\t\t\"ruleId\": \"10040007\",\n" +
                "\t\t\t\"type\": 1\n" +
                "\t\t}]\n" +
                "\t}, {\n" +
                "\t\t\"channelList\": [1, 6],\n" +
                "\t\t\"hasChildren\": true,\n" +
                "\t\t\"id\": 5212,\n" +
                "\t\t\"level\": 1,\n" +
                "\t\t\"name\": \"母婴用品\",\n" +
                "\t\t\"parentId\": 0,\n" +
                "\t\t\"priority\": 6,\n" +
                "\t\t\"ruleId\": \"1006\",\n" +
                "\t\t\"type\": 1,\n" +
                "\t\t\"child\": [{\n" +
                "\t\t\t\"channelList\": [1, 6],\n" +
                "\t\t\t\"hasChildren\": true,\n" +
                "\t\t\t\"id\": 5213,\n" +
                "\t\t\t\"level\": 2,\n" +
                "\t\t\t\"name\": \"奶粉/辅食/营养品/零食\",\n" +
                "\t\t\t\"parentId\": 5212,\n" +
                "\t\t\t\"priority\": 49,\n" +
                "\t\t\t\"ruleId\": \"10060001\",\n" +
                "\t\t\t\"type\": 1\n" +
                "\t\t}, {\n" +
                "\t\t\t\"channelList\": [1, 6],\n" +
                "\t\t\t\"hasChildren\": true,\n" +
                "\t\t\t\"id\": 5639,\n" +
                "\t\t\t\"level\": 2,\n" +
                "\t\t\t\"name\": \"婴童用品\",\n" +
                "\t\t\t\"parentId\": 5212,\n" +
                "\t\t\t\"priority\": 53,\n" +
                "\t\t\t\"ruleId\": \"10060005\",\n" +
                "\t\t\t\"type\": 1\n" +
                "\t\t}, {\n" +
                "\t\t\t\"channelList\": [1, 6],\n" +
                "\t\t\t\"hasChildren\": true,\n" +
                "\t\t\t\"id\": 5848,\n" +
                "\t\t\t\"level\": 2,\n" +
                "\t\t\t\"name\": \"孕妇装/孕产妇用品/营养\",\n" +
                "\t\t\t\"parentId\": 5212,\n" +
                "\t\t\t\"priority\": 54,\n" +
                "\t\t\t\"ruleId\": \"10060006\",\n" +
                "\t\t\t\"type\": 1\n" +
                "\t\t}, {\n" +
                "\t\t\t\"channelList\": [1, 6],\n" +
                "\t\t\t\"hasChildren\": true,\n" +
                "\t\t\t\"id\": 5375,\n" +
                "\t\t\t\"level\": 2,\n" +
                "\t\t\t\"name\": \"玩具/童车/益智/积木/模型\",\n" +
                "\t\t\t\"parentId\": 5212,\n" +
                "\t\t\t\"priority\": 52,\n" +
                "\t\t\t\"ruleId\": \"10060004\",\n" +
                "\t\t\t\"type\": 1\n" +
                "\t\t}, {\n" +
                "\t\t\t\"channelList\": [1, 6],\n" +
                "\t\t\t\"hasChildren\": true,\n" +
                "\t\t\t\"id\": 5269,\n" +
                "\t\t\t\"level\": 2,\n" +
                "\t\t\t\"name\": \"童装/婴儿装/亲子装\",\n" +
                "\t\t\t\"parentId\": 5212,\n" +
                "\t\t\t\"priority\": 51,\n" +
                "\t\t\t\"ruleId\": \"10060003\",\n" +
                "\t\t\t\"type\": 1\n" +
                "\t\t}, {\n" +
                "\t\t\t\"channelList\": [1, 6],\n" +
                "\t\t\t\"hasChildren\": true,\n" +
                "\t\t\t\"id\": 5252,\n" +
                "\t\t\t\"level\": 2,\n" +
                "\t\t\t\"name\": \"童鞋/婴儿鞋/亲子鞋\",\n" +
                "\t\t\t\"parentId\": 5212,\n" +
                "\t\t\t\"priority\": 50,\n" +
                "\t\t\t\"ruleId\": \"10060002\",\n" +
                "\t\t\t\"type\": 1\n" +
                "\t\t}]\n" +
                "\t}, {\n" +
                "\t\t\"channelList\": [1, 6],\n" +
                "\t\t\"hasChildren\": true,\n" +
                "\t\t\"id\": 13784,\n" +
                "\t\t\"level\": 1,\n" +
                "\t\t\"name\": \"汽摩电动\",\n" +
                "\t\t\"parentId\": 0,\n" +
                "\t\t\"priority\": 14,\n" +
                "\t\t\"ruleId\": \"1014\",\n" +
                "\t\t\"type\": 1,\n" +
                "\t\t\"child\": [{\n" +
                "\t\t\t\"channelList\": [1, 6],\n" +
                "\t\t\t\"hasChildren\": true,\n" +
                "\t\t\t\"id\": 14221,\n" +
                "\t\t\t\"level\": 2,\n" +
                "\t\t\t\"name\": \"摩托车/装备/配件\",\n" +
                "\t\t\t\"parentId\": 13784,\n" +
                "\t\t\t\"priority\": 117,\n" +
                "\t\t\t\"ruleId\": \"10140003\",\n" +
                "\t\t\t\"type\": 1\n" +
                "\t\t}, {\n" +
                "\t\t\t\"channelList\": [1, 6],\n" +
                "\t\t\t\"hasChildren\": true,\n" +
                "\t\t\t\"id\": 14484,\n" +
                "\t\t\t\"level\": 2,\n" +
                "\t\t\t\"name\": \"新车/二手车\",\n" +
                "\t\t\t\"parentId\": 13784,\n" +
                "\t\t\t\"priority\": 119,\n" +
                "\t\t\t\"ruleId\": \"10140005\",\n" +
                "\t\t\t\"type\": 1\n" +
                "\t\t}, {\n" +
                "\t\t\t\"channelList\": [1, 6],\n" +
                "\t\t\t\"hasChildren\": true,\n" +
                "\t\t\t\"id\": 14334,\n" +
                "\t\t\t\"level\": 2,\n" +
                "\t\t\t\"name\": \"汽车用品/电子/改装/清洁\",\n" +
                "\t\t\t\"parentId\": 13784,\n" +
                "\t\t\t\"priority\": 118,\n" +
                "\t\t\t\"ruleId\": \"10140004\",\n" +
                "\t\t\t\"type\": 1\n" +
                "\t\t}, {\n" +
                "\t\t\t\"channelList\": [1, 6],\n" +
                "\t\t\t\"hasChildren\": true,\n" +
                "\t\t\t\"id\": 13908,\n" +
                "\t\t\t\"level\": 2,\n" +
                "\t\t\t\"name\": \"汽车零部件/养护/美容/维保\",\n" +
                "\t\t\t\"parentId\": 13784,\n" +
                "\t\t\t\"priority\": 116,\n" +
                "\t\t\t\"ruleId\": \"10140002\",\n" +
                "\t\t\t\"type\": 1\n" +
                "\t\t}, {\n" +
                "\t\t\t\"channelList\": [1, 6],\n" +
                "\t\t\t\"hasChildren\": true,\n" +
                "\t\t\t\"id\": 14500,\n" +
                "\t\t\t\"level\": 2,\n" +
                "\t\t\t\"name\": \"沙滩车/装备/配件\",\n" +
                "\t\t\t\"parentId\": 13784,\n" +
                "\t\t\t\"priority\": 122,\n" +
                "\t\t\t\"ruleId\": \"10140008\",\n" +
                "\t\t\t\"type\": 1\n" +
                "\t\t}, {\n" +
                "\t\t\t\"channelList\": [1, 6],\n" +
                "\t\t\t\"hasChildren\": true,\n" +
                "\t\t\t\"id\": 13785,\n" +
                "\t\t\t\"level\": 2,\n" +
                "\t\t\t\"name\": \"电动车/配件\",\n" +
                "\t\t\t\"parentId\": 13784,\n" +
                "\t\t\t\"priority\": 115,\n" +
                "\t\t\t\"ruleId\": \"10140001\",\n" +
                "\t\t\t\"type\": 1\n" +
                "\t\t}, {\n" +
                "\t\t\t\"channelList\": [1, 6],\n" +
                "\t\t\t\"hasChildren\": true,\n" +
                "\t\t\t\"id\": 14494,\n" +
                "\t\t\t\"level\": 2,\n" +
                "\t\t\t\"name\": \"船舶/装备/配件\",\n" +
                "\t\t\t\"parentId\": 13784,\n" +
                "\t\t\t\"priority\": 121,\n" +
                "\t\t\t\"ruleId\": \"10140007\",\n" +
                "\t\t\t\"type\": 1\n" +
                "\t\t}, {\n" +
                "\t\t\t\"channelList\": [1, 6],\n" +
                "\t\t\t\"hasChildren\": true,\n" +
                "\t\t\t\"id\": 14489,\n" +
                "\t\t\t\"level\": 2,\n" +
                "\t\t\t\"name\": \"飞行器/装备/配件\",\n" +
                "\t\t\t\"parentId\": 13784,\n" +
                "\t\t\t\"priority\": 120,\n" +
                "\t\t\t\"ruleId\": \"10140006\",\n" +
                "\t\t\t\"type\": 1\n" +
                "\t\t}]\n" +
                "\t}, {\n" +
                "\t\t\"channelList\": [1, 6],\n" +
                "\t\t\"hasChildren\": true,\n" +
                "\t\t\"id\": 11897,\n" +
                "\t\t\"level\": 1,\n" +
                "\t\t\"name\": \"生活服务\",\n" +
                "\t\t\"parentId\": 0,\n" +
                "\t\t\"priority\": 12,\n" +
                "\t\t\"ruleId\": \"1012\",\n" +
                "\t\t\"type\": 1,\n" +
                "\t\t\"child\": [{\n" +
                "\t\t\t\"channelList\": [1, 6],\n" +
                "\t\t\t\"hasChildren\": true,\n" +
                "\t\t\t\"id\": 12402,\n" +
                "\t\t\t\"level\": 2,\n" +
                "\t\t\t\"name\": \"个性定制/设计服务/DIY\",\n" +
                "\t\t\t\"parentId\": 11897,\n" +
                "\t\t\t\"priority\": 97,\n" +
                "\t\t\t\"ruleId\": \"10120004\",\n" +
                "\t\t\t\"type\": 1\n" +
                "\t\t}, {\n" +
                "\t\t\t\"channelList\": [1, 6],\n" +
                "\t\t\t\"hasChildren\": true,\n" +
                "\t\t\t\"id\": 12396,\n" +
                "\t\t\t\"level\": 2,\n" +
                "\t\t\t\"name\": \"房产/租房/新房/二手房/委托服务\",\n" +
                "\t\t\t\"parentId\": 11897,\n" +
                "\t\t\t\"priority\": 96,\n" +
                "\t\t\t\"ruleId\": \"10120003\",\n" +
                "\t\t\t\"type\": 1\n" +
                "\t\t}, {\n" +
                "\t\t\t\"channelList\": [1, 6],\n" +
                "\t\t\t\"hasChildren\": true,\n" +
                "\t\t\t\"id\": 12621,\n" +
                "\t\t\t\"level\": 2,\n" +
                "\t\t\t\"name\": \"教育培训\",\n" +
                "\t\t\t\"parentId\": 11897,\n" +
                "\t\t\t\"priority\": 99,\n" +
                "\t\t\t\"ruleId\": \"10120006\",\n" +
                "\t\t\t\"type\": 1\n" +
                "\t\t}, {\n" +
                "\t\t\t\"channelList\": [1, 6],\n" +
                "\t\t\t\"hasChildren\": true,\n" +
                "\t\t\t\"id\": 11898,\n" +
                "\t\t\t\"level\": 2,\n" +
                "\t\t\t\"name\": \"本地化生活服务\",\n" +
                "\t\t\t\"parentId\": 11897,\n" +
                "\t\t\t\"priority\": 94,\n" +
                "\t\t\t\"ruleId\": \"10120001\",\n" +
                "\t\t\t\"type\": 1\n" +
                "\t\t}, {\n" +
                "\t\t\t\"channelList\": [1, 6],\n" +
                "\t\t\t\"hasChildren\": true,\n" +
                "\t\t\t\"id\": 12632,\n" +
                "\t\t\t\"level\": 2,\n" +
                "\t\t\t\"name\": \"理财\",\n" +
                "\t\t\t\"parentId\": 11897,\n" +
                "\t\t\t\"priority\": 100,\n" +
                "\t\t\t\"ruleId\": \"10120007\",\n" +
                "\t\t\t\"type\": 1\n" +
                "\t\t}, {\n" +
                "\t\t\t\"channelList\": [1, 6],\n" +
                "\t\t\t\"hasChildren\": true,\n" +
                "\t\t\t\"id\": 12635,\n" +
                "\t\t\t\"level\": 2,\n" +
                "\t\t\t\"name\": \"网店/网络服务/软件\",\n" +
                "\t\t\t\"parentId\": 11897,\n" +
                "\t\t\t\"priority\": 101,\n" +
                "\t\t\t\"ruleId\": \"10120008\",\n" +
                "\t\t\t\"type\": 1\n" +
                "\t\t}, {\n" +
                "\t\t\t\"channelList\": [1, 6],\n" +
                "\t\t\t\"hasChildren\": true,\n" +
                "\t\t\t\"id\": 12588,\n" +
                "\t\t\t\"level\": 2,\n" +
                "\t\t\t\"name\": \"购物提货券\",\n" +
                "\t\t\t\"parentId\": 11897,\n" +
                "\t\t\t\"priority\": 98,\n" +
                "\t\t\t\"ruleId\": \"10120005\",\n" +
                "\t\t\t\"type\": 1\n" +
                "\t\t}, {\n" +
                "\t\t\t\"channelList\": [1, 6],\n" +
                "\t\t\t\"hasChildren\": true,\n" +
                "\t\t\t\"id\": 12356,\n" +
                "\t\t\t\"level\": 2,\n" +
                "\t\t\t\"name\": \"餐饮美食卡券\",\n" +
                "\t\t\t\"parentId\": 11897,\n" +
                "\t\t\t\"priority\": 95,\n" +
                "\t\t\t\"ruleId\": \"10120002\",\n" +
                "\t\t\t\"type\": 1\n" +
                "\t\t}, {\n" +
                "\t\t\t\"channelList\": [1, 6],\n" +
                "\t\t\t\"hasChildren\": true,\n" +
                "\t\t\t\"id\": 12666,\n" +
                "\t\t\t\"level\": 2,\n" +
                "\t\t\t\"name\": \"鲜花速递/花卉仿真/绿植园艺\",\n" +
                "\t\t\t\"parentId\": 11897,\n" +
                "\t\t\t\"priority\": 102,\n" +
                "\t\t\t\"ruleId\": \"10120009\",\n" +
                "\t\t\t\"type\": 1\n" +
                "\t\t}]\n" +
                "\t}, {\n" +
                "\t\t\"channelList\": [1, 6],\n" +
                "\t\t\"hasChildren\": true,\n" +
                "\t\t\"id\": 3388,\n" +
                "\t\t\"level\": 1,\n" +
                "\t\t\"name\": \"美妆饰品\",\n" +
                "\t\t\"parentId\": 0,\n" +
                "\t\t\"priority\": 3,\n" +
                "\t\t\"ruleId\": \"1003\",\n" +
                "\t\t\"type\": 1,\n" +
                "\t\t\"child\": [{\n" +
                "\t\t\t\"channelList\": [1, 6],\n" +
                "\t\t\t\"hasChildren\": true,\n" +
                "\t\t\t\"id\": 3451,\n" +
                "\t\t\t\"level\": 2,\n" +
                "\t\t\t\"name\": \"彩妆/香水/美妆工具\",\n" +
                "\t\t\t\"parentId\": 3388,\n" +
                "\t\t\t\"priority\": 18,\n" +
                "\t\t\t\"ruleId\": \"10030002\",\n" +
                "\t\t\t\"type\": 1\n" +
                "\t\t}, {\n" +
                "\t\t\t\"channelList\": [1, 6],\n" +
                "\t\t\t\"hasChildren\": true,\n" +
                "\t\t\t\"id\": 3693,\n" +
                "\t\t\t\"level\": 2,\n" +
                "\t\t\t\"name\": \"手表\",\n" +
                "\t\t\t\"parentId\": 3388,\n" +
                "\t\t\t\"priority\": 23,\n" +
                "\t\t\t\"ruleId\": \"10030007\",\n" +
                "\t\t\t\"type\": 1\n" +
                "\t\t}, {\n" +
                "\t\t\t\"channelList\": [1, 6],\n" +
                "\t\t\t\"hasChildren\": true,\n" +
                "\t\t\t\"id\": 3706,\n" +
                "\t\t\t\"level\": 2,\n" +
                "\t\t\t\"name\": \"珠宝/钻石/翡翠/黄金\",\n" +
                "\t\t\t\"parentId\": 3388,\n" +
                "\t\t\t\"priority\": 24,\n" +
                "\t\t\t\"ruleId\": \"10030008\",\n" +
                "\t\t\t\"type\": 1\n" +
                "\t\t}, {\n" +
                "\t\t\t\"channelList\": [1, 6],\n" +
                "\t\t\t\"hasChildren\": true,\n" +
                "\t\t\t\"id\": 3389,\n" +
                "\t\t\t\"level\": 2,\n" +
                "\t\t\t\"name\": \"眼镜/酒具/替烟品\",\n" +
                "\t\t\t\"parentId\": 3388,\n" +
                "\t\t\t\"priority\": 17,\n" +
                "\t\t\t\"ruleId\": \"10030001\",\n" +
                "\t\t\t\"type\": 1\n" +
                "\t\t}, {\n" +
                "\t\t\t\"channelList\": [1, 6],\n" +
                "\t\t\t\"hasChildren\": true,\n" +
                "\t\t\t\"id\": 3496,\n" +
                "\t\t\t\"level\": 2,\n" +
                "\t\t\t\"name\": \"美容护发/假发\",\n" +
                "\t\t\t\"parentId\": 3388,\n" +
                "\t\t\t\"priority\": 19,\n" +
                "\t\t\t\"ruleId\": \"10030003\",\n" +
                "\t\t\t\"type\": 1\n" +
                "\t\t}, {\n" +
                "\t\t\t\"channelList\": [1, 6],\n" +
                "\t\t\t\"hasChildren\": true,\n" +
                "\t\t\t\"id\": 3529,\n" +
                "\t\t\t\"level\": 2,\n" +
                "\t\t\t\"name\": \"美容护肤/美体/精油\",\n" +
                "\t\t\t\"parentId\": 3388,\n" +
                "\t\t\t\"priority\": 20,\n" +
                "\t\t\t\"ruleId\": \"10030004\",\n" +
                "\t\t\t\"type\": 1\n" +
                "\t\t}, {\n" +
                "\t\t\t\"channelList\": [1, 6],\n" +
                "\t\t\t\"hasChildren\": true,\n" +
                "\t\t\t\"id\": 3635,\n" +
                "\t\t\t\"level\": 2,\n" +
                "\t\t\t\"name\": \"美容美体仪器\",\n" +
                "\t\t\t\"parentId\": 3388,\n" +
                "\t\t\t\"priority\": 21,\n" +
                "\t\t\t\"ruleId\": \"10030005\",\n" +
                "\t\t\t\"type\": 1\n" +
                "\t\t}, {\n" +
                "\t\t\t\"channelList\": [1, 6],\n" +
                "\t\t\t\"hasChildren\": true,\n" +
                "\t\t\t\"id\": 3664,\n" +
                "\t\t\t\"level\": 2,\n" +
                "\t\t\t\"name\": \"饰品/流行首饰\",\n" +
                "\t\t\t\"parentId\": 3388,\n" +
                "\t\t\t\"priority\": 22,\n" +
                "\t\t\t\"ruleId\": \"10030006\",\n" +
                "\t\t\t\"type\": 1\n" +
                "\t\t}]\n" +
                "\t}, {\n" +
                "\t\t\"channelList\": [1, 6],\n" +
                "\t\t\"hasChildren\": true,\n" +
                "\t\t\"id\": 14502,\n" +
                "\t\t\"level\": 1,\n" +
                "\t\t\"name\": \"话费卡券\",\n" +
                "\t\t\"parentId\": 0,\n" +
                "\t\t\"priority\": 15,\n" +
                "\t\t\"ruleId\": \"1015\",\n" +
                "\t\t\"type\": 1,\n" +
                "\t\t\"child\": [{\n" +
                "\t\t\t\"channelList\": [1, 6],\n" +
                "\t\t\t\"hasChildren\": true,\n" +
                "\t\t\t\"id\": 14510,\n" +
                "\t\t\t\"level\": 2,\n" +
                "\t\t\t\"name\": \"会员会费/虚拟币\",\n" +
                "\t\t\t\"parentId\": 14502,\n" +
                "\t\t\t\"priority\": 124,\n" +
                "\t\t\t\"ruleId\": \"10150002\",\n" +
                "\t\t\t\"type\": 1\n" +
                "\t\t}, {\n" +
                "\t\t\t\"channelList\": [1, 6],\n" +
                "\t\t\t\"hasChildren\": true,\n" +
                "\t\t\t\"id\": 14513,\n" +
                "\t\t\t\"level\": 2,\n" +
                "\t\t\t\"name\": \"会员会费/虚拟币/消费卡\",\n" +
                "\t\t\t\"parentId\": 14502,\n" +
                "\t\t\t\"priority\": 125,\n" +
                "\t\t\t\"ruleId\": \"10150003\",\n" +
                "\t\t\t\"type\": 1\n" +
                "\t\t}, {\n" +
                "\t\t\t\"channelList\": [1, 6],\n" +
                "\t\t\t\"hasChildren\": true,\n" +
                "\t\t\t\"id\": 14526,\n" +
                "\t\t\t\"level\": 2,\n" +
                "\t\t\t\"name\": \"网游装备/游戏币/帐号/代练\",\n" +
                "\t\t\t\"parentId\": 14502,\n" +
                "\t\t\t\"priority\": 126,\n" +
                "\t\t\t\"ruleId\": \"10150004\",\n" +
                "\t\t\t\"type\": 1\n" +
                "\t\t}, {\n" +
                "\t\t\t\"channelList\": [1, 6],\n" +
                "\t\t\t\"hasChildren\": true,\n" +
                "\t\t\t\"id\": 14533,\n" +
                "\t\t\t\"level\": 2,\n" +
                "\t\t\t\"name\": \"网络游戏点卡\",\n" +
                "\t\t\t\"parentId\": 14502,\n" +
                "\t\t\t\"priority\": 127,\n" +
                "\t\t\t\"ruleId\": \"10150005\",\n" +
                "\t\t\t\"type\": 1\n" +
                "\t\t}, {\n" +
                "\t\t\t\"channelList\": [1, 6],\n" +
                "\t\t\t\"hasChildren\": true,\n" +
                "\t\t\t\"id\": 14503,\n" +
                "\t\t\t\"level\": 2,\n" +
                "\t\t\t\"name\": \"腾讯QQ专区\",\n" +
                "\t\t\t\"parentId\": 14502,\n" +
                "\t\t\t\"priority\": 123,\n" +
                "\t\t\t\"ruleId\": \"10150001\",\n" +
                "\t\t\t\"type\": 1\n" +
                "\t\t}]\n" +
                "\t}, {\n" +
                "\t\t\"channelList\": [1, 6],\n" +
                "\t\t\"hasChildren\": true,\n" +
                "\t\t\"id\": 6502,\n" +
                "\t\t\"level\": 1,\n" +
                "\t\t\"name\": \"运动户外\",\n" +
                "\t\t\"parentId\": 0,\n" +
                "\t\t\"priority\": 9,\n" +
                "\t\t\"ruleId\": \"1009\",\n" +
                "\t\t\"type\": 1,\n" +
                "\t\t\"child\": [{\n" +
                "\t\t\t\"channelList\": [1, 6],\n" +
                "\t\t\t\"hasChildren\": true,\n" +
                "\t\t\t\"id\": 6503,\n" +
                "\t\t\t\"level\": 2,\n" +
                "\t\t\t\"name\": \"户外/登山/野营/旅行用品\",\n" +
                "\t\t\t\"parentId\": 6502,\n" +
                "\t\t\t\"priority\": 65,\n" +
                "\t\t\t\"ruleId\": \"10090001\",\n" +
                "\t\t\t\"type\": 1\n" +
                "\t\t}, {\n" +
                "\t\t\t\"channelList\": [1, 6],\n" +
                "\t\t\t\"hasChildren\": true,\n" +
                "\t\t\t\"id\": 7733,\n" +
                "\t\t\t\"level\": 2,\n" +
                "\t\t\t\"name\": \"自行车/骑行装备/零配件\",\n" +
                "\t\t\t\"parentId\": 6502,\n" +
                "\t\t\t\"priority\": 70,\n" +
                "\t\t\t\"ruleId\": \"10090006\",\n" +
                "\t\t\t\"type\": 1\n" +
                "\t\t}, {\n" +
                "\t\t\t\"channelList\": [1, 6],\n" +
                "\t\t\t\"hasChildren\": true,\n" +
                "\t\t\t\"id\": 6883,\n" +
                "\t\t\t\"level\": 2,\n" +
                "\t\t\t\"name\": \"运动/瑜伽/健身/球迷用品\",\n" +
                "\t\t\t\"parentId\": 6502,\n" +
                "\t\t\t\"priority\": 66,\n" +
                "\t\t\t\"ruleId\": \"10090002\",\n" +
                "\t\t\t\"type\": 1\n" +
                "\t\t}, {\n" +
                "\t\t\t\"channelList\": [1, 6],\n" +
                "\t\t\t\"hasChildren\": true,\n" +
                "\t\t\t\"id\": 7650,\n" +
                "\t\t\t\"level\": 2,\n" +
                "\t\t\t\"name\": \"运动包/户外包/配件\",\n" +
                "\t\t\t\"parentId\": 6502,\n" +
                "\t\t\t\"priority\": 67,\n" +
                "\t\t\t\"ruleId\": \"10090003\",\n" +
                "\t\t\t\"type\": 1\n" +
                "\t\t}, {\n" +
                "\t\t\t\"channelList\": [1, 6],\n" +
                "\t\t\t\"hasChildren\": true,\n" +
                "\t\t\t\"id\": 7689,\n" +
                "\t\t\t\"level\": 2,\n" +
                "\t\t\t\"name\": \"运动服/休闲服装\",\n" +
                "\t\t\t\"parentId\": 6502,\n" +
                "\t\t\t\"priority\": 68,\n" +
                "\t\t\t\"ruleId\": \"10090004\",\n" +
                "\t\t\t\"type\": 1\n" +
                "\t\t}, {\n" +
                "\t\t\t\"channelList\": [1, 6],\n" +
                "\t\t\t\"hasChildren\": true,\n" +
                "\t\t\t\"id\": 7717,\n" +
                "\t\t\t\"level\": 2,\n" +
                "\t\t\t\"name\": \"运动鞋\",\n" +
                "\t\t\t\"parentId\": 6502,\n" +
                "\t\t\t\"priority\": 69,\n" +
                "\t\t\t\"ruleId\": \"10090005\",\n" +
                "\t\t\t\"type\": 1\n" +
                "\t\t}]\n" +
                "\t}, {\n" +
                "\t\t\"channelList\": [1, 6],\n" +
                "\t\t\"hasChildren\": true,\n" +
                "\t\t\"id\": 2519,\n" +
                "\t\t\"level\": 1,\n" +
                "\t\t\"name\": \"食品酒水\",\n" +
                "\t\t\"parentId\": 0,\n" +
                "\t\t\"priority\": 2,\n" +
                "\t\t\"ruleId\": \"1002\",\n" +
                "\t\t\"type\": 1,\n" +
                "\t\t\"child\": [{\n" +
                "\t\t\t\"channelList\": [1, 6],\n" +
                "\t\t\t\"hasChildren\": true,\n" +
                "\t\t\t\"id\": 2698,\n" +
                "\t\t\t\"level\": 2,\n" +
                "\t\t\t\"name\": \"传统滋补营养品\",\n" +
                "\t\t\t\"parentId\": 2519,\n" +
                "\t\t\t\"priority\": 11,\n" +
                "\t\t\t\"ruleId\": \"10020003\",\n" +
                "\t\t\t\"type\": 1\n" +
                "\t\t}, {\n" +
                "\t\t\t\"channelList\": [1, 6],\n" +
                "\t\t\t\"hasChildren\": true,\n" +
                "\t\t\t\"id\": 2520,\n" +
                "\t\t\t\"level\": 2,\n" +
                "\t\t\t\"name\": \"保健食品/膳食营养食品\",\n" +
                "\t\t\t\"parentId\": 2519,\n" +
                "\t\t\t\"priority\": 9,\n" +
                "\t\t\t\"ruleId\": \"10020001\",\n" +
                "\t\t\t\"type\": 1\n" +
                "\t\t}, {\n" +
                "\t\t\t\"channelList\": [1, 6],\n" +
                "\t\t\t\"hasChildren\": true,\n" +
                "\t\t\t\"id\": 2833,\n" +
                "\t\t\t\"level\": 2,\n" +
                "\t\t\t\"name\": \"咖啡/麦片/饮料/乳制品\",\n" +
                "\t\t\t\"parentId\": 2519,\n" +
                "\t\t\t\"priority\": 13,\n" +
                "\t\t\t\"ruleId\": \"10020005\",\n" +
                "\t\t\t\"type\": 1\n" +
                "\t\t}, {\n" +
                "\t\t\t\"channelList\": [1, 6],\n" +
                "\t\t\t\"hasChildren\": true,\n" +
                "\t\t\t\"id\": 3167,\n" +
                "\t\t\t\"level\": 2,\n" +
                "\t\t\t\"name\": \"水产肉类/新鲜蔬果/熟食/现做食品\",\n" +
                "\t\t\t\"parentId\": 2519,\n" +
                "\t\t\t\"priority\": 16,\n" +
                "\t\t\t\"ruleId\": \"10020008\",\n" +
                "\t\t\t\"type\": 1\n" +
                "\t\t}, {\n" +
                "\t\t\t\"channelList\": [1, 6],\n" +
                "\t\t\t\"hasChildren\": true,\n" +
                "\t\t\t\"id\": 2887,\n" +
                "\t\t\t\"level\": 2,\n" +
                "\t\t\t\"name\": \"粮油米面/南北干货/调味品\",\n" +
                "\t\t\t\"parentId\": 2519,\n" +
                "\t\t\t\"priority\": 14,\n" +
                "\t\t\t\"ruleId\": \"10020006\",\n" +
                "\t\t\t\"type\": 1\n" +
                "\t\t}, {\n" +
                "\t\t\t\"channelList\": [1, 6],\n" +
                "\t\t\t\"hasChildren\": true,\n" +
                "\t\t\t\"id\": 2631,\n" +
                "\t\t\t\"level\": 2,\n" +
                "\t\t\t\"name\": \"茶\",\n" +
                "\t\t\t\"parentId\": 2519,\n" +
                "\t\t\t\"priority\": 10,\n" +
                "\t\t\t\"ruleId\": \"10020002\",\n" +
                "\t\t\t\"type\": 1\n" +
                "\t\t}, {\n" +
                "\t\t\t\"channelList\": [1, 6],\n" +
                "\t\t\t\"hasChildren\": true,\n" +
                "\t\t\t\"id\": 2820,\n" +
                "\t\t\t\"level\": 2,\n" +
                "\t\t\t\"name\": \"酒类\",\n" +
                "\t\t\t\"parentId\": 2519,\n" +
                "\t\t\t\"priority\": 12,\n" +
                "\t\t\t\"ruleId\": \"10020004\",\n" +
                "\t\t\t\"type\": 1\n" +
                "\t\t}, {\n" +
                "\t\t\t\"channelList\": [1, 6],\n" +
                "\t\t\t\"hasChildren\": true,\n" +
                "\t\t\t\"id\": 3021,\n" +
                "\t\t\t\"level\": 2,\n" +
                "\t\t\t\"name\": \"零食/坚果/特产\",\n" +
                "\t\t\t\"parentId\": 2519,\n" +
                "\t\t\t\"priority\": 15,\n" +
                "\t\t\t\"ruleId\": \"10020007\",\n" +
                "\t\t\t\"type\": 1\n" +
                "\t\t}]\n" +
                "\t}]";
        JSONArray jsonArray = JSONObject.parseArray(result);
        for (int i = 0; i < jsonArray.size(); i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            ProductCategory productCategory = new ProductCategory();
            productCategory.setProductCategoryPid(jsonObject.getString("parentId"));
            productCategory.setName(jsonObject.getString("name"));
            productCategory.setSeqNum(i);
            productCategory.setChildrenFlag(jsonObject.getBoolean("hasChildren"));
        }

    }


}

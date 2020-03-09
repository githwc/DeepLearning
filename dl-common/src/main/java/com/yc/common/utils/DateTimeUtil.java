package com.yc.common.utils;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.Calendar;
import java.util.Date;

/**
 * 功能描述：日期时间工具包
 *
 * <p>版权所有：</p>
 * 未经本人许可，不得以任何方式复制或使用本程序任何部分
 *
 * @Company: 紫色年华
 * @Author: xieyc
 * @Datetime: 2019-05-09 19:04
 */
public class DateTimeUtil {

    /**
     * 日期类型 *
     */
    public static final String yyyyMMDD = "yyyy-MM-dd";
    public static final String yyyyMMddHHmmss = "yyyy-MM-dd HH:mm:ss";
    public static final String HHmmss = "HH:mm:ss";

    /**
     * 计算2个日期之间相差多少年月日 ===== dealExportData 子方法 =====
     *
     * @param beginDate 开始时间
     * @param endDate 结束时间
     * @return 0,2,13
     */
    public static String dayCompareStr(String beginDate,String endDate){
        Period period = Period.between(LocalDate.parse(beginDate), LocalDate.parse(endDate));
        StringBuffer sb = new StringBuffer();
        sb.append(period.getYears()).append(",")
                .append(period.getMonths()).append(",")
                .append(period.getDays());
        return sb.toString();
    }


    /**
     * 以年、月、日为单位计算2个日期之间相差
     *  各自计算结果是多少
     *     以年为单位相差为：6年
     *     以月为单位相差为：73个月
     *     以日为单位相差为：2220天
     *
     * @param fromDate
     * @param toDate
     * @return  1,12,364
     */
    public static String dayCompare(Date fromDate,Date toDate){
        Calendar from = Calendar.getInstance();
        from.setTime(fromDate);
        Calendar to = Calendar.getInstance();
        to.setTime(toDate);

        int fromYear = from.get(Calendar.YEAR);
        int fromMonth = from.get(Calendar.MONTH);

        int toYear = to.get(Calendar.YEAR);
        int toMonth = to.get(Calendar.MONTH);

        int year = toYear - fromYear;
        int month = toYear *  12  + toMonth  -  (fromYear  *  12  +  fromMonth);
        int day = (int) ((to.getTimeInMillis()  -  from.getTimeInMillis())  /  (24  *  3600  *  1000));
        return year+","+month+","+day;
    }

    /**
     * 以天、时、分、秒计算两个时间差
     * @param startTime 开始时间
     * @param endTime 结束时间
     * @return X天 X小时 X分钟 X秒 例如:49分钟23秒
     */
    public static String getDateDiff(LocalDateTime startTime, LocalDateTime endTime) {
        long nd = 1000 * 24 * 60 * 60;
        long nh = 1000 * 60 * 60;
        long nm = 1000 * 60;
        long ns = 1000;
        // 获得两个时间的毫秒时间差异
        Duration duration = Duration.between(startTime,endTime);
        long diff = duration.toMillis();
        // 计算差多少天
        long day = diff / nd;
        // 计算差多少小时
        long hour = diff % nd / nh;
        // 计算差多少分钟
        long min = diff % nd % nh / nm;
        // 计算差多少秒//输出结果
        long sec = diff % nd % nh % nm / ns;
        if(hour == 0 ){
            return min + "分钟"+sec +"秒";
        }else if(hour > 0){
            return hour + "小时" + min + "分钟"+sec +"秒";
        }else {
            return day + "天" + hour + "小时" + min + "分钟"+sec +"秒";
        }
    }

    /**
     * @Description:阿拉伯数字为中文数字(按指定格式)
     * @Param date:  java.util.Date
     * @Param format:   时间格式："yyyy-MM-dd HH:mm:ss"
     * @Return: 二〇一九年十二月二十一日
     * @Date: 19:22 2019/5/9
     */
    public static String toZhCNString(Date date,String format) {
        String strDate = "";
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        if (date != null) {
            strDate = sdf.format(date);
            String strUp[] = {"〇", "一", "二", "三", "四", "五", "六", "七", "八", "九", "十"},
                    strTen[] = {"", "十", "二十", "三十"};
            String str = "";
            int nPos = 0;
            if (!"".equals(strDate)) {
                for (int i = 0; i < 10; i++) {
                    if (i < 4) {
                        str += strUp[Integer.parseInt(strDate.substring(i, i + 1))];
                    } else if (i > 4 && i != 7) {
                        nPos = Integer.parseInt(strDate.substring(i, i + 1));
                        str += strTen[nPos];
                        i++;
                        nPos = Integer.parseInt(strDate.substring(i, i + 1));
                        if (nPos > 0){
                            str += strUp[nPos];
                        }
                    } else{
                        str += i == 4 ? "年" : "月";
                    }
                }
                strDate = str + "日";
            }
        }
        return strDate;
    }

}

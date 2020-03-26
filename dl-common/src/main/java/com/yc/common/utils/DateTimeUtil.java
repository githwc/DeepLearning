package com.yc.common.utils;

import org.apache.commons.lang3.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
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
public class DateTimeUtil extends org.apache.commons.lang3.time.DateUtils {

    /**
     * 日期类型 *
     */
    public static final String yyyyMMDD = "yyyy-MM-dd";
    public static final String yyyyMMddHHmmss = "yyyy-MM-dd HH:mm:ss";
    public static final String HHmmss = "HH:mm:ss";

    public static final DateTimeFormatter yyyyMMDD_FMT = DateTimeFormatter.ofPattern(yyyyMMDD);
    public static final DateTimeFormatter yyyyMMddHHmmss_FMT = DateTimeFormatter.ofPattern(yyyyMMddHHmmss);

    /**
     * java.util.Date to String
     *
     * @param date
     * @return
     */
    public static String dateToString(Date date) {
        return new SimpleDateFormat(yyyyMMddHHmmss).format(date);
    }

    /**
     * java.util.Date to String
     *
     * @param date
     * @param pattern
     * @return
     */
    public static String dateToString(Date date, String pattern) {
        return new SimpleDateFormat(pattern).format(date);
    }

    /**
     * String to java.util.Date
     *
     * @param dateStr
     * @return
     */
    public static Date stringToDate(String dateStr) {
        try {
            return new SimpleDateFormat(yyyyMMddHHmmss).parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * String to java.util.Date
     *
     * @param dateStr
     * @param pattern
     * @return
     */
    public static Date stringToDate(String dateStr, String pattern) {
        try {
            return new SimpleDateFormat(pattern).parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * String to LocalDate
     *
     * @param dateStr
     * @return
     */
    public static LocalDate stringToLocalDate(String dateStr) {
        if (StringUtils.isBlank(dateStr)) {
            return null;
        }
        return LocalDate.parse(dateStr, yyyyMMDD_FMT);
    }

    /**
     * String to LocalDateTime
     *
     * @param dateStr
     * @return
     */
    public static LocalDateTime stringToLocalDateTime(String dateStr) {
        if (StringUtils.isBlank(dateStr)) {
            return null;
        }
        return LocalDateTime.parse(dateStr, yyyyMMddHHmmss_FMT);
    }

    /**
     * Date to LocalDate
     *
     * @param date
     * @return
     */
    public static LocalDate dateToLocalDate(Date date) {
        LocalDateTime dateTime = dateToLocalDateTime(date);
        if (dateTime == null) {
            return null;
        }
        return dateTime.toLocalDate();
    }

    /**
     * Date to LocalDateTime
     *
     * @param date
     * @return
     */
    public static LocalDateTime dateToLocalDateTime(Date date) {
        if (date == null) {
            return null;
        }
        Instant instant = date.toInstant();
        return LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
    }

    /**
     * Date to LocalTime
     *
     * @param date
     * @return
     */
    public static LocalTime dateToLocalTime(Date date) {
        LocalDateTime dateTime = dateToLocalDateTime(date);
        if (dateTime == null) {
            return null;
        }
        return dateTime.toLocalTime();
    }


    /**
     * 得到年
     *
     * @param date Date对象
     * @return 年
     */
    public static int getYear(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c.get(Calendar.YEAR);
    }

    /**
     * 得到月
     *
     * @param date Date对象
     * @return 月
     */
    public static int getMonth(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c.get(Calendar.MONTH) + 1;

    }

    /**
     * 得到日
     *
     * @param date Date对象
     * @return 日
     */
    public static int getDay(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c.get(Calendar.DAY_OF_MONTH);
    }

    /**
     * 转换日期 将日期转为今天, 昨天, 前天, XXXX-XX-XX, ...
     *
     * @param time 时间
     * @return 当前日期转换为更容易理解的方式
     */
    public static String translateDate(Long time) {
        long oneDay = 24 * 60 * 60 * 1000;
        Calendar current = Calendar.getInstance();
        Calendar today = Calendar.getInstance();    //今天

        today.set(Calendar.YEAR, current.get(Calendar.YEAR));
        today.set(Calendar.MONTH, current.get(Calendar.MONTH));
        today.set(Calendar.DAY_OF_MONTH, current.get(Calendar.DAY_OF_MONTH));
        //  Calendar.HOUR——12小时制的小时数 Calendar.HOUR_OF_DAY——24小时制的小时数
        today.set(Calendar.HOUR_OF_DAY, 0);
        today.set(Calendar.MINUTE, 0);
        today.set(Calendar.SECOND, 0);

        long todayStartTime = today.getTimeInMillis();

        if (time >= todayStartTime && time < todayStartTime + oneDay) { // today
            return "今天";
        } else if (time >= todayStartTime - oneDay && time < todayStartTime) { // yesterday
            return "昨天";
        } else if (time >= todayStartTime - oneDay * 2 && time < todayStartTime - oneDay) { // the day before yesterday
            return "前天";
        } else if (time > todayStartTime + oneDay) { // future
            return "将来某一天";
        } else {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date date = new Date(time);
            return dateFormat.format(date);
        }
    }

    /**
     * 转换日期 转换为更为人性化的时间
     *
     * @param time 时间
     * @return
     */
    private String translateDate(long time, long curTime) {
        long oneDay = 24 * 60 * 60;
        Calendar today = Calendar.getInstance();    //今天
        today.setTimeInMillis(curTime * 1000);
        today.set(Calendar.HOUR_OF_DAY, 0);
        today.set(Calendar.MINUTE, 0);
        today.set(Calendar.SECOND, 0);
        long todayStartTime = today.getTimeInMillis() / 1000;
        if (time >= todayStartTime) {
            long d = curTime - time;
            if (d <= 60) {
                return "1分钟前";
            } else if (d <= 60 * 60) {
                long m = d / 60;
                if (m <= 0) {
                    m = 1;
                }
                return m + "分钟前";
            } else {
                SimpleDateFormat dateFormat = new SimpleDateFormat("今天 HH:mm");
                Date date = new Date(time * 1000);
                String dateStr = dateFormat.format(date);
                if (!StringUtils.isBlank(dateStr) && dateStr.contains(" 0")) {
                    dateStr = dateStr.replace(" 0", " ");
                }
                return dateStr;
            }
        } else {
            if (time < todayStartTime && time > todayStartTime - oneDay) {
                SimpleDateFormat dateFormat = new SimpleDateFormat("昨天 HH:mm");
                Date date = new Date(time * 1000);
                String dateStr = dateFormat.format(date);
                if (!StringUtils.isBlank(dateStr) && dateStr.contains(" 0")) {

                    dateStr = dateStr.replace(" 0", " ");
                }
                return dateStr;
            } else if (time < todayStartTime - oneDay && time > todayStartTime - 2 * oneDay) {
                SimpleDateFormat dateFormat = new SimpleDateFormat("前天 HH:mm");
                Date date = new Date(time * 1000);
                String dateStr = dateFormat.format(date);
                if (!StringUtils.isBlank(dateStr) && dateStr.contains(" 0")) {
                    dateStr = dateStr.replace(" 0", " ");
                }
                return dateStr;
            } else {
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                Date date = new Date(time * 1000);
                String dateStr = dateFormat.format(date);
                if (!StringUtils.isBlank(dateStr) && dateStr.contains(" 0")) {
                    dateStr = dateStr.replace(" 0", " ");
                }
                return dateStr;
            }
        }
    }

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

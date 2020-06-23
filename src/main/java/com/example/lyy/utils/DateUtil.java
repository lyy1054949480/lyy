package com.example.lyy.utils;

import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * @author yindq
 * @title DateUtil
 * @description 日期操作的工具类
 * @date 2019/6/4 14:47
 */
public class DateUtil {
    private static final int ZERO = 0;
    private static final int ONE = 1;
    private static final int SEVEN = 7;
    private static final int FIFTEEN = 15;
    private static final int SIXTEEN = 16;
    private static final int TWENTY = 20;
    private static final long ND = 1000 * 24 * 60 * 60;
    private static final long NH = 1000 * 60 * 60;
    private static final long NM = 1000 * 60;
    /**
     * 格式化格式 yyyyMMdd
     */
    public static final String REDIS_FORMAT = "yyyyMMdd";
    /**
     * 格式化格式 yyyy-MM-dd
     */
    public static final String INSPECTOR_FORMAT = "yyyy-MM-dd";
    /**
     * 格式化格式 yyyy-MM-dd HH:mm:ss
     */
    public static final String NORMAL_FORMAT = "yyyy-MM-dd HH:mm:ss";

    /**
     * LocalDateTime转换为Date
     *
     * @param localDateTime
     * @return
     */
    public static Date localDateTimeToDate(LocalDateTime localDateTime) {
        ZoneId zoneId = ZoneId.systemDefault();
        ZonedDateTime zdt = localDateTime.atZone(zoneId);
        return Date.from(zdt.toInstant());
    }

    /**
     * 将当前时间 变成当月1号或16号7点
     *
     * @param localDateTime
     * @return
     */
    public static Date getStartDate(LocalDateTime localDateTime) {
        int day = localDateTime.getDayOfMonth();
        if (day < SIXTEEN) {
            day = ONE;
        } else {
            day = SIXTEEN;
        }
        LocalDateTime startDate = LocalDateTime.of(localDateTime.getYear(), localDateTime.getMonthValue(),
                day, SEVEN, ZERO, ZERO);
        return localDateTimeToDate(startDate);
    }

    /**
     * 将当前时间 变成当月15号或月末20点
     *
     * @param localDateTime
     * @return
     */
    public static Date getEndDate(LocalDateTime localDateTime) {
        int day = localDateTime.getDayOfMonth();
        int lastDay = localDateTime.with(TemporalAdjusters.lastDayOfMonth()).getDayOfMonth();
        if (day < SIXTEEN) {
            day = FIFTEEN;
        } else {
            day = lastDay;
        }
        LocalDateTime endDate = LocalDateTime.of(localDateTime.getYear(), localDateTime.getMonthValue(),
                day, TWENTY, ZERO, ZERO);
        return localDateTimeToDate(endDate);
    }

    /**
     * LocalDateTime格式化为String【yyyyMMdd】
     *
     * @param localDateTime
     * @return
     */
    public static String formatDate(LocalDateTime localDateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(REDIS_FORMAT);
        return formatter.format(localDateTime);
    }

    /**
     * Date格式化为String
     *
     * @param date
     * @param format
     * @return
     */
    public static String formatDate(Date date,String format){
        if(date == null){
            return "";
        }
        ZoneId zoneId = ZoneId.systemDefault();
        LocalDateTime localDateTime = date.toInstant().atZone(zoneId).toLocalDateTime();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
        return formatter.format(localDateTime);
    }

    /**
     * 获取当前时间星期
     * 对应com.longfor.wydt.commons.enums.WeekdayEnum 星期枚举
     *
     * @param
     * @return
     */
    public static int getWeekDay() {
        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int i = calendar.get(Calendar.DAY_OF_WEEK);
        return i - 1 == 0 ? 7 : i - 1;
    }

    /**
     * 获取当前时间星期
     * 对应com.longfor.wydt.commons.enums.WeekdayEnum 星期枚举
     *
     * @param
     * @return
     */
    public static int getNextWeekDay() {
        Date date = DateUtil.getNextDate(Time.valueOf("00:00:00"),1);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int i = calendar.get(Calendar.DAY_OF_WEEK);
        return i - 1 == 0 ? 7 : i - 1;
    }

    /**
     * 检查时间 [time1,time2] 是否在时间范围 [time3,time4] 内
     *
     * @param time1,time2,time3,time4
     * @return boolean
     */
    public static boolean checkTimeRange(Time time1, Time time2, Time time3, Time time4) {
        return !time1.before(time3) && !time2.after(time4);
    }

    public static boolean checkTime(Time time1, Time time2, Time time3, Time time4) {
        //判断【time1,time2】、【time3,time4】是否跨天
        boolean check = !time2.after(time1);
        boolean range = !time4.after(time3);
        Time dawn = Time.valueOf("00:00:00");
        Time late = Time.valueOf("23:59:59");
        //检查时间跨天，范围未跨天
        if (check && !range) {
            return false;
        }
        //检查时间未跨天，范围跨天
        if (!check && range) {
            //将范围时间拆开【time3,dawn】【dawn,time4】
            return checkTimeRange(time1, time2, time3, late) || checkTimeRange(time1, time2, dawn, time4);
        }
        //检查时间跨天，范围都跨天
        //检查时间未跨天，范围跨天
        return checkTimeRange(time1, time2, time3, time4);
    }

    /**
     * 将Time转为Date
     *
     * @param time
     * @return Date
     */
    public static Date getDate(Time time) {
        Date date = new Date();
        date.setHours(time.getHours());
        date.setMinutes(time.getMinutes());
        date.setSeconds(0);
        return date;
    }

    /**
     * 将Time转为Date
     *
     * @param time
     * @return Date
     */
    public static Date getNextDate(Time time, Integer days) {
        Calendar c = Calendar.getInstance();
        Date date = new Date();
        date.setHours(time.getHours());
        date.setMinutes(time.getMinutes());
        date.setSeconds(0);
        c.setTime(date);
        int day = c.get(Calendar.DATE);
        c.set(Calendar.DATE, day + days);
        return c.getTime();
    }

    /**
     * 将Time转为Date
     *
     * @param date
     * @return Time
     */
    public static Time getTiem(Date date) {
        DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
        String format = dateFormat.format(date);
        Time time = Time.valueOf(format);
        return time;
    }

    /**
     * 检查字符串是否为Date
     *
     * @param date
     * @return boolean
     */
    public static boolean checkDate(String date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", Locale.US);
        try {
            simpleDateFormat.setLenient(false);
            simpleDateFormat.parse(date);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 检查字符串是否为yyyy/MM/dd,是则返回日期
     *
     * @param date
     * @return Date
     */
    public static Date checkDateTrim(String date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd");
        try {
            simpleDateFormat.setLenient(false);
            Date parse = simpleDateFormat.parse(date);
            return parse;
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 将字符串转为Date
     *
     * @param date
     * @return boolean
     */
    public static Date getDate(String date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", Locale.US);
        try {
            simpleDateFormat.setLenient(false);
            Date parse = simpleDateFormat.parse(date);
            return parse;
        } catch (Exception e) {
            return new Date();
        }
    }


    /**
     * 获取时间差-分钟
     *
     * @param type        1,2,3，4 天 时 分 秒
     * @param date1,date2
     * @return Integer
     */
    public static Long getDiffTime(Date date1, Date date2, Integer type) {

        long diff = date1.getTime() - date2.getTime();
        //diff = diff > 0 ? diff : date2.getTime() - date1.getTime();
        long time = 0L;
        switch (type) {
            case 1:
                time = diff / ND;
                break;
            case 2:
                time = diff / NH;
                break;
            case 3:
                time = diff / NM;
                break;
            default:
                time = diff/1000;
                break;
        }

        return time;
    }

    /**
     * 判断两个时间是否有交叉
     *
     * @param leftStartDate,leftEndDate,rightStartDate,rightEndDate
     * @return boolean
     */
    public static boolean isOverlap(Date leftStartDate, Date leftEndDate, Date rightStartDate, Date rightEndDate) {


        return
                ((leftStartDate.getTime() >= rightStartDate.getTime())
                        && leftStartDate.getTime() < rightEndDate.getTime())
                        ||
                        ((leftStartDate.getTime() > rightStartDate.getTime())
                                && leftStartDate.getTime() <= rightEndDate.getTime())
                        ||
                        ((rightStartDate.getTime() >= leftStartDate.getTime())
                                && rightStartDate.getTime() < leftEndDate.getTime())
                        ||
                        ((rightStartDate.getTime() > leftStartDate.getTime())
                                && rightStartDate.getTime() <= leftEndDate.getTime());

    }

    /**
     * 将时间格式格式化成年月日
     */
    public static Date dateFormat(Date date) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
            String formatTime = sdf.format(date);
            return sdf.parse(formatTime);
        } catch (ParseException e) {
            return null;
        }

    }

    /**
     * @Title: 判断日期是否在月度计划中
     * @Description: 判断日期是否在月度计划中
     * @date: 2019/8/6
     * @author: zhaoxuezhao
     * @param: [date, startDay, duration]
     * @return: boolean
     */
    public static boolean checkDateRange(Date date,Integer startDay,Integer duration){
        Calendar c1 = Calendar.getInstance();
        c1.setTime(date);
        c1.set(Calendar.HOUR_OF_DAY,0);
        c1.set(Calendar.MINUTE,0);
        c1.set(Calendar.SECOND,0);
        int month = c1.get(Calendar.MONTH);
        date = c1.getTime();
        Calendar c2 = Calendar.getInstance();
        c2.setTime(date);
        c2.set(Calendar.MONTH,month);
        c2.set(Calendar.DATE,startDay);
        c2.set(Calendar.HOUR_OF_DAY,0);
        c2.set(Calendar.MINUTE,0);
        c2.set(Calendar.SECOND,0);
        Date startDate = c2.getTime();
        c2.set(Calendar.DATE,startDay+duration);
        Date endTime = c2.getTime();
        return !date.before(startDate) && !date.after(endTime);
    }

    /**
     *计算计划开始时间，整改工单计划开始时间比入参的时间延后两个小时
     */
    public static Date dateAdd(Date date, Integer num){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MINUTE,num);
        return calendar.getTime();
    }


}

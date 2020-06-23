package com.example.lyy.util.auxiliary;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @Description: 日期工具类
 * @author
 * @date 2018/8/8 10:01
 */
public class DateUtil {

    /**
     * 日期格式 年月日
     */
    public static final String DATE = "yyyy-MM-dd";

    /**
     * 日期格式 年月日时分秒
     */
    public static final String TIME = "yyyy-MM-dd HH:mm:ss";


    public static final String DATETIME = "yyyyMMdd";
    
    /**
     * 获取一段时间后的某个时间点
     * 
     * @author guohuibin
     * @date 2018/7/28 14:28
     * @param date
     * @param calendarType
     * @param num
     * @return java.util.Date
     */
    public static Date getDate(Date date, int calendarType, int num){
        if (date == null) {
            date = new Date();
        }
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(calendarType, num);
        Date d = c.getTime();
        return d;
    }
    
    /**
     * 日期String转Date
     * 
     * @author guohuibin
     * @date 2018/8/8 11:07
     * @param dateStr
     * @param dateFormat
     * @return java.util.Date
     */
    public static Date str2Date(String dateStr, String dateFormat){
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
        Date date = null;
        try {
            date = sdf.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    
    /**
     * 日期Date转String
     * 
     * @author guohuibin
     * @date 2018/8/8 11:42
     * @param date
     * @param dateFormat
     * @return java.lang.String
     */
    public static String date2Str(Date date, String dateFormat){
        if (date == null) {
            date = new Date();
        }
        SimpleDateFormat df = new SimpleDateFormat(dateFormat);
        String dateStr = df.format(date);
        return dateStr;
    }
    
    /**
     * 获取当前时间
     * 
     * @author guohuibin
     * @date 2018/8/8 19:52
     * @param dateFormat 日期格式(年月日 或 年月日时分秒)
     * @return java.util.Date
     */
    public static Date getNowDate(String dateFormat){
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
        String dateStr = sdf.format(date);
        Date now = null;
        try {
            now = sdf.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return now;
    }

    /**
     * 获取起期
     * 
     * @author guohuibin
     * @date 2018/8/9 16:01
     * @param date
     * @return java.util.Date
     */
    public static Date getStartDate(Date date){
        date = DateUtil.getDate(date, Calendar.DAY_OF_MONTH, 1);
        String str = date2Str(date, DATE) + " 00:00:00";
        Date startDate = str2Date(str, TIME);
        return startDate;
    }

    /**
     * 获取止期
     * 
     * @author guohuibin
     * @date 2018/8/9 16:01
     * @param date
     * @param num
     * @return java.util.Date
     */
    public static Date getEndDate(Date date, int num){
        date = DateUtil.getDate(date, Calendar.YEAR, num);
        date = DateUtil.getDate(date, Calendar.DAY_OF_MONTH, -1);
        String str = date2Str(date, DATE) + " 23:59:59";
        Date endDate = str2Date(str, TIME);
        return endDate;
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kezuo.util.mytool;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;

/**
 * @Description: 时间日期工具类
 * @author gliu
 * @date 2016-9-9 tags Copyright（C） 2010~2020 深圳市宏电技术股份有限公司
 */
public class TimeUtil {

    //时间格式
    public static final String YMDHMS = "yyyy-MM-dd HH:mm:ss";
    public static final String YMDHM = "yyyy-MM-dd HH:mm";
    public static final String YMDH = "yyyy-MM-dd HH";
    public static final String YMD = "yyyy-MM-dd";
    public static final String YM = "yyyy-MM";
    public static final String Y = "yyyy";
    public static final String HMS = "HH:mm:ss";

    /**
     * 增减天数获取新日期YMD
     *
     * @param date
     * @param step
     * @return
     */
    public static String getDateByDayStep(String date, int step) {
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(convertStringToDate(date, YMD));
        calendar.add(Calendar.DATE, step);
        return convertDateToString(calendar.getTime(), YMD);
    }

    /**
     * 增减小时数获取新时间YMDHMS
     *
     * @param time
     * @param step
     * @return
     */
    public static String getTimeByHourStep(String time, int step) {
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(convertStringToDate(time, YMDHMS));
        calendar.add(Calendar.HOUR, step);
        return convertDateToString(calendar.getTime(), YMDHMS);
    }

    /**
     * 根据传入时间获取该时间上个月最后一天的日期
     *
     * @param time
     * @return
     */
    public static String getPreMonthLastDate(String time) {
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(convertStringToDate(time.substring(0, 7) + "-01", YMD));
        calendar.add(Calendar.DATE, -1);
        return convertDateToString(calendar.getTime(), YMD);
    }

    /**
     * 根据传入时间获取该时间下个月第一天的日期
     *
     * @param time
     * @return
     */
    public static String getNextMonthFirstDate(String time) {
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(convertStringToDate(time, YMDHMS));
        calendar.add(Calendar.MONTH, 1);
        return convertDateToString(calendar.getTime(), YM) + "-01";
    }

    /**
     * 根据传入的日期（String）获得该日期所对应月最多有多少天
     *
     * @param date
     * @return
     */
    public static int getMaxDaysInMonth(String date) {
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(convertStringToDate(date.substring(0, 7) + "-01", YMD));
        calendar.add(Calendar.MONTH, 1);
        calendar.add(Calendar.DATE, -1);
        return calendar.get(Calendar.DATE);
    }

    /**
     * 根据传入的日期（Date）获得该日期所对应月最多有多少天
     *
     * @param date
     * @return
     */
    public static int getMaxDaysInMonth(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int maxDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
        return maxDay;
    }

    /**
     * 根据给定的日期格式将日期字符串解析为日期对象
     *
     * @param dateString 日期字符串
     * @param pattern 给定的日期格式,如果为NULL则默认使用yyyy-MM-dd
     * @return Date 解析后的日期
     */
    public static Date convertStringToDate(String dateString, String pattern) {
        Date date = null;
        if (pattern == null || pattern.trim().equals("")) {
            pattern = YMD;
        }
        try {
            if (pattern.equals(YMD)) {
                dateString = dateString.substring(0, pattern.length());
            } else {
                dateString = dateString.replace(".0", "");
            }
            date = DateUtils.parseDate(dateString, new String[]{pattern});
        } catch (ParseException ex) {
            Logger.getLogger(TimeUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
        return date;
    }

    /**
     * 根据给定的日期格式将日期解析为日期字符串
     *
     * @param date 日期
     * @param pattern 给定的日期格式,如果为NULL则默认使用yyyy-MM-dd
     * @return String 解析后的日期字符串
     */
    public static String convertDateToString(Date date, String pattern) {
        String dateString = null;
        if (pattern == null || pattern.trim().length() == 0) {
            pattern = YMD;
        }
        try {
            dateString = DateFormatUtils.format(date, pattern);
        } catch (Exception ex) {
            Logger.getLogger(TimeUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
        return dateString;
    }

    /**
     * 获得指定时间的后一小时
     *
     * @param specifiedDay
     * @return
     * @throws Exception
     */
    public static String getSpecifiedHourAfter(String specifiedTime) {
        Calendar c = Calendar.getInstance();
        Date date = null;
        try {
            //date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(specifiedTime);
            date = DateUtils.parseDate(specifiedTime, new String[]{"yyyy-MM-dd HH:mm:ss"});
        } catch (ParseException e) {
            Logger.getLogger(TimeUtil.class.getName()).log(Level.SEVERE, null, e);
        }
        c.setTime(date);
        int hour = c.get(Calendar.HOUR_OF_DAY);
        c.set(Calendar.HOUR_OF_DAY, hour + 1);
//        String dayBefore = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(c
//                .getTime());
        String dayBefore = DateFormatUtils.format(c.getTime(), "yyyy-MM-dd HH:mm:ss");
        return dayBefore;
    }

    /**
     * 获得指定日期的后一天
     *
     * @param specifiedDay
     * @return
     */
    public static String getSpecifiedDayAfter(String specifiedDay) {
        Calendar c = Calendar.getInstance();
        Date date = null;
        try {
            //date = new SimpleDateFormat("yyyy-MM-dd").parse(specifiedDay);
            date = DateUtils.parseDate(specifiedDay, new String[]{"yyyy-MM-dd"});
        } catch (ParseException e) {
            Logger.getLogger(TimeUtil.class.getName()).log(Level.SEVERE, null, e);
        }
        c.setTime(date);
        int day = c.get(Calendar.DATE);
        c.set(Calendar.DATE, day + 1);
//        String dayAfter = new SimpleDateFormat("yyyy-MM-dd")
//                .format(c.getTime());
        String dayAfter = DateFormatUtils.format(c.getTime(), "yyyy-MM-dd");
        return dayAfter;
    }

    /**
     * 获得指定日期的前一天
     *
     * @param specifiedDay
     * @return
     */
    public static String getSpecifiedDayBefore(String specifiedDay) {
        Calendar c = Calendar.getInstance();
        Date date = null;
        try {
            if (specifiedDay.contains(":")) {
                specifiedDay = specifiedDay.substring(0, 10);
            }
            date = DateUtils.parseDate(specifiedDay, new String[]{"yyyy-MM-dd"});
        } catch (ParseException e) {
            Logger.getLogger(TimeUtil.class.getName()).log(Level.SEVERE, null, e);
        }
        c.setTime(date);
        int day = c.get(Calendar.DATE);
        c.set(Calendar.DATE, day - 1);
        String dayBefore = DateFormatUtils.format(c.getTime(), "yyyy-MM-dd");
        return dayBefore;
    }

    public static String getSpecifiedMonthBefore(String specifiedDay) {
        Calendar c = Calendar.getInstance();
        Date date = null;
        try {
            date = DateUtils.parseDate(specifiedDay, new String[]{"yyyy-MM-dd"});
        } catch (ParseException e) {
            Logger.getLogger(TimeUtil.class.getName()).log(Level.SEVERE, null, e);
        }
        c.setTime(date);
        int month = c.get(Calendar.MONTH);
        c.set(Calendar.MONTH, month - 1);
        String dayBefore = DateFormatUtils.format(c.getTime(), "yyyy-MM-dd");
        return dayBefore;
    }

    public static String getSpecifiedYearBefore(String specifiedDay) {
        Calendar c = Calendar.getInstance();
        Date date = null;
        try {
            date = DateUtils.parseDate(specifiedDay, new String[]{"yyyy-MM-dd"});
        } catch (ParseException e) {
            Logger.getLogger(TimeUtil.class.getName()).log(Level.SEVERE, null, e);
        }
        c.setTime(date);
        int year = c.get(Calendar.YEAR);
        c.set(Calendar.YEAR, year - 1);
        String dayBefore = DateFormatUtils.format(c.getTime(), "yyyy-MM-dd");
        return dayBefore;
    }

    /**
     * 比较两个日期是否相等
     *
     * @param d1 日期
     * @param d2 日期
     * @param pattern 给定的日期格式,如果为NULL则默认使用yyyy-MM-dd hh:mm:ss yyyy-MM-dd
     * hh:mm:ss
     * @return true 相等，false 不等
     */
    public static boolean compareDate(Date d1, Date d2, String pattern) {
        boolean result = false;
        if (d1 != null && d2 != null) {
            String date1 = convertDateToString(d1, pattern);
            String date2 = convertDateToString(d2, pattern);
            if (date1.equals(date2)) {
                result = true;
            }
        }
        return result;
    }

    /**
     * 根据给定时间格式，获取当前时间字符串
     *
     * @param pattern
     * @return
     */
    public static String getCurrentTimeString(String pattern) {
        String result = null;
        if (pattern == null || pattern.trim().length() == 0) {
            pattern = YMD;
        }
        Date now = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);//可以方便地修改日期格式
        result = dateFormat.format(now);
        return result;
    }
}

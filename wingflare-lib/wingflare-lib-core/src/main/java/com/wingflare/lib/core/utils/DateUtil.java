package com.wingflare.lib.core.utils;


import com.wingflare.lib.core.constants.DateFormat;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * @author naizui_ycx
 * @date {2021/12/14}
 * @description 事件日期工具
 */
public class DateUtil {

    private final static String[] monthShortName = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sept", "Oct", "Nov", "Dec"};

    /**
     * 根据默认格式将指定字符串解析成日期
     *
     * @param str
     *            指定字符串
     * @return 返回解析后的日期
     */
    public static Date parse(String str) {
        return parse(str, DateFormat.PATTERN_CLASSICAL);
    }

    /**
     * 根据指定格式将指定字符串解析成日期
     *
     * @param str
     *            指定日期
     * @param pattern
     *            指定格式
     * @return 返回解析后的日期
     */
    public static Date parse(String str, String pattern) {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        try {
            return sdf.parse(str);
        } catch (Exception ignored) {
        }
        return null;
    }

    /**
     * 根据默认格式将日期转格式化成字符串
     *
     * @param date
     *            指定日期
     * @return 返回格式化后的字符串
     */
    public static String format(Date date) {
        return format(date, DateFormat.PATTERN_CLASSICAL);
    }

    /**
     * 根据指定格式将指定日期格式化成字符串
     *
     * @param date
     *            指定日期
     * @param pattern
     *            指定格式
     * @return 返回格式化后的字符串
     */
    public static String format(Date date, String pattern) {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        return sdf.format(date);
    }

    /**
     * 根据指定格式将当前日期格式化成字符串
     *
     * @param pattern
     *            指定格式
     * @return 返回格式化后的字符串
     */
    public static String format(String pattern) {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        return sdf.format(new Date());
    }

    /**
     * 将当前日期格式化成字符串
     *
     * @return 返回格式化后的字符串
     */
    public static String format() {
        SimpleDateFormat sdf = new SimpleDateFormat(DateFormat.PATTERN_CLASSICAL);
        return sdf.format(new Date());
    }

    /**
     * 获取时间date1与date2相差的秒数
     *
     * @param date1
     *            起始时间
     * @param date2
     *            结束时间
     * @return 返回相差的秒数
     */
    public static int getOffsetSeconds(Date date1, Date date2) {
        return (int) ((date2.getTime() - date1.getTime()) / 1000);
    }

    /**
     * 获取时间date1与date2相差的分钟数
     *
     * @param date1
     *            起始时间
     * @param date2
     *            结束时间
     * @return 返回相差的分钟数
     */
    public static int getOffsetMinutes(Date date1, Date date2) {
        return getOffsetSeconds(date1, date2) / 60;
    }

    /**
     * 获取时间date1与date2相差的小时数
     *
     * @param date1
     *            起始时间
     * @param date2
     *            结束时间
     * @return 返回相差的小时数
     */
    public static int getOffsetHours(Date date1, Date date2) {
        return getOffsetMinutes(date1, date2) / 60;
    }

    /**
     * 获取时间date1与date2相差的天数数
     *
     * @param date1
     *            起始时间
     * @param date2
     *            结束时间
     * @return 返回相差的天数
     */
    public static int getOffsetDays(Date date1, Date date2) {
        return getOffsetHours(date1, date2) / 24;
    }

    /**
     * 获取时间date1与date2相差的周数
     *
     * @param date1
     *            起始时间
     * @param date2
     *            结束时间
     * @return 返回相差的周数
     */
    public static int getOffsetWeeks(Date date1, Date date2) {
        return getOffsetDays(date1, date2) / 7;
    }

    /**
     * 获取重置指定日期的时分秒后的时间
     *
     * @param date
     *            指定日期
     * @param hour
     *            指定小时
     * @param minute
     *            指定分钟
     * @param second
     *            指定秒
     * @return 返回重置时分秒后的时间
     */
    public static Date getResetTime(Date date, int hour, int minute, int second) {
        Calendar cal = Calendar.getInstance();
        if (date != null) {
            cal.setTime(date);
        }
        cal.set(Calendar.HOUR_OF_DAY, hour);
        cal.set(Calendar.SECOND, minute);
        cal.set(Calendar.MINUTE, second);
        return cal.getTime();
    }

    /**
     * 返回指定日期的起始时间
     *
     * @param date
     *            指定日期（例如2014-08-01）
     * @return 返回起始时间（例如2014-08-01 00:00:00）
     */
    public static Date getIntegralStartTime(Date date) {
        return getResetTime(date, 0, 0, 0);
    }

    /**
     * 返回指定日期的结束时间
     *
     * @param date
     *            指定日期（例如2014-08-01）
     * @return 返回结束时间（例如2014-08-01 23:59:59）
     */
    public static Date getIntegralEndTime(Date date) {
        return getResetTime(date, 23, 59, 59);
    }

    /**
     * 获取指定日期累加年月日后的时间
     *
     * @param date 指定日期
     * @param year 指定年数
     * @param month 指定月数
     * @param day 指定天数
     * @return 返回累加年月日后的时间
     */
    public static Date rollDate(Date date, int year, int month, int day, int hour, int minute, int second) {
        Calendar cal = Calendar.getInstance();
        if (date != null) {
            cal.setTime(date);
        }
        cal.add(Calendar.YEAR, year);
        cal.add(Calendar.MONTH, month);
        cal.add(Calendar.DATE, day);
        cal.add(Calendar.HOUR, hour);
        cal.add(Calendar.MINUTE, minute);
        cal.add(Calendar.SECOND, second);
        return cal.getTime();
    }

    /**
     * 获取指定日期累加指定单位时间后的时间
     */
    public static Date rollDate(Date date, int n, int field) {
        Calendar cal = Calendar.getInstance();

        if (date != null) {
            cal.setTime(date);
        }

        cal.add(field, n);

        return cal.getTime();
    }

    /**
     * 获取指定日期累加指定年数后的时间
     */
    public static Date rollYear(Date date, int year) {
        return rollDate(date, year, 0, 0, 0, 0, 0);
    }

    /**
     * 获取指定日期累加指定月数后的时间
     */
    public static Date rollMonth(Date date, int month) {
        return rollDate(date, 0, month, 0, 0, 0, 0);
    }

    /**
     * 获取指定日期累加指定天数后的时间
     */
    public static Date rollDay(Date date, int day) {
        return rollDate(date, 0, 0, day, 0, 0, 0);
    }

    /**
     * 获取指定日期累加指定小时后的时间
     */
    public static Date rollHour(Date date, int hour) {
        return rollDate(date, 0, 0, 0, hour, 0, 0);
    }

    /**
     * 获取指定日期累加指定分钟数后的时间
     */
    public static Date rollMinute(Date date, int minute) {
        return rollDate(date, 0, 0, 0, 0, minute, 0);
    }

    /**
     * 获取指定日期累加指定秒数后的时间
     */
    public static Date rollSecond(Date date, int second) {
        return rollDate(date, 0, 0, 0, 0, 0, second);
    }

    /**
     * 计算指定日期所在月份的天数
     *
     * @param date
     *            指定日期
     * @return 返回所在月份的天数
     */
    public static int getDayOfMonth(Date date) {
        Calendar cal = Calendar.getInstance();
        if (date != null) {
            cal.setTime(date);
        }
        return cal.getActualMaximum(Calendar.DATE);
    }

    /**
     * 获取当月第一天的起始时间，例如2014-08-01 00:00:00
     *
     * @return 返回当月第一天的起始时间
     */
    public static Date getMonthStartTime() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.DAY_OF_MONTH, 1);
        return getIntegralStartTime(cal.getTime());
    }

    /**
     * 获取当月最后一天的结束时间，例如2014-08-31 23:59:59
     *
     * @return 返回当月最后一天的结束时间
     */
    public static Date getMonthEndTime() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.DAY_OF_MONTH, getDayOfMonth(cal.getTime()));
        return getIntegralEndTime(cal.getTime());
    }

    /**
     * 获取上个月第一天的起始时间，例如2014-07-01 00:00:00
     *
     * @return 返回上个月第一天的起始时间
     */
    public static Date getLastMonthStartTime() {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MONTH, -1);
        cal.set(Calendar.DAY_OF_MONTH, 1);
        return getIntegralStartTime(cal.getTime());
    }

    /**
     * 获取上个月最后一天的结束时间，例如2014-07-31 23:59:59
     *
     * @return 返回上个月最后一天的结束时间
     */
    public static Date getLastMonthEndTime() {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MONTH, -1);
        cal.set(Calendar.DAY_OF_MONTH, getDayOfMonth(cal.getTime()));
        return getIntegralEndTime(cal.getTime());
    }

    /**
     * 获取下个月第一天的起始时间，例如2014-09-01 00:00:00
     *
     * @return 返回下个月第一天的起始时间
     */
    public static Date getNextMonthStartTime() {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MONTH, 1);
        cal.set(Calendar.DAY_OF_MONTH, 1);
        return getIntegralStartTime(cal.getTime());
    }

    /**
     * 获取下个月最后一天的结束时间，例如2014-09-30 23:59:59
     *
     * @return 返回下个月最后一天的结束时间
     */
    public static Date getNextMonthEndTime() {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MONTH, 1);
        cal.set(Calendar.DAY_OF_MONTH, getDayOfMonth(cal.getTime()));
        return getIntegralEndTime(cal.getTime());
    }

    /**
     * 获取当前季度第一天的起始时间
     *
     * @return 返回当前季度第一天的起始时间
     */
    public static Date getQuarterStartTime() {
        Calendar cal = Calendar.getInstance();
        int month = cal.get(Calendar.MONTH);
        if (month < 3) {
            cal.set(Calendar.MONTH, 0);
        } else if (month < 6) {
            cal.set(Calendar.MONTH, 3);
        } else if (month < 9) {
            cal.set(Calendar.MONTH, 6);
        } else {
            cal.set(Calendar.MONTH, 9);
        }
        cal.set(Calendar.DAY_OF_MONTH, 1);
        return getIntegralStartTime(cal.getTime());
    }

    /**
     * 获取当前季度最后一天的结束时间
     *
     * @return 返回当前季度最后一天的结束时间
     */
    public static Date getQuarterEndTime() {
        Calendar cal = Calendar.getInstance();
        int month = cal.get(Calendar.MONTH);
        if (month < 3) {
            cal.set(Calendar.MONTH, 2);
        } else if (month < 6) {
            cal.set(Calendar.MONTH, 5);
        } else if (month < 9) {
            cal.set(Calendar.MONTH, 8);
        } else {
            cal.set(Calendar.MONTH, 11);
        }
        cal.set(Calendar.DAY_OF_MONTH, getDayOfMonth(cal.getTime()));
        return getIntegralEndTime(cal.getTime());
    }

    /**
     * 获取前一个工作日
     *
     * @return 返回前一个工作日
     */
    public static Date getPrevWorkday() {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DAY_OF_MONTH, -1);
        if (cal.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
            cal.add(Calendar.DAY_OF_MONTH, -2);
        }
        if (cal.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY) {
            cal.add(Calendar.DAY_OF_MONTH, -1);
        }
        return getIntegralStartTime(cal.getTime());
    }

    /**
     * 获取下一个工作日
     *
     * @return 返回下个工作日
     */
    public static Date getNextWorkday() {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DAY_OF_MONTH, 1);
        if (cal.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY) {
            cal.add(Calendar.DAY_OF_MONTH, 2);
        }
        if (cal.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
            cal.add(Calendar.DAY_OF_MONTH, 1);
        }
        return getIntegralStartTime(cal.getTime());
    }

    /**
     * 获取当周的第一个工作日
     *
     * @return 返回第一个工作日
     */
    public static Date getFirstWorkday() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        return getIntegralStartTime(cal.getTime());
    }

    /**
     * 获取当周的最后一个工作日
     *
     * @return 返回最后一个工作日
     */
    public static Date getLastWorkday() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.DAY_OF_WEEK, Calendar.FRIDAY);
        return getIntegralStartTime(cal.getTime());
    }

    /**
     * 判断指定日期是否是工作日
     *
     * @param date
     *            指定日期
     * @return 如果是工作日返回true，否则返回false
     */
    public static boolean isWorkday(Date date) {
        Calendar cal = Calendar.getInstance();
        if (date != null) {
            cal.setTime(date);
        }
        int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
        return !(dayOfWeek == Calendar.SATURDAY || dayOfWeek == Calendar.SUNDAY);
    }

    /**
     * 获取指定日期是星期几
     *
     * @param date
     *            指定日期
     * @return 返回星期几的描述
     */
    public static String getWeekdayDesc(Date date) {
        final String[] weeks = new String[]{"星期日", "星期一", "星期二", "星期三", "星期四",
                "星期五", "星期六"};
        Calendar cal = Calendar.getInstance();
        if (date != null) {
            cal.setTime(date);
        }
        return weeks[cal.get(Calendar.DAY_OF_WEEK) - 1];
    }

    /**
     * 获取指定日期距离当前时间的时间差描述（如3小时前、1天前）
     *
     * @param date
     *            指定日期
     * @return 返回时间差的描述
     */
    public static String getTimeOffsetDesc(Date date) {
        int seconds = getOffsetSeconds(date, new Date());
        if (Math.abs(seconds) < 60) {
            return Math.abs(seconds) + "秒" + (seconds > 0 ? "前" : "后");
        }
        int minutes = seconds / 60;
        if (Math.abs(minutes) < 60) {
            return Math.abs(minutes) + "分钟" + (minutes > 0 ? "前" : "后");
        }
        int hours = minutes / 60;
        if (Math.abs(hours) < 60) {
            return Math.abs(hours) + "小时" + (hours > 0 ? "前" : "后");
        }
        int days = hours / 24;
        if (Math.abs(days) < 7) {
            return Math.abs(days) + "天" + (days > 0 ? "前" : "后");
        }
        int weeks = days / 7;
        if (Math.abs(weeks) < 5) {
            return Math.abs(weeks) + "周" + (weeks > 0 ? "前" : "后");
        }
        int monthes = days / 30;
        if (Math.abs(monthes) < 12) {
            return Math.abs(monthes) + "个月" + (monthes > 0 ? "前" : "后");
        }
        int years = monthes / 12;
        return Math.abs(years) + "年" + (years > 0 ? "前" : "后");
    }

    /**
     * 获取当前时间的默认BetweenTime
     *
     * @return String 2022-05-13 00:00,2022-05-13 23:59:59.999999999
     * @author lixuan
     * @date 2022/4/26 15:36
     **/
    public static String getNowBetweenTime() {
        LocalDate date = LocalDate.now();
        return date + " " + LocalTime.MIN + "," + date + " " + LocalTime.MAX;
    }

    /**
     * 获取当月的默认时间
     * @return String 2022-05-01 00:00,2022-05-31 23:59:59.999999999
     * @author lixuan
     * @date 2022/5/13 17:29
     **/
    public static String getMonthBetweenTime() {
        LocalDate date = LocalDate.now();
        LocalDate firstDay = date.with(TemporalAdjusters.firstDayOfMonth());
        LocalDate lastDay = date.with(TemporalAdjusters.lastDayOfMonth());
        return firstDay + " " + LocalTime.MIN + "," + lastDay + " " + LocalTime.MAX;
    }

    /**
     * 获取当前时间往后一周的默认 BetweenTime
     * @return String
     * @author lixuan
     * @date 2022/5/13 17:35
     **/
    public static String getWeekBetweenTime(){
        return LocalDate.now() + " " + LocalTime.MIN + "," + LocalDate.now().plusDays(7) + " " + LocalTime.MAX;
    }



    /**
     * Date 转 LocalDate
     *
     * @param date date
     * @return LocalDate
     * @author lixuan
     * @date 2022/4/27 11:05
     **/
    public static LocalDate dateConvertLocalDate(Date date) {
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }

    /**
     * Date 转 LocalDateTime
     *
     * @param date date
     * @return LocalDateTime
     * @author lixuan
     * @date 2022/4/27 11:06
     **/
    public static LocalDateTime dateConvertLocalDateTime(Date date) {
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
    }

    /**
     * LocalDate 转 Date
     *
     * @param localDate localDate
     * @return Date
     * @author lixuan
     * @date 2022/4/27 11:07
     **/
    public static Date localDateConvertDate(LocalDate localDate) {
        return Date.from(localDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
    }

    /**
     * LocalDateTime 转Date
     *
     * @param localDateTime localDateTime
     * @return Date
     * @author lixuan
     * @date 2022/4/27 11:09
     **/
    public static Date localDateTimeConvertDate(LocalDateTime localDateTime) {
        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }

    /**
     * 获取指定天的开始时间
     * @return
     */
    public static Date getStartTimeOfCurrentDay(Date date){
        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.setTime(date);
        setMinTimeOfDay(calendar);
        return calendar.getTime();
    }

    /**
     * 获取指定天的结束时间
     * @return
     */
    public static Date getEndTimeOfCurrentDay(Date date){
        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.setTime(date);
        setMaxTimeOfDay(calendar);
        return calendar.getTime();
    }

    /**
     * 获取指定周的开始时间
     * @return
     */
    public static Date getStartTimeOfCurrentWeek(Date date){
        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.setTime(date);
        calendar.setFirstDayOfWeek(Calendar.MONDAY);
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        setMinTimeOfDay(calendar);
        return calendar.getTime();
    }

    /**
     * 获取指定月的结束时间
     * @return
     */
    public static Date getEndTimeOfCurrentWeek(Date date){
        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.setTime(date);
        calendar.setFirstDayOfWeek(Calendar.MONDAY);
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
        setMaxTimeOfDay(calendar);
        return calendar.getTime();
    }

    /**
     * 获取指定月的开始时间
     * @return
     */
    public static Date getStartTimeOfCurrentMonth(Date date){
        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.setTime(date);
        calendar.set(calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),1);
        setMinTimeOfDay(calendar);
        return calendar.getTime();
    }

    /**
     * 获取指定月的结束时间
     * @return
     */
    public static Date getEndTimeOfCurrentMonth(Date date){
        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.setTime(date);
        int maxMonthDay = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        calendar.set(calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),maxMonthDay);
        setMaxTimeOfDay(calendar);
        return calendar.getTime();
    }

    /**
     * 获取指定年的开始时间 注意月份要减1
     * @return
     */
    public static Date getStartTimeOfCurrentYear(Date date){
        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.setTime(date);
        calendar.set(calendar.get(Calendar.YEAR),0,1);
        setMinTimeOfDay(calendar);
        return calendar.getTime();
    }

    /**
     * 获取指定年的结束时间 注意月份要减1
     * @return
     */
    public static Date getEndTimeOfCurrentYear(Date date){
        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.setTime(date);
        calendar.set(calendar.get(Calendar.YEAR),11,31);
        setMaxTimeOfDay(calendar);
        return calendar.getTime();
    }

    /**
     * 设置当天的开始时间
     * @param calendar
     */
    private static void setMinTimeOfDay(Calendar calendar) {
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.MILLISECOND, 0);
    }

    /**
     * 设置当天的结束时间
     * @param calendar
     */
    private static void setMaxTimeOfDay(Calendar calendar) {
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.MILLISECOND, 999);
    }

    /**
     * 判断时间是否在区间内
     *
     * @param nowTime
     * @param startTime
     * @param endTime
     * @return
     */
    public static boolean isDateRange(Date nowTime, Date startTime, Date endTime) {
        if (nowTime.getTime() >= startTime.getTime()
                && nowTime.getTime() <= endTime.getTime()) {
            return true;
        }

        return false;
    }

    /**
     * 获取指定日期上个月的日期
     *
     * @param yearMonth 指定日期（格式：yyyyMM）
     * @return {@link String} 指定日期上个月的日期（格式：yyyyMM）
     * @author shaoyuyao
     * @date 2022/7/30 17:46
     */
    public static String getLastMonthYearMonth(String yearMonth) {
        // 判断是否跨年
        if ("01".equals(yearMonth.substring(4))) {
            return (Integer.parseInt(yearMonth.substring(0, 4)) - 1) + "12";
        } else {
            return String.valueOf(Integer.parseInt(yearMonth) - 1);
        }
    }

    /**
     * 获取指定日期上几个月的日期列表
     *
     * @param yearMonth    指定日期（格式：yyyyMM）
     * @param beforeNumber 获取数量
     * @param isContain    是否包含指定日期
     * @return {@link List< String>} 日期列表（格式：yyyyMM）
     * @author shaoyuyao
     * @date 2022/7/30 17:46
     */
    public static List<String> getBeforeYearMonthList(String yearMonth, int beforeNumber, boolean isContain) {
        if (StringUtil.isEmpty(yearMonth) || yearMonth.length() != 6) {
            yearMonth = format(new Date(), "yyyyMM");
        }

        List<String> beforeYearMonthList;
        if (isContain) {
            beforeYearMonthList = new ArrayList<>(beforeNumber++);
            beforeYearMonthList.add(yearMonth);
        } else {
            beforeYearMonthList = new ArrayList<>(beforeNumber);
        }

        for (int i = 1; i < beforeNumber; i++) {
            yearMonth = getLastMonthYearMonth(yearMonth);
            beforeYearMonthList.add(yearMonth);
        }

        // 列表元素顺序反转
        Collections.reverse(beforeYearMonthList);

        return beforeYearMonthList;
    }
    /**
     * 获取当前时间与月末的秒差
     *
     * @return long 秒差
     */
    public static long getLastDaySeconds() {
        LocalDateTime localDateTime = LocalDateTime.now().plusMonths(1).withDayOfMonth(1).withHour(0).withMinute(0).withSecond(0).withNano(0);
        return ChronoUnit.SECONDS.between(LocalDateTime.now(), localDateTime);
    }

    /**
     * 根据传入的时间获取简称
     *
     * @param date 时间
     * @return String
     */
    public static String getMonthShortName(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return monthShortName[cal.get(Calendar.MONTH)];
    }

    /**
     * 将两个Date类型的值进行比较，且只对年月日进行比较
     */
    public static int compareDate(Date date1, Date date2) {
        int value1 = Integer.parseInt(format(date1, DateFormat.yyMMdd));
        int value2 = Integer.parseInt(format(date2, DateFormat.yyMMdd));
        if (value1 > value2) {
            return 1;
        } else if (value1 < value2) {
            return -1;
        }
        return 0;
    }

    /**
     * 获取指定日期月份的最后一天，例如：2022-09-26 17:11:00 -> 2022-09-30 17:11:00
     *
     * @param date 指定日期
     */
    public static Date getLastDayOfMonth(Date date) {
        return getLastDay(date, Calendar.DAY_OF_MONTH);
    }

    /**
     * 获取指定日期星期的最后一天，例如：2022-09-22 17:11:00 -> 2022-09-25 17:11:00
     *
     * @param date 指定日期
     */
    public static Date getLastDayOfWeek(Date date) {
        return getLastDay(date, Calendar.DAY_OF_WEEK);
    }

    /**
     * 获取指定日期指定单位的最后一天
     *
     * @param date 指定日期
     * @param unit 指定单位
     * @return
     */
    public static Date getLastDay(Date date, int unit) {
        if (date == null) {
            return null;
        }

        Calendar calendar = dateToCalendar(date);
        calendar.set(unit, calendar.getActualMaximum(unit));

        return calendar.getTime();
    }

    /**
     * 对指定日期进行月份计算
     *
     * @param date 指定日期
     * @param amount 为正数时进行累加，负数时进行累减
     */
    public static Date addMonth(Date date, int amount) {
        return add(date, amount, Calendar.MONTH);
    }

    /**
     * 对指定日期进行天数计算
     *
     * @param date 指定日期
     * @param amount 为正数时进行累加，负数时进行累减
     */
    public static Date addDay(Date date, int amount) {
        return add(date, amount, Calendar.DAY_OF_MONTH);
    }

    /**
     * 对指定日期进行指定单位计算
     *
     * @param date 指定日期
     * @param amount 为正数时进行累加，负数时进行累减
     * @param unit 日期单位
     */
    public static Date add(Date date, int amount, int unit) {
        if (date == null || amount == 0) {
            return date;
        }

        Calendar calendar = dateToCalendar(date);
        calendar.add(unit, amount);
        return calendar.getTime();
    }

    /**
     * Date转Calendar
     */
    public static Calendar dateToCalendar(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar;
    }

    /**
     * 获取日期中的天，例如：2022-09-22 17:11:00 -> 22
     */
    public static int getDay(Date date) {
        Calendar calendar = dateToCalendar(date);
        return calendar.get(Calendar.DAY_OF_MONTH);
    }

    /**
     * 修改指定日期的月份
     *
     * @param date  指定日期
     * @param value 月份，1-12
     * @return
     */
    public static Date setMonth(Date date, int value) {
        return set(date, value, Calendar.MONDAY);
    }

    /**
     * 修改指定日期的天
     *
     * @param date  指定日期
     * @param value 天
     * @return
     */
    public static Date setDay(Date date, int value) {
        return set(date, value, Calendar.DAY_OF_MONTH);
    }

    /**
     * 修改指定日期的指定单位
     *
     * @param date  指定日期
     * @param value 月份，1-12
     * @param unit  日期单位
     * @return
     */
    public static Date set(Date date, int value, int unit) {
        Calendar calendar = dateToCalendar(date);
        if (unit == Calendar.MONDAY) {
            value++;
        }
        calendar.set(unit, value);
        return calendar.getTime();
    }

    /**
     * 将指定日期的时分秒设置为0
     */
    public static Date timeSetZero(Date date) {
        Calendar calendar = dateToCalendar(date);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

}

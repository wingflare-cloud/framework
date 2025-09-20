package com.wingflare.lib.scheduler;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Set;
import java.util.TreeSet;

/**
 * Cron表达式支持秒级精度的解析和计算
 * 格式: 秒 分 时 日 月 周 [年]
 * 支持特殊字符: * ? - , / L W #
 */
public class CronExpression {
    
    // Cron字段索引
    private static final int SECOND = 0;
    private static final int MINUTE = 1;
    private static final int HOUR = 2;
    private static final int DAY_OF_MONTH = 3;
    private static final int MONTH = 4;
    private static final int DAY_OF_WEEK = 5;
    private static final int YEAR = 6;
    
    // 字段范围
    private static final int[] MIN_VALUES = {0, 0, 0, 1, 1, 0, 1970};
    private static final int[] MAX_VALUES = {59, 59, 23, 31, 12, 6, 2099};
    
    // 月份天数
    private static final int[] DAYS_IN_MONTH = {31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
    
    private final String cronExpression;
    private final String[] fields;
    private final Set<Integer>[] fieldSets;
    
    /**
     * 创建Cron表达式
     * @param cronExpression Cron表达式字符串
     */
    @SuppressWarnings("unchecked")
    public CronExpression(String cronExpression) {
        if (cronExpression == null || cronExpression.trim().isEmpty()) {
            throw new IllegalArgumentException("Cron表达式不能为空");
        }
        
        this.cronExpression = cronExpression.trim();
        String[] tempFields = this.cronExpression.split("\\s+");
        
        if (tempFields.length < 6 || tempFields.length > 7) {
            throw new IllegalArgumentException("Cron表达式格式错误，应为: 秒 分 时 日 月 周 [年]");
        }
        
        // 如果没有年份字段，添加默认的 "*"
        if (tempFields.length == 6) {
            this.fields = Arrays.copyOf(tempFields, 7);
            this.fields[6] = "*";
        } else {
            this.fields = tempFields;
        }
        
        this.fieldSets = new Set[7];
        parseFields();
    }
    
    /**
     * 解析所有字段
     */
    private void parseFields() {
        for (int i = 0; i < 7; i++) {
            fieldSets[i] = parseField(fields[i], i);
        }
        
        // 验证日和周字段不能同时指定
        if (!fields[DAY_OF_MONTH].equals("?") && !fields[DAY_OF_WEEK].equals("?")) {
            throw new IllegalArgumentException("日期和星期字段不能同时指定，其中一个必须为 '?'");
        }
    }
    
    /**
     * 解析单个字段
     */
    private Set<Integer> parseField(String field, int fieldIndex) {
        Set<Integer> values = new TreeSet<>();
        
        if ("*".equals(field) || "?".equals(field)) {
            // 添加所有可能的值
            for (int i = MIN_VALUES[fieldIndex]; i <= MAX_VALUES[fieldIndex]; i++) {
                values.add(i);
            }
            return values;
        }
        
        // 处理逗号分隔的多个值
        String[] parts = field.split(",");
        for (String part : parts) {
            parseFieldPart(part.trim(), fieldIndex, values);
        }
        
        return values;
    }
    
    /**
     * 解析字段的单个部分
     */
    private void parseFieldPart(String part, int fieldIndex, Set<Integer> values) {
        if (part.contains("/")) {
            // 处理步长表达式 如 0/5
            parseStepExpression(part, fieldIndex, values);
        } else if (part.contains("-")) {
            // 处理范围表达式 如 1-5
            parseRangeExpression(part, fieldIndex, values);
        } else {
            // 处理单个值
            int value = parseValue(part, fieldIndex);
            values.add(value);
        }
    }
    
    /**
     * 解析步长表达式
     */
    private void parseStepExpression(String part, int fieldIndex, Set<Integer> values) {
        String[] stepParts = part.split("/");
        if (stepParts.length != 2) {
            throw new IllegalArgumentException("步长表达式格式错误: " + part);
        }
        
        int start, end, step;
        
        if ("*".equals(stepParts[0])) {
            start = MIN_VALUES[fieldIndex];
            end = MAX_VALUES[fieldIndex];
        } else if (stepParts[0].contains("-")) {
            String[] rangeParts = stepParts[0].split("-");
            start = parseValue(rangeParts[0], fieldIndex);
            end = parseValue(rangeParts[1], fieldIndex);
        } else {
            start = parseValue(stepParts[0], fieldIndex);
            end = MAX_VALUES[fieldIndex];
        }
        
        step = Integer.parseInt(stepParts[1]);
        
        for (int i = start; i <= end; i += step) {
            values.add(i);
        }
    }
    
    /**
     * 解析范围表达式
     */
    private void parseRangeExpression(String part, int fieldIndex, Set<Integer> values) {
        String[] rangeParts = part.split("-");
        if (rangeParts.length != 2) {
            throw new IllegalArgumentException("范围表达式格式错误: " + part);
        }
        
        int start = parseValue(rangeParts[0], fieldIndex);
        int end = parseValue(rangeParts[1], fieldIndex);
        
        for (int i = start; i <= end; i++) {
            values.add(i);
        }
    }
    
    /**
     * 解析单个数值
     */
    private int parseValue(String value, int fieldIndex) {
        try {
            int intValue = Integer.parseInt(value);
            if (intValue < MIN_VALUES[fieldIndex] || intValue > MAX_VALUES[fieldIndex]) {
                throw new IllegalArgumentException(
                    String.format("字段值 %d 超出范围 [%d-%d]", 
                                  intValue, MIN_VALUES[fieldIndex], MAX_VALUES[fieldIndex]));
            }
            return intValue;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("无效的数值: " + value);
        }
    }
    
    /**
     * 计算下一次执行时间
     */
    public LocalDateTime getNextExecutionTime(LocalDateTime fromTime) {
        LocalDateTime nextTime = fromTime.plusSeconds(1).withNano(0);
        
        // 最大尝试次数，避免无限循环
        int maxIterations = 366 * 24 * 60 * 60; // 一年的秒数
        int iterations = 0;
        
        while (iterations < maxIterations) {
            if (matches(nextTime)) {
                return nextTime;
            }
            
            nextTime = incrementTime(nextTime);
            iterations++;
        }
        
        throw new IllegalStateException("无法找到下一个有效的执行时间");
    }
    
    /**
     * 检查时间是否匹配Cron表达式
     */
    private boolean matches(LocalDateTime dateTime) {
        return fieldSets[SECOND].contains(dateTime.getSecond()) &&
               fieldSets[MINUTE].contains(dateTime.getMinute()) &&
               fieldSets[HOUR].contains(dateTime.getHour()) &&
               matchesDayAndWeek(dateTime) &&
               fieldSets[MONTH].contains(dateTime.getMonthValue()) &&
               fieldSets[YEAR].contains(dateTime.getYear());
    }
    
    /**
     * 检查日期和星期是否匹配
     */
    private boolean matchesDayAndWeek(LocalDateTime dateTime) {
        boolean dayMatches = fields[DAY_OF_MONTH].equals("?") || 
                           fieldSets[DAY_OF_MONTH].contains(dateTime.getDayOfMonth());
        
        boolean weekMatches = fields[DAY_OF_WEEK].equals("?") || 
                            fieldSets[DAY_OF_WEEK].contains(dateTime.getDayOfWeek().getValue() % 7);
        
        // 如果日字段是?，则只检查星期；如果星期字段是?，则只检查日
        if (fields[DAY_OF_MONTH].equals("?")) {
            return weekMatches;
        } else if (fields[DAY_OF_WEEK].equals("?")) {
            return dayMatches;
        } else {
            // 理论上不应该到达这里，因为在解析时已经验证
            return dayMatches || weekMatches;
        }
    }
    
    /**
     * 智能递增时间
     */
    private LocalDateTime incrementTime(LocalDateTime dateTime) {
        // 首先尝试递增秒
        if (fieldSets[SECOND].contains(dateTime.getSecond())) {
            // 当前秒匹配，尝试下一秒
            return dateTime.plusSeconds(1);
        }
        
        // 寻找下一个匹配的秒
        Integer nextSecond = findNext(fieldSets[SECOND], dateTime.getSecond());
        if (nextSecond != null) {
            return dateTime.withSecond(nextSecond);
        }
        
        // 没有更大的秒，递增分钟
        return incrementMinute(dateTime.withSecond(fieldSets[SECOND].iterator().next()));
    }
    
    /**
     * 递增分钟
     */
    private LocalDateTime incrementMinute(LocalDateTime dateTime) {
        Integer nextMinute = findNext(fieldSets[MINUTE], dateTime.getMinute());
        if (nextMinute != null) {
            return dateTime.withMinute(nextMinute);
        }
        
        // 递增小时
        return incrementHour(dateTime.withMinute(fieldSets[MINUTE].iterator().next()));
    }
    
    /**
     * 递增小时
     */
    private LocalDateTime incrementHour(LocalDateTime dateTime) {
        Integer nextHour = findNext(fieldSets[HOUR], dateTime.getHour());
        if (nextHour != null) {
            return dateTime.withHour(nextHour);
        }
        
        // 递增日
        return incrementDay(dateTime.withHour(fieldSets[HOUR].iterator().next()));
    }
    
    /**
     * 递增日
     */
    private LocalDateTime incrementDay(LocalDateTime dateTime) {
        LocalDateTime nextDay = dateTime.plusDays(1);
        
        // 重置为当天的最小时间
        nextDay = nextDay.withHour(fieldSets[HOUR].iterator().next())
                        .withMinute(fieldSets[MINUTE].iterator().next())
                        .withSecond(fieldSets[SECOND].iterator().next());
        
        return nextDay;
    }
    
    /**
     * 在集合中找到大于当前值的下一个值
     */
    private Integer findNext(Set<Integer> values, int current) {
        for (Integer value : values) {
            if (value > current) {
                return value;
            }
        }
        return null;
    }
    
    /**
     * 获取月份的天数
     */
    private int getDaysInMonth(int year, int month) {
        if (month == 2) {
            return isLeapYear(year) ? 29 : 28;
        }
        return DAYS_IN_MONTH[month - 1];
    }
    
    /**
     * 判断是否为闰年
     */
    private boolean isLeapYear(int year) {
        return (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);
    }
    
    /**
     * 检查表达式是否有效
     */
    public boolean isValid() {
        try {
            // 尝试获取下一次执行时间来验证表达式
            getNextExecutionTime(LocalDateTime.now());
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
    @Override
    public String toString() {
        return cronExpression;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        CronExpression that = (CronExpression) obj;
        return cronExpression.equals(that.cronExpression);
    }
    
    @Override
    public int hashCode() {
        return cronExpression.hashCode();
    }
}
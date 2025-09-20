package com.wingflare.lib.scheduler;


import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Cron表达式解析器，支持基本的cron语法并添加缓存
 */
public class CronExpressionParser {

    // 标准Cron模式：秒 分 时 日 月 周（周字段可选）
    private static final Pattern CRON_PATTERN = Pattern.compile(
            "^(\\S+)\\s+(\\S+)\\s+(\\S+)\\s+(\\S+)\\s+(\\S+)(?:\\s+(\\S+))?$");

    // 字段最小值与最大值映射（用于边界校验和步长计算）
    private static final Map<Integer, Integer> FIELD_MIN_VALUES;
    private static final Map<Integer, Integer> FIELD_MAX_VALUES;

    static {
        FIELD_MIN_VALUES = new HashMap<>();
        FIELD_MAX_VALUES = new HashMap<>();

        // 初始化日历字段的取值范围
        FIELD_MIN_VALUES.put(Calendar.SECOND, 0);
        FIELD_MIN_VALUES.put(Calendar.MINUTE, 0);
        FIELD_MIN_VALUES.put(Calendar.HOUR_OF_DAY, 0);
        FIELD_MIN_VALUES.put(Calendar.DAY_OF_MONTH, 1);
        FIELD_MIN_VALUES.put(Calendar.MONTH, 0);
        FIELD_MIN_VALUES.put(Calendar.DAY_OF_WEEK, 1);

        FIELD_MAX_VALUES.put(Calendar.SECOND, 59);
        FIELD_MAX_VALUES.put(Calendar.MINUTE, 59);
        FIELD_MAX_VALUES.put(Calendar.HOUR_OF_DAY, 23);
        FIELD_MAX_VALUES.put(Calendar.DAY_OF_MONTH, 31);
        FIELD_MAX_VALUES.put(Calendar.MONTH, 11);
        FIELD_MAX_VALUES.put(Calendar.DAY_OF_WEEK, 7);
    }

    // Cron表达式缓存（避免重复解析）
    private static final SimpleCache<String, String> CRON_CACHE = new SimpleCache<>(100, 3600000);


    /**
     * 验证Cron表达式格式是否合法
     */
    public static boolean isValidExpression(String cronExpression) {
        if (cronExpression == null || cronExpression.trim().isEmpty()) {
            return false;
        }
        return CRON_PATTERN.matcher(cronExpression.trim()).matches();
    }


    /**
     * 计算下一次执行时间（核心修复：基于上一次执行时间计算，避免漂移）
     * @param cronExpression Cron表达式
     * @param lastExecutionTime 上一次执行时间（毫秒）
     * @return 下一次执行时间（毫秒）
     */
    public static long nextExecutionTime(String cronExpression, long lastExecutionTime) {
        if (!isValidExpression(cronExpression)) {
            throw new IllegalArgumentException("Invalid cron expression: " + cronExpression);
        }

        // 缓存校验（保留原逻辑）
        String cached = CRON_CACHE.get(cronExpression);
        if (cached == null) {
            CRON_CACHE.put(cronExpression, cronExpression);
        }

        Matcher matcher = CRON_PATTERN.matcher(cronExpression);
        matcher.matches();

        // 解析Cron字段：秒 分 时 日 月 周
        String secondsPart = matcher.group(1);
        String minutesPart = matcher.group(2);
        String hoursPart = matcher.group(3);
        String dayOfMonthPart = matcher.group(4);
        String monthPart = matcher.group(5);
        String dayOfWeekPart = matcher.group(6);

        // 初始化日历：基于上一次执行时间，清除毫秒数（关键修复）
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(lastExecutionTime);
        calendar.set(Calendar.MILLISECOND, 0); // 清除毫秒，避免匹配偏差
        calendar.add(Calendar.SECOND, 1); // 从下一秒开始计算，避免重复执行

        // 最大检查时间：1年内（防止无限循环）
        long maxCheckTime = calendar.getTimeInMillis() + 365L * 24 * 60 * 60 * 1000;

        // 时间推进逻辑（按秒递增，确保不跳过匹配点）
        while (calendar.getTimeInMillis() <= maxCheckTime) {
            // 按月份→日期→小时→分钟→秒的顺序匹配（大单位优先）
            if (!matchesField(calendar, monthPart, Calendar.MONTH)) {
                incrementMonth(calendar);
                continue;
            }

            if (!matchesField(calendar, dayOfMonthPart, Calendar.DAY_OF_MONTH) ||
                    !matchesDayOfWeek(calendar, dayOfWeekPart)) {
                incrementDay(calendar);
                continue;
            }

            if (!matchesField(calendar, hoursPart, Calendar.HOUR_OF_DAY)) {
                incrementHour(calendar);
                continue;
            }

            if (!matchesField(calendar, minutesPart, Calendar.MINUTE)) {
                incrementMinute(calendar);
                continue;
            }

            // 最后匹配秒字段（确保步长准确）
            if (matchesField(calendar, secondsPart, Calendar.SECOND)) {
                return calendar.getTimeInMillis();
            }

            // 按秒递增（核心修复：避免跳过秒数）
            calendar.add(Calendar.SECOND, 1);
        }

        throw new IllegalArgumentException("No valid execution time for cron: " + cronExpression);
    }


    /**
     * 匹配单个字段（修复步长计算逻辑）
     */
    private static boolean matchesField(Calendar calendar, String cronPart, int field) {
        // 处理通配符（*）和不指定（?，仅用于日/周）
        if ("*".equals(cronPart) || "?".equals(cronPart)) {
            return true;
        }

        // 处理列表（,）
        if (cronPart.contains(",")) {
            String[] parts = cronPart.split(",");
            for (String part : parts) {
                if (matchesSinglePart(calendar, part.trim(), field)) {
                    return true;
                }
            }
            return false;
        }

        // 处理单个部分（范围/步长/具体值）
        return matchesSinglePart(calendar, cronPart, field);
    }


    /**
     * 匹配单个部分（修复步长计算）
     */
    private static boolean matchesSinglePart(Calendar calendar, String part, int field) {
        int currentValue = calendar.get(field);
        int minValue = FIELD_MIN_VALUES.get(field);

        // 处理范围（-）
        if (part.contains("-")) {
            String[] range = part.split("-");
            if (range.length != 2) {
                return false;
            }
            int start = parseFieldValue(range[0], field);
            int end = parseFieldValue(range[1], field);
            return currentValue >= start && currentValue <= end;
        }

        // 处理步长（/）（核心修复：基于最小值计算步长）
        if (part.contains("/")) {
            String[] stepParts = part.split("/");
            if (stepParts.length != 2) {
                return false;
            }

            int step = Integer.parseInt(stepParts[1]);
            if (step <= 0) {
                return false;
            }

            // 步长起始：* 表示从最小值开始
            int start = "*".equals(stepParts[0]) ? minValue : parseFieldValue(stepParts[0], field);

            // 确保当前值在起始值之后，且符合步长间隔
            return currentValue >= start && (currentValue - start) % step == 0;
        }

        // 处理具体值（数字/月份名称/星期名称）
        int targetValue = parseFieldValue(part, field);
        return currentValue == targetValue;
    }


    /**
     * 解析字段值（修复月份/星期的数值映射）
     */
    private static int parseFieldValue(String value, int field) {
        // 处理月份名称（JAN-DEC → 0-11）
        if (field == Calendar.MONTH) {
            Map<String, Integer> monthMap = new HashMap<>();
            monthMap.put("JAN", 0); monthMap.put("FEB", 1); monthMap.put("MAR", 2);
            monthMap.put("APR", 3); monthMap.put("MAY", 4); monthMap.put("JUN", 5);
            monthMap.put("JUL", 6); monthMap.put("AUG", 7); monthMap.put("SEP", 8);
            monthMap.put("OCT", 9); monthMap.put("NOV", 10); monthMap.put("DEC", 11);

            if (monthMap.containsKey(value.toUpperCase())) {
                return monthMap.get(value.toUpperCase());
            }
        }

        // 处理星期名称（SUN-SAT → 1-7，与Calendar一致）
        if (field == Calendar.DAY_OF_WEEK) {
            Map<String, Integer> weekMap = new HashMap<>();
            weekMap.put("SUN", 1); weekMap.put("MON", 2); weekMap.put("TUE", 3);
            weekMap.put("WED", 4); weekMap.put("THU", 5); weekMap.put("FRI", 6);
            weekMap.put("SAT", 7);

            if (weekMap.containsKey(value.toUpperCase())) {
                return weekMap.get(value.toUpperCase());
            }
        }

        // 处理数字值（验证范围）
        int intValue = Integer.parseInt(value);
        int min = FIELD_MIN_VALUES.get(field);
        int max = FIELD_MAX_VALUES.get(field);

        if (intValue < min || intValue > max) {
            throw new IllegalArgumentException("Value " + intValue + " out of range for field " + field);
        }

        // 月份特殊处理（Cron 1-12 → Calendar 0-11）
        if (field == Calendar.MONTH) {
            return intValue - 1;
        }

        return intValue;
    }


    /**
     * 匹配星期字段（单独处理，避免日和周冲突）
     */
    private static boolean matchesDayOfWeek(Calendar calendar, String dayOfWeekPart) {
        if (dayOfWeekPart == null || "?".equals(dayOfWeekPart) || "*".equals(dayOfWeekPart)) {
            return true;
        }
        return matchesField(calendar, dayOfWeekPart, Calendar.DAY_OF_WEEK);
    }


    /**
     * 辅助方法：递增月份（重置日/时/分/秒）
     */
    private static void incrementMonth(Calendar calendar) {
        calendar.add(Calendar.MONTH, 1);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
    }


    /**
     * 辅助方法：递增日期（重置时/分/秒）
     */
    private static void incrementDay(Calendar calendar) {
        calendar.add(Calendar.DAY_OF_MONTH, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
    }


    /**
     * 辅助方法：递增小时（重置分/秒）
     */
    private static void incrementHour(Calendar calendar) {
        calendar.add(Calendar.HOUR_OF_DAY, 1);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
    }


    /**
     * 辅助方法：递增分钟（重置秒）
     */
    private static void incrementMinute(Calendar calendar) {
        calendar.add(Calendar.MINUTE, 1);
        calendar.set(Calendar.SECOND, 0);
    }


    /**
     * 简化方法：从当前时间计算下一次执行时间
     */
    public static long nextExecutionTime(String cronExpression) {
        return nextExecutionTime(cronExpression, System.currentTimeMillis());
    }


    /**
     * 关闭缓存（释放资源）
     */
    public static void shutdownCache() {
        CRON_CACHE.shutdown();
    }

}

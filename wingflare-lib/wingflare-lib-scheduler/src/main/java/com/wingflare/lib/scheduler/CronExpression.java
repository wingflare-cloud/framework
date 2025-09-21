package com.wingflare.lib.scheduler;


import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;

/**
 * Cron表达式支持秒级精度的解析和计算（优化：支持英文星期缩写+星期范围）
 * 格式: 秒 分 时 日 月 周 [年]
 * 支持特殊字符: * ? - , / L W #（新增：星期缩写 SUN/MON/TUE/WED/THU/FRI/SAT）
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

    // 字段范围（周字段：0=周日，1=周一，…，6=周六，与Java DayOfWeek%7结果对齐）
    private static final int[] MIN_VALUES = {0, 0, 0, 1, 1, 0, 1970};
    private static final int[] MAX_VALUES = {59, 59, 23, 31, 12, 6, 2099};

    // 英文星期缩写→数字映射表（与MIN/MAX_VALUES[DAY_OF_WEEK]对齐）
    private static final Map<String, Integer> WEEKDAY_ABBR_MAP;
    static {
        // 周六：DayOfWeek.SATURDAY.getValue()=6 → 6%7=6
        WEEKDAY_ABBR_MAP = Map.of("SUN", 0, // 周日：Java DayOfWeek.SUNDAY.getValue()=7 → 7%7=0
                "MON", 1, // 周一：DayOfWeek.MONDAY.getValue()=1 → 1%7=1
                "TUE", 2, // 周二：DayOfWeek.TUESDAY.getValue()=2 → 2%7=2
                "WED", 3, // 周三：DayOfWeek.WEDNESDAY.getValue()=3 → 3%7=3
                "THU", 4, // 周四：DayOfWeek.THURSDAY.getValue()=4 → 4%7=4
                "FRI", 5, // 周五：DayOfWeek.FRIDAY.getValue()=5 → 5%7=5
                "SAT", 6);
    }

    // 月份天数（处理2月闰年）
    private static final int[] DAYS_IN_MONTH = {31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

    private final String cronExpression;
    private final String[] fields;
    private final Set<Integer>[] fieldSets;

    /**
     * 创建Cron表达式
     * @param cronExpression Cron表达式字符串（支持 MON-FRI 这类星期语法）
     */
    @SuppressWarnings("unchecked")
    public CronExpression(String cronExpression) {
        if (cronExpression == null || cronExpression.trim().isEmpty()) {
            throw new IllegalArgumentException("The Cron expression cannot be empty");
        }

        this.cronExpression = cronExpression.trim();
        String[] tempFields = this.cronExpression.split("\\s+");

        if (tempFields.length < 6 || tempFields.length > 7) {
            throw new IllegalArgumentException(
                    "Invalid Cron expression format. It should be: seconds minutes hours day month week [year] (Example: 0 15 10 ? * MON-FRI)");
        }

        // 补全年份字段（默认*，表示所有年份）
        this.fields = tempFields.length == 6 ? Arrays.copyOf(tempFields, 7) : tempFields;
        if (tempFields.length == 6) this.fields[YEAR] = "*";

        // 初始化字段集合，解析所有字段
        this.fieldSets = new Set[7];
        parseFields();
    }

    /**
     * 解析所有字段（核心：校验日/周字段不能同时非?）
     */
    private void parseFields() {
        for (int i = 0; i < 7; i++) {
            fieldSets[i] = parseField(fields[i], i);
        }

        // 强制校验：日和周字段不能同时指定（必须有一个为?）
        if (!fields[DAY_OF_MONTH].equals("?") && !fields[DAY_OF_WEEK].equals("?")) {
            throw new IllegalArgumentException(
                    "The day (4th field) and week (6th field) cannot be specified at the same time; one of them must be '?'.");
        }
    }

    /**
     * 解析单个字段（区分 * / ? - , 等符号）
     */
    private Set<Integer> parseField(String field, int fieldIndex) {
        Set<Integer> values = new TreeSet<>();
        String upperField = field.toUpperCase(); // 统一大写，兼容小写缩写（如mon→MON）

        // 1. 处理 *：所有可能值
        if ("*".equals(upperField)) {
            for (int i = MIN_VALUES[fieldIndex]; i <= MAX_VALUES[fieldIndex]; i++) {
                values.add(i);
            }
            return values;
        }

        // 2. 处理 ?：不指定（返回空集，匹配时通过fields判断，而非fieldSets）
        if ("?".equals(upperField)) {
            return values;
        }

        // 3. 处理 ,：多个值（如 1,3,5 或 MON,WED,FRI）
        String[] parts = upperField.split(",");
        for (String part : parts) {
            parseFieldPart(part.trim(), fieldIndex, values);
        }

        return values;
    }

    /**
     * 解析字段的单个部分（处理 / 步长 和 - 范围）
     */
    private void parseFieldPart(String part, int fieldIndex, Set<Integer> values) {
        if (part.contains("/")) {
            parseStepExpression(part, fieldIndex, values); // 步长（如 0/5 或 MON/2）
        } else if (part.contains("-")) {
            parseRangeExpression(part, fieldIndex, values); // 范围（如 1-5 或 MON-FRI）
        } else {
            values.add(parseValue(part, fieldIndex)); // 单个值（如 10 或 MON）
        }
    }

    /**
     * 解析步长表达式（如 0/5：从0开始每5个值；MON/2：从周一开始每2周）
     */
    private void parseStepExpression(String part, int fieldIndex, Set<Integer> values) {
        String[] stepParts = part.split("/");
        if (stepParts.length != 2) {
            throw new IllegalArgumentException("Invalid step expression format: " + part + " (Correct examples: 0/5 or MON/2)");
        }

        int start, end, step;
        String startPart = stepParts[0];
        step = parseStepValue(stepParts[1], fieldIndex);

        // 处理起始值（支持 * 或 范围 或 单个值/缩写）
        if ("*".equals(startPart)) {
            start = MIN_VALUES[fieldIndex];
            end = MAX_VALUES[fieldIndex];
        } else if (startPart.contains("-")) {
            String[] rangeParts = startPart.split("-");
            start = parseValue(rangeParts[0], fieldIndex);
            end = parseValue(rangeParts[1], fieldIndex);
        } else {
            start = parseValue(startPart, fieldIndex);
            end = MAX_VALUES[fieldIndex];
        }

        // 生成步长值（确保递增方向正确）
        if (start <= end) {
            for (int i = start; i <= end; i += step) {
                values.add(i);
            }
        } else {
            for (int i = start; i >= end; i -= step) {
                values.add(i);
            }
        }
    }

    /**
     * 解析范围表达式（如 1-5：1到5；MON-FRI：周一到周五）
     */
    private void parseRangeExpression(String part, int fieldIndex, Set<Integer> values) {
        String[] rangeParts = part.split("-");
        if (rangeParts.length != 2) {
            throw new IllegalArgumentException("Invalid range expression format: " + part + " (Correct examples: 1-5 or MON-FRI)");
        }

        int start = parseValue(rangeParts[0], fieldIndex);
        int end = parseValue(rangeParts[1], fieldIndex);

        // 生成范围值（支持正向范围 1-5 和 反向范围 5-1）
        if (start <= end) {
            for (int i = start; i <= end; i++) {
                values.add(i);
            }
        } else {
            for (int i = start; i >= end; i--) {
                values.add(i);
            }
        }
    }

    /**
     * 解析单个值（核心：周字段支持英文缩写，其他字段仅支持数字）
     */
    private int parseValue(String value, int fieldIndex) {
        // 周字段特殊处理：先尝试匹配英文缩写
        if (fieldIndex == DAY_OF_WEEK) {
            Integer mappedValue = WEEKDAY_ABBR_MAP.get(value.toUpperCase());
            if (mappedValue != null) {
                return mappedValue;
            }
        }

        // 非周字段/非缩写：解析数字并校验范围
        try {
            int intValue = Integer.parseInt(value);
            int min = MIN_VALUES[fieldIndex];
            int max = MAX_VALUES[fieldIndex];

            if (intValue < min || intValue > max) {
                throw new IllegalArgumentException(String.format(
                        "The %s field value is invalid: %s (Allowed range: %d-%d, and the week field also supports SUN/MON/TUE/WED/THU/FRI/SAT)",
                        getFieldName(fieldIndex), value, min, max
                ));
            }
            return intValue;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(String.format(
                    "The %s field value is invalid: %s (Only the week field supports English abbreviations; other fields must be numbers)",
                    getFieldName(fieldIndex), value
            ));
        }
    }

    /**
     * 解析步长值（确保步长为正整数）
     */
    private int parseStepValue(String stepStr, int fieldIndex) {
        try {
            int step = Integer.parseInt(stepStr);
            if (step <= 0) {
                throw new IllegalArgumentException("Step must be a positive integer:" + stepStr);
            }
            return step;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(String.format(
                    "Invalid step for %s field: %s (must be a positive integer)",
                    getFieldName(fieldIndex), stepStr
            ));
        }
    }

    /**
     * 计算下一次执行时间（从指定时间的下一秒开始）
     */
    public LocalDateTime getNextExecutionTime(LocalDateTime fromTime) {
        if (fromTime == null) fromTime = LocalDateTime.now();

        LocalDateTime nextTime = fromTime.plusSeconds(1).withNano(0);
        int maxIterations = 366 * 24 * 60 * 60; // 最大尝试次数（1年的秒数，避免无限循环）
        int iterations = 0;

        while (iterations < maxIterations) {
            if (matches(nextTime)) {
                return nextTime;
            }
            nextTime = incrementTime(nextTime);
            iterations++;
        }

        throw new IllegalStateException(
                "Unable to find the next valid execution time (possibly because the Cron expression has no matching dates, such as the 31st day + February)");
    }

    /**
     * 检查时间是否匹配Cron表达式
     */
    private boolean matches(LocalDateTime dateTime) {
        // 1. 校验秒、分、时、月、年（这些字段无?，直接匹配fieldSets）
        boolean baseMatch = fieldSets[SECOND].contains(dateTime.getSecond())
                && fieldSets[MINUTE].contains(dateTime.getMinute())
                && fieldSets[HOUR].contains(dateTime.getHour())
                && fieldSets[MONTH].contains(dateTime.getMonthValue())
                && fieldSets[YEAR].contains(dateTime.getYear());

        if (!baseMatch) return false;

        // 2. 校验日和周（处理?逻辑）
        return matchesDayAndWeek(dateTime);
    }

    /**
     * 校验日和周字段（核心：?表示忽略该字段）
     */
    private boolean matchesDayAndWeek(LocalDateTime dateTime) {
        // 日字段非?：仅校验日
        if (!fields[DAY_OF_MONTH].equals("?")) {
            // 额外校验：日不能超过当月实际天数（如2月没有30号）
            int actualDays = getDaysInMonth(dateTime.getYear(), dateTime.getMonthValue());
            int day = dateTime.getDayOfMonth();
            return day <= actualDays && fieldSets[DAY_OF_MONTH].contains(day);
        }

        // 周字段非?：仅校验周（Java DayOfWeek→数字：周日7→0，周一1→1，…，周六6→6）
        if (!fields[DAY_OF_WEEK].equals("?")) {
            int weekNum = dateTime.getDayOfWeek().getValue() % 7;
            return fieldSets[DAY_OF_WEEK].contains(weekNum);
        }

        // 理论上不会走到这里（parseFields已校验日和周不能同时为?）
        return false;
    }

    /**
     * 智能递增时间（优先递增秒→分→时→日，避免无效循环）
     */
    private LocalDateTime incrementTime(LocalDateTime dateTime) {
        // 1. 尝试递增秒（当前秒在fieldSets中，直接+1秒）
        if (fieldSets[SECOND].contains(dateTime.getSecond())) {
            return dateTime.plusSeconds(1);
        }

        // 2. 寻找下一个匹配的秒（无则重置秒，递增分）
        Integer nextSecond = findNextValue(fieldSets[SECOND], dateTime.getSecond());
        if (nextSecond != null) {
            return dateTime.withSecond(nextSecond);
        }

        // 3. 重置秒为最小值，递增分
        int minSecond = fieldSets[SECOND].iterator().next();
        LocalDateTime nextMinuteTime = dateTime.withSecond(minSecond);

        Integer nextMinute = findNextValue(fieldSets[MINUTE], nextMinuteTime.getMinute());
        if (nextMinute != null) {
            return nextMinuteTime.withMinute(nextMinute);
        }

        // 4. 重置分为最小值，递增时
        int minMinute = fieldSets[MINUTE].iterator().next();
        LocalDateTime nextHourTime = nextMinuteTime.withMinute(minMinute);

        Integer nextHour = findNextValue(fieldSets[HOUR], nextHourTime.getHour());
        if (nextHour != null) {
            return nextHourTime.withHour(nextHour);
        }

        // 5. 重置时为最小值，递增日（进入下一天）
        int minHour = fieldSets[HOUR].iterator().next();
        return nextHourTime.withHour(minHour).plusDays(1);
    }

    /**
     * 在有序集合中寻找大于当前值的下一个值（TreeSet保证有序）
     */
    private Integer findNextValue(Set<Integer> values, int current) {
        for (Integer value : values) {
            if (value > current) {
                return value;
            }
        }
        return null;
    }

    /**
     * 获取当月实际天数（处理闰年2月）
     */
    private int getDaysInMonth(int year, int month) {
        if (month < 1 || month > 12) {
            throw new IllegalArgumentException("Month invalid:" + month);
        }
        if (month == 2) {
            return isLeapYear(year) ? 29 : 28;
        }
        return DAYS_IN_MONTH[month - 1];
    }

    /**
     * 判断闰年（能被4整除且不能被100整除，或能被400整除）
     */
    private boolean isLeapYear(int year) {
        return (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);
    }

    /**
     * 获取字段名称（用于友好错误提示）
     */
    private String getFieldName(int fieldIndex) {
        return switch (fieldIndex) {
            case SECOND -> "秒";
            case MINUTE -> "分";
            case HOUR -> "时";
            case DAY_OF_MONTH -> "日";
            case MONTH -> "月";
            case DAY_OF_WEEK -> "周";
            case YEAR -> "年";
            default -> "未知字段";
        };
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
        return Objects.hash(cronExpression);
    }

}
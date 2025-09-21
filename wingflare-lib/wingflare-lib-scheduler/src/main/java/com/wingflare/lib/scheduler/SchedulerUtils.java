package com.wingflare.lib.scheduler;


import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicReference;

/**
 * 定时任务工具类 - 提供简单易用的静态API
 * 线程安全的全局调度器实例
 */
public final class SchedulerUtils {
    
    // 全局调度器实例
    private static final AtomicReference<TaskScheduler> GLOBAL_SCHEDULER = new AtomicReference<>();
    
    // 私有构造函数，防止实例化
    private SchedulerUtils() {
        throw new UnsupportedOperationException("Utility classes cannot be instantiated");
    }
    
    /**
     * 获取全局调度器实例（懒加载）
     */
    public static TaskScheduler getGlobalScheduler() {
        TaskScheduler scheduler = GLOBAL_SCHEDULER.get();
        if (scheduler == null) {
            synchronized (SchedulerUtils.class) {
                scheduler = GLOBAL_SCHEDULER.get();
                if (scheduler == null) {
                    scheduler = new TaskScheduler();
                    scheduler.start();
                    GLOBAL_SCHEDULER.set(scheduler);
                    
                    // 添加JVM关闭钩子
                    Runtime.getRuntime().addShutdownHook(new Thread(() -> {
                        TaskScheduler globalScheduler = GLOBAL_SCHEDULER.get();
                        if (globalScheduler != null) {
                            globalScheduler.stop();
                        }
                    }));
                }
            }
        }
        return scheduler;
    }
    
    /**
     * 设置自定义的全局调度器
     */
    public static void setGlobalScheduler(TaskScheduler scheduler) {
        TaskScheduler oldScheduler = GLOBAL_SCHEDULER.getAndSet(scheduler);
        if (oldScheduler != null) {
            oldScheduler.stop();
        }
    }
    
    // ================== Cron任务API ==================
    
    /**
     * 提交Cron任务
     * @param cronExpression Cron表达式（支持秒级）
     * @param task 要执行的任务
     * @return 任务执行Future
     */
    public static Future<Void> cron(String cronExpression, Runnable task) {
        return cron(null, cronExpression, task);
    }
    
    /**
     * 提交命名的Cron任务
     * @param name 任务名称
     * @param cronExpression Cron表达式
     * @param task 要执行的任务
     * @return 任务执行Future
     */
    public static Future<Void> cron(String name, String cronExpression, Runnable task) {
        return getGlobalScheduler().scheduleWithCron(name, cronExpression, task);
    }
    
    /**
     * 提交带超时的Cron任务
     * @param name 任务名称
     * @param cronExpression Cron表达式
     * @param task 要执行的任务
     * @param timeoutMs 超时时间（毫秒）
     * @return 任务执行Future
     */
    public static Future<Void> cron(String name, String cronExpression, Runnable task, long timeoutMs) {
        return getGlobalScheduler().scheduleWithCron(name, cronExpression, task, timeoutMs);
    }
    
    // ================== 固定延时任务API ==================
    
    /**
     * 提交固定延时任务
     * @param task 要执行的任务
     * @param initialDelayMs 初始延时（毫秒）
     * @param delayMs 执行间隔（毫秒）
     * @return 任务执行Future
     */
    public static Future<Void> fixedDelay(Runnable task, long initialDelayMs, long delayMs) {
        return fixedDelay(null, task, initialDelayMs, delayMs);
    }
    
    /**
     * 提交命名的固定延时任务
     * @param name 任务名称
     * @param task 要执行的任务
     * @param initialDelayMs 初始延时（毫秒）
     * @param delayMs 执行间隔（毫秒）
     * @return 任务执行Future
     */
    public static Future<Void> fixedDelay(String name, Runnable task, long initialDelayMs, long delayMs) {
        return getGlobalScheduler().scheduleWithFixedDelay(name, task, initialDelayMs, delayMs);
    }
    
    // ================== 固定频率任务API ==================
    
    /**
     * 提交固定频率任务
     * @param task 要执行的任务
     * @param initialDelayMs 初始延时（毫秒）
     * @param periodMs 执行周期（毫秒）
     * @return 任务执行Future
     */
    public static Future<Void> fixedRate(Runnable task, long initialDelayMs, long periodMs) {
        return fixedRate(null, task, initialDelayMs, periodMs);
    }
    
    /**
     * 提交命名的固定频率任务
     * @param name 任务名称
     * @param task 要执行的任务
     * @param initialDelayMs 初始延时（毫秒）
     * @param periodMs 执行周期（毫秒）
     * @return 任务执行Future
     */
    public static Future<Void> fixedRate(String name, Runnable task, long initialDelayMs, long periodMs) {
        return getGlobalScheduler().scheduleAtFixedRate(name, task, initialDelayMs, periodMs);
    }
    
    // ================== 一次性延时任务API ==================
    
    /**
     * 提交一次性延时任务
     * @param task 要执行的任务
     * @param delayMs 延时时间（毫秒）
     * @return 任务执行Future
     */
    public static Future<Void> delay(Runnable task, long delayMs) {
        return delay(null, task, delayMs);
    }
    
    /**
     * 提交命名的一次性延时任务
     * @param name 任务名称
     * @param task 要执行的任务
     * @param delayMs 延时时间（毫秒）
     * @return 任务执行Future
     */
    public static Future<Void> delay(String name, Runnable task, long delayMs) {
        return getGlobalScheduler().schedule(name, task, delayMs);
    }
    
    // ================== 便捷API（使用时间单位） ==================
    
    /**
     * 每秒执行一次
     */
    public static Future<Void> everySecond(Runnable task) {
        return cron("*/1 * * * * ?", task);
    }
    
    /**
     * 每分钟执行一次
     */
    public static Future<Void> everyMinute(Runnable task) {
        return cron("0 * * * * ?", task);
    }
    
    /**
     * 每小时执行一次
     */
    public static Future<Void> everyHour(Runnable task) {
        return cron("0 0 * * * ?", task);
    }
    
    /**
     * 每天执行一次（午夜）
     */
    public static Future<Void> everyDay(Runnable task) {
        return cron("0 0 0 * * ?", task);
    }
    
    /**
     * 每天指定时间执行
     * @param hour 小时（0-23）
     * @param minute 分钟（0-59）
     */
    public static Future<Void> dailyAt(int hour, int minute, Runnable task) {
        return cron(String.format("0 %d %d * * ?", minute, hour), task);
    }
    
    /**
     * 每周指定时间执行
     * @param dayOfWeek 星期几（1=周一, 7=周日）
     * @param hour 小时
     * @param minute 分钟
     */
    public static Future<Void> weeklyAt(int dayOfWeek, int hour, int minute, Runnable task) {
        return cron(String.format("0 %d %d ? * %d", minute, hour, dayOfWeek), task);
    }
    
    /**
     * 每月指定时间执行
     * @param day 日期（1-31）
     * @param hour 小时
     * @param minute 分钟
     */
    public static Future<Void> monthlyAt(int day, int hour, int minute, Runnable task) {
        return cron(String.format("0 %d %d %d * ?", minute, hour, day), task);
    }
    
    // ================== 任务管理API ==================
    
    /**
     * 取消任务
     * @param taskId 任务ID
     * @return 是否成功取消
     */
    public static boolean cancelTask(long taskId) {
        return getGlobalScheduler().cancelTask(taskId);
    }
    
    /**
     * 获取调度器统计信息
     */
    public static TaskScheduler.SchedulerStats getStats() {
        return getGlobalScheduler().getSchedulerStats();
    }
    
    /**
     * 关闭全局调度器
     */
    public static void shutdown() {
        TaskScheduler scheduler = GLOBAL_SCHEDULER.getAndSet(null);
        if (scheduler != null) {
            scheduler.stop();
        }
    }
    
    /**
     * 检查调度器是否运行中
     */
    public static boolean isRunning() {
        TaskScheduler scheduler = GLOBAL_SCHEDULER.get();
        return scheduler != null && scheduler.getSchedulerStats().isRunning();
    }
}
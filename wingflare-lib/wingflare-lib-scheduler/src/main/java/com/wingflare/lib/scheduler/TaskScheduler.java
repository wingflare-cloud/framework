package com.wingflare.lib.scheduler;


import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * 任务调度器，对外提供静态方法API
 */
public class TaskScheduler {

    // 静态单例实例
    private static volatile TaskScheduler instance;
    private static final Object LOCK = new Object();

    private final HierarchicalTimeWheel timeWheel;
    private final ExecutorService workerPool;
    private volatile boolean isShutdown = false;

    private static class SingletonHolder {
        private static final TaskScheduler INSTANCE = new TaskScheduler(new TaskSchedulerConfig());
    }

    /**
     * 私有构造函数
     */
    private TaskScheduler(TaskSchedulerConfig config) {
        this.workerPool = config.getThreadPoolConfig().createExecutorService();
        this.timeWheel = new HierarchicalTimeWheel(
                config.getTickMs(),
                config.getWheelSize(),
                config.getLevels(),
                workerPool,
                config.getExceptionHandler()
        );
        this.timeWheel.start();
    }

    /**
     * 调度延时任务
     * @param task 任务
     * @param delayMs 延迟毫秒数
     * @return 任务未来对象，可用于取消任务
     */
    public static TaskFuture schedule(Runnable task, long delayMs) {
        return SingletonHolder.INSTANCE.scheduleInternal(task, delayMs, 0, TaskType.ONCE, null, 0);
    }

    /**
     * 调度带超时的延时任务
     * @param task 任务
     * @param delayMs 延迟毫秒数
     * @param timeoutMs 超时毫秒数
     * @return 任务未来对象，可用于取消任务
     */
    public static TaskFuture scheduleWithTimeout(Runnable task, long delayMs, long timeoutMs) {
        return SingletonHolder.INSTANCE.scheduleInternal(task, delayMs, 0, TaskType.ONCE, null, timeoutMs);
    }

    /**
     * 调度固定频率任务
     * @param task 任务
     * @param initialDelayMs 初始延迟毫秒数
     * @param periodMs 执行周期毫秒数
     * @return 任务未来对象，可用于取消任务
     */
    public static TaskFuture scheduleAtFixedRate(Runnable task, long initialDelayMs, long periodMs) {
        return SingletonHolder.INSTANCE.scheduleInternal(task, initialDelayMs, periodMs, TaskType.FIXED_RATE, null, 0);
    }

    /**
     * 调度固定延迟任务
     * @param task 任务
     * @param initialDelayMs 初始延迟毫秒数
     * @param delayMs 每次执行后的延迟毫秒数
     * @return 任务未来对象，可用于取消任务
     */
    public static TaskFuture scheduleWithFixedDelay(Runnable task, long initialDelayMs, long delayMs) {
        return SingletonHolder.INSTANCE.scheduleInternal(task, initialDelayMs, delayMs, TaskType.FIXED_DELAY, null, 0);
    }

    /**
     * 调度Cron任务
     * @param task 任务
     * @param cronExpression cron表达式
     * @return 任务未来对象，可用于取消任务
     */
    public static TaskFuture scheduleCron(Runnable task, String cronExpression) {
        return SingletonHolder.INSTANCE.scheduleInternal(task, 0, 0, TaskType.CRON, cronExpression, 0);
    }

    /**
     * 调度带超时的Cron任务
     * @param task 任务
     * @param cronExpression cron表达式
     * @param timeoutMs 超时毫秒数
     * @return 任务未来对象，可用于取消任务
     */
    public static TaskFuture scheduleCronWithTimeout(Runnable task, String cronExpression, long timeoutMs) {
        return SingletonHolder.INSTANCE.scheduleInternal(task, 0, 0, TaskType.CRON, cronExpression, timeoutMs);
    }

    /**
     * 内部调度方法
     */
    private TaskFuture scheduleInternal(Runnable task, long initialDelayMs, long periodMs,
                                        TaskType type, String cronExpression, long timeoutMs) {
        ScheduledTask scheduledTask = new ScheduledTask(
                task, type, initialDelayMs, periodMs, cronExpression, timeoutMs);
        timeWheel.addTask(scheduledTask);

        // 返回任务未来对象
        return new TaskFuture() {
            @Override
            public boolean cancel(boolean mayInterruptIfRunning) {
                return timeWheel.cancelTask(scheduledTask.getId(), mayInterruptIfRunning);
            }

            @Override
            public boolean isCancelled() {
                return scheduledTask.getStatus() == TaskStatus.CANCELLED;
            }

            @Override
            public boolean isDone() {
                TaskStatus status = scheduledTask.getStatus();
                return status == TaskStatus.COMPLETED ||
                        status == TaskStatus.CANCELLED ||
                        status == TaskStatus.FAILED;
            }

            @Override
            public TaskStatus getStatus() {
                return scheduledTask.getStatus();
            }
        };
    }

    /**
     * 关闭调度器
     */
    public static void shutdown() {
        TaskScheduler scheduler = instance;
        if (scheduler != null) {
            synchronized (LOCK) {
                if (!scheduler.isShutdown) {
                    // 停止时间轮
                    scheduler.timeWheel.stop();

                    // 关闭工作线程池
                    scheduler.workerPool.shutdownNow();
                    try {
                        // 等待一段时间让线程池终止
                        if (!scheduler.workerPool.awaitTermination(2, TimeUnit.SECONDS)) {
                            System.err.println("Worker pool did not terminate properly");
                        }
                    } catch (InterruptedException e) {
                        scheduler.workerPool.shutdownNow();
                    }

                    // 关闭Cron缓存
                    CronExpressionParser.shutdownCache();

                    scheduler.isShutdown = true;
                    instance = null; // 允许重新初始化
                }
            }
        }
    }

    /**
     * 示例用法
     */
    public static void main(String[] args) throws InterruptedException {
        System.out.println("当前时间: " + new Date());
        // 延时任务示例
        TaskScheduler.schedule(() -> System.out.println("延迟2秒执行的任务: " + new Date()), 2000);

        // 带超时的任务示例
        TaskFuture timeoutTask = TaskScheduler.scheduleWithTimeout(() -> {
            try {
                Thread.sleep(1500); // 模拟任务执行，会超时
                System.out.println("带超时的任务执行完成");
            } catch (InterruptedException e) {
                System.out.println("带超时的任务被中断");
            }
        }, 1000, 1000); // 1秒后执行，超时1秒

        // 固定频率任务示例
        TaskFuture fixedRateTask = TaskScheduler.scheduleAtFixedRate(() ->
                System.out.println("固定频率任务(每3秒): " + new Date()), 1000, 3000);

        // 固定延迟任务示例
        TaskScheduler.scheduleWithFixedDelay(() -> {
            try {
                Thread.sleep(1000); // 模拟任务执行1秒
                System.out.println("固定延迟任务(每次间隔2秒): " + new Date());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, 2000, 2000);

        // Cron任务示例 - 每10秒执行一次
        TaskFuture cronTask = TaskScheduler.scheduleCron(() ->
                System.out.println("Cron任务(每10秒): " + new Date()), "0/10 * * * * ?");

        // 运行15秒后取消部分任务
        Thread.sleep(15000);
        System.out.println("取消固定频率任务");
        fixedRateTask.cancel(false);

        // 再运行15秒后关闭
        Thread.sleep(15000);

        System.out.println("取消Cron任务");
        cronTask.cancel(false);

        System.out.println("Shutting down scheduler...");
        TaskScheduler.shutdown();
        System.out.println("Scheduler shutdown complete");
    }

}

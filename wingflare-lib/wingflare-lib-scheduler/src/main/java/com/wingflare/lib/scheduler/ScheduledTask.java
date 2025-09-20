package com.wingflare.lib.scheduler;


import java.util.Calendar;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 具体任务实现类
 */
public class ScheduledTask implements Task {

    private static final AtomicInteger TASK_ID_GENERATOR = new AtomicInteger(0);

    private final String id;
    private final Runnable task;
    private final TaskType type;
    private final long initialDelay;
    private final long period;
    private final String cronExpression;
    private final long timeout;
    private long nextExecutionTime;
    private volatile TaskStatus status;
    private final Object lock = new Object();


    public ScheduledTask(Runnable task, TaskType type, long initialDelay, long period,
                         String cronExpression, long timeout) {
        this.id = "task-" + TASK_ID_GENERATOR.incrementAndGet();
        this.task = task;
        this.type = type;
        this.initialDelay = initialDelay;
        this.period = period;
        this.cronExpression = cronExpression;
        this.timeout = timeout;
        this.status = TaskStatus.PENDING;

        // 初始化首次执行时间（确保Cron时间准确）
        if (type == TaskType.CRON) {
            if (!CronExpressionParser.isValidExpression(cronExpression)) {
                throw new IllegalArgumentException("Invalid cron expression: " + cronExpression);
            }
            // 初始化时清零毫秒，避免偏差
            Calendar cal = Calendar.getInstance();
            cal.setTimeInMillis(System.currentTimeMillis());
            cal.set(Calendar.MILLISECOND, 0);
            this.nextExecutionTime = CronExpressionParser.nextExecutionTime(cronExpression, cal.getTimeInMillis());
        } else {
            this.nextExecutionTime = System.currentTimeMillis() + initialDelay;
        }
    }


    @Override
    public void run() throws Exception {
        synchronized (lock) {
            if (status == TaskStatus.CANCELLED) {
                return;
            }
            status = TaskStatus.RUNNING;
        }

        try {
            task.run(); // 执行用户任务

            synchronized (lock) {
                // 更新任务状态（周期性任务置为等待，否则置为完成）
                status = isRecurring() ? TaskStatus.PENDING : TaskStatus.COMPLETED;
            }
        } catch (Exception e) {
            synchronized (lock) {
                status = TaskStatus.FAILED;
            }
            throw e;
        }
    }


    /**
     * 计算下一次执行时间（核心修复：Cron任务基于上一次时间计算）
     */
    @Override
    public void calculateNextExecutionTime() {
        if (status == TaskStatus.CANCELLED) {
            return;
        }

        switch (type) {
            case FIXED_RATE:
                // 固定频率：以上一次开始时间为基准
                nextExecutionTime += period;
                break;

            case FIXED_DELAY:
                // 固定延迟：以上一次结束时间为基准
                nextExecutionTime = System.currentTimeMillis() + period;
                break;

            case CRON:
                // 关键修复：基于上一次执行时间计算下一次，避免漂移
                long lastExecTime = this.nextExecutionTime;
                this.nextExecutionTime = CronExpressionParser.nextExecutionTime(cronExpression, lastExecTime);

                // 双重校验：确保下一次时间在当前时间之后（避免任务堆积）
                long now = System.currentTimeMillis();
                while (this.nextExecutionTime <= now) {
                    this.nextExecutionTime = CronExpressionParser.nextExecutionTime(cronExpression, this.nextExecutionTime);
                }
                break;

            case ONCE:
                // 一次性任务无需计算下一次
                break;
        }
    }


    // 以下为接口实现的getter/setter方法
    @Override
    public long getNextExecutionTime() {
        return nextExecutionTime;
    }

    @Override
    public void setNextExecutionTime(long time) {
        this.nextExecutionTime = time;
    }

    @Override
    public long getTimeout() {
        return timeout;
    }

    @Override
    public boolean isRecurring() {
        return type != TaskType.ONCE;
    }

    @Override
    public String getCronExpression() {
        return cronExpression;
    }

    @Override
    public TaskStatus getStatus() {
        synchronized (lock) {
            return status;
        }
    }

    @Override
    public void setStatus(TaskStatus status) {
        synchronized (lock) {
            this.status = status;
        }
    }

    @Override
    public String getId() {
        return id;
    }


    /**
     * 取消任务
     */
    public boolean cancel(boolean mayInterruptIfRunning) {
        synchronized (lock) {
            if (status == TaskStatus.RUNNING && !mayInterruptIfRunning) {
                return false;
            }
            if (status == TaskStatus.COMPLETED || status == TaskStatus.CANCELLED) {
                return false;
            }
            status = TaskStatus.CANCELLED;
            return true;
        }
    }

}

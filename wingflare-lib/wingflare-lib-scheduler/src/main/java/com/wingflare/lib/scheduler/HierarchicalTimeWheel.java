package com.wingflare.lib.scheduler;


import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * 多级时间轮
 */
public class HierarchicalTimeWheel {

    private final long tickMs; // 时间轮刻度（建议10ms，提升精度）
    private final int wheelSize; // 每个时间轮的槽数量
    private final long interval; // 时间轮总时长（tickMs * wheelSize）
    private final List<Set<Task>> buckets; // 存储任务的槽
    private volatile long currentTime; // 当前时间轮指向的时间
    private final ExecutorService workerPool; // 执行任务的线程池
    private final ScheduledExecutorService scheduler; // 驱动时间轮的调度器
    private final AtomicBoolean isRunning = new AtomicBoolean(false);
    private final HierarchicalTimeWheel higherWheel; // 更高级别的时间轮
    private final TaskExceptionHandler exceptionHandler; // 异常处理器
    private final Map<String, Task> taskMap = new ConcurrentHashMap<>(); // 任务跟踪映射


    public HierarchicalTimeWheel(long tickMs, int wheelSize, int levels,
                                 ExecutorService workerPool, TaskExceptionHandler exceptionHandler) {
        this(tickMs, wheelSize, workerPool, exceptionHandler, levels - 1);
    }


    private HierarchicalTimeWheel(long tickMs, int wheelSize,
                                  ExecutorService workerPool,
                                  TaskExceptionHandler exceptionHandler,
                                  int remainingLevels) {
        this.tickMs = tickMs;
        this.wheelSize = wheelSize;
        this.interval = tickMs * wheelSize;
        this.buckets = new ArrayList<>(wheelSize);

        // 初始化任务槽（使用并发集合避免线程安全问题）
        for (int i = 0; i < wheelSize; i++) {
            buckets.add(ConcurrentHashMap.newKeySet());
        }

        // 初始化当前时间：清零毫秒，提升匹配精度
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(System.currentTimeMillis());
        cal.set(Calendar.MILLISECOND, 0);
        this.currentTime = cal.getTimeInMillis();

        this.workerPool = workerPool;
        this.exceptionHandler = exceptionHandler;

        // 创建高级时间轮（递归构造）
        this.higherWheel = remainingLevels > 0 ?
                new HierarchicalTimeWheel(tickMs * wheelSize, wheelSize, workerPool, exceptionHandler, remainingLevels - 1) :
                null;

        // 初始化时间轮驱动线程（设置为守护线程）
        this.scheduler = Executors.newSingleThreadScheduledExecutor(runnable -> {
            Thread thread = new Thread(runnable, "time-wheel-ticker-" + tickMs);
            thread.setDaemon(true);
            return thread;
        });
    }


    /**
     * 启动时间轮
     */
    public void start() {
        if (isRunning.compareAndSet(false, true)) {
            // 立即校准一次时间，避免初始延迟
            advance();
            // 按刻度周期性推进时间轮
            scheduler.scheduleAtFixedRate(this::advance, tickMs, tickMs, TimeUnit.MILLISECONDS);

            // 启动高级时间轮
            if (higherWheel != null) {
                higherWheel.start();
            }
        }
    }


    /**
     * 推进时间轮（核心优化：减少时间累积误差）
     */
    private void advance() {
        // 校准当前时间：避免系统时间漂移
        long newTime = System.currentTimeMillis();
        newTime = newTime - (newTime % 1000); // 清零毫秒，与Cron时间计算对齐

        // 处理系统时间倒退（重置时间轮）
        if (newTime < currentTime) {
            reset();
            return;
        }

        // 推进时间轮（按刻度处理，避免跳过任务）
        long oldTime = currentTime;
        this.currentTime = newTime;

        // 处理每个刻度的任务（确保不遗漏）
        for (long t = oldTime + tickMs; t <= currentTime; t += tickMs) {
            int index = (int)((t / tickMs) % wheelSize);
            Set<Task> bucket = buckets.get(index);

            if (!bucket.isEmpty()) {
                // 复制任务集合并清空，避免并发修改问题
                Set<Task> tasks = new HashSet<>(bucket);
                bucket.clear();

                // 提交任务到线程池执行
                for (Task task : tasks) {
                    workerPool.submit(() -> processTask(task));
                }
            }
        }
    }


    /**
     * 处理任务执行与后续调度
     */
    private void processTask(Task task) {
        // 检查任务是否已取消
        if (task.getStatus() == TaskStatus.CANCELLED) {
            taskMap.remove(task.getId());
            return;
        }

        try {
            // 检查任务是否真的到期（允许1秒误差，避免调度延迟导致的漏执行）
            long now = System.currentTimeMillis();
            long taskTime = task.getNextExecutionTime();

            if (taskTime > now + 1000) { // 未到期，重新添加到时间轮
                addTaskToWheel(task);
                return;
            }

            // 执行任务（带超时控制）
            ExecutorService executor = Executors.newSingleThreadExecutor();
            Future<?> future = executor.submit(() -> {
                try {
                    task.run();
                } catch (Exception e) {
                    exceptionHandler.handleException(task, e);
                }
            });

            try {
                if (task.getTimeout() > 0) {
                    future.get(task.getTimeout(), TimeUnit.MILLISECONDS);
                } else {
                    future.get();
                }
            } catch (TimeoutException e) {
                future.cancel(true);
                throw new TimeoutException("Task " + task.getId() + " execution timed out");
            } finally {
                executor.shutdownNow();
            }

            // 周期性任务：计算下一次时间并重新添加
            if (task.isRecurring() && task.getStatus() != TaskStatus.CANCELLED) {
                task.calculateNextExecutionTime();
                addTaskToWheel(task);
            } else {
                taskMap.remove(task.getId());
            }
        } catch (Exception e) {
            exceptionHandler.handleException(task, e);
            taskMap.remove(task.getId());
        }
    }


    /**
     * 添加任务到时间轮
     */
    public void addTask(Task task) {
        if (task.getStatus() == TaskStatus.CANCELLED) {
            return;
        }
        taskMap.put(task.getId(), task);
        addTaskToWheel(task);
    }


    /**
     * 将任务添加到合适的时间轮（当前轮或高级轮）
     */
    private void addTaskToWheel(Task task) {
        long taskTime = task.getNextExecutionTime();
        long current = currentTime;

        // 任务已取消
        if (task.getStatus() == TaskStatus.CANCELLED) {
            taskMap.remove(task.getId());
            return;
        }

        // 任务已到期：立即执行
        if (taskTime <= current + tickMs) {
            workerPool.submit(() -> processTask(task));
            return;
        }

        // 任务在当前时间轮范围内
        if (taskTime < current + interval) {
            long ticks = (taskTime - current) / tickMs;
            int index = (int)((current / tickMs + ticks) % wheelSize);
            buckets.get(index).add(task);
            return;
        }

        // 任务需要放在更高级的时间轮
        if (higherWheel != null) {
            higherWheel.addTaskToWheel(task);
        } else {
            // 无高级轮时放入最后一个槽
            int index = (int)((current / tickMs + wheelSize - 1) % wheelSize);
            buckets.get(index).add(task);
        }
    }


    /**
     * 停止时间轮（释放资源）
     */
    public void stop() {
        if (isRunning.compareAndSet(true, false)) {
            // 停止当前时间轮的调度器
            scheduler.shutdownNow();
            try {
                if (!scheduler.awaitTermination(1, TimeUnit.SECONDS)) {
                    System.err.println("Time wheel scheduler did not terminate properly");
                }
            } catch (InterruptedException e) {
                scheduler.shutdownNow();
            }

            // 停止高级时间轮
            if (higherWheel != null) {
                higherWheel.stop();
            }

            // 关闭工作线程池
            workerPool.shutdownNow();

            // 清空任务
            taskMap.clear();
            buckets.forEach(Set::clear);
        }
    }


    /**
     * 重置时间轮（系统时间倒退时调用）
     */
    private void reset() {
        // 重新校准当前时间
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(System.currentTimeMillis());
        cal.set(Calendar.MILLISECOND, 0);
        this.currentTime = cal.getTimeInMillis();

        // 重新添加当前时间轮的任务
        for (Set<Task> bucket : buckets) {
            Set<Task> tasks = new HashSet<>(bucket);
            bucket.clear();

            for (Task task : tasks) {
                addTaskToWheel(task);
            }
        }

        // 重置高级时间轮
        if (higherWheel != null) {
            higherWheel.reset();
        }
    }


    /**
     * 取消任务
     */
    public boolean cancelTask(String taskId, boolean mayInterruptIfRunning) {
        Task task = taskMap.get(taskId);
        if (task == null) {
            return false;
        }

        // 从映射和槽中移除任务
        taskMap.remove(taskId);
        for (Set<Task> bucket : buckets) {
            bucket.remove(task);
        }

        // 通知高级时间轮移除任务
        if (higherWheel != null) {
            higherWheel.cancelTask(taskId, mayInterruptIfRunning);
        }

        // 取消任务执行
        if (task instanceof ScheduledTask) {
            return ((ScheduledTask) task).cancel(mayInterruptIfRunning);
        }

        return false;
    }


    /**
     * 获取任务状态
     */
    public TaskStatus getTaskStatus(String taskId) {
        Task task = taskMap.get(taskId);
        return task != null ? task.getStatus() : null;
    }

}

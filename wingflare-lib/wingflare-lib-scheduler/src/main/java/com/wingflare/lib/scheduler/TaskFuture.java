package com.wingflare.lib.scheduler;


/**
 * 任务未来对象
 */
public interface TaskFuture {

    boolean cancel(boolean mayInterruptIfRunning);

    boolean isCancelled();

    boolean isDone();

    TaskStatus getStatus();

}

package com.wingflare.lib.scheduler;


/**
 * 任务接口，定义任务的基本行为
 */
public interface Task {

    void run() throws Exception;

    long getNextExecutionTime();

    void setNextExecutionTime(long time);

    long getTimeout();

    boolean isRecurring();

    void calculateNextExecutionTime();

    String getCronExpression();

    TaskStatus getStatus();

    void setStatus(TaskStatus status);

    String getId();

}

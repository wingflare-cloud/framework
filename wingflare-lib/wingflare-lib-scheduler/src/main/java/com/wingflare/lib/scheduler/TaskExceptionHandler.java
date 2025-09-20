package com.wingflare.lib.scheduler;


/**
 * 任务异常处理器接口
 */
public interface TaskExceptionHandler {

    void handleException(Task task, Throwable throwable);

}

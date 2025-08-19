package com.wingflare.facade.lib.task;


import java.util.List;

/**
 * 任务服务类
 */
public interface TaskService {

    /**
     * 获取任务列表
     *
     * @return
     */
    List<TaskBO> getTaskList();

    /**
     * 暂停任务
     *
     * @param task
     */
    void pauseTask(TaskBO task);

    /**
     * 恢复任务
     *
     * @param task
     */
    void resumeTask(TaskBO task);


    /**
     * 删除任务
     *
     * @param task
     */
    void deleteTask(TaskBO task);


    /**
     * 创建任务
     *
     * @param task
     */
    void createTask(TaskBO task);


    /**
     * 更新任务
     *
     * @param task
     */
    void updateTask(TaskBO task);


}

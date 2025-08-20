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
    void pauseTask(TaskBO task) throws Exception;

    /**
     * 恢复任务
     *
     * @param task
     */
    void resumeTask(TaskBO task) throws Exception;


    /**
     * 删除任务
     *
     * @param task
     */
    void deleteTask(TaskBO task) throws Exception;


    /**
     * 创建任务
     *
     * @param task
     */
    void createTask(TaskBO task) throws Exception;


    /**
     * 更新任务
     *
     * @param task
     */
    void updateTask(TaskBO task) throws Exception;

    /**
     * 运行任务
     *
     * @param task
     * @throws Exception
     */
    void runTask(TaskBO task) throws Exception;


}

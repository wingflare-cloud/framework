package com.wingflare.adapter.task.mysql;

import com.wingflare.facade.lib.task.TaskBO;
import com.wingflare.lib.task.TaskAbstractService;

import java.util.List;

/**
 * mysql 定时任务服务实现
 */
public class MysqlTaskService extends TaskAbstractService {


    @Override
    public List<TaskBO> getTaskList() {
        return List.of();
    }


}

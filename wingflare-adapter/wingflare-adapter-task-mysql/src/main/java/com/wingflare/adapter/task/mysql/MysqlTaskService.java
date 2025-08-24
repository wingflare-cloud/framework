package com.wingflare.adapter.task.mysql;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.wingflare.adapter.task.mysql.convert.TaskConvert;
import com.wingflare.adapter.task.mysql.db.TaskDO;
import com.wingflare.adapter.task.mysql.mapper.TaskMapper;
import com.wingflare.facade.lib.task.ScheduleStatus;
import com.wingflare.facade.lib.task.TaskBO;
import com.wingflare.lib.core.exceptions.BusinessLogicException;
import com.wingflare.lib.core.utils.CollectionUtil;
import com.wingflare.lib.task.TaskAbstractService;
import com.wingflare.lib.task.utils.ScheduleUtil;
import jakarta.annotation.Resource;
import org.mybatis.spring.annotation.MapperScan;
import org.quartz.SchedulerException;
import org.springframework.context.ApplicationContext;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * mysql 定时任务服务实现
 */
@MapperScan("com.wingflare.adapter.task.mysql.mapper")
public class MysqlTaskService extends TaskAbstractService {

    @Resource
    private TaskMapper taskMapper;

    @Resource
    private ApplicationContext context;


    @Override
    public List<TaskBO> getTaskList() {
        String appName = context.getApplicationName();

        LambdaQueryWrapper<TaskDO> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(TaskDO::getActuator, appName);
        BigInteger lastTaskId = BigInteger.ZERO;
        List<TaskDO> list = new ArrayList<>();

        while (true) {
            wrapper.gt(TaskDO::getTaskId, lastTaskId);
            wrapper.last("limit 500");
            List<TaskDO> tempList = taskMapper.selectList(wrapper);

            if (CollectionUtil.isEmpty(tempList)) {
                break;
            }

            lastTaskId = tempList.getLast().getTaskId();
            list.addAll(tempList);

            if (tempList.size() != 500) {
                break;
            }
        }

        return TaskConvert.convert.doToBoList(list);
    }

    @Transactional
    public void pauseTask(BigInteger taskId) throws SchedulerException {
        TaskDO taskDO = taskMapper.selectById(taskId);

        if (taskDO != null && Objects.equals(taskDO.getStatus(), ScheduleStatus.NORMAL.getValue())) {
            taskDO.setStatus(ScheduleStatus.PAUSE.getValue());
            pauseTask(TaskConvert.convert.doToBo(taskDO));
        }
    }

    @Transactional
    public void resumeTask(BigInteger taskId) throws SchedulerException {
        TaskDO taskDO = taskMapper.selectById(taskId);

        if (taskDO != null && !Objects.equals(taskDO.getStatus(), ScheduleStatus.NORMAL.getValue())) {
            taskDO.setStatus(ScheduleStatus.NORMAL.getValue());
            resumeTask(TaskConvert.convert.doToBo(taskDO));
        }
    }

    @Transactional
    public void deleteTask(BigInteger taskId) throws SchedulerException {
        TaskDO taskDO = taskMapper.selectById(taskId);

        if (taskDO != null) {
            taskMapper.deleteById(taskId);
            deleteTask(TaskConvert.convert.doToBo(taskDO));
        }
    }

    @Transactional
    public void createTask(TaskDO taskDO) throws Exception {
        if (taskMapper.insert(taskDO) > 0) {
            createTask(TaskConvert.convert.doToBo(taskDO));
        } else {
            throw new BusinessLogicException("task.create.error");
        }
    }

}

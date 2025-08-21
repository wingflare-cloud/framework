package com.wingflare.adapter.task.mysql;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.wingflare.adapter.task.mysql.convert.TaskConvert;
import com.wingflare.adapter.task.mysql.db.TaskDO;
import com.wingflare.adapter.task.mysql.mapper.TaskMapper;
import com.wingflare.facade.lib.task.TaskBO;
import com.wingflare.lib.core.utils.CollectionUtil;
import com.wingflare.lib.task.TaskAbstractService;
import jakarta.annotation.Resource;
import org.springframework.context.ApplicationContext;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

/**
 * mysql 定时任务服务实现
 */
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
        }

        return TaskConvert.convert.doToBoList(list);
    }


}

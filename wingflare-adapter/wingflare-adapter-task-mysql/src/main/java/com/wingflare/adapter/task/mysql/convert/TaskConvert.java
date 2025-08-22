package com.wingflare.adapter.task.mysql.convert;


import com.wingflare.adapter.task.mysql.db.TaskDO;
import com.wingflare.facade.lib.task.TaskBO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;


/**
 * TaskConvert
 */
@Mapper
public interface TaskConvert {

    TaskConvert convert = Mappers.getMapper(TaskConvert.class);

    List<TaskBO> doToBoList(List<TaskDO> taskDOList);

    TaskBO doToBoList(TaskDO taskDO);

}

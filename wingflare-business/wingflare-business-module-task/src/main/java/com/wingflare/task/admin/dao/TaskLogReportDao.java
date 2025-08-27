package com.wingflare.task.admin.dao;


import com.wingflare.task.admin.core.model.TaskLogReport;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * job log
 * @author xuxueli 2019-11-22
 */
@Mapper
public interface TaskLogReportDao {

	public int save(TaskLogReport taskLogReport);

	public int update(TaskLogReport taskLogReport);

	public List<TaskLogReport> queryLogReport(@Param("triggerDayFrom") Date triggerDayFrom,
                                              @Param("triggerDayTo") Date triggerDayTo);

	public TaskLogReport queryLogReportTotal();

}

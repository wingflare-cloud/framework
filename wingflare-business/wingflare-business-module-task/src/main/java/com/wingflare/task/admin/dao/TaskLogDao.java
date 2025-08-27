package com.wingflare.task.admin.dao;

import com.wingflare.task.admin.core.model.TaskLog;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * job log
 * @author xuxueli 2016-1-12 18:03:06
 */
@Mapper
public interface TaskLogDao {

	// exist taskId not use taskGroup, not exist use taskGroup
	public List<TaskLog> pageList(@Param("offset") int offset,
                                  @Param("pagesize") int pagesize,
                                  @Param("taskGroup") int taskGroup,
                                  @Param("taskId") int taskId,
                                  @Param("triggerTimeStart") Date triggerTimeStart,
                                  @Param("triggerTimeEnd") Date triggerTimeEnd,
                                  @Param("logStatus") int logStatus);
	public int pageListCount(@Param("offset") int offset,
							 @Param("pagesize") int pagesize,
							 @Param("taskGroup") int taskGroup,
							 @Param("taskId") int taskId,
							 @Param("triggerTimeStart") Date triggerTimeStart,
							 @Param("triggerTimeEnd") Date triggerTimeEnd,
							 @Param("logStatus") int logStatus);
	
	public TaskLog load(@Param("id") long id);

	public long save(TaskLog taskLog);

	public int updateTriggerInfo(TaskLog taskLog);

	public int updateHandleInfo(TaskLog taskLog);
	
	public int delete(@Param("taskId") int taskId);

	public Map<String, Object> findLogReport(@Param("from") Date from,
											 @Param("to") Date to);

	public List<Long> findClearTaskIds(@Param("taskGroup") int taskGroup,
									  @Param("taskId") int taskId,
									  @Param("clearBeforeTime") Date clearBeforeTime,
									  @Param("clearBeforeNum") int clearBeforeNum,
									  @Param("pagesize") int pagesize);
	public int clearLog(@Param("logIds") List<Long> logIds);

	public List<Long> findFailTaskLogIds(@Param("pagesize") int pagesize);

	public int updateAlarmStatus(@Param("logId") long logId,
								 @Param("oldAlarmStatus") int oldAlarmStatus,
								 @Param("newAlarmStatus") int newAlarmStatus);

	public List<Long> findLostTaskIds(@Param("losedTime") Date losedTime);

}

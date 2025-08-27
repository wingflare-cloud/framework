package com.wingflare.task.admin.dao;

import com.wingflare.task.admin.core.model.TaskInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 * job info
 * @author xuxueli 2016-1-12 18:03:45
 */
@Mapper
public interface TaskInfoDao {

	public List<TaskInfo> pageList(@Param("offset") int offset,
                                   @Param("pagesize") int pagesize,
                                   @Param("taskGroup") int taskGroup,
                                   @Param("triggerStatus") int triggerStatus,
                                   @Param("jobDesc") String jobDesc,
                                   @Param("executorHandler") String executorHandler,
                                   @Param("author") String author);
	public int pageListCount(@Param("offset") int offset,
							 @Param("pagesize") int pagesize,
							 @Param("taskGroup") int taskGroup,
							 @Param("triggerStatus") int triggerStatus,
							 @Param("jobDesc") String jobDesc,
							 @Param("executorHandler") String executorHandler,
							 @Param("author") String author);
	
	public int save(TaskInfo info);

	public TaskInfo loadById(@Param("id") int id);
	
	public int update(TaskInfo taskInfo);
	
	public int delete(@Param("id") long id);

	public List<TaskInfo> getJobsByGroup(@Param("taskGroup") int taskGroup);

	public int findAllCount();

	/**
	 * find schedule job, limit "trigger_status = 1"
	 *
	 * @param maxNextTime
	 * @param pagesize
	 * @return
	 */
	public List<TaskInfo> scheduleJobQuery(@Param("maxNextTime") long maxNextTime, @Param("pagesize") int pagesize );

	/**
	 * update schedule job
	 *
	 * 	1、can only update "trigger_status = 1", Avoid stopping tasks from being opened
	 * 	2、valid "triggerStatus gte 0", filter illegal state
	 *
	 * @param taskInfo
	 * @return
	 */
	public int scheduleUpdate(TaskInfo taskInfo);


}

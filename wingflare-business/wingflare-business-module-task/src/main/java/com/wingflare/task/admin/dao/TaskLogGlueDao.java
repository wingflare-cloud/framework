package com.wingflare.task.admin.dao;


import com.wingflare.task.admin.core.model.TaskLogGlue;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * job log for glue
 * @author xuxueli 2016-5-19 18:04:56
 */
@Mapper
public interface TaskLogGlueDao {
	
	public int save(TaskLogGlue taskLogGlue);
	
	public List<TaskLogGlue> findBytaskId(@Param("taskId") int taskId);

	public int removeOld(@Param("taskId") int taskId, @Param("limit") int limit);

	public int deleteBytaskId(@Param("taskId") int taskId);
	
}

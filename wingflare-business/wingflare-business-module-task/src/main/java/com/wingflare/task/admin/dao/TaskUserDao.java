package com.wingflare.task.admin.dao;


import com.wingflare.task.admin.core.model.TaskUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author xuxueli 2019-05-04 16:44:59
 */
@Mapper
public interface TaskUserDao {

	public List<TaskUser> pageList(@Param("offset") int offset,
                                   @Param("pagesize") int pagesize,
                                   @Param("username") String username,
                                   @Param("role") int role);
	public int pageListCount(@Param("offset") int offset,
							 @Param("pagesize") int pagesize,
							 @Param("username") String username,
							 @Param("role") int role);

	public TaskUser loadByUserName(@Param("username") String username);

	public int save(TaskUser taskUser);

	public int update(TaskUser taskUser);
	
	public int delete(@Param("id") int id);

}

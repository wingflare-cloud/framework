package com.wingflare.task.admin.service;


import com.wingflare.lib.task.biz.model.ReturnT;
import com.wingflare.task.admin.core.model.TaskInfo;
import com.wingflare.task.admin.core.model.TaskUser;

import java.util.Date;
import java.util.Map;

/**
 * core job action for xxl-job
 * 
 * @author xuxueli 2016-5-28 15:30:33
 */
public interface TaskService {

	/**
	 * page list
	 *
	 * @param start
	 * @param length
	 * @param taskGroup
	 * @param jobDesc
	 * @param executorHandler
	 * @param author
	 * @return
	 */
	public Map<String, Object> pageList(int start, int length, int taskGroup, int triggerStatus, String jobDesc, String executorHandler, String author);

	/**
	 * add job
	 *
	 * @param jobInfo
	 * @return
	 */
	public ReturnT<String> add(TaskInfo jobInfo, TaskUser loginUser);

	/**
	 * update job
	 *
	 * @param jobInfo
	 * @return
	 */
	public ReturnT<String> update(TaskInfo jobInfo, TaskUser loginUser);

	/**
	 * remove job
	 * 	 *
	 * @param id
	 * @return
	 */
	public ReturnT<String> remove(int id);

	/**
	 * start job
	 *
	 * @param id
	 * @return
	 */
	public ReturnT<String> start(int id);

	/**
	 * stop job
	 *
	 * @param id
	 * @return
	 */
	public ReturnT<String> stop(int id);

	/**
	 * trigger
	 *
	 * @param loginUser
	 * @param taskId
	 * @param executorParam
	 * @param addressList
	 * @return
	 */
	public ReturnT<String> trigger(TaskUser loginUser, int taskId, String executorParam, String addressList);

	/**
	 * dashboard info
	 *
	 * @return
	 */
	public Map<String,Object> dashboardInfo();

	/**
	 * chart info
	 *
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public ReturnT<Map<String,Object>> chartInfo(Date startDate, Date endDate);

}

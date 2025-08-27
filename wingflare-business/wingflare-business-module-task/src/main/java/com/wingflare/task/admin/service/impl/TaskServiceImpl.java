package com.wingflare.task.admin.service.impl;


import com.wingflare.lib.task.biz.model.ReturnT;
import com.wingflare.lib.task.enums.ExecutorBlockStrategyEnum;
import com.wingflare.lib.task.glue.GlueTypeEnum;
import com.wingflare.lib.task.util.DateUtil;
import com.wingflare.task.admin.core.cron.CronExpression;
import com.wingflare.task.admin.core.model.TaskGroup;
import com.wingflare.task.admin.core.model.TaskInfo;
import com.wingflare.task.admin.core.model.TaskLogReport;
import com.wingflare.task.admin.core.model.TaskUser;
import com.wingflare.task.admin.core.route.ExecutorRouteStrategyEnum;
import com.wingflare.task.admin.core.scheduler.MisfireStrategyEnum;
import com.wingflare.task.admin.core.scheduler.ScheduleTypeEnum;
import com.wingflare.task.admin.core.thread.JobScheduleHelper;
import com.wingflare.task.admin.core.thread.JobTriggerPoolHelper;
import com.wingflare.task.admin.core.trigger.TriggerTypeEnum;
import com.wingflare.task.admin.core.util.I18nUtil;
import com.wingflare.task.admin.dao.TaskGroupDao;
import com.wingflare.task.admin.dao.TaskInfoDao;
import com.wingflare.task.admin.dao.TaskLogDao;
import com.wingflare.task.admin.dao.TaskLogGlueDao;
import com.wingflare.task.admin.dao.TaskLogReportDao;
import com.wingflare.task.admin.service.TaskService;
import jakarta.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * core job action for xxl-job
 * @author xuxueli 2016-5-28 15:30:33
 */
@Service
public class TaskServiceImpl implements TaskService {
	private static final Logger logger = LoggerFactory.getLogger(TaskServiceImpl.class);

	@Resource
	private TaskGroupDao taskGroupDao;
	@Resource
	private TaskInfoDao taskInfoDao;
	@Resource
	public TaskLogDao taskLogDao;
	@Resource
	private TaskLogGlueDao taskLogGlueDao;
	@Resource
	private TaskLogReportDao taskLogReportDao;
	
	@Override
	public Map<String, Object> pageList(int start, int length, int taskGroup, int triggerStatus, String jobDesc, String executorHandler, String author) {

		// page list
		List<TaskInfo> list = taskInfoDao.pageList(start, length, taskGroup, triggerStatus, jobDesc, executorHandler, author);
		int list_count = taskInfoDao.pageListCount(start, length, taskGroup, triggerStatus, jobDesc, executorHandler, author);
		
		// package result
		Map<String, Object> maps = new HashMap<String, Object>();
	    maps.put("recordsTotal", list_count);		// 总记录数
	    maps.put("recordsFiltered", list_count);	// 过滤后的总记录数
	    maps.put("data", list);  					// 分页列表
		return maps;
	}

	@Override
	public ReturnT<String> add(TaskInfo jobInfo, TaskUser loginUser) {

		// valid base
		TaskGroup group = taskGroupDao.load(jobInfo.getTaskGroup());
		if (group == null) {
			return new ReturnT<String>(ReturnT.FAIL_CODE, (I18nUtil.getString("system_please_choose")+I18nUtil.getString("jobinfo_field_taskGroup")) );
		}
		if (jobInfo.getTaskDesc()==null || jobInfo.getTaskDesc().trim().isEmpty()) {
			return new ReturnT<String>(ReturnT.FAIL_CODE, (I18nUtil.getString("system_please_input")+I18nUtil.getString("jobinfo_field_jobdesc")) );
		}
		if (jobInfo.getAuthor()==null || jobInfo.getAuthor().trim().isEmpty()) {
			return new ReturnT<String>(ReturnT.FAIL_CODE, (I18nUtil.getString("system_please_input")+I18nUtil.getString("jobinfo_field_author")) );
		}

		// valid trigger
		ScheduleTypeEnum scheduleTypeEnum = ScheduleTypeEnum.match(jobInfo.getScheduleType(), null);
		if (scheduleTypeEnum == null) {
			return new ReturnT<String>(ReturnT.FAIL_CODE, (I18nUtil.getString("schedule_type")+I18nUtil.getString("system_unvalid")) );
		}
		if (scheduleTypeEnum == ScheduleTypeEnum.CRON) {
			if (jobInfo.getScheduleConf()==null || !CronExpression.isValidExpression(jobInfo.getScheduleConf())) {
				return new ReturnT<String>(ReturnT.FAIL_CODE, "Cron"+I18nUtil.getString("system_unvalid"));
			}
		} else if (scheduleTypeEnum == ScheduleTypeEnum.FIX_RATE/* || scheduleTypeEnum == ScheduleTypeEnum.FIX_DELAY*/) {
			if (jobInfo.getScheduleConf() == null) {
				return new ReturnT<String>(ReturnT.FAIL_CODE, (I18nUtil.getString("schedule_type")) );
			}
			try {
				int fixSecond = Integer.valueOf(jobInfo.getScheduleConf());
				if (fixSecond < 1) {
					return new ReturnT<String>(ReturnT.FAIL_CODE, (I18nUtil.getString("schedule_type")+I18nUtil.getString("system_unvalid")) );
				}
			} catch (Exception e) {
				return new ReturnT<String>(ReturnT.FAIL_CODE, (I18nUtil.getString("schedule_type")+I18nUtil.getString("system_unvalid")) );
			}
		}

		// valid job
		if (GlueTypeEnum.match(jobInfo.getGlueType()) == null) {
			return new ReturnT<String>(ReturnT.FAIL_CODE, (I18nUtil.getString("jobinfo_field_gluetype")+I18nUtil.getString("system_unvalid")) );
		}
		if (GlueTypeEnum.BEAN==GlueTypeEnum.match(jobInfo.getGlueType()) && (jobInfo.getExecutorHandler()==null || jobInfo.getExecutorHandler().trim().length()==0) ) {
			return new ReturnT<String>(ReturnT.FAIL_CODE, (I18nUtil.getString("system_please_input")+"JobHandler") );
		}
		// 》fix "\r" in shell
		if (GlueTypeEnum.GLUE_SHELL==GlueTypeEnum.match(jobInfo.getGlueType()) && jobInfo.getGlueSource()!=null) {
			jobInfo.setGlueSource(jobInfo.getGlueSource().replaceAll("\r", ""));
		}

		// valid advanced
		if (ExecutorRouteStrategyEnum.match(jobInfo.getExecutorRouteStrategy(), null) == null) {
			return new ReturnT<String>(ReturnT.FAIL_CODE, (I18nUtil.getString("jobinfo_field_executorRouteStrategy")+I18nUtil.getString("system_unvalid")) );
		}
		if (MisfireStrategyEnum.match(jobInfo.getMisfireStrategy(), null) == null) {
			return new ReturnT<String>(ReturnT.FAIL_CODE, (I18nUtil.getString("misfire_strategy")+I18nUtil.getString("system_unvalid")) );
		}
		if (ExecutorBlockStrategyEnum.match(jobInfo.getExecutorBlockStrategy(), null) == null) {
			return new ReturnT<String>(ReturnT.FAIL_CODE, (I18nUtil.getString("jobinfo_field_executorBlockStrategy")+I18nUtil.getString("system_unvalid")) );
		}

		// 》ChildtaskId valid
		if (jobInfo.getChildtaskId()!=null && jobInfo.getChildtaskId().trim().length()>0) {
			String[] childtaskIds = jobInfo.getChildtaskId().split(",");
			for (String childtaskIdItem: childtaskIds) {
				if (childtaskIdItem!=null && childtaskIdItem.trim().length()>0 && isNumeric(childtaskIdItem)) {
					TaskInfo childJobInfo = taskInfoDao.loadById(Integer.parseInt(childtaskIdItem));
					if (childJobInfo==null) {
						return new ReturnT<String>(ReturnT.FAIL_CODE,
								MessageFormat.format((I18nUtil.getString("jobinfo_field_childtaskId")+"({0})"+I18nUtil.getString("system_not_found")), childtaskIdItem));
					}
					if (!loginUser.validPermission(childJobInfo.getTaskGroup())) {
						return new ReturnT<String>(ReturnT.FAIL_CODE,
								MessageFormat.format((I18nUtil.getString("jobinfo_field_childtaskId")+"({0})"+I18nUtil.getString("system_permission_limit")), childtaskIdItem));
					}
				} else {
					return new ReturnT<String>(ReturnT.FAIL_CODE,
							MessageFormat.format((I18nUtil.getString("jobinfo_field_childtaskId")+"({0})"+I18nUtil.getString("system_unvalid")), childtaskIdItem));
				}
			}

			// join , avoid "xxx,,"
			String temp = "";
			for (String item:childtaskIds) {
				temp += item + ",";
			}
			temp = temp.substring(0, temp.length()-1);

			jobInfo.setChildtaskId(temp);
		}

		// add in db
		jobInfo.setAddTime(new Date());
		jobInfo.setUpdateTime(new Date());
		jobInfo.setGlueUpdatetime(new Date());
		// remove the whitespace
		jobInfo.setExecutorHandler(jobInfo.getExecutorHandler().trim());
		taskInfoDao.save(jobInfo);
		if (jobInfo.getId() < 1) {
			return new ReturnT<String>(ReturnT.FAIL_CODE, (I18nUtil.getString("jobinfo_field_add")+I18nUtil.getString("system_fail")) );
		}

		return new ReturnT<String>(String.valueOf(jobInfo.getId()));
	}

	private boolean isNumeric(String str){
		try {
			int result = Integer.valueOf(str);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}

	@Override
	public ReturnT<String> update(TaskInfo jobInfo, TaskUser loginUser) {

		// valid base
		if (jobInfo.getTaskDesc()==null || jobInfo.getTaskDesc().trim().isEmpty()) {
			return new ReturnT<String>(ReturnT.FAIL_CODE, (I18nUtil.getString("system_please_input")+I18nUtil.getString("jobinfo_field_jobdesc")) );
		}
		if (jobInfo.getAuthor()==null || jobInfo.getAuthor().trim().isEmpty()) {
			return new ReturnT<String>(ReturnT.FAIL_CODE, (I18nUtil.getString("system_please_input")+I18nUtil.getString("jobinfo_field_author")) );
		}

		// valid trigger
		ScheduleTypeEnum scheduleTypeEnum = ScheduleTypeEnum.match(jobInfo.getScheduleType(), null);
		if (scheduleTypeEnum == null) {
			return new ReturnT<String>(ReturnT.FAIL_CODE, (I18nUtil.getString("schedule_type")+I18nUtil.getString("system_unvalid")) );
		}
		if (scheduleTypeEnum == ScheduleTypeEnum.CRON) {
			if (jobInfo.getScheduleConf()==null || !CronExpression.isValidExpression(jobInfo.getScheduleConf())) {
				return new ReturnT<String>(ReturnT.FAIL_CODE, "Cron"+I18nUtil.getString("system_unvalid") );
			}
		} else if (scheduleTypeEnum == ScheduleTypeEnum.FIX_RATE /*|| scheduleTypeEnum == ScheduleTypeEnum.FIX_DELAY*/) {
			if (jobInfo.getScheduleConf() == null) {
				return new ReturnT<String>(ReturnT.FAIL_CODE, (I18nUtil.getString("schedule_type")+I18nUtil.getString("system_unvalid")) );
			}
			try {
				int fixSecond = Integer.valueOf(jobInfo.getScheduleConf());
				if (fixSecond < 1) {
					return new ReturnT<String>(ReturnT.FAIL_CODE, (I18nUtil.getString("schedule_type")+I18nUtil.getString("system_unvalid")) );
				}
			} catch (Exception e) {
				return new ReturnT<String>(ReturnT.FAIL_CODE, (I18nUtil.getString("schedule_type")+I18nUtil.getString("system_unvalid")) );
			}
		}

		// valid advanced
		if (ExecutorRouteStrategyEnum.match(jobInfo.getExecutorRouteStrategy(), null) == null) {
			return new ReturnT<String>(ReturnT.FAIL_CODE, (I18nUtil.getString("jobinfo_field_executorRouteStrategy")+I18nUtil.getString("system_unvalid")) );
		}
		if (MisfireStrategyEnum.match(jobInfo.getMisfireStrategy(), null) == null) {
			return new ReturnT<String>(ReturnT.FAIL_CODE, (I18nUtil.getString("misfire_strategy")+I18nUtil.getString("system_unvalid")) );
		}
		if (ExecutorBlockStrategyEnum.match(jobInfo.getExecutorBlockStrategy(), null) == null) {
			return new ReturnT<String>(ReturnT.FAIL_CODE, (I18nUtil.getString("jobinfo_field_executorBlockStrategy")+I18nUtil.getString("system_unvalid")) );
		}

		// 》ChildtaskId valid
		if (jobInfo.getChildtaskId()!=null && jobInfo.getChildtaskId().trim().length()>0) {
			String[] childtaskIds = jobInfo.getChildtaskId().split(",");
			for (String childtaskIdItem: childtaskIds) {
				if (childtaskIdItem!=null && childtaskIdItem.trim().length()>0 && isNumeric(childtaskIdItem)) {
					// parse child
					int childtaskId = Integer.parseInt(childtaskIdItem);
					if (childtaskId == jobInfo.getId()) {
						return new ReturnT<String>(ReturnT.FAIL_CODE, (I18nUtil.getString("jobinfo_field_childtaskId")+"("+childtaskId+")"+I18nUtil.getString("system_unvalid")) );
					}

					// valid child
					TaskInfo childJobInfo = taskInfoDao.loadById(childtaskId);
					if (childJobInfo==null) {
						return new ReturnT<String>(ReturnT.FAIL_CODE,
								MessageFormat.format((I18nUtil.getString("jobinfo_field_childtaskId")+"({0})"+I18nUtil.getString("system_not_found")), childtaskIdItem));
					}
					if (!loginUser.validPermission(childJobInfo.getTaskGroup())) {
						return new ReturnT<String>(ReturnT.FAIL_CODE,
								MessageFormat.format((I18nUtil.getString("jobinfo_field_childtaskId")+"({0})"+I18nUtil.getString("system_permission_limit")), childtaskIdItem));
					}
				} else {
					return new ReturnT<String>(ReturnT.FAIL_CODE,
							MessageFormat.format((I18nUtil.getString("jobinfo_field_childtaskId")+"({0})"+I18nUtil.getString("system_unvalid")), childtaskIdItem));
				}
			}

			// join , avoid "xxx,,"
			String temp = "";
			for (String item:childtaskIds) {
				temp += item + ",";
			}
			temp = temp.substring(0, temp.length()-1);

			jobInfo.setChildtaskId(temp);
		}

		// group valid
		TaskGroup taskGroup = taskGroupDao.load(jobInfo.getTaskGroup());
		if (taskGroup == null) {
			return new ReturnT<String>(ReturnT.FAIL_CODE, (I18nUtil.getString("jobinfo_field_taskGroup")+I18nUtil.getString("system_unvalid")) );
		}

		// stage job info
		TaskInfo exists_jobInfo = taskInfoDao.loadById(jobInfo.getId());
		if (exists_jobInfo == null) {
			return new ReturnT<String>(ReturnT.FAIL_CODE, (I18nUtil.getString("jobinfo_field_id")+I18nUtil.getString("system_not_found")) );
		}

		// next trigger time (5s后生效，避开预读周期)
		long nextTriggerTime = exists_jobInfo.getTriggerNextTime();
		boolean scheduleDataNotChanged = jobInfo.getScheduleType().equals(exists_jobInfo.getScheduleType()) && jobInfo.getScheduleConf().equals(exists_jobInfo.getScheduleConf());
		if (exists_jobInfo.getTriggerStatus() == 1 && !scheduleDataNotChanged) {
			try {
				Date nextValidTime = JobScheduleHelper.generateNextValidTime(jobInfo, new Date(System.currentTimeMillis() + JobScheduleHelper.PRE_READ_MS));
				if (nextValidTime == null) {
					return new ReturnT<String>(ReturnT.FAIL_CODE, (I18nUtil.getString("schedule_type")+I18nUtil.getString("system_unvalid")) );
				}
				nextTriggerTime = nextValidTime.getTime();
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				return new ReturnT<String>(ReturnT.FAIL_CODE, (I18nUtil.getString("schedule_type")+I18nUtil.getString("system_unvalid")) );
			}
		}

		exists_jobInfo.setTaskGroup(jobInfo.getTaskGroup());
		exists_jobInfo.setTaskDesc(jobInfo.getTaskDesc());
		exists_jobInfo.setAuthor(jobInfo.getAuthor());
		exists_jobInfo.setAlarmEmail(jobInfo.getAlarmEmail());
		exists_jobInfo.setScheduleType(jobInfo.getScheduleType());
		exists_jobInfo.setScheduleConf(jobInfo.getScheduleConf());
		exists_jobInfo.setMisfireStrategy(jobInfo.getMisfireStrategy());
		exists_jobInfo.setExecutorRouteStrategy(jobInfo.getExecutorRouteStrategy());
		// remove the whitespace
		exists_jobInfo.setExecutorHandler(jobInfo.getExecutorHandler().trim());
		exists_jobInfo.setExecutorParam(jobInfo.getExecutorParam());
		exists_jobInfo.setExecutorBlockStrategy(jobInfo.getExecutorBlockStrategy());
		exists_jobInfo.setExecutorTimeout(jobInfo.getExecutorTimeout());
		exists_jobInfo.setExecutorFailRetryCount(jobInfo.getExecutorFailRetryCount());
		exists_jobInfo.setChildtaskId(jobInfo.getChildtaskId());
		exists_jobInfo.setTriggerNextTime(nextTriggerTime);

		exists_jobInfo.setUpdateTime(new Date());
        taskInfoDao.update(exists_jobInfo);


		return ReturnT.SUCCESS;
	}

	@Override
	public ReturnT<String> remove(int id) {
		TaskInfo taskInfo = taskInfoDao.loadById(id);
		if (taskInfo == null) {
			return ReturnT.SUCCESS;
		}

		taskInfoDao.delete(id);
		taskLogDao.delete(id);
		taskLogGlueDao.deleteBytaskId(id);
		return ReturnT.SUCCESS;
	}

	@Override
	public ReturnT<String> start(int id) {
		// load and valid
		TaskInfo taskInfo = taskInfoDao.loadById(id);
		if (taskInfo == null) {
			return new ReturnT<String>(ReturnT.FAIL.getCode(), I18nUtil.getString("jobinfo_glue_taskId_unvalid"));
		}

		// valid
		ScheduleTypeEnum scheduleTypeEnum = ScheduleTypeEnum.match(taskInfo.getScheduleType(), ScheduleTypeEnum.NONE);
		if (ScheduleTypeEnum.NONE == scheduleTypeEnum) {
			return new ReturnT<String>(ReturnT.FAIL_CODE, (I18nUtil.getString("schedule_type_none_limit_start")) );
		}

		// next trigger time (5s后生效，避开预读周期)
		long nextTriggerTime = 0;
		try {
			Date nextValidTime = JobScheduleHelper.generateNextValidTime(taskInfo, new Date(System.currentTimeMillis() + JobScheduleHelper.PRE_READ_MS));
			if (nextValidTime == null) {
				return new ReturnT<String>(ReturnT.FAIL_CODE, (I18nUtil.getString("schedule_type")+I18nUtil.getString("system_unvalid")) );
			}
			nextTriggerTime = nextValidTime.getTime();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return new ReturnT<String>(ReturnT.FAIL_CODE, (I18nUtil.getString("schedule_type")+I18nUtil.getString("system_unvalid")) );
		}

		taskInfo.setTriggerStatus(1);
		taskInfo.setTriggerLastTime(0);
		taskInfo.setTriggerNextTime(nextTriggerTime);

		taskInfo.setUpdateTime(new Date());
		taskInfoDao.update(taskInfo);
		return ReturnT.SUCCESS;
	}

	@Override
	public ReturnT<String> stop(int id) {
		// load and valid
        TaskInfo taskInfo = taskInfoDao.loadById(id);
		if (taskInfo == null) {
			return new ReturnT<String>(ReturnT.FAIL.getCode(), I18nUtil.getString("jobinfo_glue_taskId_unvalid"));
		}

		// stop
		taskInfo.setTriggerStatus(0);
		taskInfo.setTriggerLastTime(0);
		taskInfo.setTriggerNextTime(0);

		taskInfo.setUpdateTime(new Date());
		taskInfoDao.update(taskInfo);
		return ReturnT.SUCCESS;
	}



	@Override
	public ReturnT<String> trigger(TaskUser loginUser, int taskId, String executorParam, String addressList) {
		// permission
		if (loginUser == null) {
			return new ReturnT<String>(ReturnT.FAIL.getCode(), I18nUtil.getString("system_permission_limit"));
		}
		TaskInfo taskInfo = taskInfoDao.loadById(taskId);
		if (taskInfo == null) {
			return new ReturnT<String>(ReturnT.FAIL.getCode(), I18nUtil.getString("jobinfo_glue_taskId_unvalid"));
		}
		if (!hasPermission(loginUser, taskInfo.getTaskGroup())) {
			return new ReturnT<String>(ReturnT.FAIL.getCode(), I18nUtil.getString("system_permission_limit"));
		}

		// force cover job param
		if (executorParam == null) {
			executorParam = "";
		}

		JobTriggerPoolHelper.trigger(taskId, TriggerTypeEnum.MANUAL, -1, null, executorParam, addressList);
		return ReturnT.SUCCESS;
	}

	private boolean hasPermission(TaskUser loginUser, int taskGroup){
		if (loginUser.getRole() == 1) {
			return true;
		}
		List<String> groupIdStrs = new ArrayList<>();
		if (loginUser.getPermission()!=null && loginUser.getPermission().trim().length()>0) {
			groupIdStrs = Arrays.asList(loginUser.getPermission().trim().split(","));
		}
		return groupIdStrs.contains(String.valueOf(taskGroup));
	}

	@Override
	public Map<String, Object> dashboardInfo() {

		int jobInfoCount = taskInfoDao.findAllCount();
		int taskLogCount = 0;
		int taskLogSuccessCount = 0;
		TaskLogReport taskLogReport = taskLogReportDao.queryLogReportTotal();
		if (taskLogReport != null) {
			taskLogCount = taskLogReport.getRunningCount() + taskLogReport.getSucCount() + taskLogReport.getFailCount();
			taskLogSuccessCount = taskLogReport.getSucCount();
		}

		// executor count
		Set<String> executorAddressSet = new HashSet<String>();
		List<TaskGroup> groupList = taskGroupDao.findAll();

		if (groupList!=null && !groupList.isEmpty()) {
			for (TaskGroup group: groupList) {
				if (group.getRegistryList()!=null && !group.getRegistryList().isEmpty()) {
					executorAddressSet.addAll(group.getRegistryList());
				}
			}
		}

		int executorCount = executorAddressSet.size();

		Map<String, Object> dashboardMap = new HashMap<String, Object>();
		dashboardMap.put("jobInfoCount", jobInfoCount);
		dashboardMap.put("taskLogCount", taskLogCount);
		dashboardMap.put("taskLogSuccessCount", taskLogSuccessCount);
		dashboardMap.put("executorCount", executorCount);
		return dashboardMap;
	}

	@Override
	public ReturnT<Map<String, Object>> chartInfo(Date startDate, Date endDate) {

		// process
		List<String> triggerDayList = new ArrayList<String>();
		List<Integer> triggerDayCountRunningList = new ArrayList<Integer>();
		List<Integer> triggerDayCountSucList = new ArrayList<Integer>();
		List<Integer> triggerDayCountFailList = new ArrayList<Integer>();
		int triggerCountRunningTotal = 0;
		int triggerCountSucTotal = 0;
		int triggerCountFailTotal = 0;

		List<TaskLogReport> logReportList = taskLogReportDao.queryLogReport(startDate, endDate);

		if (logReportList!=null && logReportList.size()>0) {
			for (TaskLogReport item: logReportList) {
				String day = DateUtil.formatDate(item.getTriggerDay());
				int triggerDayCountRunning = item.getRunningCount();
				int triggerDayCountSuc = item.getSucCount();
				int triggerDayCountFail = item.getFailCount();

				triggerDayList.add(day);
				triggerDayCountRunningList.add(triggerDayCountRunning);
				triggerDayCountSucList.add(triggerDayCountSuc);
				triggerDayCountFailList.add(triggerDayCountFail);

				triggerCountRunningTotal += triggerDayCountRunning;
				triggerCountSucTotal += triggerDayCountSuc;
				triggerCountFailTotal += triggerDayCountFail;
			}
		} else {
			for (int i = -6; i <= 0; i++) {
				triggerDayList.add(DateUtil.formatDate(DateUtil.addDays(new Date(), i)));
				triggerDayCountRunningList.add(0);
				triggerDayCountSucList.add(0);
				triggerDayCountFailList.add(0);
			}
		}

		Map<String, Object> result = new HashMap<String, Object>();
		result.put("triggerDayList", triggerDayList);
		result.put("triggerDayCountRunningList", triggerDayCountRunningList);
		result.put("triggerDayCountSucList", triggerDayCountSucList);
		result.put("triggerDayCountFailList", triggerDayCountFailList);

		result.put("triggerCountRunningTotal", triggerCountRunningTotal);
		result.put("triggerCountSucTotal", triggerCountSucTotal);
		result.put("triggerCountFailTotal", triggerCountFailTotal);

		return new ReturnT<Map<String, Object>>(result);
	}

}

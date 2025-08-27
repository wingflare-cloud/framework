package com.wingflare.task.admin.core.complete;


import com.wingflare.lib.task.biz.model.ReturnT;
import com.wingflare.lib.task.context.TaskContext;
import com.wingflare.task.admin.core.conf.TaskAdminConfig;
import com.wingflare.task.admin.core.model.TaskInfo;
import com.wingflare.task.admin.core.model.TaskLog;
import com.wingflare.task.admin.core.thread.JobTriggerPoolHelper;
import com.wingflare.task.admin.core.trigger.TriggerTypeEnum;
import com.wingflare.task.admin.core.util.I18nUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.MessageFormat;

/**
 * @author xuxueli 2020-10-30 20:43:10
 */
public class TaskCompleter {
    private static final Logger logger = LoggerFactory.getLogger(TaskCompleter.class);

    /**
     * common fresh handle entrance (limit only once)
     *
     * @param taskLog
     * @return
     */
    public static int updateHandleInfoAndFinish(TaskLog taskLog) {

        // finish
        finishJob(taskLog);

        // text最大64kb 避免长度过长
        if (taskLog.getHandleMsg().length() > 15000) {
            taskLog.setHandleMsg( taskLog.getHandleMsg().substring(0, 15000) );
        }

        // fresh handle
        return TaskAdminConfig.getAdminConfig().getTaskLogDao().updateHandleInfo(taskLog);
    }


    /**
     * do somethind to finish job
     */
    private static void finishJob(TaskLog taskLog){

        // 1、handle success, to trigger child job
        String triggerChildMsg = null;
        if (TaskContext.HANDLE_CODE_SUCCESS == taskLog.getHandleCode()) {
            TaskInfo taskInfo = TaskAdminConfig.getAdminConfig().getXxlJobInfoDao().loadById(taskLog.gettaskId());
            if (taskInfo !=null && taskInfo.getChildtaskId()!=null && taskInfo.getChildtaskId().trim().length()>0) {
                triggerChildMsg = "<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>"+ I18nUtil.getString("jobconf_trigger_child_run") +"<<<<<<<<<<< </span><br>";

                String[] childtaskIds = taskInfo.getChildtaskId().split(",");
                for (int i = 0; i < childtaskIds.length; i++) {
                    int childtaskId = (childtaskIds[i]!=null && childtaskIds[i].trim().length()>0 && isNumeric(childtaskIds[i]))?Integer.valueOf(childtaskIds[i]):-1;
                    if (childtaskId > 0) {
                        // valid
                        if (childtaskId == taskLog.gettaskId()) {
                            logger.debug(">>>>>>>>>>> xxl-job, XxlJobCompleter-finishJob ignore childtaskId,  childtaskId {} is self.", childtaskId);
                            continue;
                        }

                        // trigger child job
                        JobTriggerPoolHelper.trigger(childtaskId, TriggerTypeEnum.PARENT, -1, null, null, null);
                        ReturnT<String> triggerChildResult = ReturnT.SUCCESS;

                        // add msg
                        triggerChildMsg += MessageFormat.format(I18nUtil.getString("jobconf_callback_child_msg1"),
                                (i+1),
                                childtaskIds.length,
                                childtaskIds[i],
                                (triggerChildResult.getCode()==ReturnT.SUCCESS_CODE?I18nUtil.getString("system_success"):I18nUtil.getString("system_fail")),
                                triggerChildResult.getMsg());
                    } else {
                        triggerChildMsg += MessageFormat.format(I18nUtil.getString("jobconf_callback_child_msg2"),
                                (i+1),
                                childtaskIds.length,
                                childtaskIds[i]);
                    }
                }

            }
        }

        if (triggerChildMsg != null) {
            taskLog.setHandleMsg( taskLog.getHandleMsg() + triggerChildMsg );
        }

        // 2、fix_delay trigger next
        // on the way

    }

    private static boolean isNumeric(String str){
        try {
            int result = Integer.valueOf(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

}

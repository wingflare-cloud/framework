package com.wingflare.task.admin.core.alarm.impl;


import com.wingflare.lib.task.biz.model.ReturnT;
import com.wingflare.task.admin.core.alarm.TaskAlarm;
import com.wingflare.task.admin.core.conf.TaskAdminConfig;
import com.wingflare.task.admin.core.model.TaskGroup;
import com.wingflare.task.admin.core.model.TaskInfo;
import com.wingflare.task.admin.core.model.TaskLog;
import com.wingflare.task.admin.core.util.I18nUtil;
import jakarta.mail.internet.MimeMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import java.text.MessageFormat;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * job alarm by email
 *
 * @author xuxueli 2020-01-19
 */
@Component
public class EmailTaskAlarm implements TaskAlarm {
    private static Logger logger = LoggerFactory.getLogger(EmailTaskAlarm.class);

    /**
     * fail alarm
     *
     * @param taskLog
     */
    @Override
    public boolean doAlarm(TaskInfo info, TaskLog taskLog){
        boolean alarmResult = true;

        // send monitor email
        if (info!=null && info.getAlarmEmail()!=null && info.getAlarmEmail().trim().length()>0) {

            // alarmContent
            String alarmContent = "Alarm Job LogId=" + taskLog.getId();
            if (taskLog.getTriggerCode() != ReturnT.SUCCESS_CODE) {
                alarmContent += "<br>TriggerMsg=<br>" + taskLog.getTriggerMsg();
            }
            if (taskLog.getHandleCode()>0 && taskLog.getHandleCode() != ReturnT.SUCCESS_CODE) {
                alarmContent += "<br>HandleCode=" + taskLog.getHandleMsg();
            }

            // email info
            TaskGroup group = TaskAdminConfig.getAdminConfig().getXxltaskGroupDao().load(Integer.valueOf(info.getTaskGroup()));
            String personal = I18nUtil.getString("admin_name_full");
            String title = I18nUtil.getString("jobconf_monitor");
            String content = MessageFormat.format(loadEmailJobAlarmTemplate(),
                    group!=null?group.getTitle():"null",
                    info.getId(),
                    info.getTaskDesc(),
                    alarmContent);

            Set<String> emailSet = new HashSet<String>(Arrays.asList(info.getAlarmEmail().split(",")));
            for (String email: emailSet) {

                // make mail
                try {
                    MimeMessage mimeMessage = TaskAdminConfig.getAdminConfig().getMailSender().createMimeMessage();

                    MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
                    helper.setFrom(TaskAdminConfig.getAdminConfig().getEmailFrom(), personal);
                    helper.setTo(email);
                    helper.setSubject(title);
                    helper.setText(content, true);

                    TaskAdminConfig.getAdminConfig().getMailSender().send(mimeMessage);
                } catch (Exception e) {
                    logger.error(">>>>>>>>>>> xxl-job, job fail alarm email send error, taskLogId:{}", taskLog.getId(), e);

                    alarmResult = false;
                }

            }
        }

        return alarmResult;
    }

    /**
     * load email job alarm template
     *
     * @return
     */
    private static final String loadEmailJobAlarmTemplate(){
        String mailBodyTemplate = "<h5>" + I18nUtil.getString("jobconf_monitor_detail") + "：</span>" +
                "<table border=\"1\" cellpadding=\"3\" style=\"border-collapse:collapse; width:80%;\" >\n" +
                "   <thead style=\"font-weight: bold;color: #ffffff;background-color: #ff8c00;\" >" +
                "      <tr>\n" +
                "         <td width=\"20%\" >"+ I18nUtil.getString("jobinfo_field_taskGroup") +"</td>\n" +
                "         <td width=\"10%\" >"+ I18nUtil.getString("jobinfo_field_id") +"</td>\n" +
                "         <td width=\"20%\" >"+ I18nUtil.getString("jobinfo_field_jobdesc") +"</td>\n" +
                "         <td width=\"10%\" >"+ I18nUtil.getString("jobconf_monitor_alarm_title") +"</td>\n" +
                "         <td width=\"40%\" >"+ I18nUtil.getString("jobconf_monitor_alarm_content") +"</td>\n" +
                "      </tr>\n" +
                "   </thead>\n" +
                "   <tbody>\n" +
                "      <tr>\n" +
                "         <td>{0}</td>\n" +
                "         <td>{1}</td>\n" +
                "         <td>{2}</td>\n" +
                "         <td>"+ I18nUtil.getString("jobconf_monitor_alarm_type") +"</td>\n" +
                "         <td>{3}</td>\n" +
                "      </tr>\n" +
                "   </tbody>\n" +
                "</table>";

        return mailBodyTemplate;
    }

}

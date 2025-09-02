package com.wingflare.adapter.alarm.email;


import com.alibaba.fastjson2.JSONObject;
import com.wingflare.api.alarm.AlarmContext;
import com.wingflare.api.alarm.AlarmDrive;
import com.wingflare.api.email.MailAccount;
import com.wingflare.lib.container.DiUtil;
import com.wingflare.lib.email.EmailUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

/**
 * 邮件告警
 */
public class EmailAlarm implements AlarmDrive<AlarmContext> {


    private static final Logger log = LoggerFactory.getLogger(EmailAlarm.class);
    private MailAccount mailAccount;
    private boolean isInt = false;

    @Override
    public String getAlarmType() {
        return "email";
    }

    @Override
    public boolean sendMessage(AlarmContext alarmContext) {
        try {
            initMailAccount();
            String notifyAttribute = alarmContext.getNotifyAttribute();
            AlarmEmailAttribute alarmEmailAttribute = JSONObject.parseObject(notifyAttribute, AlarmEmailAttribute.class);
            String text = alarmContext.getText();
            text = text.replaceAll("\n", "<br/>");

            if (mailAccount != null) {
                EmailUtil.send(mailAccount, alarmEmailAttribute.getTos(), alarmContext.getTitle(), text, true);
            } else {
                EmailUtil.send(alarmEmailAttribute.getTos(), alarmContext.getTitle(), text, true);
            }

        } catch (Exception e) {
            log.error("Sending email message failed:", e);
            return false;
        }

        return true;
    }

    private void initMailAccount() {
        if (!isInt) {
            synchronized (this) {
                isInt = true;
                AlarmEmailProperties alarmEmailProperties = DiUtil.get(AlarmEmailProperties.class);

                if (alarmEmailProperties != null) {
                    mailAccount = new MailAccount();
                    mailAccount.setHost(alarmEmailProperties.getHost());
                    mailAccount.setPort(alarmEmailProperties.getPort());
                    mailAccount.setAuth(Optional.ofNullable(alarmEmailProperties.getAuth()).orElse(Boolean.FALSE));
                    mailAccount.setFrom(alarmEmailProperties.getFrom());
                    mailAccount.setUser(alarmEmailProperties.getUser());
                    mailAccount.setPass(alarmEmailProperties.getPass());
                    mailAccount.setSocketFactoryPort(Optional.ofNullable(alarmEmailProperties.getPort()).orElse(465));
                    mailAccount.setStarttlsEnable(Optional.ofNullable(alarmEmailProperties.getStarttlsEnable()).orElse(Boolean.FALSE));
                    mailAccount.setSslEnable(Optional.ofNullable(alarmEmailProperties.getSslEnable()).orElse(Boolean.FALSE));
                    mailAccount.setTimeout(Optional.ofNullable(alarmEmailProperties.getTimeout()).orElse(0L));
                    mailAccount.setConnectionTimeout(Optional.ofNullable(alarmEmailProperties.getConnectionTimeout()).orElse(0L));

                    if (alarmEmailProperties.getProperties() != null) {
                        alarmEmailProperties.getProperties().forEach(mailAccount::setCustomProperty);
                    }
                }
            }
        }
    }

}

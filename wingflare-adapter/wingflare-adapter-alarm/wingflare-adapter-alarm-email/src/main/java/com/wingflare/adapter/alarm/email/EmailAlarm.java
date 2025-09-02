package com.wingflare.adapter.alarm.email;


import com.alibaba.fastjson2.JSONObject;
import com.wingflare.api.alarm.AlarmContext;
import com.wingflare.api.alarm.AlarmDrive;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Objects;
import java.util.Optional;

/**
 * 邮件告警
 */
public class EmailAlarm implements AlarmDrive<AlarmContext> {


    private static final Logger log = LoggerFactory.getLogger(EmailAlarm.class);
    private final AlarmEmailProperties alarmEmailProperties;
    private MailAccount mailAccount;

    @Override
    public String getAlarmType() {
        return "email";
    }

    @Override
    public boolean sendMessage(AlarmContext alarmContext) {
        if (Objects.isNull(mailAccount)) {
            log.warn("Please check if the email configuration is enabled");
            return false;
        }

        try {
            String notifyAttribute = alarmContext.getNotifyAttribute();
            AlarmEmailAttribute alarmEmailAttribute = JSONObject.parseObject(notifyAttribute, AlarmEmailAttribute.class);
            String text = alarmContext.getText();
            text = text.replaceAll("\n", "<br/>");
            MailUtil.send(mailAccount, alarmEmailAttribute.getTos(), alarmContext.getTitle(), text, true);
        } catch (Exception e) {
            log.error("Sending email message failed:", e);
            return false;
        }

        return true;
    }

    private MailAccount initMailAccount(AlarmEmailProperties alarmEmailProperties) {
        MailAccount account = new MailAccount();
        account.setHost(alarmEmailProperties.getHost());
        account.setPort(alarmEmailProperties.getPort());
        account.setAuth(Optional.ofNullable(alarmEmailProperties.getAuth()).orElse(Boolean.FALSE));
        account.setFrom(alarmEmailProperties.getFrom());
        account.setUser(alarmEmailProperties.getUser());
        account.setPass(alarmEmailProperties.getPass());
        account.setSocketFactoryPort(Optional.ofNullable(alarmEmailProperties.getPort()).orElse(465));
        account.setStarttlsEnable(Optional.ofNullable(alarmEmailProperties.getStarttlsEnable()).orElse(Boolean.FALSE));
        account.setSslEnable(Optional.ofNullable(alarmEmailProperties.getSslEnable()).orElse(Boolean.FALSE));
        account.setTimeout(Optional.ofNullable(alarmEmailProperties.getTimeout()).orElse(0L));
        account.setConnectionTimeout(Optional.ofNullable(alarmEmailProperties.getConnectionTimeout()).orElse(0L));

        if (ObjUtil.isNotEmpty(alarmEmailProperties.getProperties())) {
            alarmEmailProperties.getProperties().forEach(account::setCustomProperty);
        }

        return account;
    }

}

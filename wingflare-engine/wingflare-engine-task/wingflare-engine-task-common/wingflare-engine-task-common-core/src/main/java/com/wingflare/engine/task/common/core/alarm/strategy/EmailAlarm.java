package com.wingflare.engine.task.common.core.alarm.strategy;

import cn.hutool.core.util.ObjUtil;
import com.wingflare.engine.task.common.core.alarm.AlarmContext;
import com.wingflare.engine.task.common.core.alarm.attribute.EmailAttribute;
import com.wingflare.engine.task.common.core.alarm.email.MailAccount;
import com.wingflare.engine.task.common.core.alarm.email.TaskMailProperties;
import com.wingflare.engine.task.common.core.enums.AlarmTypeEnum;
import com.wingflare.engine.task.common.core.util.JsonUtil;
import com.wingflare.engine.task.common.core.util.MailUtils;
import com.wingflare.engine.task.common.log.SnailJobLog;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * @author: opensnail
 * @date : 2021-11-25 09:20
 */
@Component
public class EmailAlarm extends AbstractAlarm<AlarmContext> {

    private final TaskMailProperties taskMailProperties;
    private MailAccount mailAccount;


    public EmailAlarm(TaskMailProperties taskMailProperties) {
        this.taskMailProperties = taskMailProperties;
    }

    @Override
    public Integer getAlarmType() {
        return AlarmTypeEnum.EMAIL.getValue();
    }

    @Override
    public boolean syncSendMessage(AlarmContext alarmContext) {
        if (Objects.isNull(mailAccount)) {
            SnailJobLog.LOCAL.warn("Please check if the email configuration is enabled");
            return false;
        }

        try {
            String notifyAttribute = alarmContext.getNotifyAttribute();
            EmailAttribute emailAttribute = JsonUtil.parseObject(notifyAttribute, EmailAttribute.class);
            String text = alarmContext.getText();
            text = text.replaceAll("\n", "<br/>");
            MailUtils.send(mailAccount, emailAttribute.getTos(), alarmContext.getTitle(), text, true);
        } catch (Exception e) {
            SnailJobLog.LOCAL.error("Sending email message failed:", e);
            return false;
        }

        return true;
    }

    @Override
    public boolean asyncSendMessage(List<AlarmContext> alarmContexts) {
        for (AlarmContext alarmContext : alarmContexts) {
            asyncSendMessage(alarmContext);
        }

        return Boolean.TRUE;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        super.afterPropertiesSet();
        Boolean enabled = taskMailProperties.getEnabled();
        if (Objects.isNull(enabled) || Boolean.FALSE.equals(enabled)) {
            return;
        }

        mailAccount = initMailAccount(taskMailProperties);
        MailUtils.setMailAccount(mailAccount);
    }

    private MailAccount initMailAccount(TaskMailProperties taskMailProperties) {
        MailAccount account = new MailAccount();
        account.setHost(taskMailProperties.getHost());
        account.setPort(taskMailProperties.getPort());
        account.setAuth(Optional.ofNullable(taskMailProperties.getAuth()).orElse(Boolean.FALSE));
        account.setFrom(taskMailProperties.getFrom());
        account.setUser(taskMailProperties.getUser());
        account.setPass(taskMailProperties.getPass());
        account.setSocketFactoryPort(Optional.ofNullable(taskMailProperties.getPort()).orElse(465));
        account.setStarttlsEnable(Optional.ofNullable(taskMailProperties.getStarttlsEnable()).orElse(Boolean.FALSE));
        account.setSslEnable(Optional.ofNullable(taskMailProperties.getSslEnable()).orElse(Boolean.FALSE));
        account.setTimeout(Optional.ofNullable(taskMailProperties.getTimeout()).orElse(0L));
        account.setConnectionTimeout(Optional.ofNullable(taskMailProperties.getConnectionTimeout()).orElse(0L));

        if (ObjUtil.isNotEmpty(taskMailProperties.getProperties())) {
            taskMailProperties.getProperties().forEach(account::setCustomProperty);
        }

        return account;
    }
}


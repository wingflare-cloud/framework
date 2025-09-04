package com.wingflare.engine.task.datasource.template.access;

import com.wingflare.engine.task.datasource.template.access.config.GroupConfigAccess;
import com.wingflare.engine.task.datasource.template.access.config.NotifyConfigAccess;
import com.wingflare.engine.task.datasource.template.access.config.SceneConfigAccess;
import com.wingflare.engine.task.datasource.template.access.job.JobExecutorAccess;
import com.wingflare.engine.task.datasource.template.access.task.RetryDeadLetterTaskAccess;
import com.wingflare.engine.task.datasource.template.access.task.RetryTaskAccess;
import com.wingflare.engine.task.datasource.template.enums.OperationTypeEnum;
import com.wingflare.engine.task.datasource.template.exception.TaskDatasourceException;
import com.wingflare.engine.task.datasource.template.persistence.dataobject.log.JobLogMessageDO;
import com.wingflare.engine.task.datasource.template.persistence.dataobject.log.RetryTaskLogMessageDO;
import com.wingflare.engine.task.datasource.template.persistence.po.GroupConfig;
import com.wingflare.engine.task.datasource.template.persistence.po.JobExecutor;
import com.wingflare.engine.task.datasource.template.persistence.po.NotifyConfig;
import com.wingflare.engine.task.datasource.template.persistence.po.Retry;
import com.wingflare.engine.task.datasource.template.persistence.po.RetryDeadLetter;
import com.wingflare.engine.task.datasource.template.persistence.po.RetrySceneConfig;
import com.wingflare.engine.task.datasource.template.persistence.po.RetryTask;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * 数据处理模板类
 *
 * @author opensnail
 * @date 2023-08-06 09:55:12
 * @since 2.2.0
 */
@Component
public class AccessTemplate {
    protected Map<String, Access> REGISTER_ACCESS = new HashMap<>();

    public AccessTemplate(List<Access> accesses) {

        for (Access access : accesses) {
            for (final OperationTypeEnum typeEnum : OperationTypeEnum.values()) {
                if (access.supports(typeEnum.name())) {
                    REGISTER_ACCESS.put(typeEnum.name(), access);
                    break;
                }
            }
        }
    }

    /**
     * 获取重试任务操作类
     *
     * @return {@link RetryTaskAccess} 重试任务操作类
     */
    public TaskAccess<RetryTask> getRetryTaskAccess() {
        return (TaskAccess<RetryTask>) Optional.ofNullable(REGISTER_ACCESS.get(OperationTypeEnum.RETRY_TASK.name()))
                .orElseThrow(() -> new TaskDatasourceException("not supports operation type"));
    }

    /**
     * 获取重试任务操作类
     *
     * @return {@link RetryTaskAccess} 重试任务操作类
     */
    public TaskAccess<Retry> getRetryAccess() {
        return (TaskAccess<Retry>) Optional.ofNullable(REGISTER_ACCESS.get(OperationTypeEnum.RETRY.name()))
                .orElseThrow(() -> new TaskDatasourceException("not supports operation type"));
    }

    /**
     * 获取死信任务操作类
     *
     * @return {@link RetryDeadLetterTaskAccess} 获取死信任务操作类
     */
    public TaskAccess<RetryDeadLetter> getRetryDeadLetterAccess() {
        return (TaskAccess<RetryDeadLetter>) Optional.ofNullable(
                        REGISTER_ACCESS.get(OperationTypeEnum.RETRY_DEAD_LETTER.name()))
                .orElseThrow(() -> new TaskDatasourceException("not supports operation type"));

    }

    /**
     * 获取场景配置操作类
     *
     * @return {@link SceneConfigAccess} 获取场景配置操作类
     */
    public ConfigAccess<RetrySceneConfig> getSceneConfigAccess() {
        return (ConfigAccess<RetrySceneConfig>) Optional.ofNullable(REGISTER_ACCESS.get(OperationTypeEnum.SCENE.name()))
                .orElseThrow(() -> new TaskDatasourceException("not supports operation type"));

    }

    /**
     * 获取组配置操作类
     *
     * @return {@link GroupConfigAccess} 获取组配置操作类
     */
    public ConfigAccess<GroupConfig> getGroupConfigAccess() {
        return (ConfigAccess<GroupConfig>) Optional.ofNullable(REGISTER_ACCESS.get(OperationTypeEnum.GROUP.name()))
                .orElseThrow(() -> new TaskDatasourceException("not supports operation type"));

    }

    /**
     * 获取通知配置操作类
     *
     * @return {@link NotifyConfigAccess} 获取通知配置操作类
     */
    public ConfigAccess<NotifyConfig> getNotifyConfigAccess() {
        return (ConfigAccess<NotifyConfig>) Optional.ofNullable(REGISTER_ACCESS.get(OperationTypeEnum.NOTIFY.name()))
                .orElseThrow(() -> new TaskDatasourceException("not supports operation type"));

    }

    /**
     * 获取通知配置操作类
     *
     * @return {@link NotifyConfigAccess} 获取通知配置操作类
     */
    public LogAccess<RetryTaskLogMessageDO> getRetryTaskLogMessageAccess() {
        return (LogAccess<RetryTaskLogMessageDO>) Optional.ofNullable(REGISTER_ACCESS.get(OperationTypeEnum.RETRY_LOG.name()))
                .orElseThrow(() -> new TaskDatasourceException("not supports operation type"));

    }


    /**
     * 获取通知配置操作类
     *
     * @return {@link NotifyConfigAccess} 获取通知配置操作类
     */
    public LogAccess<JobLogMessageDO> getJobLogMessageAccess() {
        return (LogAccess<JobLogMessageDO>) Optional.ofNullable(REGISTER_ACCESS.get(OperationTypeEnum.JOB_LOG.name()))
                .orElseThrow(() -> new TaskDatasourceException("not supports operation type"));

    }


    /**
     * 获取定时任务执行器操作类
     *
     * @return {@link JobExecutorAccess} 获取定时任务执行器操作类
     */
    public JobAccess<JobExecutor> getJobExecutorAccess() {
        return (JobAccess<JobExecutor>) Optional.ofNullable(REGISTER_ACCESS.get(OperationTypeEnum.JOB_EXECUTORS.name()))
                .orElseThrow(() -> new TaskDatasourceException("not supports operation type"));

    }

}

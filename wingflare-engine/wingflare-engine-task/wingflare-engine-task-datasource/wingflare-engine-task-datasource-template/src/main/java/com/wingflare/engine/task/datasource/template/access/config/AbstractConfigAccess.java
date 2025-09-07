package com.wingflare.engine.task.datasource.template.access.config;

import cn.hutool.core.collection.CollUtil;
import com.wingflare.engine.task.common.core.enums.JobNotifySceneEnum;
import com.wingflare.engine.task.common.core.enums.NodeTypeEnum;
import com.wingflare.engine.task.common.core.enums.RetryNotifySceneEnum;
import com.wingflare.engine.task.common.core.enums.StatusEnum;
import com.wingflare.engine.task.common.core.util.JsonUtil;
import com.wingflare.engine.task.common.model.request.ConfigRequest;
import com.wingflare.engine.task.datasource.template.access.ConfigAccess;
import com.wingflare.engine.task.datasource.template.enums.DbTypeEnum;
import com.wingflare.engine.task.datasource.template.persistence.mapper.GroupConfigMapper;
import com.wingflare.engine.task.datasource.template.persistence.mapper.NotifyConfigMapper;
import com.wingflare.engine.task.datasource.template.persistence.mapper.NotifyRecipientMapper;
import com.wingflare.engine.task.datasource.template.persistence.mapper.SceneConfigMapper;
import com.wingflare.engine.task.datasource.template.persistence.po.GroupConfig;
import com.wingflare.engine.task.datasource.template.persistence.po.NotifyConfig;
import com.wingflare.engine.task.datasource.template.persistence.po.NotifyRecipient;
import com.wingflare.engine.task.datasource.template.persistence.po.RetrySceneConfig;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import jakarta.annotation.Resource;
import org.springframework.core.env.Environment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 获取配置通道通用模板
 *
 * @author: opensnail
 * @date : 2022-01-05 09:12
 */
public abstract class AbstractConfigAccess<T> implements ConfigAccess<T> {

    @Resource
    protected NotifyConfigMapper notifyConfigMapper;
    @Resource
    protected SceneConfigMapper sceneConfigMapper;
    @Resource
    protected GroupConfigMapper groupConfigMapper;
    @Resource
    protected NotifyRecipientMapper notifyRecipientMapper;
    @Resource
    protected Environment env;

    protected static final List<String> ALLOW_DB = Arrays.asList(
            DbTypeEnum.MYSQL.getDb(),
            DbTypeEnum.MARIADB.getDb(),
            DbTypeEnum.POSTGRES.getDb(),
            DbTypeEnum.ORACLE.getDb(),
            DbTypeEnum.SQLSERVER.getDb(),
            DbTypeEnum.DM.getDb(),
            DbTypeEnum.KINGBASE.getDb());

    protected DbTypeEnum getDbType() {
        String url = env.getProperty("spring.datasource.url");
        return DbTypeEnum.modeOf(url);
    }

    protected RetrySceneConfig getByGroupNameAndSceneName(String groupName, String sceneName, String namespaceId) {
        return sceneConfigMapper.selectOne(new LambdaQueryWrapper<RetrySceneConfig>()
                .eq(RetrySceneConfig::getNamespaceId, namespaceId)
                .eq(RetrySceneConfig::getGroupName, groupName)
                .eq(RetrySceneConfig::getSceneName, sceneName));
    }

    protected List<RetrySceneConfig> getByGroupNameAndSceneNameList(Set<String> groupNames, Set<String> sceneNames, Set<String> namespaceIds) {
        return sceneConfigMapper.selectList(new LambdaQueryWrapper<RetrySceneConfig>()
                .in(RetrySceneConfig::getNamespaceId, namespaceIds)
                .in(RetrySceneConfig::getGroupName, groupNames)
                .in(RetrySceneConfig::getSceneName, sceneNames));
    }

    protected List<RetrySceneConfig> getSceneConfigs(String groupName) {
        return sceneConfigMapper.selectList(new LambdaQueryWrapper<RetrySceneConfig>()
                .eq(RetrySceneConfig::getGroupName, groupName));
    }

    protected GroupConfig getByGroupName(String groupName, final String namespaceId) {
        return groupConfigMapper.selectOne(new LambdaQueryWrapper<GroupConfig>()
                .eq(GroupConfig::getNamespaceId, namespaceId)
                .eq(GroupConfig::getGroupName, groupName));
    }

    protected List<NotifyConfig> getNotifyConfigs(String groupName, String namespaceId) {
        return notifyConfigMapper.selectList(
                new LambdaQueryWrapper<NotifyConfig>()
                        .eq(NotifyConfig::getNamespaceId, namespaceId)
                        .eq(NotifyConfig::getGroupName, groupName)
                        .eq(NotifyConfig::getNotifyStatus, StatusEnum.YES.getStatus())
        );
    }


    @Override
    public GroupConfig getGroupConfigByGroupName(String groupName, String namespaceId) {
        return getByGroupName(groupName, namespaceId);
    }

    @Override
    public RetrySceneConfig getSceneConfigByGroupNameAndSceneName(String groupName, String sceneName, String namespaceId) {
        return getByGroupNameAndSceneName(groupName, sceneName, namespaceId);
    }

    @Override
    public List<RetrySceneConfig> getSceneConfigByGroupNameAndSceneNameList(Set<String> groupNames, Set<String> sceneNames, Set<String> namespaceIds) {
        return getByGroupNameAndSceneNameList(groupNames, sceneNames, namespaceIds);
    }

    @Override
    public List<NotifyConfig> getNotifyListConfigByGroupName(String groupName, String namespaceId) {
        return getNotifyConfigs(groupName, namespaceId);
    }

    @Override
    public List<RetrySceneConfig> getSceneConfigByGroupName(String groupName) {
        return getSceneConfigs(groupName);
    }

    @Override
    public Set<String> getBlacklist(String groupName, String namespaceId) {

        GroupConfig groupConfig = getByGroupName(groupName, namespaceId);
        if (Objects.isNull(groupConfig)) {
            return new HashSet<>();
        }

        LambdaQueryWrapper<RetrySceneConfig> sceneConfigLambdaQueryWrapper = new LambdaQueryWrapper<RetrySceneConfig>()
                .select(RetrySceneConfig::getSceneName)
                .eq(RetrySceneConfig::getGroupName, groupName);

        if (StatusEnum.YES.getStatus().equals(groupConfig.getGroupStatus())) {
            sceneConfigLambdaQueryWrapper.eq(RetrySceneConfig::getSceneStatus, StatusEnum.NO.getStatus());
        }

        List<RetrySceneConfig> retrySceneConfigs = sceneConfigMapper.selectList(sceneConfigLambdaQueryWrapper);
        if (CollUtil.isEmpty(retrySceneConfigs)) {
            return new HashSet<>();
        }

        return retrySceneConfigs.stream().map(RetrySceneConfig::getSceneName).collect(Collectors.toSet());
    }

    @Override
    public List<GroupConfig> getAllConfigGroupList(String namespaceId) {
        List<GroupConfig> allSystemConfigGroupList = groupConfigMapper.selectList(
                new LambdaQueryWrapper<GroupConfig>()
                        .eq(GroupConfig::getNamespaceId, namespaceId)
                        .orderByAsc(GroupConfig::getId));
        if (CollUtil.isEmpty(allSystemConfigGroupList)) {
            return new ArrayList<>();
        }

        return allSystemConfigGroupList;
    }

    @Override
    public List<RetrySceneConfig> getAllConfigSceneList() {
        List<RetrySceneConfig> allSystemConfigSceneList = sceneConfigMapper.selectList(
                new LambdaQueryWrapper<RetrySceneConfig>().orderByAsc(RetrySceneConfig::getId));
        if (CollUtil.isEmpty(allSystemConfigSceneList)) {
            return new ArrayList<>();
        }
        return allSystemConfigSceneList;
    }

    @Override
    public Integer getConfigVersion(String groupName, String namespaceId) {
        GroupConfig groupConfig = getGroupConfigByGroupName(groupName, namespaceId);
        if (Objects.isNull(groupConfig)) {
            return 0;
        }

        return groupConfig.getVersion();
    }

    @Override
    public ConfigRequest getConfigInfo(String groupName, final String namespaceId) {

        ConfigRequest configRequest = new ConfigRequest();
        configRequest.setVersion(getConfigVersion(groupName, namespaceId));

        List<NotifyConfig> notifyList = getNotifyListConfigByGroupName(groupName, namespaceId);

        List<ConfigRequest.Notify> notifies = new ArrayList<>();
        for (NotifyConfig notifyConfig : notifyList) {

            // 只选择客户端的通知配置即可
            RetryNotifySceneEnum retryNotifyScene = RetryNotifySceneEnum.getNotifyScene(notifyConfig.getNotifyScene(),
                    NodeTypeEnum.CLIENT);
            JobNotifySceneEnum jobNotifyScene = JobNotifySceneEnum.getJobNotifyScene(notifyConfig.getNotifyScene(),
                    NodeTypeEnum.CLIENT);
            if (Objects.isNull(retryNotifyScene) && Objects.isNull(jobNotifyScene)) {
                continue;
            }

            String recipientIds = notifyConfig.getRecipientIds();
            List<NotifyRecipient> notifyRecipients = notifyRecipientMapper.selectBatchIds(
                    JsonUtil.parseList(recipientIds, Long.class));
            notifies.add(getNotify(notifyConfig, notifyRecipients, retryNotifyScene, jobNotifyScene));
        }

        configRequest.setNotifyList(notifies);

        List<RetrySceneConfig> retrySceneConfig = getSceneConfigByGroupName(groupName);

        List<ConfigRequest.Scene> sceneList = new ArrayList<>();
        for (RetrySceneConfig config : retrySceneConfig) {
            ConfigRequest.Scene scene = new ConfigRequest.Scene();
            scene.setSceneName(config.getSceneName());
            scene.setDdl(config.getDeadlineRequest());
            sceneList.add(scene);
        }

        configRequest.setSceneList(sceneList);
        return configRequest;
    }

    private static ConfigRequest.Notify getNotify(final NotifyConfig notifyConfig, final List<NotifyRecipient> notifyRecipients,
                                                  final RetryNotifySceneEnum retryNotifyScene, final JobNotifySceneEnum jobNotifyScene) {
        List<ConfigRequest.Notify.Recipient> recipients = new ArrayList<>();
        for (final NotifyRecipient notifyRecipient : notifyRecipients) {
            ConfigRequest.Notify.Recipient recipient = new ConfigRequest.Notify.Recipient();
            recipient.setNotifyAttribute(notifyRecipient.getNotifyAttribute());
            recipient.setNotifyType(notifyRecipient.getNotifyType());
            recipients.add(recipient);
        }

        ConfigRequest.Notify notify = new ConfigRequest.Notify();
        if (Objects.nonNull(retryNotifyScene)) {
            notify.setRetryNotifyScene(retryNotifyScene.getNotifyScene());
        }

        if (Objects.nonNull(jobNotifyScene)) {
            notify.setJobNotifyScene(jobNotifyScene.getNotifyScene());
        }

        notify.setNotifyThreshold(notifyConfig.getNotifyThreshold());
        notify.setRecipients(recipients);
        return notify;
    }
}

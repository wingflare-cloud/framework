package com.wingflare.engine.task.server.web.service.handler;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.lang.Assert;
import com.wingflare.engine.task.common.core.util.StreamUtils;
import com.wingflare.engine.task.server.common.exception.TaskServerException;
import com.wingflare.engine.task.datasource.template.access.AccessTemplate;
import com.wingflare.engine.task.datasource.template.persistence.po.GroupConfig;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.google.common.collect.Sets;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

/**
 * @author: opensnail
 * @date : 2024-05-31
 * @since : sj_1.0.0
 */
@Component
public class GroupHandler {

    private final AccessTemplate accessTemplate;

    public GroupHandler(AccessTemplate accessTemplate) {
        this.accessTemplate = accessTemplate;
    }

    /**
     * 校验组是否存在
     *
     * @param groupNameSet 待校验的组
     * @param namespaceId  空间
     */
    public void validateGroupExistence(Set<String> groupNameSet, String namespaceId) {
        Assert.notEmpty(groupNameSet, () -> new TaskServerException("Group cannot be empty"));
        List<GroupConfig> groupConfigs = accessTemplate.getGroupConfigAccess()
                .list(new LambdaQueryWrapper<GroupConfig>()
                        .select(GroupConfig::getGroupName)
                        .eq(GroupConfig::getNamespaceId, namespaceId)
                        .in(GroupConfig::getGroupName, groupNameSet)
                );

        Set<String> notExistedGroupNameSet = Sets.difference(groupNameSet,
                StreamUtils.toSet(groupConfigs, GroupConfig::getGroupName));

        Assert.isTrue(CollUtil.isEmpty(notExistedGroupNameSet),
                () -> new TaskServerException("Group {} does not exist", notExistedGroupNameSet));
    }

}

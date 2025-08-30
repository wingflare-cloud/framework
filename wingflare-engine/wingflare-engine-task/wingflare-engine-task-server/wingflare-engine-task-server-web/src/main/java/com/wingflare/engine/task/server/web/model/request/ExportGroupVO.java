package com.wingflare.engine.task.server.web.model.request;


import java.util.Set;

/**
 * @author: opensnail
 * @date : 2024-05-29
 * @since : sj_1.0.0
 */
public class ExportGroupVO {

    private String groupName;

    private Integer groupStatus;

    private Set<Long> groupIds;

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public Integer getGroupStatus() {
        return groupStatus;
    }

    public void setGroupStatus(Integer groupStatus) {
        this.groupStatus = groupStatus;
    }

    public Set<Long> getGroupIds() {
        return groupIds;
    }

    public void setGroupIds(Set<Long> groupIds) {
        this.groupIds = groupIds;
    }
}

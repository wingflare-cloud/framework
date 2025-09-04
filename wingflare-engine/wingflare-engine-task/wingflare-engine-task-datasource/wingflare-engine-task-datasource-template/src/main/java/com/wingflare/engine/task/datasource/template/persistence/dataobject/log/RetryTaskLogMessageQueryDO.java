package com.wingflare.engine.task.datasource.template.persistence.dataobject.log;

import com.wingflare.engine.task.datasource.template.persistence.dataobject.common.PageQueryDO;


public class RetryTaskLogMessageQueryDO extends PageQueryDO {
    private String groupName;

    private Long retryTaskId;

    private Long startRealTime;

    private boolean searchCount;

    private String sid;

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public Long getRetryTaskId() {
        return retryTaskId;
    }

    public void setRetryTaskId(Long retryTaskId) {
        this.retryTaskId = retryTaskId;
    }

    public Long getStartRealTime() {
        return startRealTime;
    }

    public void setStartRealTime(Long startRealTime) {
        this.startRealTime = startRealTime;
    }

    public boolean isSearchCount() {
        return searchCount;
    }

    public void setSearchCount(boolean searchCount) {
        this.searchCount = searchCount;
    }

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }
}
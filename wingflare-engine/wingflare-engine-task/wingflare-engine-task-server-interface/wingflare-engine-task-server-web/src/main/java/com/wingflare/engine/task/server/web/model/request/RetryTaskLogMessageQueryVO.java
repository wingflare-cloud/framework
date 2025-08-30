package com.wingflare.engine.task.server.web.model.request;

import com.wingflare.engine.task.server.common.vo.base.BaseQueryVO;

/**
 * @author: opensnail
 * @date : 2022-02-28 09:08
 */
public class RetryTaskLogMessageQueryVO extends BaseQueryVO {

    private String groupName;
    private Long retryTaskId;
    private Long startRealTime;
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

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }
}

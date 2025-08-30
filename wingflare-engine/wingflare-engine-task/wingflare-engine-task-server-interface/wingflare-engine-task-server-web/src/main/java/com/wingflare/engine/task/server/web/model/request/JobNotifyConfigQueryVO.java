package com.wingflare.engine.task.server.web.model.request;

import com.wingflare.engine.task.server.common.vo.base.BaseQueryVO;

/**
 * @author zuoJunLin
 * @date 2023-12-02 11:16:14
 * @since 2.5.0
 */
public class JobNotifyConfigQueryVO extends BaseQueryVO {
    private String groupName;
    private Long jobId;

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public Long getJobId() {
        return jobId;
    }

    public void setJobId(Long jobId) {
        this.jobId = jobId;
    }
}

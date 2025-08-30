package com.wingflare.engine.task.server.web.model.request;

import com.wingflare.engine.task.server.common.vo.base.BaseQueryVO;


/**
 * @author opensnail
 * @date 2023-10-11 22:28:07
 * @since 2.4.0
 */
public class JobExecutorQueryVO extends BaseQueryVO {
    private String groupName;
    private String executorInfo;
    /**
     * 1:java; 2:python; 3:go;
     */
    private String executorType;

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getExecutorInfo() {
        return executorInfo;
    }

    public void setExecutorInfo(String executorInfo) {
        this.executorInfo = executorInfo;
    }

    public String getExecutorType() {
        return executorType;
    }

    public void setExecutorType(String executorType) {
        this.executorType = executorType;
    }
}

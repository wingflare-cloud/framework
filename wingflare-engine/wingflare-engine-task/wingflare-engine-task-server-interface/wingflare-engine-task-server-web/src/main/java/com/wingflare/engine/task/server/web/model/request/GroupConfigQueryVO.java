package com.wingflare.engine.task.server.web.model.request;

import com.wingflare.engine.task.server.common.vo.base.BaseQueryVO;


/**
 * @author: opensnail
 * @date : 2021-11-22 13:45
 */
public class GroupConfigQueryVO extends BaseQueryVO {

    private String groupName;
    private Integer groupStatus;

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
}

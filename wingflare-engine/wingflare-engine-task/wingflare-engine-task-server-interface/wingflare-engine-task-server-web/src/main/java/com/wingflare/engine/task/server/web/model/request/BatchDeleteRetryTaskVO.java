package com.wingflare.engine.task.server.web.model.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.util.List;

/**
 * 批量删除重试数据
 *
 * @author: opensnail
 * @date : 2023-04-30 22:30
 */
public class BatchDeleteRetryTaskVO {

    /**
     * 组名称
     */
    @NotBlank(message = "groupName cannot be null")
    @Pattern(regexp = "^[A-Za-z0-9_-]{1,64}$", message = "Only supports 1~64 characters, including numbers, letters, underscores, and hyphens")
    private String groupName;

    /**
     * 重试表id
     */
    @NotEmpty(message = "At least one item must be selected")
    @Size(max = 100, message = "A maximum of 100 can be deleted")
    private List<Long> ids;

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public List<Long> getIds() {
        return ids;
    }

    public void setIds(List<Long> ids) {
        this.ids = ids;
    }
}

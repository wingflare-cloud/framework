package com.wingflare.engine.task.server.web.model.request;

import jakarta.validation.constraints.NotEmpty;

import java.util.List;

/**
 * 批量删除死信表数据
 *
 * @author: opensnail
 * @date : 2023-04-30 22:30
 */
public class BatchDeleteRetryDeadLetterVO {

    /**
     * 重试表id
     */
    @NotEmpty(message = "At least one item must be selected")
    private List<Long> ids;

    public List<Long> getIds() {
        return ids;
    }

    public void setIds(List<Long> ids) {
        this.ids = ids;
    }
}

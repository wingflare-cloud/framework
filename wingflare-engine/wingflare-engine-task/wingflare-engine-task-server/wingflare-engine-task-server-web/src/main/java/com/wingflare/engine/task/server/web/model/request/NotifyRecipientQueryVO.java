package com.wingflare.engine.task.server.web.model.request;

import com.wingflare.engine.task.server.common.vo.base.BaseQueryVO;

/**
 * @author opensnail
 * @date 2024-04-17 21:26:22
 * @since sj_1.0.0
 */
public class NotifyRecipientQueryVO extends BaseQueryVO {

    private Integer notifyType;

    private String recipientName;

    public Integer getNotifyType() {
        return notifyType;
    }

    public void setNotifyType(Integer notifyType) {
        this.notifyType = notifyType;
    }

    public String getRecipientName() {
        return recipientName;
    }

    public void setRecipientName(String recipientName) {
        this.recipientName = recipientName;
    }
}

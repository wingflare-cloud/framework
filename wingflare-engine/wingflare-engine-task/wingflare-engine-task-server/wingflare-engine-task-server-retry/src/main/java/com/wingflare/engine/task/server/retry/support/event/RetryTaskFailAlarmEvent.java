package com.wingflare.engine.task.server.retry.support.event;

import com.wingflare.api.event.BaseEvent;
import com.wingflare.engine.task.server.retry.dto.RetryTaskFailAlarmEventDTO;

/**
 * 重试任务失败事件
 *
 * @author: zhengweilin
 * @date : 2024-12-13 16:57
 * @since 1.3.0
 */
public class RetryTaskFailAlarmEvent extends BaseEvent {

    private final RetryTaskFailAlarmEventDTO retryTaskFailAlarmEventDTO;

    public RetryTaskFailAlarmEvent(RetryTaskFailAlarmEventDTO retryTaskFailAlarmEventDTO) {
        super(retryTaskFailAlarmEventDTO);
        this.retryTaskFailAlarmEventDTO = retryTaskFailAlarmEventDTO;
    }

    public RetryTaskFailAlarmEventDTO getRetryTaskFailAlarmEventDTO() {
        return retryTaskFailAlarmEventDTO;
    }
}

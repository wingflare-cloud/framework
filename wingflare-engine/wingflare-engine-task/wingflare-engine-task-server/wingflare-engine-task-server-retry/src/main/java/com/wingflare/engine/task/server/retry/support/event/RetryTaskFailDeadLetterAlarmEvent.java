package com.wingflare.engine.task.server.retry.support.event;

import com.wingflare.engine.task.server.retry.dto.RetryTaskFailDeadLetterAlarmEventDTO;
import org.springframework.context.ApplicationEvent;

import java.util.List;

/**
 * 重试任务失败进入死信队列事件
 *
 * @author: zuoJunLin
 * @date : 2023-11-20 21:40
 * @since 2.5.0
 */
public class RetryTaskFailDeadLetterAlarmEvent extends ApplicationEvent {
    public List<RetryTaskFailDeadLetterAlarmEventDTO> getRetryDeadLetters() {
        return retryDeadLetters;
    }

    private List<RetryTaskFailDeadLetterAlarmEventDTO> retryDeadLetters;

    public RetryTaskFailDeadLetterAlarmEvent(List<RetryTaskFailDeadLetterAlarmEventDTO> retryDeadLetters) {
        super(retryDeadLetters);
        this.retryDeadLetters = retryDeadLetters;
    }
}

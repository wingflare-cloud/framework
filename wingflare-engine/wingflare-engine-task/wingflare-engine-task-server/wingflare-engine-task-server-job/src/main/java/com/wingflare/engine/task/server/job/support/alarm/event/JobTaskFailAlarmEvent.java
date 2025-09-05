package com.wingflare.engine.task.server.job.support.alarm.event;

import com.wingflare.api.event.BaseEvent;
import com.wingflare.engine.task.server.job.dto.JobTaskFailAlarmEventDTO;

/**
 * job任务失败事件
 *
 * @author: zuoJunLin
 * @date : 2023-12-02 21:40
 * @since 2.5.0
 */
public class JobTaskFailAlarmEvent extends BaseEvent {

    private JobTaskFailAlarmEventDTO jobTaskFailAlarmEventDTO;

    public JobTaskFailAlarmEvent(JobTaskFailAlarmEventDTO jobTaskFailAlarmEventDTO) {
        super(jobTaskFailAlarmEventDTO);
        this.jobTaskFailAlarmEventDTO = jobTaskFailAlarmEventDTO;
    }

    public void setJobTaskFailAlarmEventDTO(JobTaskFailAlarmEventDTO jobTaskFailAlarmEventDTO) {
        this.jobTaskFailAlarmEventDTO = jobTaskFailAlarmEventDTO;
    }

    public JobTaskFailAlarmEventDTO getJobTaskFailAlarmEventDTO() {
        return jobTaskFailAlarmEventDTO;
    }
}

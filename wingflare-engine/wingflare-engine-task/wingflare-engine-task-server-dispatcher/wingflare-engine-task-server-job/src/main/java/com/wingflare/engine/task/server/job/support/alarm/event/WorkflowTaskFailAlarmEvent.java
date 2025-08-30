package com.wingflare.engine.task.server.job.support.alarm.event;

import com.wingflare.engine.task.server.job.dto.WorkflowTaskFailAlarmEventDTO;
import org.springframework.context.ApplicationEvent;

/**
 * workflow任务失败事件
 *
 * @author: opensnail
 * @date : 2023-12-02 21:40
 * @since sj_1.0.0
 */
public class WorkflowTaskFailAlarmEvent extends ApplicationEvent {

    private WorkflowTaskFailAlarmEventDTO workflowTaskFailAlarmEventDTO;

    public WorkflowTaskFailAlarmEvent(WorkflowTaskFailAlarmEventDTO workflowTaskFailAlarmEventDTO) {
        super(workflowTaskFailAlarmEventDTO);
        this.workflowTaskFailAlarmEventDTO = workflowTaskFailAlarmEventDTO;
    }

    public WorkflowTaskFailAlarmEventDTO getWorkflowTaskFailAlarmEventDTO() {
        return workflowTaskFailAlarmEventDTO;
    }

    public void setWorkflowTaskFailAlarmEventDTO(WorkflowTaskFailAlarmEventDTO workflowTaskFailAlarmEventDTO) {
        this.workflowTaskFailAlarmEventDTO = workflowTaskFailAlarmEventDTO;
    }
}

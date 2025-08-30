package com.wingflare.engine.task.server.service.dto;


/**
 * <p>
 *
 * </p>
 *
 * @author opensnail
 * @date 2025-07-05
 */
public class CalculateNextTriggerAtDTO {
    private Long id;
    private Integer oldResident;
    private Integer newResident;
    private Integer triggerType;
    private String triggerInterval;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getOldResident() {
        return oldResident;
    }

    public void setOldResident(Integer oldResident) {
        this.oldResident = oldResident;
    }

    public Integer getNewResident() {
        return newResident;
    }

    public void setNewResident(Integer newResident) {
        this.newResident = newResident;
    }

    public Integer getTriggerType() {
        return triggerType;
    }

    public void setTriggerType(Integer triggerType) {
        this.triggerType = triggerType;
    }

    public String getTriggerInterval() {
        return triggerInterval;
    }

    public void setTriggerInterval(String triggerInterval) {
        this.triggerInterval = triggerInterval;
    }
}

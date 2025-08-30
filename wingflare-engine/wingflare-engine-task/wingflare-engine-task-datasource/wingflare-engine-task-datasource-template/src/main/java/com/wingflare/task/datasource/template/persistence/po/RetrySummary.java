package com.wingflare.task.datasource.template.persistence.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.time.LocalDateTime;

/**
 * @author zhengweilin
 * @version 2.6.0
 * @date 2023/11/28
 */
@TableName("sj_retry_summary")
public class RetrySummary extends CreateUpdateDt {

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 命名空间
     */
    private String namespaceId;

    /**
     * 组名
     */
    private String groupName;

    /**
     * 调度时间
     */
    private LocalDateTime triggerAt;

    /**
     * 场景名称
     */
    private String sceneName;

    /**
     * 运行数量
     */
    private Integer runningNum;

    /**
     * 完成数量
     */
    private Integer finishNum;

    /**
     * 最大重试数量
     */
    private Integer maxCountNum;

    /**
     * 暂停
     */
    private Integer suspendNum;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNamespaceId() {
        return namespaceId;
    }

    public void setNamespaceId(String namespaceId) {
        this.namespaceId = namespaceId;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public LocalDateTime getTriggerAt() {
        return triggerAt;
    }

    public void setTriggerAt(LocalDateTime triggerAt) {
        this.triggerAt = triggerAt;
    }

    public String getSceneName() {
        return sceneName;
    }

    public void setSceneName(String sceneName) {
        this.sceneName = sceneName;
    }

    public Integer getRunningNum() {
        return runningNum;
    }

    public void setRunningNum(Integer runningNum) {
        this.runningNum = runningNum;
    }

    public Integer getFinishNum() {
        return finishNum;
    }

    public void setFinishNum(Integer finishNum) {
        this.finishNum = finishNum;
    }

    public Integer getMaxCountNum() {
        return maxCountNum;
    }

    public void setMaxCountNum(Integer maxCountNum) {
        this.maxCountNum = maxCountNum;
    }

    public Integer getSuspendNum() {
        return suspendNum;
    }

    public void setSuspendNum(Integer suspendNum) {
        this.suspendNum = suspendNum;
    }
}

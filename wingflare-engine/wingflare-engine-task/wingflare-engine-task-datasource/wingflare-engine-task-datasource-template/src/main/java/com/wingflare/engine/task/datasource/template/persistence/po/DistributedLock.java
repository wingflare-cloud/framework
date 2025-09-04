package com.wingflare.engine.task.datasource.template.persistence.po;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.time.LocalDateTime;

/**
 * 锁定表
 *
 * @author opensnail
 * @since 2023-07-20
 */
@TableName("wf_task_distributed_lock")
public class DistributedLock extends CreateUpdateDt {

    /**
     * 锁名称
     */
    @TableId(value = "name")
    private String name;

    /**
     * 锁定时长
     */
    private LocalDateTime lockUntil;

    /**
     * 锁定时间
     */
    private LocalDateTime lockedAt;

    /**
     * 锁定者
     */
    private String lockedBy;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getLockUntil() {
        return lockUntil;
    }

    public void setLockUntil(LocalDateTime lockUntil) {
        this.lockUntil = lockUntil;
    }

    public LocalDateTime getLockedAt() {
        return lockedAt;
    }

    public void setLockedAt(LocalDateTime lockedAt) {
        this.lockedAt = lockedAt;
    }

    public String getLockedBy() {
        return lockedBy;
    }

    public void setLockedBy(String lockedBy) {
        this.lockedBy = lockedBy;
    }
}

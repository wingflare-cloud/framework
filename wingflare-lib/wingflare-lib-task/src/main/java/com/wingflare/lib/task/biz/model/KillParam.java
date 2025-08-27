package com.wingflare.lib.task.biz.model;

import java.io.Serializable;

/**
 * @author xuxueli 2020-04-11 22:27
 */
public class KillParam implements Serializable {
    private static final long serialVersionUID = 42L;

    public KillParam() {
    }
    public KillParam(int taskId) {
        this.taskId = taskId;
    }

    private int taskId;


    public int gettaskId() {
        return taskId;
    }

    public void settaskId(int taskId) {
        this.taskId = taskId;
    }

}
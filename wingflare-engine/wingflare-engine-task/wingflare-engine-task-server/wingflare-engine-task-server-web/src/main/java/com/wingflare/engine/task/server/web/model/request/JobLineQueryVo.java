package com.wingflare.engine.task.server.web.model.request;

import com.wingflare.engine.task.server.common.enums.SystemModeEnum;


public class JobLineQueryVo extends LineQueryVO {
    /**
     * 系统模式
     *
     * @see SystemModeEnum
     */
    private String mode;

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }
}

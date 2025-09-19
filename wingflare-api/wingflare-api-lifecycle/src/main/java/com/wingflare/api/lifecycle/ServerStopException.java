package com.wingflare.api.lifecycle;


/**
 * 服务停止异常
 */
public class ServerStopException extends RuntimeException {

    private Lifecycle lifecycle;

    public ServerStopException(String message) {
        super(message);
    }

    public Lifecycle getLifecycle() {
        return lifecycle;
    }

    public void setLifecycle(Lifecycle lifecycle) {
        this.lifecycle = lifecycle;
    }
}

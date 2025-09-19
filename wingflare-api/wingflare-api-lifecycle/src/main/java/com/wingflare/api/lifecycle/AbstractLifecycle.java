package com.wingflare.api.lifecycle;


/**
 * 生命周期组件抽象基类
 */
public abstract class AbstractLifecycle implements Lifecycle {

    private volatile LifecycleStatus status = LifecycleStatus.UNRUN;

    @Override
    public final void start() {
        if (status != LifecycleStatus.UNRUN) {
            return;
        }

        try {
            status = LifecycleStatus.STARTING;
            doStart();
            status = LifecycleStatus.RUNNING;
        } catch (Exception e) {
            status = LifecycleStatus.UNRUN;
            throw new RuntimeException("组件启动失败: " + getClass().getName(), e);
        }
    }

    @Override
    public final void close() {
        if (status != LifecycleStatus.RUNNING) {
            return;
        }

        try {
            status = LifecycleStatus.DESTROYING;
            doClose();
            status = LifecycleStatus.DESTROYED;
        } catch (Exception e) {
            status = LifecycleStatus.RUNNING;
            throw new RuntimeException("组件关闭失败: " + getClass().getName(), e);
        }
    }

    @Override
    public LifecycleStatus getStatus() {
        return status;
    }

    /**
     * 实际启动逻辑，由子类实现
     */
    protected abstract void doStart();

    /**
     * 实际关闭逻辑，由子类实现
     */
    protected abstract void doClose();
}

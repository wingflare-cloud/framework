package com.wingflare.engine.task.server.retry.support.context;

import com.wingflare.engine.task.server.common.WaitStrategy;
import com.wingflare.engine.task.server.common.dto.RegisterNodeInfo;
import com.wingflare.engine.task.server.retry.support.RetryContext;
import com.wingflare.engine.task.datasource.template.persistence.po.Retry;
import com.wingflare.engine.task.datasource.template.persistence.po.RetrySceneConfig;

import java.util.Objects;
import java.util.Set;

/**
 * 持续重试上下文
 *
 * @author: opensnail
 * @date : 2021-11-29 18:32
 */
public class MaxAttemptsPersistenceRetryContext<V> implements RetryContext<V> {

    /**
     * 通知客户端回调结果
     */
    private V callResult;

    /**
     * 异常信息
     */
    private Exception exception;

    /**
     * 等待策略
     */
    private WaitStrategy waitStrategy;

    /**
     * 当前重试数据
     */
    private Retry retry;

    /**
     * 目前处理关闭的场景
     */
    private Set<String> sceneBlacklist;

    /**
     * 需要调度的节点
     */
    private RegisterNodeInfo serverNode;

    /**
     * 场景配置
     */
    private RetrySceneConfig retrySceneConfig;

    @Override
    public V getCallResult() {
        return callResult;
    }

    @Override
    public WaitStrategy getWaitStrategy() {
        return waitStrategy;
    }

    @Override
    public void setWaitStrategy(WaitStrategy waitStrategy) {
        this.waitStrategy = waitStrategy;
    }

    public Retry getRetry() {
        return retry;
    }

    public void setRetry(Retry retry) {
        this.retry = retry;
    }

    @Override
    public Set<String> getSceneBlacklist() {
        return sceneBlacklist;
    }

    public void setSceneBlacklist(Set<String> sceneBlacklist) {
        this.sceneBlacklist = sceneBlacklist;
    }

    @Override
    public RegisterNodeInfo getServerNode() {
        return serverNode;
    }

    public void setServerNode(RegisterNodeInfo serverNode) {
        this.serverNode = serverNode;
    }

    public RetrySceneConfig getRetrySceneConfig() {
        return retrySceneConfig;
    }

    public void setRetrySceneConfig(RetrySceneConfig retrySceneConfig) {
        this.retrySceneConfig = retrySceneConfig;
    }

    @Override
    public Retry getRetryTask() {
        return null;
    }

    @Override
    public void setCallResult(V v) {
        this.callResult = v;
    }

    @Override
    public void setException(Exception e) {
        this.exception = e;
    }

    @Override
    public boolean hasException() {
        return Objects.nonNull(exception);
    }

    @Override
    public Exception getException() {
        return exception;
    }

    @Override
    public RetrySceneConfig sceneConfig() {
        return retrySceneConfig;
    }

}

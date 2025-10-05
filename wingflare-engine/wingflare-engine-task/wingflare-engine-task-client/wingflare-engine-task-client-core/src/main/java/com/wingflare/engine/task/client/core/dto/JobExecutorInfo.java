package com.wingflare.engine.task.client.core.dto;


import java.lang.reflect.Method;
import java.util.Map;

/**
 * @author opensnail
 * @date 2023-09-27 22:34:29
 * @since 2.4.0
 */
public class JobExecutorInfo {

    private final String executorName;

    private final Method method;

    private final Map<String, Method> mapExecutorMap;

    Method reduceExecutor;

    Method mergeReduceExecutor;

    private Object executor;

    public JobExecutorInfo(String executorName, Method method, Map<String, Method> mapExecutorMap,
                           Method reduceExecutor, Method mergeReduceExecutor, Object executor) {
        this.executorName = executorName;
        this.method = method;
        this.mapExecutorMap = mapExecutorMap;
        this.reduceExecutor = reduceExecutor;
        this.mergeReduceExecutor = mergeReduceExecutor;
        this.executor = executor;
    }

    public String getExecutorName() {
        return executorName;
    }

    public Method getMethod() {
        return method;
    }

    public Map<String, Method> getMapExecutorMap() {
        return mapExecutorMap;
    }

    public Method getReduceExecutor() {
        return reduceExecutor;
    }

    public void setReduceExecutor(Method reduceExecutor) {
        this.reduceExecutor = reduceExecutor;
    }

    public Method getMergeReduceExecutor() {
        return mergeReduceExecutor;
    }

    public void setMergeReduceExecutor(Method mergeReduceExecutor) {
        this.mergeReduceExecutor = mergeReduceExecutor;
    }

    public Object getExecutor() {
        return executor;
    }

    public void setExecutor(Object executor) {
        this.executor = executor;
    }
}

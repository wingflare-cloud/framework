package com.wingflare.engine.task.client.core.register;


import com.wingflare.api.lifecycle.Lifecycle;
import com.wingflare.engine.task.client.common.RpcClient;
import com.wingflare.engine.task.client.common.exception.TaskClientException;
import com.wingflare.engine.task.client.common.rpc.client.RequestBuilder;
import com.wingflare.engine.task.client.core.Scanner;
import com.wingflare.engine.task.client.core.cache.JobExecutorInfoCache;
import com.wingflare.engine.task.client.core.dto.JobExecutorInfo;
import com.wingflare.engine.task.common.core.enums.StatusEnum;
import com.wingflare.engine.task.common.core.model.TaskRpcResult;
import com.wingflare.engine.task.common.log.TaskEngineLog;
import com.wingflare.engine.task.common.model.request.JobExecutorRequest;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author: opensnail
 * @date : 2022-02-10 09:12
 */
@Component
public class JobExecutorRegistrar implements Lifecycle {
    private final List<Scanner> scanners;
    private final List<JobExecutorRequest> contextList = new ArrayList<>();
    public static RpcClient CLIENT;
    // 存储需要使用 endsWith 判断的后缀
    private static final Set<String> END_WITH_SET = new HashSet<>(Arrays.asList(
            "AnnotationJobExecutor",
            "AnnotationMapJobExecutor",
            "AnnotationMapReduceJobExecutor"
    ));

    // 存储需要使用 equals 判断的字符串
    private static final Set<String> EQUALS_SET = new HashSet<>(Arrays.asList(
            "CMDJobExecutor",
            "HttpExecutor",
            "PowerShellJobExecutor",
            "ShellJobExecutor"
    ));

    public JobExecutorRegistrar(List<Scanner> scanners) {
        this.scanners = scanners;
    }

    public void registerRetryHandler(JobExecutorInfo jobExecutorInfo) {
        String executorName = jobExecutorInfo.getExecutorName();
        if (JobExecutorInfoCache.isExisted(executorName)) {
            throw new TaskClientException("Duplicate executor names are not allowed: {}", executorName);
        }
        JobExecutorInfoCache.put(jobExecutorInfo);

        // 排除内部注解执行器
        for (String suffix : END_WITH_SET) {
            if (executorName.endsWith(suffix)) {
                return;
            }
        }

        if (EQUALS_SET.contains(executorName)) {
            return;
        }
        JobExecutorRequest jobExecutorRequest = new JobExecutorRequest();
        jobExecutorRequest.setExecutorInfo(executorName);
        contextList.add(jobExecutorRequest);
    }

    public void registerRetryHandler(List<JobExecutorInfo> contextList) {
        for (JobExecutorInfo jobExecutorInfo : contextList) {
            registerRetryHandler(jobExecutorInfo);
        }
    }

    @Override
    public void start() {
        CLIENT = RequestBuilder.<RpcClient, TaskRpcResult>newBuilder()
                .client(RpcClient.class)
                .callback(
                        rpcResult -> {
                            if (StatusEnum.NO.getStatus().equals(rpcResult.getStatus())) {
                                TaskEngineLog.LOCAL.error("Job executors register error requestId:[{}] message:[{}]", rpcResult.getReqId(), rpcResult.getMessage());
                            }
                        })
                .build();
        for (Scanner scanner : scanners) {
            this.registerRetryHandler(scanner.doScan());
        }
        // 推送当前执行器至服务器
        // 需要获取当前执行器的group及ns
        try {
            if (!contextList.isEmpty()) {
                CLIENT.registryExecutors(contextList);
            }
        }catch (Exception e){
            TaskEngineLog.LOCAL.error("Job executors register error", e);
        }
    }

    @Override
    public void close() {
    }
}

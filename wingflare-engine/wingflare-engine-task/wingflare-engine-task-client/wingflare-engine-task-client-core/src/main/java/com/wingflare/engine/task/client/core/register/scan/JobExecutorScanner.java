package com.wingflare.engine.task.client.core.register.scan;


import cn.hutool.core.collection.CollUtil;
import com.wingflare.engine.task.client.core.IJobExecutor;
import com.wingflare.engine.task.client.core.Scanner;
import com.wingflare.api.task.annotation.TaskExecutor;
import com.wingflare.api.task.annotation.MapExecutor;
import com.wingflare.api.task.annotation.MergeReduceExecutor;
import com.wingflare.api.task.annotation.ReduceExecutor;
import com.wingflare.engine.task.client.core.cache.JobExecutorInfoCache;
import com.wingflare.engine.task.client.core.dto.*;
import com.wingflare.engine.task.common.log.TaskEngineLog;
import com.google.common.collect.Lists;
import org.springframework.aop.framework.AopProxyUtils;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.MethodIntrospector;
import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @author: opensnail
 * @date : 2023-09-27 16:55
 */
@Component
public class JobExecutorScanner implements Scanner, ApplicationContextAware {

    public ApplicationContext applicationContext;

    @Override
    public List<JobExecutorInfo> doScan() {
        return scanJobExecutor();
    }

    private List<JobExecutorInfo> scanJobExecutor() {

        List<JobExecutorInfo> jobExecutorInfoList = new ArrayList<>();
        String[] beanDefinitionNames = applicationContext.getBeanNamesForType(Object.class, false, true);
        for (String beanDefinitionName : beanDefinitionNames) {
            Object bean = applicationContext.getBean(beanDefinitionName);

            Map<Method, TaskExecutor> annotatedMethods = null;
            try {
                annotatedMethods = MethodIntrospector.selectMethods(bean.getClass(),
                        (MethodIntrospector.MetadataLookup<TaskExecutor>) method -> AnnotatedElementUtils
                                .findMergedAnnotation(method, TaskExecutor.class));
            } catch (Throwable ex) {
                TaskEngineLog.LOCAL.error("TaskExecutor load exception for {}: {}", beanDefinitionName, ex);
            }

            Class executorNotProxy = AopProxyUtils.ultimateTargetClass(bean);
            String executorClassName = executorNotProxy.getName();

            // 通过实现接口进行注册
            if (IJobExecutor.class.isAssignableFrom(bean.getClass())) {
                if (!JobExecutorInfoCache.isExisted(executorClassName)) {
                    jobExecutorInfoList.add(new JobExecutorInfo(executorClassName,
                            ReflectionUtils.findMethod(bean.getClass(), "jobExecute"),
                            null, null, null, bean));
                }
            }

            // 扫描类的注解
            TaskExecutor taskExecutor = bean.getClass().getAnnotation(TaskExecutor.class);
            if (Objects.nonNull(taskExecutor)) {
                String executorName = taskExecutor.name();
                if (!JobExecutorInfoCache.isExisted(executorName)) {
                    List<Class<? extends JobArgs>> classes = Lists.newArrayList(ShardingJobArgs.class, JobArgs.class);
                    Method method = null;
                    for (Class<? extends JobArgs> clazz : classes) {
                        method = ReflectionUtils.findMethod(bean.getClass(), taskExecutor.method(), clazz);
                        if (Objects.nonNull(method)) {
                            break;
                        }
                    }

                    if (method == null) {
                        method = ReflectionUtils.findMethod(bean.getClass(), taskExecutor.method());
                    }

                    // 扫描MapExecutor、ReduceExecutor、MergeReduceExecutor注解
                    Map<String, Method> mapExecutorMethodMap = new HashMap<>();
                    Method reduceExecutor = null;
                    Method mergeReduceExecutor = null;
                    Method[] methods = bean.getClass().getMethods();
                    for (final Method method1 : methods) {
                        Class<?>[] parameterTypes = method1.getParameterTypes();
                        MapExecutor mapExecutor = method1.getAnnotation(MapExecutor.class);
                        if (Objects.nonNull(mapExecutor)
                                && parameterTypes.length > 0
                                && parameterTypes[0].isAssignableFrom(MapArgs.class)) {
                            mapExecutorMethodMap.put(mapExecutor.taskName(), method1);
                        }

                        ReduceExecutor reduceExecutorAnno = method1.getAnnotation(ReduceExecutor.class);
                        if (Objects.nonNull(reduceExecutorAnno)
                                && parameterTypes.length > 0
                                && parameterTypes[0].isAssignableFrom(ReduceArgs.class)) {
                            reduceExecutor = method1;
                            continue;
                        }

                        MergeReduceExecutor mergeReduceExecutorAnno = method1.getAnnotation(MergeReduceExecutor.class);
                        if (Objects.nonNull(mergeReduceExecutorAnno)
                                && parameterTypes.length > 0
                                && parameterTypes[0].isAssignableFrom(MergeReduceArgs.class)) {
                            mergeReduceExecutor = method1;
                        }
                    }

                    JobExecutorInfo jobExecutorInfo =
                            new JobExecutorInfo(
                                    executorName,
                                    method,
                                    mapExecutorMethodMap,
                                    reduceExecutor,
                                    mergeReduceExecutor,
                                    bean
                            );
                    jobExecutorInfoList.add(jobExecutorInfo);
                }

            }

            if (CollUtil.isEmpty(annotatedMethods)) {
                continue;
            }

            // 扫描方法上的注解
            for (Map.Entry<Method, TaskExecutor> methodEntry : annotatedMethods.entrySet()) {
                Method executeMethod = methodEntry.getKey();
                taskExecutor = methodEntry.getValue();
                if (JobExecutorInfoCache.isExisted(taskExecutor.name())) {
                    continue;
                }

                JobExecutorInfo jobExecutorInfo =
                        new JobExecutorInfo(
                                taskExecutor.name(),
                                executeMethod,
                                null, null, null,
                                bean
                        );
                jobExecutorInfoList.add(jobExecutorInfo);
            }
        }

        return jobExecutorInfoList;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}

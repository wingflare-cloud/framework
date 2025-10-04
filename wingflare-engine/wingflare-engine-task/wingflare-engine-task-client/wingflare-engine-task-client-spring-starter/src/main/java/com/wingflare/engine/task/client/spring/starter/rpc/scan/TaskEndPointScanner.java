package com.wingflare.engine.task.client.spring.starter.rpc.scan;


import com.wingflare.engine.task.client.common.annotation.Mapping;
import com.wingflare.engine.task.client.common.annotation.TaskEndPoint;
import com.wingflare.engine.task.client.common.rpc.supports.scan.EndPointInfo;
import com.wingflare.engine.task.common.log.TaskEngineLog;
import org.springframework.aop.framework.AopProxyUtils;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.MethodIntrospector;
import org.springframework.core.annotation.AnnotatedElementUtils;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @author opensnail
 * @date 2024-04-11 22:29:07
 * @since 3.3.0
 */
public class TaskEndPointScanner implements ApplicationContextAware {

    private ApplicationContext context;

    public List<EndPointInfo> doScan() {
        return scanEndPoint();
    }

    private List<EndPointInfo> scanEndPoint() {

        List<EndPointInfo> endPointInfoList = new ArrayList<>();
        String[] beanDefinitionNames = context.getBeanNamesForType(Object.class, false, true);
        for (String beanDefinitionName : beanDefinitionNames) {
            Object bean = context.getBean(beanDefinitionName);
            Class executorNotProxy = AopProxyUtils.ultimateTargetClass(bean);
            String executorClassName = executorNotProxy.getName();

            // 扫描类的注解
            TaskEndPoint jobExecutor = (TaskEndPoint) executorNotProxy.getAnnotation(TaskEndPoint.class);

            if (Objects.nonNull(jobExecutor)) {
                Map<Method, Mapping> annotatedMethods = null;
                try {
                    annotatedMethods = MethodIntrospector.selectMethods(bean.getClass(),
                            (MethodIntrospector.MetadataLookup<Mapping>) method -> AnnotatedElementUtils
                                    .findMergedAnnotation(method, Mapping.class));
                } catch (Throwable ex) {
                    TaskEngineLog.LOCAL.error("Mapping load exception for {}: {}", beanDefinitionName, ex);
                }

                for (Map.Entry<Method, Mapping> entry : annotatedMethods.entrySet()) {
                    Method method = entry.getKey();
                    Mapping mapping = entry.getValue();
                    endPointInfoList.add(new EndPointInfo(executorClassName, method, bean, mapping.method(), mapping.path()));
                }
            }

        }

        return endPointInfoList;
    }

    @Override
    public void setApplicationContext(ApplicationContext context) throws BeansException {
        this.context = context;
    }
}

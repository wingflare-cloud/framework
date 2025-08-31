package com.wingflare.engine.task.client.retry.core.intercepter;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import com.wingflare.engine.task.client.common.cache.GroupVersionCache;
import com.wingflare.engine.task.client.common.config.TaskProperties;
import com.wingflare.engine.task.client.retry.core.MethodResult;
import com.wingflare.engine.task.client.retry.core.RetryCondition;
import com.wingflare.engine.task.client.retry.core.annotation.Propagation;
import com.wingflare.engine.task.client.retry.core.annotation.Retryable;
import com.wingflare.engine.task.client.retry.core.cache.RetryerInfoCache;
import com.wingflare.engine.task.client.retry.core.retryer.RetryerInfo;
import com.wingflare.engine.task.client.retry.core.retryer.RetryerResultContext;
import com.wingflare.engine.task.client.retry.core.strategy.RetryStrategy;
import com.wingflare.engine.task.common.core.alarm.AlarmContext;
import com.wingflare.engine.task.common.core.alarm.TaskAlarmFactory;
import com.wingflare.engine.task.common.core.context.SnailSpringContext;
import com.wingflare.engine.task.common.core.enums.RetryNotifySceneEnum;
import com.wingflare.engine.task.common.core.enums.RetryResultStatusEnum;
import com.wingflare.engine.task.common.core.model.TaskHeaders;
import com.wingflare.engine.task.common.core.util.EnvironmentUtils;
import com.wingflare.engine.task.common.core.util.NetUtil;
import com.wingflare.engine.task.common.log.TaskEngineLog;
import com.wingflare.engine.task.common.model.request.ConfigRequest;
import com.wingflare.engine.task.common.model.request.ConfigRequest.Notify.Recipient;
import com.google.common.base.Defaults;
import com.google.common.collect.Lists;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.apache.commons.lang.ClassUtils;
import org.springframework.aop.AfterAdvice;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.AnnotatedElementUtils;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

import static com.wingflare.engine.task.common.core.constant.SystemConstants.YYYY_MM_DD_HH_MM_SS;

/**
 * @author opensnail
 * @date 2023-08-23
 */
public class TaskRetryInterceptor implements MethodInterceptor, AfterAdvice, Serializable, Ordered {

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern(YYYY_MM_DD_HH_MM_SS);
    private static final String retryErrorMoreThresholdTextMessageFormatter =
            "<font face=\"微软雅黑\" color=#ff0000 size=4>{}环境 重试组件异常</font>  \n" +
                    "> IP:{}  \n" +
                    "> 空间ID:{}  \n" +
                    "> 名称:{}  \n" +
                    "> 时间:{}  \n" +
                    "> 异常:{}  \n";

    private final RetryStrategy retryStrategy;
    private final int order;

    public TaskRetryInterceptor(int order, RetryStrategy localRetryStrategies) {
        this.order = order;
        this.retryStrategy = localRetryStrategies;
    }

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {

        String traceId = UUID.randomUUID().toString();

        TaskEngineLog.LOCAL.debug("Start entering the around method traceId:[{}]", traceId);
        Retryable retryable = getAnnotationParameter(invocation.getMethod());
        String executorClassName = invocation.getThis().getClass().getName();
        String methodEntrance = getMethodEntrance(retryable, executorClassName);

        if (Propagation.REQUIRES_NEW.equals(retryable.propagation())) {
            // 如果已经是入口了就不需要继续添加入口了
            if (!RetrySiteSnapshot.isMethodEntrance(methodEntrance)) {
                // 这里需要挂起外部重试的内存的信息
                if (RetrySiteSnapshot.isRunning()
                        && RetrySiteSnapshot.getStage() == RetrySiteSnapshot.EnumStage.LOCAL.getStage()) {
                    RetrySiteSnapshot.suspend();
                    // 清除线程信息
                    RetrySiteSnapshot.removeAll();
                }
                // 设置新的内容信息
                RetrySiteSnapshot.setMethodEntrance(methodEntrance);
            }
        } else if (!RetrySiteSnapshot.existedMethodEntrance()) {
            RetrySiteSnapshot.setMethodEntrance(methodEntrance);
        } else {
            TaskEngineLog.LOCAL.debug("No need to set entrance signs:[{}]", traceId);
        }

        Throwable throwable = null;
        Object result = null;
        RetryerResultContext retryerResultContext;
        try {
            result = invocation.proceed();
        } catch (Throwable t) {
            throwable = t;
        } finally {

            TaskEngineLog.LOCAL.debug("Start retrying. traceId:[{}] scene:[{}] executorClassName:[{}]", traceId,
                    retryable.scene(), executorClassName);
            // 入口则开始处理重试
            retryerResultContext = doHandlerRetry(invocation, traceId, retryable, executorClassName, methodEntrance,
                    throwable, result);
        }

        TaskEngineLog.LOCAL.debug("Method return value is [{}]. traceId:[{}]", result, traceId, throwable);

        // 若是重试完成了, 则判断是否返回重试完成后的数据
        if (Objects.nonNull(retryerResultContext)) {
            // 重试成功直接返回结果
            if (retryerResultContext.getRetryResultStatusEnum().getStatus()
                    .equals(RetryResultStatusEnum.SUCCESS.getStatus())) {
                return retryerResultContext.getResult();
            }
            // 若注解配置了isThrowException=false 则不抛出异常, 必须存在异常的情况
            else if (!retryable.isThrowException() && Objects.nonNull(throwable)) {
                return retryFailHandle(invocation, retryable, executorClassName, throwable, traceId, retryerResultContext);
            }
        }

        // 无需开启重试的场景，需要清除缓存信息
        if ((RetrySiteSnapshot.isMethodEntrance(methodEntrance) && !RetrySiteSnapshot.isRunning())) {
            RetrySiteSnapshot.removeSuspend();
            RetrySiteSnapshot.removeAll();
        }

        if (throwable != null) {
            throw throwable;
        } else {
            return result;
        }

    }

    private boolean retryIfResult(Object result, Retryable retryable, String traceId, String executorClassName) {
        try {
            Class<? extends RetryCondition> retryConditionClass = retryable.retryIfResult();
            if (Objects.nonNull(retryConditionClass) && !retryConditionClass.isAssignableFrom(RetryCondition.NoRetry.class)) {
                RetryCondition retryCondition = retryConditionClass.getDeclaredConstructor().newInstance();
                return retryCondition.shouldRetry(result);
            }
        } catch (Throwable e) {
            TaskEngineLog.LOCAL.debug("Retry condition fail. traceId:[{}] scene:[{}] executorClassName:[{}]", traceId,
                    retryable.scene(), executorClassName);
        }
        return false;
    }

    private static Object retryFailHandle(MethodInvocation invocation, Retryable retryable, String executorClassName, Throwable throwable, String traceId, RetryerResultContext retryerResultContext) {
        Method method = invocation.getMethod();

        // 如果存在用户自定义MethodResult，返回用户自定义值
        try {
            Class<? extends MethodResult> methodResultClass = retryable.methodResult();
            if (Objects.nonNull(methodResultClass) && !methodResultClass.isAssignableFrom(MethodResult.NoMethodResult.class)) {
                MethodResult methodResult = methodResultClass.getDeclaredConstructor().newInstance();
                Object resultObj = methodResult.result(retryable.scene(), executorClassName, invocation.getArguments(), throwable);
                Class<?> returnType = ClassUtils.primitiveToWrapper(method.getReturnType());
                if (Objects.nonNull(resultObj) && returnType.isAssignableFrom(resultObj.getClass())) {
                    return resultObj;
                }
            }
        } catch (Throwable e) {
            TaskEngineLog.LOCAL.debug("Get method result is error. traceId:[{}] scene:[{}] executorClassName:[{}]", traceId, retryable.scene(), executorClassName, throwable);
        }

        // 若返回值是NULL且是基本类型则返回默认值
        if (Objects.isNull(retryerResultContext.getResult()) && method.getReturnType().isPrimitive()) {
            return Defaults.defaultValue(method.getReturnType());
        }
        return retryerResultContext.getResult();
    }

    private RetryerResultContext doHandlerRetry(MethodInvocation invocation, String traceId, Retryable retryable,
                                                String executorClassName, String methodEntrance, Throwable throwable, Object result) {

        if (!RetrySiteSnapshot.isMethodEntrance(methodEntrance)
                || RetrySiteSnapshot.isRunning()
                || (Objects.isNull(throwable) && !retryIfResult(result, retryable, traceId, executorClassName))
                // 重试流量不开启重试
                || RetrySiteSnapshot.isRetryFlow()
                // 下游响应不重试码，不开启重试
                || RetrySiteSnapshot.isRetryForStatusCode()
                // 匹配异常信息
                || !retryIfException(throwable, RetryerInfoCache.get(retryable.scene(), executorClassName))
        ) {
            if (!RetrySiteSnapshot.isMethodEntrance(methodEntrance)) {
                TaskEngineLog.LOCAL.debug("Non-method entry does not enable local retries. traceId:[{}] [{}]", traceId,
                        RetrySiteSnapshot.getMethodEntrance());
            } else if (RetrySiteSnapshot.isRunning()) {
                TaskEngineLog.LOCAL.debug("Existing running retry tasks do not enable local retries. traceId:[{}] [{}]",
                        traceId, RetrySiteSnapshot.EnumStage.valueOfStage(RetrySiteSnapshot.getStage()));
            } else if (Objects.isNull(throwable)) {
                TaskEngineLog.LOCAL.debug("No exception, no local retries. traceId:[{}]", traceId);
            } else if (RetrySiteSnapshot.isRetryFlow()) {
                TaskEngineLog.LOCAL.debug("Retry traffic does not enable local retries. traceId:[{}] [{}]", traceId,
                        RetrySiteSnapshot.getRetryHeader());
            } else if (RetrySiteSnapshot.isRetryForStatusCode()) {
                TaskEngineLog.LOCAL.debug("Existing exception retry codes do not enable local retries. traceId:[{}]",
                        traceId);
            } else if (!retryIfException(throwable, RetryerInfoCache.get(retryable.scene(), executorClassName))) {
                TaskEngineLog.LOCAL.debug("Exception mismatch. traceId:[{}]", traceId);
            } else {
                TaskEngineLog.LOCAL.debug("Unknown situations do not enable local retry scenarios. traceId:[{}]",
                        traceId);
            }
            return null;
        }

        return openRetry(invocation, traceId, retryable, executorClassName, throwable);
    }

    private RetryerResultContext openRetry(MethodInvocation point, String traceId, Retryable retryable,
                                           String executorClassName, Throwable throwable) {

        try {

            // 标识重试流量
            initHeaders(retryable);

            RetryerResultContext context = retryStrategy.openRetry(retryable.scene(), executorClassName,
                    point.getArguments());
            if (RetryResultStatusEnum.SUCCESS.getStatus().equals(context.getRetryResultStatusEnum().getStatus())) {
                TaskEngineLog.LOCAL.debug("local retry successful. traceId:[{}] result:[{}]", traceId,
                        context.getResult());
            } else {
                TaskEngineLog.LOCAL.debug("local retry result. traceId:[{}] throwable:[{}]", traceId,
                        context.getThrowable());
            }

            return context;
        } catch (Exception e) {
            TaskEngineLog.LOCAL.error("retry component handling exception，traceId:[{}]", traceId, e);

            // 预警
            sendMessage(e);

        } finally {
            // 清除当前重试的信息
            RetrySiteSnapshot.removeAll();
            // 还原挂起的信息
            RetrySiteSnapshot.restore();

        }

        return null;
    }

    private void initHeaders(final Retryable retryable) {

        TaskHeaders taskHeaders = new TaskHeaders();
        taskHeaders.setRetry(Boolean.TRUE);
        taskHeaders.setRetryId(IdUtil.getSnowflakeNextIdStr());
        taskHeaders.setDdl(GroupVersionCache.getDdl(retryable.scene()));
        RetrySiteSnapshot.setRetryHeader(taskHeaders);
    }

    private void sendMessage(Exception e) {

        try {
            ConfigRequest.Notify notify = GroupVersionCache.getRetryNotifyAttribute(
                    RetryNotifySceneEnum.CLIENT_COMPONENT_ERROR.getNotifyScene());
            if (Objects.nonNull(notify)) {
                TaskProperties taskProperties = SnailSpringContext.getBean(TaskProperties.class);
                if (Objects.isNull(taskProperties)) {
                    return;
                }
                List<Recipient> recipients = Optional.ofNullable(notify.getRecipients()).orElse(Lists.newArrayList());
                for (final Recipient recipient : recipients) {
                    AlarmContext context = AlarmContext.build()
                            .text(retryErrorMoreThresholdTextMessageFormatter,
                                    EnvironmentUtils.getActiveProfile(),
                                    NetUtil.getLocalIpStr(),
                                    taskProperties.getNamespace(),
                                    taskProperties.getGroup(),
                                    LocalDateTime.now().format(formatter),
                                    e.getMessage())
                            .title("retry component handling exception:[{}]", taskProperties.getGroup())
                            .notifyAttribute(recipient.getNotifyAttribute());

                    Optional.ofNullable(TaskAlarmFactory.getAlarmType(recipient.getNotifyType()))
                            .ifPresent(alarm -> alarm.asyncSendMessage(context));
                }

            }
        } catch (Exception e1) {
            TaskEngineLog.LOCAL.error("Client failed to send component exception alert.", e1);
        }

    }

    public String getMethodEntrance(Retryable retryable, String executorClassName) {

        if (Objects.isNull(retryable)) {
            return StrUtil.EMPTY;
        }

        return retryable.scene().concat("_").concat(executorClassName);
    }

    private Retryable getAnnotationParameter(Method method) {

        Retryable retryable = null;
        if (method.isAnnotationPresent(Retryable.class)) {
            //获取当前类的方法上标注的注解对象
            retryable = method.getAnnotation(Retryable.class);
        }

        if (retryable == null) {
            // 返回当前类或父类或接口方法上标注的注解对象
            retryable = AnnotatedElementUtils.findMergedAnnotation(method, Retryable.class);
        }

        return retryable;
    }

    @Override
    public int getOrder() {
        return order;
    }


    private boolean retryIfException(Throwable throwable, RetryerInfo retryerInfo) {

        Set<Class<? extends Throwable>> exclude = retryerInfo.getExclude();
        Set<Class<? extends Throwable>> include = retryerInfo.getInclude();

        if (CollUtil.isEmpty(include) && CollUtil.isEmpty(exclude)) {
            return true;
        }

        for (Class<? extends Throwable> e : include) {
            if (e.isAssignableFrom(throwable.getClass())) {
                return true;
            }
        }

        if (CollUtil.isNotEmpty(exclude)) {
            for (Class<? extends Throwable> e : exclude) {
                if (e.isAssignableFrom(throwable.getClass())) {
                    return false;
                }
            }

            return true;
        }

        return false;
    }
}

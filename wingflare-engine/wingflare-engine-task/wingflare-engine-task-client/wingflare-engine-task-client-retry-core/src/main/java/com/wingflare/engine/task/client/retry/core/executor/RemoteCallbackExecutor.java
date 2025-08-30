package com.wingflare.engine.task.client.retry.core.executor;

import cn.hutool.core.lang.Assert;
import com.wingflare.engine.task.client.common.log.support.SnailJobLogManager;
import com.wingflare.engine.task.client.retry.core.callback.complete.RetryCompleteCallback;
import com.wingflare.engine.task.client.retry.core.context.CallbackContext;
import com.wingflare.engine.task.client.retry.core.exception.SnailRetryClientException;
import com.wingflare.engine.task.client.retry.core.log.RetryLogMeta;
import com.wingflare.engine.task.client.retry.core.retryer.RetryerInfo;
import com.wingflare.engine.task.common.core.context.SnailSpringContext;
import com.wingflare.engine.task.common.core.enums.RetryStatusEnum;
import com.wingflare.engine.task.common.log.SnailJobLog;
import com.wingflare.engine.task.common.log.enums.LogTypeEnum;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Method;
import java.util.Objects;

/**
 * <p>
 *
 * </p>
 *
 * @author opensnail
 * @date 2025-02-12
 */
@Component
public class RemoteCallbackExecutor {

    /**
     * 执行服务端回调任务
     *
     * @param context
     * @throws NoSuchMethodException
     * @throws InstantiationException
     * @throws IllegalAccessException
     */
    public void doRetryCallback(CallbackContext context) throws NoSuchMethodException, InstantiationException,
            IllegalAccessException {
        try {

            initLogContext(context);

            // 以Spring Bean模式回调
            doCallbackForSpringBean(context);

            // 上报执行成功
            SnailJobLog.REMOTE.info("Task executed successfully taskId:[{}]", context.getRetryTaskId());
        } catch (NoSuchBeanDefinitionException e) {
            // 若不是SpringBean 则直接反射以普通类调用
            doCallbackForOrdinaryClass(context);
        } finally {
            SnailJobLogManager.removeAll();
        }
    }

    private static void initLogContext(CallbackContext context) {
        // 初始化实时日志上下文
        RetryLogMeta retryLogMeta = new RetryLogMeta();
        retryLogMeta.setGroupName(context.getGroupName());
        retryLogMeta.setNamespaceId(context.getNamespaceId());
        retryLogMeta.setRetryTaskId(context.getRetryTaskId());
        retryLogMeta.setRetryId(context.getRetryId());
        SnailJobLogManager.initLogInfo(retryLogMeta, LogTypeEnum.RETRY);
    }

    /**
     * 以Spring Bean模式回调
     *
     * @return Result
     */
    private void doCallbackForSpringBean(CallbackContext context) {
        RetryerInfo retryerInfo = context.getRetryerInfo();
        Object[] deSerialize = context.getDeSerialize();
        Class<? extends RetryCompleteCallback> retryCompleteCallbackClazz = retryerInfo.getRetryCompleteCallback();

        RetryCompleteCallback retryCompleteCallback = SnailSpringContext.getBeanByType(retryCompleteCallbackClazz);
        switch (Objects.requireNonNull(RetryStatusEnum.getByStatus(context.getRetryStatus()))) {
            case FINISH:
                retryCompleteCallback.doSuccessCallback(retryerInfo.getScene(), retryerInfo.getExecutorClassName(),
                        deSerialize);
                break;
            case MAX_COUNT:
                retryCompleteCallback.doMaxRetryCallback(retryerInfo.getScene(), retryerInfo.getExecutorClassName(),
                        deSerialize);
                break;
            default:
                throw new SnailRetryClientException("Callback status exception");
        }

    }

    /**
     * 以普通类进行回调
     *
     * @return Result
     */
    private void doCallbackForOrdinaryClass(CallbackContext context) throws NoSuchMethodException, InstantiationException, IllegalAccessException {
        RetryerInfo retryerInfo = context.getRetryerInfo();
        Object[] deSerialize = context.getDeSerialize();
        Class<? extends RetryCompleteCallback> retryCompleteCallbackClazz = retryerInfo.getRetryCompleteCallback();

        RetryCompleteCallback retryCompleteCallback = retryCompleteCallbackClazz.newInstance();
        Method method;
        switch (Objects.requireNonNull(RetryStatusEnum.getByStatus(context.getRetryStatus()))) {
            case FINISH:
                method = retryCompleteCallbackClazz.getMethod("doSuccessCallback", String.class, String.class,
                        Object[].class);
                break;
            case MAX_COUNT:
                method = retryCompleteCallbackClazz.getMethod("doMaxRetryCallback", String.class, String.class,
                        Object[].class);
                break;
            default:
                throw new SnailRetryClientException("Callback status exception");
        }

        Assert.notNull(method, () -> new SnailRetryClientException("no such method"));
        ReflectionUtils.invokeMethod(method, retryCompleteCallback, retryerInfo.getScene(),
                retryerInfo.getExecutorClassName(), deSerialize);

        SnailJobLog.REMOTE.info("Task executed successfully taskId:[{}] [{}]", context.getRetryTaskId());

    }

}

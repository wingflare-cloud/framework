package com.wingflare.engine.task.client.common.window;

import cn.hutool.core.collection.CollUtil;
import com.wingflare.engine.task.common.core.util.JsonUtil;
import com.wingflare.engine.task.common.core.window.LeapArray;
import com.wingflare.engine.task.common.core.window.Listener;
import com.wingflare.engine.task.common.core.window.WindowWrap;
import com.wingflare.engine.task.common.model.request.RetryTaskRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * 通过滑动窗口上报异常数据
 *
 * @author: opensnail
 * @date : 2022-05-28 15:07
 */
public class RetryLeapArray extends LeapArray<ConcurrentLinkedQueue<RetryTaskRequest>> {

    private final static Logger log = LoggerFactory.getLogger(RetryLeapArray.class);

    protected List<Listener<RetryTaskRequest>> listenerList;

    public RetryLeapArray(int sampleCount, int intervalInMs) {
        super(sampleCount, intervalInMs);
    }

    public RetryLeapArray(int sampleCount, int intervalInMs, List<Listener<RetryTaskRequest>> listenerList) {
        super(sampleCount, intervalInMs);
        this.listenerList = listenerList;
    }

    public RetryLeapArray(int sampleCount, int intervalInMs, Listener<RetryTaskRequest> listener) {
        this(sampleCount, intervalInMs, Collections.singletonList(listener));
    }

    @Override
    public ConcurrentLinkedQueue<RetryTaskRequest> newEmptyBucket(long timeMillis) {
        return new ConcurrentLinkedQueue<>();
    }

    @Override
    protected WindowWrap<ConcurrentLinkedQueue<RetryTaskRequest>> resetWindowTo(WindowWrap<ConcurrentLinkedQueue<RetryTaskRequest>> windowWrap, long startTime) {

        ConcurrentLinkedQueue<RetryTaskRequest> deepCopy = new ConcurrentLinkedQueue<>(windowWrap.value());
        try {

            if (CollUtil.isNotEmpty(deepCopy)) {
                for (Listener<RetryTaskRequest> listener : listenerList) {
                    listener.handler(new ArrayList<>(deepCopy));
                }
            }
        } catch (Exception e) {
            log.error("Sliding window listener processing failed data:[{}]", JsonUtil.toJsonString(windowWrap.value()), e);
        }

        windowWrap.value().removeAll(deepCopy);
        windowWrap.resetTo(startTime);

        return windowWrap;
    }
}

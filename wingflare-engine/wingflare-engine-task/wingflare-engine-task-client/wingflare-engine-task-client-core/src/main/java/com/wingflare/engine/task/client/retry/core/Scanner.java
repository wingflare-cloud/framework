package com.wingflare.engine.task.client.retry.core;

import com.wingflare.engine.task.client.retry.core.retryer.RetryerInfo;

import java.util.List;

/**
 * @author: opensnail
 * @date : 2022-02-10 09:13
 */
public interface Scanner {

    List<RetryerInfo> doScan();
}

package com.wingflare.engine.task.client.retry;


import com.wingflare.engine.task.client.retry.retryer.RetryerInfo;

import java.util.List;

/**
 * @author: opensnail
 * @date : 2022-02-10 09:13
 */
public interface Scanner {

    List<RetryerInfo> doScan();
}

package com.wingflare.engine.task.client.core;


import com.wingflare.engine.task.client.core.dto.JobExecutorInfo;

import java.util.List;

/**
 * @author: opensnail
 * @date : 2022-02-10 09:13
 */
public interface Scanner {

    List<JobExecutorInfo> doScan();
}

package com.wingflare.engine.task.server.web.service;

import com.wingflare.engine.task.server.common.vo.JobLogQueryVO;

/**
 * @author: opensnail
 * @date : 2023-10-12 09:54
 * @since ：2.4.0
 */
public interface JobLogService {

    void getJobLogPage(JobLogQueryVO jobQueryVO);

}

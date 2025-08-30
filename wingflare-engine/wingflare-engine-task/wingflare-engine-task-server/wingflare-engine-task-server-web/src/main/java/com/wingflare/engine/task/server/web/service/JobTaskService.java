package com.wingflare.engine.task.server.web.service;

import com.wingflare.engine.task.server.web.model.base.PageResult;
import com.wingflare.engine.task.server.web.model.request.JobTaskQueryVO;
import com.wingflare.engine.task.server.web.model.response.JobTaskResponseVO;

import java.util.List;

/**
 * @author: opensnail
 * @date : 2023-10-12 09:54
 * @since ：2.4.0
 */
public interface JobTaskService {

    PageResult<List<JobTaskResponseVO>> getJobTaskPage(JobTaskQueryVO jobTaskQueryVO);

    List<JobTaskResponseVO> getTreeJobTask(JobTaskQueryVO jobTaskQueryVO);

}

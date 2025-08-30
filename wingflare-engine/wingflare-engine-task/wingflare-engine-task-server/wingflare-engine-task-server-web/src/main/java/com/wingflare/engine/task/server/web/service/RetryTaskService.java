package com.wingflare.engine.task.server.web.service;

import com.wingflare.engine.task.server.web.model.base.PageResult;
import com.wingflare.engine.task.server.web.model.request.RetryTaskLogMessageQueryVO;
import com.wingflare.engine.task.server.web.model.request.RetryTaskQueryVO;
import com.wingflare.engine.task.server.web.model.response.RetryTaskResponseVO;

import java.util.List;
import java.util.Set;

/**
 * @author opensnail
 * @date 2022-02-27
 * @since 2.0
 */
public interface RetryTaskService {

    PageResult<List<RetryTaskResponseVO>> getRetryTaskLogPage(RetryTaskQueryVO queryVO);

    void getRetryTaskLogMessagePage(RetryTaskLogMessageQueryVO queryVO);

    RetryTaskResponseVO getRetryTaskById(Long id);

    boolean deleteById(Long id);

    boolean batchDelete(Set<Long> ids);

    Boolean stopById(Long id);
}

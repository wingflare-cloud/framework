package com.wingflare.engine.task.server.web.service;

import com.wingflare.engine.task.server.common.vo.WorkflowBatchResponseVO;
import com.wingflare.engine.task.server.web.model.base.PageResult;
import com.wingflare.engine.task.server.web.model.request.WorkflowBatchQueryVO;

import java.util.List;
import java.util.Set;

/**
 * @author xiaowoniu
 * @date 2023-12-23 17:48:23
 * @since 2.6.0
 */
public interface WorkflowWebBatchService {

    PageResult<List<WorkflowBatchResponseVO>> listPage(WorkflowBatchQueryVO queryVO);

    Boolean stop(Long id);

    Boolean deleteByIds(Set<Long> ids);
}

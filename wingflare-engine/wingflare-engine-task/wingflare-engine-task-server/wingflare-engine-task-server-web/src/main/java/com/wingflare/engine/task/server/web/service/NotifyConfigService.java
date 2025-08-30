package com.wingflare.engine.task.server.web.service;

import com.wingflare.engine.task.server.web.model.base.PageResult;
import com.wingflare.engine.task.server.web.model.request.NotifyConfigQueryVO;
import com.wingflare.engine.task.server.web.model.request.NotifyConfigRequestVO;
import com.wingflare.engine.task.server.web.model.response.NotifyConfigResponseVO;
import com.wingflare.task.datasource.template.persistence.po.NotifyConfig;

import java.util.List;
import java.util.Set;

/**
 * @author: opensnail
 * @date : 2022-03-03 11:17
 */
public interface NotifyConfigService {

    PageResult<List<NotifyConfigResponseVO>> getNotifyConfigList(NotifyConfigQueryVO queryVO);

    List<NotifyConfig> getNotifyConfigBySystemTaskTypeList(Integer systemTaskType);

    Boolean saveNotify(NotifyConfigRequestVO requestVO);

    Boolean updateNotify(NotifyConfigRequestVO requestVO);

    NotifyConfigResponseVO getNotifyConfigDetail(Long id);

    Boolean updateStatus(Long id, Integer status);

    Boolean batchDeleteNotify(Set<Long> ids);
}

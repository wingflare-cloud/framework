package com.wingflare.engine.task.server.web.service;

import com.wingflare.engine.task.server.web.model.base.PageResult;
import com.wingflare.engine.task.server.web.model.request.ExportSceneVO;
import com.wingflare.engine.task.server.web.model.request.SceneConfigQueryVO;
import com.wingflare.engine.task.server.web.model.request.SceneConfigRequestVO;
import com.wingflare.engine.task.server.web.model.response.SceneConfigResponseVO;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;

import java.util.List;
import java.util.Set;

/**
 * @author: opensnail
 * @date : 2022-03-03 10:54
 */
public interface SceneConfigService {

    PageResult<List<SceneConfigResponseVO>> getSceneConfigPageList(SceneConfigQueryVO groupName);

    List<SceneConfigResponseVO> getSceneConfigList(String groupName);

    Boolean saveSceneConfig(SceneConfigRequestVO requestVO);

    Boolean updateSceneConfig(SceneConfigRequestVO requestVO);

    SceneConfigResponseVO getSceneConfigDetail(Long id);

    boolean updateStatus(Long id, final Integer status);

    void importSceneConfig(@Valid @NotEmpty(message = "Import data cannot be empty") List<SceneConfigRequestVO> requests);

    String exportSceneConfig(ExportSceneVO exportSceneVO);

    boolean deleteByIds(Set<Long> ids);
}

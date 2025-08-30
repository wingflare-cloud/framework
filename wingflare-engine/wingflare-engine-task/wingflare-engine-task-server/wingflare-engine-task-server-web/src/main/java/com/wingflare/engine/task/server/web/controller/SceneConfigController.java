package com.wingflare.engine.task.server.web.controller;

import com.wingflare.engine.task.common.core.annotation.OriginalControllerReturnValue;
import com.wingflare.engine.task.server.web.annotation.LoginRequired;
import com.wingflare.engine.task.server.web.model.base.PageResult;
import com.wingflare.engine.task.server.web.model.request.ExportSceneVO;
import com.wingflare.engine.task.server.web.model.request.SceneConfigQueryVO;
import com.wingflare.engine.task.server.web.model.request.SceneConfigRequestVO;
import com.wingflare.engine.task.server.web.model.response.SceneConfigResponseVO;
import com.wingflare.engine.task.server.web.service.SceneConfigService;
import com.wingflare.engine.task.server.web.util.ExportUtils;
import com.wingflare.engine.task.server.web.util.ImportUtils;
import jakarta.validation.constraints.NotEmpty;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Set;

/**
 * 重试场景接口
 *
 * @author: opensnail
 * @date : 2022-03-03 11:27
 */
@RestController
@RequestMapping("/scene-config")
public class SceneConfigController {
    private final SceneConfigService sceneConfigService;

    public SceneConfigController(SceneConfigService sceneConfigService) {
        this.sceneConfigService = sceneConfigService;
    }

    @LoginRequired
    @GetMapping("page/list")
    public PageResult<List<SceneConfigResponseVO>> getSceneConfigPageList(SceneConfigQueryVO queryVO) {
        return sceneConfigService.getSceneConfigPageList(queryVO);
    }

    @LoginRequired
    @GetMapping("list")
    public List<SceneConfigResponseVO> getSceneConfigList(@RequestParam("groupName") String groupName) {
        return sceneConfigService.getSceneConfigList(groupName);
    }

    @LoginRequired
    @GetMapping("{id}")
    public SceneConfigResponseVO getSceneConfigDetail(@PathVariable("id") Long id) {
        return sceneConfigService.getSceneConfigDetail(id);
    }

    @LoginRequired
    @PutMapping("/{id}/status/{status}")
    public Boolean updateStatus(@PathVariable("id") Long id, @PathVariable("status") Integer status) {
        return sceneConfigService.updateStatus(id, status);
    }

    @LoginRequired
    @PostMapping
    public Boolean saveSceneConfig(@RequestBody @Validated SceneConfigRequestVO requestVO) {
        return sceneConfigService.saveSceneConfig(requestVO);
    }

    @LoginRequired
    @PutMapping
    public Boolean updateSceneConfig(@RequestBody @Validated SceneConfigRequestVO requestVO) {
        return sceneConfigService.updateSceneConfig(requestVO);
    }

    @LoginRequired
    @PostMapping(value = "/import", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public void importScene(@RequestPart("file") MultipartFile file) throws IOException {
        // 写入数据
        sceneConfigService.importSceneConfig(ImportUtils.parseList(file, SceneConfigRequestVO.class));
    }

    @LoginRequired
    @PostMapping("/export")
    @OriginalControllerReturnValue
    public ResponseEntity<String> export(@RequestBody ExportSceneVO exportSceneVO) {
        return ExportUtils.doExport(sceneConfigService.exportSceneConfig(exportSceneVO));
    }

    @LoginRequired
    @DeleteMapping("/ids")
    public boolean deleteByIds(@RequestBody @NotEmpty(message = "ids cannot be null") Set<Long> ids) {
        return sceneConfigService.deleteByIds(ids);
    }
}

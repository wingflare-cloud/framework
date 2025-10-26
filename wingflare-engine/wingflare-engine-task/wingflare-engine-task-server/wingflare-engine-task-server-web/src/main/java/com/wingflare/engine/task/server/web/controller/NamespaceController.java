package com.wingflare.engine.task.server.web.controller;


import com.wingflare.api.security.annotation.RequiresLogin;
import com.wingflare.api.security.annotation.RequiresPermissions;
import com.wingflare.engine.task.server.web.model.base.PageResult;
import com.wingflare.engine.task.server.web.model.request.NamespaceQueryVO;
import com.wingflare.engine.task.server.web.model.request.NamespaceRequestVO;
import com.wingflare.engine.task.server.web.model.response.NamespaceResponseVO;
import com.wingflare.engine.task.server.web.service.NamespaceService;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


/**
 * @author: xiaowoniu
 * @date : 2023-11-21 15:02
 * @since : 2.5.0
 */
@RestController
@RequestMapping("/namespace")
public class NamespaceController {

    @Resource
    private NamespaceService namespaceService;

    @RequiresLogin
    @RequiresPermissions("task.namespace.save")
    @PostMapping
    public Boolean saveNamespace(@RequestBody @Validated NamespaceRequestVO namespaceRequestVO) {
        return namespaceService.saveNamespace(namespaceRequestVO);
    }

    @RequiresLogin
    @RequiresPermissions("task.namespace.update")
    @PutMapping
    public Boolean updateNamespace(@RequestBody @Validated NamespaceRequestVO namespaceRequestVO) {
        return namespaceService.updateNamespace(namespaceRequestVO);
    }

    @RequiresLogin
    @RequiresPermissions("task.namespace.list")
    @GetMapping("list")
    public PageResult<List<NamespaceResponseVO>> getNamespacePage(NamespaceQueryVO queryVO) {
        return namespaceService.getNamespacePage(queryVO);
    }

    @RequiresLogin
    @RequiresPermissions("task.namespace.delete")
    @DeleteMapping("{uniqueId}")
    public Boolean deleteByUniqueId(@PathVariable("uniqueId") String uniqueId) {
        return namespaceService.deleteByUniqueId(uniqueId);
    }

    @RequiresLogin
    @RequiresPermissions("task.namespace.list")
    @GetMapping("/all")
    public List<NamespaceResponseVO> getAllNamespace() {
        return namespaceService.getAllNamespace();
    }
}

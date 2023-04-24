package com.wingflare.tool.generator.controller;

import com.wingflare.tool.generator.ProjectPathResolver;
import com.wingflare.tool.generator.common.Result;
import com.wingflare.tool.generator.common.ResultGenerator;
import com.wingflare.tool.generator.configurer.GeneratorConfig;
import com.wingflare.tool.generator.dto.OutputFileInfo;
import com.wingflare.tool.generator.service.OutputFileInfoService;
import com.wingflare.tool.generator.service.UserConfigStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/api/output-file-info")
public class OutputFileInfoController {

    @Autowired
    private OutputFileInfoService outputFileInfoService;

    @Autowired
    private UserConfigStore userConfigStore;

    @Autowired
    private ProjectPathResolver projectPathResolver;

    @Autowired
    private GeneratorConfig generatorConfig;

    @GetMapping("/user-config")
    public Result getUserConfig() {
        return ResultGenerator.genSuccessResult(userConfigStore.getDefaultUserConfig());
    }

    @GetMapping("/project-root-path")
    public Result getRootPath() {
        return ResultGenerator.genSuccessResult(projectPathResolver.getBaseProjectPath());
    }

    @GetMapping("/project-base-pkg")
    public Result getBasePkg() {
        return ResultGenerator.genSuccessResult(generatorConfig.getBasePackage());
    }

    @PostMapping("/delete")
    public Result deleteOutputInfos(@RequestBody OutputFileInfo outputFileInfo) throws IOException {
        outputFileInfoService.deleteOutputFileInfo(outputFileInfo);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/save")
    public Result saveOutputInfos(@RequestBody OutputFileInfo outputFileInfo) throws IOException {
        outputFileInfoService.saveOutputFileInfo(outputFileInfo);
        return ResultGenerator.genSuccessResult();
    }

    /**
     * 查看当前项目是否存在配置文件
     *
     * @return
     */
    @GetMapping("/check-if-new-project")
    public Result checkIfNewProject() {
        return ResultGenerator.genSuccessResult(!userConfigStore.checkUserConfigExisted());
    }

    /**
     * 获取本机所有已保存配置的项目列表
     *
     * @return
     */
    @GetMapping("/all-saved-project")
    public Result getAllSavedProject() {
        return ResultGenerator.genSuccessResult(userConfigStore.getAllSavedProject());
    }

    /**
     * 为当前项目导入其它项目的配置文件
     *
     * @return
     */
    @PostMapping("/import-project-config/{sourceProjectPkg}")
    public Result importProjectConfig(@PathVariable("sourceProjectPkg") String sourceProjectPkg) throws IOException {
        userConfigStore.importProjectConfig(sourceProjectPkg);
        return ResultGenerator.genSuccessResult();
    }


}

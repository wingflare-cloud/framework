package com.wingflare.tool.generator.service;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.FileUtil;
import com.wingflare.tool.generator.ProjectPathResolver;
import com.wingflare.tool.generator.common.ServiceException;
import com.wingflare.tool.generator.configurer.GeneratorConfig;
import com.wingflare.tool.generator.configurer.GeneratorDefaultConfig;
import com.wingflare.tool.generator.dto.Constant;
import com.wingflare.tool.generator.dto.OutputFileInfo;
import com.wingflare.tool.generator.dto.UserConfig;
import com.wingflare.tool.generator.util.JsonUtil;
import com.wingflare.tool.generator.util.PathUtil;
import com.wingflare.tool.generator.util.TemplateUtil;
import com.google.common.collect.Lists;
import com.google.common.io.Files;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@Component
@Slf4j
public class UserConfigStore {

    private String storeDir;

    private String userConfigPath;

    @Resource
    private GeneratorDefaultConfig defaultConfig;

    @Autowired
    private GeneratorConfig generatorConfig;

    @PostConstruct
    public void init() {
        this.storeDir = PathUtil.joinPath(System.getProperty("user.home"), Constant.CONFIG_HOME, generatorConfig.getBasePackage());
        this.userConfigPath = this.storeDir + File.separator + "user-config.json";
    }

    public String getTemplateStoreDir() {
        return PathUtil.joinPath(this.storeDir, Constant.TEMPLATE_STORE_DIR);
    }

    public UserConfig getDefaultUserConfig() {
        UserConfig userConfig = getUserConfigFromFile();
        if (userConfig == null) {
            userConfig = new UserConfig();
            userConfig.setOutputFiles(defaultConfig.getOutputFileInfos());
            BeanUtil.copyProperties(defaultConfig, userConfig);
        }
        return userConfig;
    }

    public UserConfig getUserConfigFromFile() {
        if (!FileUtil.exist(this.userConfigPath)) {
            return null;
        }
        String userConfigStr = FileUtil.readString(userConfigPath, Charset.forName("utf-8"));
        try {
            return JsonUtil.json2obj(userConfigStr, UserConfig.class);
        } catch (Exception e) {
            log.error("读取用户配置文件发生错误：", e);
            return null;
        }
    }

    public void saveUserConfig(UserConfig userConfig) throws IOException {
        if (userConfig == null) {
            throw new ServiceException("不能写入空的用户配置");
        }
        String configStr = JsonUtil.obj2json(userConfig);
        File userConfigFile = new File(this.userConfigPath);
        if (userConfigFile.exists()) {
            userConfigFile.delete();
        }
        Files.createParentDirs(userConfigFile);
        userConfigFile.createNewFile();
        FileUtil.writeFromStream(new ByteArrayInputStream(configStr.getBytes(Charset.forName("utf-8"))), userConfigFile);
    }

    public String uploadTemplate(MultipartFile file) {
        String fileName = file.getOriginalFilename();
        assert fileName != null;
        String fileSuffix = fileName.substring(file.getOriginalFilename().lastIndexOf(".") + 1);
        String saveFileName = fileName.substring(0, fileName.lastIndexOf(fileSuffix)) + DateUtil.format(new Date(), "yyyyMMddHHmmss");
        String savePath = PathUtil.joinPath(getTemplateStoreDir(), saveFileName);
        log.info("模板上传路径为：{}", savePath);
        File saveFile = new File(savePath);
        try {
            FileUtil.writeFromStream(file.getInputStream(), saveFile);
        } catch (IOException e) {
            throw new ServiceException("上传模板文件失败", e);
        }
        return Constant.RESOURCE_PREFIX_FILE + savePath;
    }

    public boolean checkUserConfigExisted() {
        if (!FileUtil.exist(this.storeDir)) {
            return false;
        }
        return true;
    }

    public void importProjectConfig(String sourcePkg) throws IOException {
        String configHomePath = PathUtil.joinPath(System.getProperty("user.home"), Constant.CONFIG_HOME);
        if (!FileUtil.exist(configHomePath)) {
            throw new ServiceException("配置主目录不存在：" + configHomePath);
        }
        File[] files = FileUtil.ls(configHomePath);
        boolean flag = false;
        for (File file : files) {
            if (file.isDirectory() && file.getName().equals(sourcePkg)) {
                File projectConfigDir = new File(this.storeDir);
                FileUtil.copyContent(file, projectConfigDir, true);
                flag = true;
                break;
            }
        }
        if (!flag) {
            throw new ServiceException("未找到待导入的源项目配置");
        }
        String sourceProjectConfigPath = PathUtil.joinPath(System.getProperty("user.home"), Constant.CONFIG_HOME, sourcePkg);
        String targetProjectConfigPath = this.storeDir;
        UserConfig currentUserConfig = new UserConfig();
        currentUserConfig.setOutputFiles(defaultConfig.getOutputFileInfos());
        currentUserConfig.merge(this.getUserConfigFromFile(), sourceProjectConfigPath, targetProjectConfigPath);
        this.saveUserConfig(currentUserConfig);
    }

    public List<String> getAllSavedProject() {
        String configHomePath = PathUtil.joinPath(System.getProperty("user.home"), Constant.CONFIG_HOME);
        if (!FileUtil.exist(configHomePath)) {
            return Collections.emptyList();
        }
        List<String> projects = Lists.newArrayList();
        File[] files = FileUtil.ls(configHomePath);
        for (File file : files) {
            if (file.isDirectory()) {
                projects.add(file.getName());
            }
        }
        return projects;
    }

}

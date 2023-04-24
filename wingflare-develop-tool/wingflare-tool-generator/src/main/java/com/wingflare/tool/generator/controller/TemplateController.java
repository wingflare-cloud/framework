package com.wingflare.tool.generator.controller;

import com.wingflare.tool.generator.common.Result;
import com.wingflare.tool.generator.common.ResultGenerator;
import com.wingflare.tool.generator.common.ServiceException;
import com.wingflare.tool.generator.dto.OutputFileInfo;
import com.wingflare.tool.generator.dto.UserConfig;
import com.wingflare.tool.generator.service.OutputFileInfoService;
import com.wingflare.tool.generator.service.UserConfigStore;
import com.wingflare.tool.generator.util.TemplateUtil;
import com.google.common.base.Strings;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/template")
@Slf4j
public class TemplateController {

    @Autowired
    private UserConfigStore userConfigStore;


    @Autowired
    private OutputFileInfoService outputFileInfoService;

    @GetMapping("/download")
    public void download(HttpServletResponse res, @RequestParam String fileType) throws IOException {
        if (Strings.isNullOrEmpty(fileType)) {
            log.error("fileType不能为空");
            return;
        }
        UserConfig userConfig = userConfigStore.getUserConfigFromFile();
        if (userConfig == null) {
            InputStream tplIn = TemplateUtil.getBuiltInTemplate(fileType);
            download(res, tplIn);
            return;
        }
        List<OutputFileInfo> fileInfos = userConfig.getOutputFiles();
        for (OutputFileInfo fileInfo : fileInfos) {
            if (fileType.equals(fileInfo.getFileType())) {
                if (fileInfo.isBuiltIn()
                        && Strings.isNullOrEmpty(fileInfo.getTemplatePath())) {
                    InputStream tplIn = TemplateUtil.getBuiltInTemplate(fileType);
                    download(res, tplIn);
                } else {
                    String tplPath = fileInfo.getTemplatePath();
                    if (tplPath.startsWith("file:")) {
                        tplPath = tplPath.replaceFirst("file:", "");
                    }
                    File tplFile = new File(tplPath);
                    if (tplFile.exists()) {
                        download(res, Files.newInputStream(tplFile.toPath()));
                    } else {
                        throw new ServiceException("未找到模板文件：" + fileInfo.getTemplatePath());
                    }
                }
                break;
            }
        }
    }

    @PostMapping("/upload")
    public Result upload(@RequestParam("file") MultipartFile file, @RequestParam("fileType") String fileType) {
        Map<String, Object> params = Maps.newHashMap();
        String storePath = userConfigStore.uploadTemplate(file);
        params.put("templatePath", storePath);
        params.put("templateName", file.getOriginalFilename());
        return ResultGenerator.genSuccessResult(params);
    }

    private void download(HttpServletResponse res, InputStream tplIn) throws UnsupportedEncodingException {
        if (tplIn != null) {
            res.setCharacterEncoding("utf-8");
            res.setContentType("multipart/form-data;charset=UTF-8");
            try {
                OutputStream os = res.getOutputStream();
                byte[] b = new byte[2048];
                int length;
                while ((length = tplIn.read(b)) > 0) {
                    os.write(b, 0, length);
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    tplIn.close();
                } catch (IOException ignored) {
                }
            }
        }
    }


}

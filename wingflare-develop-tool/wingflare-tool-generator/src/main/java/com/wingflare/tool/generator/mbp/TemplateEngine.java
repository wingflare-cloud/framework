package com.wingflare.tool.generator.mbp;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.generator.config.ConstVal;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.config.builder.ConfigBuilder;
import com.baomidou.mybatisplus.generator.config.builder.CustomFile;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.engine.AbstractTemplateEngine;
import com.wingflare.lib.core.utils.StringUtil;
import com.wingflare.tool.generator.dto.Constant;
import lombok.extern.slf4j.Slf4j;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;
import org.apache.velocity.runtime.resource.loader.FileResourceLoader;

import javax.annotation.Nonnull;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.StringWriter;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * 对原模板引擎进行改造，使其支持file和classpath两类加载模式
 */
@Slf4j
public class TemplateEngine extends AbstractTemplateEngine {

    private VelocityEngine velocityEngine;

    private final String templateStoreDir;

    private final NameConverter nameConverter;

    public TemplateEngine(NameConverter nameConverter, String templateStoreDir) {
        this.templateStoreDir = templateStoreDir;
        this.nameConverter = nameConverter;
        Properties p = new Properties();

        try {
            // 加载classpath目录下的vm文件
            p.setProperty("class.resource.loader.class", ClasspathResourceLoader.class.getName());
            p.setProperty("file.resource.loader.class", FileResourceLoader.class.getName());
            p.setProperty(Velocity.FILE_RESOURCE_LOADER_PATH, templateStoreDir);
            p.setProperty(Velocity.RESOURCE_LOADER, "class, file");
            // 定义字符集
            p.setProperty(Velocity.INPUT_ENCODING, Velocity.ENCODING_DEFAULT);
            // 初始化Velocity引擎，指定配置Properties
            this.velocityEngine = new VelocityEngine(p);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void writer(@Nonnull Map<String, Object> objectMap, String templatePath, @Nonnull File outputFile) throws Exception {
        if (templatePath.startsWith(Constant.RESOURCE_PREFIX_FILE)) {
            templatePath = templatePath.replace(templateStoreDir, "");
        }

        log.info("templatePath: {}",  templatePath);
        Template template = velocityEngine.getTemplate(templatePath, ConstVal.UTF8);
        FileOutputStream fos = new FileOutputStream(outputFile);
        Throwable var6 = null;
        VelocityContext velocityContext = new VelocityContext(objectMap);
        velocityContext.put("StringUtil", StringUtil.class);

        try {
            OutputStreamWriter ow = new OutputStreamWriter(fos, ConstVal.UTF8);
            Throwable var8 = null;

            try {
                BufferedWriter writer = new BufferedWriter(ow);
                Throwable var10 = null;

                try {
                    template.merge(velocityContext, writer);
                } catch (Throwable var54) {
                    var10 = var54;
                    throw var54;
                } finally {
                    if (writer != null) {
                        if (var10 != null) {
                            try {
                                writer.close();
                            } catch (Throwable var53) {
                                var10.addSuppressed(var53);
                            }
                        } else {
                            writer.close();
                        }
                    }

                }
            } catch (Throwable var56) {
                var8 = var56;
                throw var56;
            } finally {
                if (ow != null) {
                    if (var8 != null) {
                        try {
                            ow.close();
                        } catch (Throwable var52) {
                            var8.addSuppressed(var52);
                        }
                    } else {
                        ow.close();
                    }
                }

            }
        } catch (Throwable var58) {
            var6 = var58;
            throw var58;
        } finally {
            if (fos != null) {
                if (var6 != null) {
                    try {
                        fos.close();
                    } catch (Throwable var51) {
                        var6.addSuppressed(var51);
                    }
                } else {
                    fos.close();
                }
            }

        }
        log.info("已生成文件: {}", outputFile.getPath());
    }

    @Override
    @Nonnull
    public AbstractTemplateEngine init(@Nonnull ConfigBuilder configBuilder) {
        return this;
    }


    @Override
    @Nonnull
    public String templateFilePath(@Nonnull String filePath) {
        return filePath;
    }


    public String write2String(Map<String, Object> objectMap, String templatePath) {
        if (templatePath.startsWith(Constant.RESOURCE_PREFIX_FILE)) {
            templatePath = templatePath.replace(templateStoreDir, "");
        }

        VelocityContext velocityContext = new VelocityContext(objectMap);
        velocityContext.put("StringUtil", StringUtil.class);
        Template template = velocityEngine.getTemplate(templatePath, ConstVal.UTF8);
        StringWriter sw = new StringWriter();
        template.merge(velocityContext, sw);
        return sw.toString();
    }

    @Override
    protected void outputCustomFile(List<CustomFile> customFiles, TableInfo tableInfo, @Nonnull Map<String, Object> objectMap) {
        String parentPath = getPathInfo(OutputFile.parent);
        for (CustomFile file : customFiles) {
            String filePath = StrUtil.isNotBlank(file.getFilePath()) ? file.getFilePath() : parentPath;
            if (StrUtil.isNotBlank(file.getPackageName())) {
                filePath = filePath + File.separator + file.getPackageName();
                filePath = filePath.replaceAll("\\.", "\\" + File.separator);
            }

            String[] arr = file.getTemplatePath().split("\\.");
            String extension = null;
            String name;

            if (arr.length > 2) {
                extension = arr[arr.length -2];
            }

            if (objectMap.containsKey("hidePrefix")
                    && objectMap.get("hidePrefix") instanceof List
                    && ((List<?>) objectMap.get("hidePrefix"))
                    .contains(String.format("%s_%s", file.getTemplatePath(), file.getFileName()))) {
                name = file.getFileName();
            } else {
                name = nameConverter.tableNameConvert(tableInfo.getName()) + file.getFileName();
            }

            objectMap.put(file.getFileName(), name);
            String fileName;

            if (StrUtil.isNotEmpty(extension)) {
                fileName = filePath + File.separator + name + "." + extension;
            } else {
                fileName = filePath + File.separator + name;
            }

            outputFile(new File(fileName), objectMap, file.getTemplatePath(), file.isFileOverride());
        }
    }

}

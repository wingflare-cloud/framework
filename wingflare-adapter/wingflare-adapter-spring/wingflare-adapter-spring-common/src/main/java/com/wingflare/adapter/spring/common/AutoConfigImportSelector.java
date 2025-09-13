package com.wingflare.adapter.spring.common;


import org.springframework.context.annotation.DeferredImportSelector;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * 自定义导入文件路径
 */
public class AutoConfigImportSelector implements DeferredImportSelector {

    // 自定义文件路径（可根据需要修改）
    private static final String CUSTOM_AUTO_CONFIG_LOCATION = "META-INF/autoconfig.imports";

    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        List<String> configClasses = new ArrayList<>();
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();

        try {
            // 扫描所有jar包及当前项目中的自定义文件
            Resource[] resources = resolver.getResources("classpath*:" + CUSTOM_AUTO_CONFIG_LOCATION);

            for (Resource resource : resources) {
                // 读取文件内容并解析配置类
                try (Scanner scanner = new Scanner(resource.getInputStream(), StandardCharsets.UTF_8)) {
                    while (scanner.hasNextLine()) {
                        String line = scanner.nextLine().trim();
                        if (!line.isEmpty() && !line.startsWith("#")) { // 跳过空行和注释
                            configClasses.add(line);
                        }
                    }
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Failed to load custom auto-configuration classes", e);
        }

        return configClasses.toArray(new String[0]);
    }
}
